package com.zr.parsedata;

import cn.hutool.json.XML;
import com.zr.parsedata.dto.ParseDataDTO;
import com.zr.parsedata.service.ParseService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ParseDataApplicationTests {

    @Resource
    private ParseService parseService;

    @Test
    void contextLoads() {

        ParseDataDTO parseDataDTO = new ParseDataDTO();
        parseDataDTO.setOriginData("<?xml version=\"1.0\" encoding=\"gbk\"?>\n" +
                "<?xml-stylesheet type=\"text/xsl\" href=\"产前病史记录_护士打印.xsl\"?>\n" +
                "<uniEPR>\n" +
                "\t<uniHead>\n" +
                "\t\t<hospitalName id=\"T9900\">上海市第一妇婴保健院</hospitalName>\n" +
                "\t\t<docId id=\"T9901\" pattern=\"[0-9A-Z]{8}-[0-9A-Z]{4}-[0-9A-Z]{4}-[0-9A-Z]{4}-[0-9A-Z]{12}\" openmode=\"edit\"/>\n" +
                "\t\t<docParentId id=\"T9902\" pattern=\"[0-9A-Z]{8}-[0-9A-Z]{4}-[0-9A-Z]{4}-[0-9A-Z]{4}-[0-9A-Z]{12}\"/>\n" +
                "\t\t<docCreator id=\"M9903\">\n" +
                "\t\t\t<doctor cname=\"签名：\">\n" +
                "\t\t\t\t<doctorNo id=\"T99030101\" length=\"6\">037</doctorNo>\n" +
                "\t\t\t\t<doctorName id=\"T99030102\">曹雅琴</doctorName>\n" +
                "\t\t\t\t<doctorTitle id=\"T99030103\"/>\n" +
                "\t\t\t</doctor>\n" +
                "\t\t\t<createDT id=\"T990302\" cname=\"首次登记：\" format=\"yyyy.mm.dd hh\">2013.08.10 18</createDT>\n" +
                "\t\t\t<signatureDT id=\"T990303\" cname=\"签名日期：\" format=\"yyyy.mm.dd\">2013.08.10</signatureDT>\n" +
                "\t\t</docCreator>\n" +
                "\t\t<refcontrol id=\"T9909\" cname=\"复制\"/>\n" +
                "\t\t<docRecorder id=\"T9904\"/>\n" +
                "\t\t<patientInfo cname=\"病人信息\">\n" +
                "\t\t\t<outpatientNo id=\"T990501\" cname=\"门诊号：\" maxlength=\"6\">YL00800172</outpatientNo>\n" +
                "\t\t\t<inpatientNo id=\"T990502\" cname=\"住院号：\" maxlength=\"6\" option=\"true\"/>\n" +
                "\t\t\t<radiationNo id=\"T990503\" cname=\"放射号：\"/>\n" +
                "\t\t\t<department id=\"T990505\" cname=\"科别\">产前初诊</department>\n" +
                "\t\t\t<ward id=\"T990506\" cname=\" 病室\"/>\n" +
                "\t\t\t<bed id=\"T990507\" cname=\"床位\"/>\n" +
                "\t\t\t<patientType id=\"T990509\" cname=\"病人类型\" option=\"false\">自费</patientType>\n" +
                "\t\t\t<name id=\"T990511\" cname=\"孕妇姓名\" maxLength=\"10\" content=\"冲压\">测试172</name>\n" +
                "\t\t\t<sex id=\"T990512\" cname=\"性别\">女</sex>\n" +
                "\t\t\t<age id=\"T990513\" cname=\"年龄\" mininclusive=\"0\" maxinclusive=\"150\" unit=\"岁\">14</age>\n" +
                "\t\t\t<nativePlace id=\"T990515\" cname=\"籍贯\" option=\"true\"/>\n" +
                "\t\t\t<nation id=\"T990516\" cname=\"民族\"/>\n" +
                "\t\t\t<maritalStatus id=\"T990517\" cname=\"婚姻\" prompt=\"已婚、未婚、离、寡\"/>\n" +
                "\t\t\t<birthplace id=\"T990518\" cname=\"出生地\"/>\n" +
                "\t\t\t<occupation id=\"T990519\" cname=\"职业\" option=\"true\" codetable=\"u_zy..v_zy_job2(code,name,py)\" multiline=\"false\"/>\n" +
                "\t\t\t<inpatientDate id=\"T990521\" cname=\"入院日期\" format=\"yyyy.mm.dd\"/>\n" +
                "\t\t\t<outpatientDate id=\"T990522\" cname=\"出院日期\" nouse=\"nouse\" format=\"yyyy.mm.dd\"/>\n" +
                "\t\t\t<deathDate id=\"T990523\" cname=\"死亡日期\" nouse=\"nouse\" format=\"yyyy.mm.dd\"/>\n" +
                "\t\t\t<inpatientTime id=\"T990524\" cname=\"入院时间\" nouse=\"nouse\" format=\"yyyy.mm.dd hh\"/>\n" +
                "\t\t\t<outpatientTime id=\"T990525\" cname=\"出院时间\" nouse=\"nouse\" format=\"yyyy.mm.dd hh\"/>\n" +
                "\t\t\t<deathTime id=\"T990526\" cname=\"死亡时间\" nouse=\"nouse\" format=\"yyyy.mm.dd hh:mm\"/>\n" +
                "\t\t\t<handoverDate id=\"T990527\" cname=\"交班日期\" nouse=\"nouse\" format=\"yyyy.mm.dd hh\"/>\n" +
                "\t\t\t<carryonDate id=\"T990528\" cname=\"接班日期\" nouse=\"nouse\" format=\"yyyy.mm.dd hh\"/>\n" +
                "\t\t\t<outgoingDate id=\"T990529\" cname=\"转出日期\" nouse=\"nouse\" format=\"yyyy.mm.dd hh\"/>\n" +
                "\t\t\t<ingoingDate id=\"T990530\" cname=\"转入日期\" nouse=\"nouse\" format=\"yyyy.mm.dd hh\"/>\n" +
                "\t\t\t<briefsummaryDate id=\"T990531\" cname=\"小结日期\" nouse=\"nouse\" format=\"yyyy.mm.dd hh\"/>\n" +
                "\t\t\t<recordDate id=\"T990541\" cname=\"记录日期\" format=\"yyyy.mm.dd hh\">2013.08.10 18</recordDate>\n" +
                "\t\t\t<offerName id=\"T990542\" cname=\"病史陈述者\">患者本人</offerName>\n" +
                "\t\t\t<birth id=\"T990545\" cname=\"出生\">1999.01.01</birth>\n" +
                "\t\t\t<bodyCard id=\"T990546\" cname=\"身份证号\" option=\"true\">31010109878123</bodyCard>\n" +
                "\t\t\t<organization id=\"T990547\" cname=\"组织\" option=\"true\"/>\n" +
                "\t\t\t<nationality id=\"T990548\" cname=\"国籍\" option=\"true\"/>\n" +
                "\t\t\t<homeAddress id=\"T990551\" cname=\"现住地址\" option=\"true\">不详</homeAddress>\n" +
                "\t\t\t<workPart id=\"T990552\" cname=\"工作单位\" option=\"true\"/>\n" +
                "\t\t\t<workAPart id=\"T990576\" cname=\"部门\" option=\"true\"/>\n" +
                "\t\t\t<homePhone id=\"T990553\" cname=\"电话\" option=\"true\"/>\n" +
                "\t\t\t<workAddress id=\"T990575\" cname=\"地址\" option=\"true\"/>\n" +
                "\t\t\t<workPhone id=\"T990554\" cname=\"电话\" option=\"true\"/>\n" +
                "\t\t\t<workAddress id=\"T990575\" cname=\"地址\" option=\"true\"/>\n" +
                "\t\t\t<homeZipcode id=\"T990555\" cname=\"邮政编码\" option=\"true\"/>\n" +
                "\t\t\t<workZipcode id=\"T990556\" cname=\"邮政编码\" option=\"true\"/>\n" +
                "\t\t\t<relationName id=\"T990571\" cname=\"姓名\" option=\"true\"/>\n" +
                "\t\t\t<relationAge id=\"T9905791\" cname=\"年龄\" option=\"true\"/>\n" +
                "\t\t\t<relationWork id=\"T9905792\" cname=\"工作单位\" option=\"true\"/>\n" +
                "\t\t\t<relationApart id=\"T9905793\" cname=\"部门\" option=\"true\"/>\n" +
                "\t\t\t<relationAddressa id=\"T9905794\" cname=\"地址\" option=\"true\"/>\n" +
                "\t\t\t<relationPhone id=\"T9905795\" cname=\"电话\" option=\"true\"/>\n" +
                "\t\t\t<relation id=\"T990572\" cname=\"关系\"/>\n" +
                "\t\t\t<relationAddress id=\"T990573\" cname=\"户口地址\" option=\"true\"/>\n" +
                "\t\t\t<relationPhone id=\"T990574\" cname=\"电话\" option=\"true\"/>\n" +
                "\t\t</patientInfo>\n" +
                "\t\t<doctorInfo cname=\"医生信息\">\n" +
                "\t\t\t<directorDoctor cname=\"主任医师\">\n" +
                "\t\t\t\t<doctorNo id=\"T99060101\" length=\"6\"/>\n" +
                "\t\t\t\t<doctorName id=\"T99060102\"/>\n" +
                "\t\t\t\t<doctorTitle id=\"T99060103\">主任医师</doctorTitle>\n" +
                "\t\t\t</directorDoctor>\n" +
                "\t\t\t<treatDoctor cname=\"主治医师\">\n" +
                "\t\t\t\t<doctorNo id=\"T99060201\" length=\"6\"/>\n" +
                "\t\t\t\t<doctorName id=\"T99060202\"/>\n" +
                "\t\t\t\t<doctorTitle id=\"T99060203\"/>\n" +
                "\t\t\t</treatDoctor>\n" +
                "\t\t\t<inhospitalDoctor cname=\"住院医生\">\n" +
                "\t\t\t\t<doctorNo id=\"T99060301\" length=\"6\"/>\n" +
                "\t\t\t\t<doctorName id=\"T99060302\"/>\n" +
                "\t\t\t\t<doctorTitle id=\"T99060303\"/>\n" +
                "\t\t\t</inhospitalDoctor>\n" +
                "\t\t\t<internDoctor cname=\"实习医生\">\n" +
                "\t\t\t\t<doctorNo id=\"T99060401\" length=\"6\"/>\n" +
                "\t\t\t\t<doctorName id=\"T99060402\"/>\n" +
                "\t\t\t\t<doctorTitle id=\"T99060403\"/>\n" +
                "\t\t\t</internDoctor>\n" +
                "\t\t</doctorInfo>\n" +
                "\t\t<access id=\"T9907\"/>\n" +
                "\t\t<docMender id=\"T9908\"/>\n" +
                "\t</uniHead>\n" +
                "\t<uniBody>\n" +
                "\t\t<completePR>\n" +
                "\t\t\t<patientInfo cname=\"病人信息\">\n" +
                "\t\t\t\t<obstetricpatientType id=\"M990591\" cname=\"产科病人类型\" display=\"M99059102\"/>\n" +
                "\t\t\t\t<fenzhu id=\"M990599\" cname=\"分组\" option=\"true\" prompt=\"A、B、C、D、E、VIP、F、G、特需\"/>\n" +
                "\t\t\t\t<preengagedate id=\"M990598\" cname=\"预约日期:\" pattern=\"^((19|20)\\d{2}).(\\d{2}).(\\d{2})$\" format=\"yyyy.mm.dd\" prompt=\"yyyy.mm.dd\" errmsg=\"您输入有误，请输入正确的日期格式，如：2008.01.01\"/>\n" +
                "\t\t\t\t<transferTreat id=\"M990575\" cname=\"转诊医疗机构\" option=\"true\"/>\n" +
                "\t\t\t\t<transferPhone id=\"M990576\" cname=\"电话\" option=\"true\"/>\n" +
                "\t\t\t\t<marryAge id=\"M990577\" cname=\"结婚年龄\" option=\"true\"/>\n" +
                "\t\t\t\t<pregnantAge id=\"M990578\" cname=\"初孕年龄\" option=\"true\"/>\n" +
                "\t\t\t\t<catameniaMenarche id=\"M990579\" cname=\"初潮年龄\" unit=\"岁\" option=\"true\"/>\n" +
                "\t\t\t\t<catameniaPeriod id=\"M990580\" cname=\"月经周期\" option=\"true\"/>\n" +
                "\t\t\t\t<lastCatamenia id=\"M990581\" cname=\"末次月经\" option=\"true\" pattern=\"^((19|20)\\d{2}).(\\d{2}).(\\d{2})$\" trigger=\"M990585=string(relativedate(date(if(integer(mid('[M990581]',6,2)) + 9 > 12, 1, 0) + integer(left('[M990581]',4)),  if(integer(mid('[M990581]',6,2)) + 9 > 12, -3, 9) + integer(mid('[M990581]', 6, 2)), integer(right('[M990581]', 2)) ), 7),'yyyy.mm.dd');\" prompt=\"yyyy.mm.dd\" format=\"yyyy.mm.dd\"/>\n" +
                "\t\t\t\t<baseBlood id=\"M990582\" cname=\"基础血压\" unit=\"mmHg\"/>\n" +
                "\t\t\t\t<checkpregnantTime id=\"M990583\" cname=\"初次检查孕期\" unit=\"周\" option=\"true\"/>\n" +
                "\t\t\t\t<quickeningPregnant id=\"M990584\" cname=\"胎动孕周\" unit=\"周\" option=\"true\">？</quickeningPregnant>\n" +
                "\t\t\t\t<expectedbirthDate id=\"M990585\" cname=\"预 产 期\" option=\"true\"/>\n" +
                "\t\t\t\t<outcheckTime id=\"M990586\" cname=\"外院检查次数\" option=\"true\"/>\n" +
                "\t\t\t\t<husbandIdentityCard id=\"M990589\" cname=\"丈夫身份证号(护照):\" option=\"true\"/>\n" +
                "\t\t\t\t<teacheClass id=\"M990590\" cname=\"宣教上课\" option=\"true\" prompt=\"拒绝上课\"/>\n" +
                "\t\t\t\t<husbandhealth id=\"M990591\" cname=\"健康状况\" option=\"true\"/>\n" +
                "\t\t\t\t<describe id=\"M990592\" cname=\"\" option=\"true\" prompt=\"孕3月前无接触放射,化学物、曾于孕_____周时发热、服药\"/>\n" +
                "\t\t\t\t<source id=\"M990593\" cname=\"来源\" option=\"true\" selectmode=\"multi\" prompt=\"本市 外地\"/>\n" +
                "\t\t\t\t<hjlx id=\"M990594\" cname=\"户籍类型\" option=\"false\" prompt=\"本市农业 本市非农业 非本市农业 非本市非农业\"/>\n" +
                "\t\t\t\t<zjlbdm id=\"M990595\" cname=\"证件类别代码\" option=\"false\" prompt=\"居民身份证 居民户口簿 护照 军官证（士兵证） 05驾驶执照\"/>\n" +
                "\t\t\t\t<jzdz id=\"M990596\" cname=\"居住地址\">\n" +
                "\t\t\t\t\t<jzdz1 id=\"M99059601\" cname=\"省\" option=\"false\" unit=\"省\" codetable=\"u_zy..v_zy_region(regionid,regionname,regionpy)isprovince=0\" multiline=\"false\" nextnode=\"M99059602\"/>\n" +
                "\t\t\t\t\t<jzdz2 id=\"M99059602\" cname=\"市\" option=\"false\" unit=\"市\" codetable=\"u_zy..v_zy_region(regionid,regionname,regionpy)\" multiline=\"false\" nextnode=\"M99059603\"/>\n" +
                "\t\t\t\t\t<jzdz3 id=\"M99059603\" cname=\"区\" option=\"false\" unit=\"区\" codetable=\"u_zy..v_zy_region(regionid,regionname,regionpy)isprovince=1\" multiline=\"false\" nextnode=\"M99059604\"/>\n" +
                "\t\t\t\t\t<jzdz4 id=\"M99059604\" cname=\"乡\" option=\"false\" unit=\"乡\" codetable=\"u_zy..v_zy_region(regionid,regionname,regionpy)\" multiline=\"false\"/>\n" +
                "\t\t\t\t</jzdz>\n" +
                "\t\t\t\t<hjdz id=\"M990597\" cname=\"户籍地址\">\n" +
                "\t\t\t\t\t<hjdz1 id=\"M99059701\" cname=\"省\" option=\"false\" unit=\"省\" codetable=\"u_zy..v_zy_region(regionid,regionname,regionpy)\" multiline=\"false\" nextnode=\"M99059702\"/>\n" +
                "\t\t\t\t\t<hjdz2 id=\"M99059702\" cname=\"市\" option=\"false\" unit=\"市\" codetable=\"u_zy..v_zy_region(regionid,regionname,regionpy)\" multiline=\"false\" nextnode=\"M99059703\"/>\n" +
                "\t\t\t\t\t<hjdz3 id=\"M99059703\" cname=\"区\" option=\"false\" unit=\"区\" codetable=\"u_zy..v_zy_region(regionid,regionname,regionpy)\" multiline=\"false\"/>\n" +
                "\t\t\t\t</hjdz>\n" +
                "\t\t\t\t<sqjc id=\"M990601\" cname=\"社区建册\" option=\"false\" prompt=\"无 有\"/>\n" +
                "\t\t\t\t<educationLevel id=\"M990602\" cname=\"文化程度\" option=\"true\"/>\n" +
                "\t\t\t</patientInfo>\n" +
                "\t\t\t<pregnantHistory id=\"M0201\" cname=\"孕产史\">\n" +
                "\t\t\t\t<content1 id=\"M020101\">\n" +
                "\t\t\t\t\t<pregnantTime id=\"M02010101\" cname=\"孕次\">1</pregnantTime>\n" +
                "\t\t\t\t\t<pregnantContent id=\"M02010102\" cname=\"描述\" option=\"true\">\n" +
                "\t\t\t\t\t\t<item1 id=\"M0201010201\" cname=\"足月产\" unit=\"次\" prompt=\"0,1,2,3,4,5\">0</item1>\n" +
                "\t\t\t\t\t\t<item2 id=\"M0201010202\" cname=\"早产\" unit=\"次\" prompt=\"0,1,2,3,4,5\">0</item2>\n" +
                "\t\t\t\t\t\t<item3 id=\"M0201010203\" cname=\"流产\" unit=\"次\" prompt=\"0,1,2,3,4,5\">0</item3>\n" +
                "\t\t\t\t\t\t<item4 id=\"M0201010204\" cname=\"人流\" unit=\"次\" prompt=\"0,1,2,3,4,5\">0</item4>\n" +
                "\t\t\t\t\t</pregnantContent>\n" +
                "\t\t\t\t</content1>\n" +
                "\t\t\t\t<content2 id=\"M020102\">\n" +
                "\t\t\t\t\t<pregnantTime id=\"M02010201\" cname=\"孕次\">2</pregnantTime>\n" +
                "\t\t\t\t\t<pregnantContent id=\"M02010202\" cname=\"描述\" option=\"true\">\n" +
                "\t\t\t\t\t\t<item5 id=\"M0201020201\" cname=\"现有子女数\"/>\n" +
                "\t\t\t\t\t\t<item6 id=\"M0201020202\" cname=\"末次生(流)产时间\" prompt=\"yyyy.mm.dd\"/>\n" +
                "\t\t\t\t\t\t<item7 id=\"M0201020203\" cname=\"末次生(流)产内容\"/>\n" +
                "\t\t\t\t\t</pregnantContent>\n" +
                "\t\t\t\t</content2>\n" +
                "\t\t\t\t<content3 id=\"M020103\">\n" +
                "\t\t\t\t\t<pregnantTime id=\"M02010301\" cname=\"孕次\">3</pregnantTime>\n" +
                "\t\t\t\t\t<pregnantContent id=\"M02010302\" cname=\"描述\">\n" +
                "\t\t\t\t\t\t<item7 id=\"M0201030201\" cname=\"婴(胎)儿死亡原因\">无</item7>\n" +
                "\t\t\t\t\t</pregnantContent>\n" +
                "\t\t\t\t</content3>\n" +
                "\t\t\t\t<content4 id=\"M020104\">\n" +
                "\t\t\t\t\t<pregnantTime id=\"M02010401\" cname=\"孕次\">4</pregnantTime>\n" +
                "\t\t\t\t\t<pregnantContent id=\"M02010402\" cname=\"描述\">\n" +
                "\t\t\t\t\t\t<item8 id=\"M0201040201\" cname=\"出血史\">无</item8>\n" +
                "\t\t\t\t\t</pregnantContent>\n" +
                "\t\t\t\t</content4>\n" +
                "\t\t\t\t<content5 id=\"M020105\">\n" +
                "\t\t\t\t\t<pregnantTime id=\"M02010501\" cname=\"孕次\">5</pregnantTime>\n" +
                "\t\t\t\t\t<pregnantContent id=\"M02010502\" cname=\"描述\">\n" +
                "\t\t\t\t\t\t<item9 id=\"M0201050201\" cname=\"难产史及手术史\">无</item9>\n" +
                "\t\t\t\t\t</pregnantContent>\n" +
                "\t\t\t\t</content5>\n" +
                "\t\t\t\t<content6 id=\"M020106\">\n" +
                "\t\t\t\t\t<pregnantTime id=\"M02010601\" cname=\"孕次\">6</pregnantTime>\n" +
                "\t\t\t\t\t<pregnantContent id=\"M02010602\" cname=\"描述\" option=\"true\">\n" +
                "\t\t\t\t\t\t<item10 id=\"M0201060201\" cname=\"其他\">无</item10>\n" +
                "\t\t\t\t\t</pregnantContent>\n" +
                "\t\t\t\t</content6>\n" +
                "\t\t\t</pregnantHistory>\n" +
                "\t\t\t<pastHis id=\"M0204\" cname=\"既往史\">\n" +
                "\t\t\t\t<infectiousHis id=\"M020401\" cname=\"过去史\" selectmode=\"multi\" option=\"true\" prompt=\"无、心脏病、肺()、肾脏、肝炎、高血压、甲亢、糖尿病、手术、阑尾切除、胆囊切除、其他\">\n" +
                "\t\t\t\t\t<text id=\"T21\">无</text>\n" +
                "\t\t\t\t</infectiousHis>\n" +
                "\t\t\t\t<homeHis id=\"M020402\" cname=\"家族史\" selectmode=\"multi\" option=\"true\" prompt=\"高血压: 父 母 遗传病() 传染病() 无\">\n" +
                "\t\t\t\t\t<text id=\"T23\">无</text>\n" +
                "\t\t\t\t</homeHis>\n" +
                "\t\t\t\t<latelyHis id=\"M020406\" cname=\"近期史\" selectmode=\"multi\" option=\"true\" prompt=\"传肝 菌痢 出血或紫斑史 有 无\">\n" +
                "\t\t\t\t\t<text id=\"T24\">无</text>\n" +
                "\t\t\t\t</latelyHis>\n" +
                "\t\t\t\t<operateHis id=\"M020407\" cname=\"手术史\" option=\"true\">\n" +
                "\t\t\t\t\t<text id=\"T22\">无</text>\n" +
                "\t\t\t\t</operateHis>\n" +
                "\t\t\t\t<drugHyperHis id=\"M020403\" cname=\"过敏史\" selectmode=\"multi\" option=\"true\" prompt=\"无、青霉素(+)、头孢、磺胺\">\n" +
                "\t\t\t\t\t<text id=\"T25\">无</text>\n" +
                "\t\t\t\t</drugHyperHis>\n" +
                "\t\t\t\t<gestationCircs id=\"M0214\" cname=\"本次妊娠异常情况\">\n" +
                "\t\t\t\t\t<text id=\"T26\">无</text>\n" +
                "\t\t\t\t</gestationCircs>\n" +
                "\t\t\t\t<jwpgcsj id=\"M020408\" cname=\"既往剖宫产时间\" option=\"true\" prompt=\"yyyy.mm.dd\"/>\n" +
                "\t\t\t\t<jwpgcyy id=\"M020409\" cname=\"既往剖宫产医院\" option=\"true\"/>\n" +
                "\t\t\t\t<jwpgcyyjb id=\"M020410\" cname=\"既往剖宫产医院级别\" option=\"false\" prompt=\"无 III级专科 III级综合 II级专科 II级综合 外资 民营 其他\"/>\n" +
                "\t\t\t\t<jwpgczz id=\"M020411\" cname=\"既往剖宫产指征\" option=\"true\" prompt=\"\"/>\n" +
                "\t\t\t\t<jwpgcssfs id=\"M020412\" cname=\"既往剖宫产手术方式\" option=\"false\" prompt=\"无 子宫下端剖宫术 腹膜外剖宫术 子宫体剖宫术 其他\"/>\n" +
                "\t\t\t\t<jwpgccx id=\"M020413\" cname=\"既往剖宫产出血\" option=\"false\" prompt=\"无 有 不详\"/>\n" +
                "\t\t\t\t<jwpgcgr id=\"M020414\" cname=\"既往剖宫产感染\" option=\"false\" prompt=\"无 有 不详\"/>\n" +
                "\t\t\t</pastHis>\n" +
                "\t\t</completePR>\n" +
                "\t</uniBody>\n" +
                "</uniEPR>\n");
        parseDataDTO.setTypeId(1);

        String html = parseService.parse(parseDataDTO);
        System.err.println(html);
    }

    @Test
    void test02() {

        String xml = "<?xml version=\"1.0\" encoding=\"gbk\"?>\n" +
                "<?xml-stylesheet type=\"text/xsl\" href=\"产前病史记录_护士打印.xsl\"?>\n" +
                "<uniEPR>\n" +
                "\t<uniHead>\n" +
                "\t\t<hospitalName id=\"T9900\">上海市第一妇婴保健院</hospitalName>\n" +
                "\t\t<docId id=\"T9901\" pattern=\"[0-9A-Z]{8}-[0-9A-Z]{4}-[0-9A-Z]{4}-[0-9A-Z]{4}-[0-9A-Z]{12}\" openmode=\"edit\"/>\n" +
                "\t\t<docParentId id=\"T9902\" pattern=\"[0-9A-Z]{8}-[0-9A-Z]{4}-[0-9A-Z]{4}-[0-9A-Z]{4}-[0-9A-Z]{12}\"/>\n" +
                "\t\t<docCreator id=\"M9903\">\n" +
                "\t\t\t<doctor cname=\"签名：\">\n" +
                "\t\t\t\t<doctorNo id=\"T99030101\" length=\"6\">037</doctorNo>\n" +
                "\t\t\t\t<doctorName id=\"T99030102\">曹雅琴</doctorName>\n" +
                "\t\t\t\t<doctorTitle id=\"T99030103\"/>\n" +
                "\t\t\t</doctor>\n" +
                "\t\t\t<createDT id=\"T990302\" cname=\"首次登记：\" format=\"yyyy.mm.dd hh\">2013.08.10 18</createDT>\n" +
                "\t\t\t<signatureDT id=\"T990303\" cname=\"签名日期：\" format=\"yyyy.mm.dd\">2013.08.10</signatureDT>\n" +
                "\t\t</docCreator>\n" +
                "\t\t<refcontrol id=\"T9909\" cname=\"复制\"/>\n" +
                "\t\t<docRecorder id=\"T9904\"/>\n" +
                "\t\t<patientInfo cname=\"病人信息\">\n" +
                "\t\t\t<outpatientNo id=\"T990501\" cname=\"门诊号：\" maxlength=\"6\">YL00800172</outpatientNo>\n" +
                "\t\t\t<inpatientNo id=\"T990502\" cname=\"住院号：\" maxlength=\"6\" option=\"true\"/>\n" +
                "\t\t\t<radiationNo id=\"T990503\" cname=\"放射号：\"/>\n" +
                "\t\t\t<department id=\"T990505\" cname=\"科别\">产前初诊</department>\n" +
                "\t\t\t<ward id=\"T990506\" cname=\" 病室\"/>\n" +
                "\t\t\t<bed id=\"T990507\" cname=\"床位\"/>\n" +
                "\t\t\t<patientType id=\"T990509\" cname=\"病人类型\" option=\"false\">自费</patientType>\n" +
                "\t\t\t<name id=\"T990511\" cname=\"孕妇姓名\" maxLength=\"10\">测试172</name>\n" +
                "\t\t\t<sex id=\"T990512\" cname=\"性别\">女</sex>\n" +
                "\t\t\t<age id=\"T990513\" cname=\"年龄\" mininclusive=\"0\" maxinclusive=\"150\" unit=\"岁\">14</age>\n" +
                "\t\t\t<nativePlace id=\"T990515\" cname=\"籍贯\" option=\"true\"/>\n" +
                "\t\t\t<nation id=\"T990516\" cname=\"民族\"/>\n" +
                "\t\t\t<maritalStatus id=\"T990517\" cname=\"婚姻\" prompt=\"已婚、未婚、离、寡\"/>\n" +
                "\t\t\t<birthplace id=\"T990518\" cname=\"出生地\"/>\n" +
                "\t\t\t<occupation id=\"T990519\" cname=\"职业\" option=\"true\" codetable=\"u_zy..v_zy_job2(code,name,py)\" multiline=\"false\"/>\n" +
                "\t\t\t<inpatientDate id=\"T990521\" cname=\"入院日期\" format=\"yyyy.mm.dd\"/>\n" +
                "\t\t\t<outpatientDate id=\"T990522\" cname=\"出院日期\" nouse=\"nouse\" format=\"yyyy.mm.dd\"/>\n" +
                "\t\t\t<deathDate id=\"T990523\" cname=\"死亡日期\" nouse=\"nouse\" format=\"yyyy.mm.dd\"/>\n" +
                "\t\t\t<inpatientTime id=\"T990524\" cname=\"入院时间\" nouse=\"nouse\" format=\"yyyy.mm.dd hh\"/>\n" +
                "\t\t\t<outpatientTime id=\"T990525\" cname=\"出院时间\" nouse=\"nouse\" format=\"yyyy.mm.dd hh\"/>\n" +
                "\t\t\t<deathTime id=\"T990526\" cname=\"死亡时间\" nouse=\"nouse\" format=\"yyyy.mm.dd hh:mm\"/>\n" +
                "\t\t\t<handoverDate id=\"T990527\" cname=\"交班日期\" nouse=\"nouse\" format=\"yyyy.mm.dd hh\"/>\n" +
                "\t\t\t<carryonDate id=\"T990528\" cname=\"接班日期\" nouse=\"nouse\" format=\"yyyy.mm.dd hh\"/>\n" +
                "\t\t\t<outgoingDate id=\"T990529\" cname=\"转出日期\" nouse=\"nouse\" format=\"yyyy.mm.dd hh\"/>\n" +
                "\t\t\t<ingoingDate id=\"T990530\" cname=\"转入日期\" nouse=\"nouse\" format=\"yyyy.mm.dd hh\"/>\n" +
                "\t\t\t<briefsummaryDate id=\"T990531\" cname=\"小结日期\" nouse=\"nouse\" format=\"yyyy.mm.dd hh\"/>\n" +
                "\t\t\t<recordDate id=\"T990541\" cname=\"记录日期\" format=\"yyyy.mm.dd hh\">2013.08.10 18</recordDate>\n" +
                "\t\t\t<offerName id=\"T990542\" cname=\"病史陈述者\">患者本人</offerName>\n" +
                "\t\t\t<birth id=\"T990545\" cname=\"出生\">1999.01.01</birth>\n" +
                "\t\t\t<bodyCard id=\"T990546\" cname=\"身份证号\" option=\"true\">31010109878123</bodyCard>\n" +
                "\t\t\t<organization id=\"T990547\" cname=\"组织\" option=\"true\"/>\n" +
                "\t\t\t<nationality id=\"T990548\" cname=\"国籍\" option=\"true\"/>\n" +
                "\t\t\t<homeAddress id=\"T990551\" cname=\"现住地址\" option=\"true\">不详</homeAddress>\n" +
                "\t\t\t<workPart id=\"T990552\" cname=\"工作单位\" option=\"true\"/>\n" +
                "\t\t\t<workAPart id=\"T990576\" cname=\"部门\" option=\"true\"/>\n" +
                "\t\t\t<homePhone id=\"T990553\" cname=\"电话\" option=\"true\"/>\n" +
                "\t\t\t<workAddress id=\"T990575\" cname=\"地址\" option=\"true\"/>\n" +
                "\t\t\t<workPhone id=\"T990554\" cname=\"电话\" option=\"true\"/>\n" +
                "\t\t\t<workAddress id=\"T990575\" cname=\"地址\" option=\"true\"/>\n" +
                "\t\t\t<homeZipcode id=\"T990555\" cname=\"邮政编码\" option=\"true\"/>\n" +
                "\t\t\t<workZipcode id=\"T990556\" cname=\"邮政编码\" option=\"true\"/>\n" +
                "\t\t\t<relationName id=\"T990571\" cname=\"姓名\" option=\"true\"/>\n" +
                "\t\t\t<relationAge id=\"T9905791\" cname=\"年龄\" option=\"true\"/>\n" +
                "\t\t\t<relationWork id=\"T9905792\" cname=\"工作单位\" option=\"true\"/>\n" +
                "\t\t\t<relationApart id=\"T9905793\" cname=\"部门\" option=\"true\"/>\n" +
                "\t\t\t<relationAddressa id=\"T9905794\" cname=\"地址\" option=\"true\"/>\n" +
                "\t\t\t<relationPhone id=\"T9905795\" cname=\"电话\" option=\"true\"/>\n" +
                "\t\t\t<relation id=\"T990572\" cname=\"关系\"/>\n" +
                "\t\t\t<relationAddress id=\"T990573\" cname=\"户口地址\" option=\"true\"/>\n" +
                "\t\t\t<relationPhone id=\"T990574\" cname=\"电话\" option=\"true\"/>\n" +
                "\t\t</patientInfo>\n" +
                "\t\t<doctorInfo cname=\"医生信息\">\n" +
                "\t\t\t<directorDoctor cname=\"主任医师\">\n" +
                "\t\t\t\t<doctorNo id=\"T99060101\" length=\"6\"/>\n" +
                "\t\t\t\t<doctorName id=\"T99060102\"/>\n" +
                "\t\t\t\t<doctorTitle id=\"T99060103\">主任医师</doctorTitle>\n" +
                "\t\t\t</directorDoctor>\n" +
                "\t\t\t<treatDoctor cname=\"主治医师\">\n" +
                "\t\t\t\t<doctorNo id=\"T99060201\" length=\"6\"/>\n" +
                "\t\t\t\t<doctorName id=\"T99060202\"/>\n" +
                "\t\t\t\t<doctorTitle id=\"T99060203\"/>\n" +
                "\t\t\t</treatDoctor>\n" +
                "\t\t\t<inhospitalDoctor cname=\"住院医生\">\n" +
                "\t\t\t\t<doctorNo id=\"T99060301\" length=\"6\"/>\n" +
                "\t\t\t\t<doctorName id=\"T99060302\"/>\n" +
                "\t\t\t\t<doctorTitle id=\"T99060303\"/>\n" +
                "\t\t\t</inhospitalDoctor>\n" +
                "\t\t\t<internDoctor cname=\"实习医生\">\n" +
                "\t\t\t\t<doctorNo id=\"T99060401\" length=\"6\"/>\n" +
                "\t\t\t\t<doctorName id=\"T99060402\"/>\n" +
                "\t\t\t\t<doctorTitle id=\"T99060403\"/>\n" +
                "\t\t\t</internDoctor>\n" +
                "\t\t</doctorInfo>\n" +
                "\t\t<access id=\"T9907\"/>\n" +
                "\t\t<docMender id=\"T9908\"/>\n" +
                "\t</uniHead>\n" +
                "\t<uniBody>\n" +
                "\t\t<completePR>\n" +
                "\t\t\t<patientInfo cname=\"病人信息\">\n" +
                "\t\t\t\t<obstetricpatientType id=\"M990591\" cname=\"产科病人类型\" display=\"M99059102\"/>\n" +
                "\t\t\t\t<fenzhu id=\"M990599\" cname=\"分组\" option=\"true\" prompt=\"A、B、C、D、E、VIP、F、G、特需\"/>\n" +
                "\t\t\t\t<preengagedate id=\"M990598\" cname=\"预约日期:\" pattern=\"^((19|20)\\d{2}).(\\d{2}).(\\d{2})$\" format=\"yyyy.mm.dd\" prompt=\"yyyy.mm.dd\" errmsg=\"您输入有误，请输入正确的日期格式，如：2008.01.01\"/>\n" +
                "\t\t\t\t<transferTreat id=\"M990575\" cname=\"转诊医疗机构\" option=\"true\"/>\n" +
                "\t\t\t\t<transferPhone id=\"M990576\" cname=\"电话\" option=\"true\"/>\n" +
                "\t\t\t\t<marryAge id=\"M990577\" cname=\"结婚年龄\" option=\"true\"/>\n" +
                "\t\t\t\t<pregnantAge id=\"M990578\" cname=\"初孕年龄\" option=\"true\"/>\n" +
                "\t\t\t\t<catameniaMenarche id=\"M990579\" cname=\"初潮年龄\" unit=\"岁\" option=\"true\"/>\n" +
                "\t\t\t\t<catameniaPeriod id=\"M990580\" cname=\"月经周期\" option=\"true\"/>\n" +
                "\t\t\t\t<lastCatamenia id=\"M990581\" cname=\"末次月经\" option=\"true\" pattern=\"^((19|20)\\d{2}).(\\d{2}).(\\d{2})$\" trigger=\"M990585=string(relativedate(date(if(integer(mid('[M990581]',6,2)) + 9 > 12, 1, 0) + integer(left('[M990581]',4)),  if(integer(mid('[M990581]',6,2)) + 9 > 12, -3, 9) + integer(mid('[M990581]', 6, 2)), integer(right('[M990581]', 2)) ), 7),'yyyy.mm.dd');\" prompt=\"yyyy.mm.dd\" format=\"yyyy.mm.dd\"/>\n" +
                "\t\t\t\t<baseBlood id=\"M990582\" cname=\"基础血压\" unit=\"mmHg\"/>\n" +
                "\t\t\t\t<checkpregnantTime id=\"M990583\" cname=\"初次检查孕期\" unit=\"周\" option=\"true\"/>\n" +
                "\t\t\t\t<quickeningPregnant id=\"M990584\" cname=\"胎动孕周\" unit=\"周\" option=\"true\">？</quickeningPregnant>\n" +
                "\t\t\t\t<expectedbirthDate id=\"M990585\" cname=\"预 产 期\" option=\"true\"/>\n" +
                "\t\t\t\t<outcheckTime id=\"M990586\" cname=\"外院检查次数\" option=\"true\"/>\n" +
                "\t\t\t\t<husbandIdentityCard id=\"M990589\" cname=\"丈夫身份证号(护照):\" option=\"true\"/>\n" +
                "\t\t\t\t<teacheClass id=\"M990590\" cname=\"宣教上课\" option=\"true\" prompt=\"拒绝上课\"/>\n" +
                "\t\t\t\t<husbandhealth id=\"M990591\" cname=\"健康状况\" option=\"true\"/>\n" +
                "\t\t\t\t<describe id=\"M990592\" cname=\"\" option=\"true\" prompt=\"孕3月前无接触放射,化学物、曾于孕_____周时发热、服药\"/>\n" +
                "\t\t\t\t<source id=\"M990593\" cname=\"来源\" option=\"true\" selectmode=\"multi\" prompt=\"本市 外地\"/>\n" +
                "\t\t\t\t<hjlx id=\"M990594\" cname=\"户籍类型\" option=\"false\" prompt=\"本市农业 本市非农业 非本市农业 非本市非农业\"/>\n" +
                "\t\t\t\t<zjlbdm id=\"M990595\" cname=\"证件类别代码\" option=\"false\" prompt=\"居民身份证 居民户口簿 护照 军官证（士兵证） 05驾驶执照\"/>\n" +
                "\t\t\t\t<jzdz id=\"M990596\" cname=\"居住地址\">\n" +
                "\t\t\t\t\t<jzdz1 id=\"M99059601\" cname=\"省\" option=\"false\" unit=\"省\" codetable=\"u_zy..v_zy_region(regionid,regionname,regionpy)isprovince=0\" multiline=\"false\" nextnode=\"M99059602\"/>\n" +
                "\t\t\t\t\t<jzdz2 id=\"M99059602\" cname=\"市\" option=\"false\" unit=\"市\" codetable=\"u_zy..v_zy_region(regionid,regionname,regionpy)\" multiline=\"false\" nextnode=\"M99059603\"/>\n" +
                "\t\t\t\t\t<jzdz3 id=\"M99059603\" cname=\"区\" option=\"false\" unit=\"区\" codetable=\"u_zy..v_zy_region(regionid,regionname,regionpy)isprovince=1\" multiline=\"false\" nextnode=\"M99059604\"/>\n" +
                "\t\t\t\t\t<jzdz4 id=\"M99059604\" cname=\"乡\" option=\"false\" unit=\"乡\" codetable=\"u_zy..v_zy_region(regionid,regionname,regionpy)\" multiline=\"false\"/>\n" +
                "\t\t\t\t</jzdz>\n" +
                "\t\t\t\t<hjdz id=\"M990597\" cname=\"户籍地址\">\n" +
                "\t\t\t\t\t<hjdz1 id=\"M99059701\" cname=\"省\" option=\"false\" unit=\"省\" codetable=\"u_zy..v_zy_region(regionid,regionname,regionpy)\" multiline=\"false\" nextnode=\"M99059702\"/>\n" +
                "\t\t\t\t\t<hjdz2 id=\"M99059702\" cname=\"市\" option=\"false\" unit=\"市\" codetable=\"u_zy..v_zy_region(regionid,regionname,regionpy)\" multiline=\"false\" nextnode=\"M99059703\"/>\n" +
                "\t\t\t\t\t<hjdz3 id=\"M99059703\" cname=\"区\" option=\"false\" unit=\"区\" codetable=\"u_zy..v_zy_region(regionid,regionname,regionpy)\" multiline=\"false\"/>\n" +
                "\t\t\t\t</hjdz>\n" +
                "\t\t\t\t<sqjc id=\"M990601\" cname=\"社区建册\" option=\"false\" prompt=\"无 有\"/>\n" +
                "\t\t\t\t<educationLevel id=\"M990602\" cname=\"文化程度\" option=\"true\"/>\n" +
                "\t\t\t</patientInfo>\n" +
                "\t\t\t<pregnantHistory id=\"M0201\" cname=\"孕产史\">\n" +
                "\t\t\t\t<content1 id=\"M020101\">\n" +
                "\t\t\t\t\t<pregnantTime id=\"M02010101\" cname=\"孕次\">1</pregnantTime>\n" +
                "\t\t\t\t\t<pregnantContent id=\"M02010102\" cname=\"描述\" option=\"true\">\n" +
                "\t\t\t\t\t\t<item1 id=\"M0201010201\" cname=\"足月产\" unit=\"次\" prompt=\"0,1,2,3,4,5\">0</item1>\n" +
                "\t\t\t\t\t\t<item2 id=\"M0201010202\" cname=\"早产\" unit=\"次\" prompt=\"0,1,2,3,4,5\">0</item2>\n" +
                "\t\t\t\t\t\t<item3 id=\"M0201010203\" cname=\"流产\" unit=\"次\" prompt=\"0,1,2,3,4,5\">0</item3>\n" +
                "\t\t\t\t\t\t<item4 id=\"M0201010204\" cname=\"人流\" unit=\"次\" prompt=\"0,1,2,3,4,5\">0</item4>\n" +
                "\t\t\t\t\t</pregnantContent>\n" +
                "\t\t\t\t</content1>\n" +
                "\t\t\t\t<content2 id=\"M020102\">\n" +
                "\t\t\t\t\t<pregnantTime id=\"M02010201\" cname=\"孕次\">2</pregnantTime>\n" +
                "\t\t\t\t\t<pregnantContent id=\"M02010202\" cname=\"描述\" option=\"true\">\n" +
                "\t\t\t\t\t\t<item5 id=\"M0201020201\" cname=\"现有子女数\"/>\n" +
                "\t\t\t\t\t\t<item6 id=\"M0201020202\" cname=\"末次生(流)产时间\" prompt=\"yyyy.mm.dd\"/>\n" +
                "\t\t\t\t\t\t<item7 id=\"M0201020203\" cname=\"末次生(流)产内容\"/>\n" +
                "\t\t\t\t\t</pregnantContent>\n" +
                "\t\t\t\t</content2>\n" +
                "\t\t\t\t<content3 id=\"M020103\">\n" +
                "\t\t\t\t\t<pregnantTime id=\"M02010301\" cname=\"孕次\">3</pregnantTime>\n" +
                "\t\t\t\t\t<pregnantContent id=\"M02010302\" cname=\"描述\">\n" +
                "\t\t\t\t\t\t<item7 id=\"M0201030201\" cname=\"婴(胎)儿死亡原因\">无</item7>\n" +
                "\t\t\t\t\t</pregnantContent>\n" +
                "\t\t\t\t</content3>\n" +
                "\t\t\t\t<content4 id=\"M020104\">\n" +
                "\t\t\t\t\t<pregnantTime id=\"M02010401\" cname=\"孕次\">4</pregnantTime>\n" +
                "\t\t\t\t\t<pregnantContent id=\"M02010402\" cname=\"描述\">\n" +
                "\t\t\t\t\t\t<item8 id=\"M0201040201\" cname=\"出血史\">无</item8>\n" +
                "\t\t\t\t\t</pregnantContent>\n" +
                "\t\t\t\t</content4>\n" +
                "\t\t\t\t<content5 id=\"M020105\">\n" +
                "\t\t\t\t\t<pregnantTime id=\"M02010501\" cname=\"孕次\">5</pregnantTime>\n" +
                "\t\t\t\t\t<pregnantContent id=\"M02010502\" cname=\"描述\">\n" +
                "\t\t\t\t\t\t<item9 id=\"M0201050201\" cname=\"难产史及手术史\">无</item9>\n" +
                "\t\t\t\t\t</pregnantContent>\n" +
                "\t\t\t\t</content5>\n" +
                "\t\t\t\t<content6 id=\"M020106\">\n" +
                "\t\t\t\t\t<pregnantTime id=\"M02010601\" cname=\"孕次\">6</pregnantTime>\n" +
                "\t\t\t\t\t<pregnantContent id=\"M02010602\" cname=\"描述\" option=\"true\">\n" +
                "\t\t\t\t\t\t<item10 id=\"M0201060201\" cname=\"其他\">无</item10>\n" +
                "\t\t\t\t\t</pregnantContent>\n" +
                "\t\t\t\t</content6>\n" +
                "\t\t\t</pregnantHistory>\n" +
                "\t\t\t<pastHis id=\"M0204\" cname=\"既往史\">\n" +
                "\t\t\t\t<infectiousHis id=\"M020401\" cname=\"过去史\" selectmode=\"multi\" option=\"true\" prompt=\"无、心脏病、肺()、肾脏、肝炎、高血压、甲亢、糖尿病、手术、阑尾切除、胆囊切除、其他\">\n" +
                "\t\t\t\t\t<text id=\"T21\">无</text>\n" +
                "\t\t\t\t</infectiousHis>\n" +
                "\t\t\t\t<homeHis id=\"M020402\" cname=\"家族史\" selectmode=\"multi\" option=\"true\" prompt=\"高血压: 父 母 遗传病() 传染病() 无\">\n" +
                "\t\t\t\t\t<text id=\"T23\">无</text>\n" +
                "\t\t\t\t</homeHis>\n" +
                "\t\t\t\t<latelyHis id=\"M020406\" cname=\"近期史\" selectmode=\"multi\" option=\"true\" prompt=\"传肝 菌痢 出血或紫斑史 有 无\">\n" +
                "\t\t\t\t\t<text id=\"T24\">无</text>\n" +
                "\t\t\t\t</latelyHis>\n" +
                "\t\t\t\t<operateHis id=\"M020407\" cname=\"手术史\" option=\"true\">\n" +
                "\t\t\t\t\t<text id=\"T22\">无</text>\n" +
                "\t\t\t\t</operateHis>\n" +
                "\t\t\t\t<drugHyperHis id=\"M020403\" cname=\"过敏史\" selectmode=\"multi\" option=\"true\" prompt=\"无、青霉素(+)、头孢、磺胺\">\n" +
                "\t\t\t\t\t<text id=\"T25\">无</text>\n" +
                "\t\t\t\t</drugHyperHis>\n" +
                "\t\t\t\t<gestationCircs id=\"M0214\" cname=\"本次妊娠异常情况\">\n" +
                "\t\t\t\t\t<text id=\"T26\">无</text>\n" +
                "\t\t\t\t</gestationCircs>\n" +
                "\t\t\t\t<jwpgcsj id=\"M020408\" cname=\"既往剖宫产时间\" option=\"true\" prompt=\"yyyy.mm.dd\"/>\n" +
                "\t\t\t\t<jwpgcyy id=\"M020409\" cname=\"既往剖宫产医院\" option=\"true\"/>\n" +
                "\t\t\t\t<jwpgcyyjb id=\"M020410\" cname=\"既往剖宫产医院级别\" option=\"false\" prompt=\"无 III级专科 III级综合 II级专科 II级综合 外资 民营 其他\"/>\n" +
                "\t\t\t\t<jwpgczz id=\"M020411\" cname=\"既往剖宫产指征\" option=\"true\" prompt=\"\"/>\n" +
                "\t\t\t\t<jwpgcssfs id=\"M020412\" cname=\"既往剖宫产手术方式\" option=\"false\" prompt=\"无 子宫下端剖宫术 腹膜外剖宫术 子宫体剖宫术 其他\"/>\n" +
                "\t\t\t\t<jwpgccx id=\"M020413\" cname=\"既往剖宫产出血\" option=\"false\" prompt=\"无 有 不详\"/>\n" +
                "\t\t\t\t<jwpgcgr id=\"M020414\" cname=\"既往剖宫产感染\" option=\"false\" prompt=\"无 有 不详\"/>\n" +
                "\t\t\t</pastHis>\n" +
                "\t\t</completePR>\n" +
                "\t</uniBody>\n" +
                "</uniEPR>\n";
        System.out.println(XML.toJSONObject(xml, true).toString());

    }

}
