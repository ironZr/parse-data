package com.zr.parsedata.core.parse.json;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import com.zr.parsedata.dto.ParseConfigDTO;
import com.zr.parsedata.core.parse.Parse;
import com.zr.parsedata.core.parse.constant.ExpressionConstant;
import com.zr.parsedata.core.parse.constant.HtmlConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 基础json解析
 */
@Slf4j
public class CommonJsonParse implements Parse {

    private static final String VAL = "val";
    private static final String OBJ = "obj";
    private static final String ARR = "arr";
    private static final String ARR_ALL = "arr-all";
    private static final String ARR_NUM = "arr-num";

    private static final String EMPTY_STR = "";

    /**
     * 处理
     */
    private static final Map<String, Function<Dto, Object>> map = Maps.newHashMapWithExpectedSize(8);

    static {
        map.put(VAL, tar -> Optional.ofNullable(tar.getJson()).map(t -> ((JSONObject) t).getStr(tar.getKey())).orElse(EMPTY_STR));
        map.put(OBJ, tar -> Optional.ofNullable(tar.getJson()).map(t -> ((JSONObject) t).getJSONObject(tar.getKey())).orElse(null));
        map.put(ARR, tar -> Optional.ofNullable(tar.getJson()).map(t -> ((JSONObject) t).getJSONArray(tar.getKey())).orElse(null));
        map.put(ARR_ALL, tar -> Optional.ofNullable(tar.getJson()).map(t ->
                IntStream.range(0, ((JSONArray) t).size()).mapToObj(((JSONArray) t)::getStr)
                        .collect(Collectors.joining(Optional.ofNullable(tar.getSplicer()).orElse(EMPTY_STR))))
                .orElse(EMPTY_STR));
        map.put(ARR_NUM, tar -> Optional.ofNullable(tar.getJson()).map(t -> {
            try {
                //可能会出现索引没有的情况 设置为""
                String[] keys = StringUtils.split(tar.getKey(), ExpressionConstant.COMMA);
                return ObjectUtil.isNotEmpty(keys) ? Arrays.stream(keys)
                        .map(i -> Optional.ofNullable(((JSONArray) t).getStr(Integer.valueOf(i))).orElse(EMPTY_STR))
                        .collect(Collectors.joining(Optional.ofNullable(tar.getSplicer()).orElse(EMPTY_STR)))
                        :
                        EMPTY_STR;
            } catch (Exception e) {
                return EMPTY_STR;
            }
        }).orElse(EMPTY_STR));
    }

    @Override
    public String parseToHtml(String json, List<ParseConfigDTO> parseConfigs) {
        //不是json则返回空
        if (Boolean.FALSE.equals(JSONUtil.isJson(json))) return EMPTY_STR;
        JSONObject jsonObject = JSONUtil.parseObj(json);

        //按照标题组id分组
        Map<Integer, List<ParseConfigDTO>> groupMap = parseConfigs.stream().collect(Collectors.groupingBy(ParseConfigDTO::getTitleGroupId));

        //存储生成html
        StringBuilder sb = new StringBuilder();

        groupMap.forEach((k, v) -> {
            //拼接html 标题 和 修饰
            sb.append(HtmlConstant.divTitle.replace(HtmlConstant.divTitleFormat, v.get(0).getTitleGroupName())).append(HtmlConstant.divStyleHead);
            v.forEach(parseConfig -> {
                String expressionAll = parseConfig.getExpression();
                //表达式分号切分 遍历
                String collect = Arrays.stream(StringUtils.split(expressionAll, ExpressionConstant.SEMICOLON)).map(expression -> {
                    //存储递归中的对象
                    AtomicReference<JSON> flag = new AtomicReference<>(jsonObject);
                    //表达式点号切分 遍历
                    return Arrays.stream(StringUtils.split(expression, ExpressionConstant.POINT)).map(attribute -> {

                        //包含"{}"处理
                        if (attribute.startsWith(ExpressionConstant.BIG_PARANTHESES_LEFT) && attribute.endsWith(ExpressionConstant.BIG_PARANTHESES_RIGHT)) {
                            return bigHandle(flag, attribute);
                        }

                        //包含"[]"处理
                        if (attribute.startsWith(ExpressionConstant.MIDDLE_PARANTHESES_LEFT) && attribute.endsWith(ExpressionConstant.MIDDLE_PARANTHESES_RIGHT)) {
                            return middleHandle(flag, attribute);
                        }

                        //包含"()"处理
                        if (attribute.startsWith(ExpressionConstant.SMALL_PARANTHESES_LEFT) && attribute.endsWith(ExpressionConstant.SMALL_PARANTHESES_Right)) {
                            return samllHandle(parseConfig.getCycleSplicer(), flag, attribute);
                        }

                        String value;
                        JSON jsonObj = flag.get();

                        //判断是集合对象还是单对象
                        if (jsonObj instanceof JSONArray) {
                            value = ((JSONArray) jsonObj).stream().map(arr -> getValue(attribute, (JSONObject) arr, parseConfig.getValueSplicer())).collect(Collectors.joining(parseConfig.getCycleSplicer()));
                        } else {
                            value = getValue(attribute, (JSONObject) jsonObj, parseConfig.getValueSplicer());
                        }
                        return value;
                    }).collect(Collectors.joining());
                }).collect(Collectors.joining(parseConfig.getValueSplicer()));
                //拼接HTML展示数据
                sb.append(HtmlConstant.divBoby.replace(HtmlConstant.divBobyKeyFormat, parseConfig.getName().concat(parseConfig.getNameSign())).replace(HtmlConstant.divBobyValueFormat, collect));
            });
            //拼接</div>
            sb.append(HtmlConstant.divStyleEnd);
        });

        //拼接HTML格式
        return HtmlConstant.html.replace(HtmlConstant.htmlTitleFormat, parseConfigs.get(0).getGroupName()).replace(HtmlConstant.htmlBodyFormat, sb.toString());
    }

    /**
     * 处理小括号
     *
     * @param cycleSplicer 循环分隔符
     * @param flag         临时存储对象
     * @param attribute    key
     * @return
     */
    private String samllHandle(String cycleSplicer, AtomicReference<JSON> flag, String attribute) {
        String key = StringUtils.substringBetween(attribute, ExpressionConstant.SMALL_PARANTHESES_LEFT, ExpressionConstant.SMALL_PARANTHESES_Right);
        return EMPTY_STR.equals(key) ?
                map.get(ARR_ALL).apply(Dto.builder().json(flag.get()).splicer(cycleSplicer).build()).toString()
                :
                map.get(ARR_NUM).apply(Dto.builder().json(flag.get()).key(key).splicer(cycleSplicer).build()).toString();
    }

    /**
     * 处理中括号
     *
     * @param flag      临时存储对象
     * @param attribute key
     * @return
     */
    private String middleHandle(AtomicReference<JSON> flag, String attribute) {
        String key = StringUtils.substringBetween(attribute, ExpressionConstant.MIDDLE_PARANTHESES_LEFT, ExpressionConstant.MIDDLE_PARANTHESES_RIGHT);
        JSONArray arr = (JSONArray) map.get(ARR).apply(Dto.builder().json(flag.get()).key(key).build());
        //存储arr
        flag.set(arr);
        return EMPTY_STR;
    }

    /**
     * 处理大括号
     *
     * @param flag      临时存储对象
     * @param attribute key
     * @return
     */
    private String bigHandle(AtomicReference<JSON> flag, String attribute) {
        String key = StringUtils.substringBetween(attribute, ExpressionConstant.BIG_PARANTHESES_LEFT, ExpressionConstant.BIG_PARANTHESES_RIGHT);
        JSONObject obj = (JSONObject) map.get(OBJ).apply(Dto.builder().json(flag.get()).key(key).build());
        //存储obj
        flag.set(obj);
        return EMPTY_STR;
    }

    /**
     * 获取值
     *
     * @param t            key
     * @param jsonObject   jsonObject
     * @param valueSplicer 数据拼接符
     * @return
     */
    static String getValue(String t, JSONObject jsonObject, String valueSplicer) {
        String value;
        if (t.contains(ExpressionConstant.AND)) {
            value = Arrays.stream(StringUtils.split(t, ExpressionConstant.AND))
                    .map(t0 -> map.get(VAL).apply(Dto.builder().json(jsonObject).key(t0).build()).toString())
                    .collect(Collectors.joining(valueSplicer));
        } else {
            value = map.get(VAL).apply(Dto.builder().json(jsonObject).key(t).build()).toString();
        }

        return value;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    static class Dto {
        private JSON json;
        private String key;
        private String splicer;
    }

}
