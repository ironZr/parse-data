package com.zr.parsedata.core.parse.constant;

public interface HtmlConstant {
    /**
     * 标题
     * {title}
     */
    String divTitle = "<h3> {title} </h3>";
    String divTitleFormat = "{title}";

    /**
     * 样式头修饰div
     */
    String divStyleHead = "<div class=\"flex\">";
    /**
     * 样式尾修饰div
     */
    String divStyleEnd = "</div>";

    /**
     * 数据div
     * {key}
     * {value}
     */
    String divBoby = "<div class=\"flexItem\">\n" +
            "            <div class=\"flexItemKey\">{key}</div>\n" +
            "            <div class=\"flexItemValue\">{value}</div>\n" +
            "</div>";
    String divBobyKeyFormat = "{key}";
    String divBobyValueFormat = "{value}";


    /**
     * html整体
     * {body}
     */
    String html = "<html>\n" +
            "\n" +
            "<head>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <title>{title}</title>\n" +
            "</head>\n" +
            "<style>\n" +
            "    .flex {\n" +
            "        display: flex;\n" +
            "        flex-direction: row;\n" +
            "        flex-wrap: wrap;\n" +
            "        width: 100%;\n" +
            "    }\n" +
            "    .flexItem {\n" +
            "        width: 33.3%;\n" +
            "        display: flex;\n" +
            "        box-sizing: border-box;\n" +
            "        padding: 1rem;\n" +
            "    }\n" +
            "    .flexItemKey {\n" +
            "        width: 88px;\n" +
            "        text-align: right;\n" +
            "    }\n" +
            "    .flexItemValue {\n" +
            "        width: 100%;\n" +
            "    }\n" +
            "</style>\n" +
            "<body>{body}</body>" +
            "</html>";

    String htmlTitleFormat = "{title}";
    String htmlBodyFormat = "{body}";
}
