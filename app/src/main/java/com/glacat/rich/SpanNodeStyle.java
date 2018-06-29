package com.glacat.rich;

/**
 * Created by Administrator on 2018/6/29.
 */

public class SpanNodeStyle {

    private String style="";

    public String getStyle() {
        return style;
    }

    public String setStyle(String style) {
        this.style += style;
        return this.style;
    }

    /**
     * 粗体
     */
    public String setFontBold(){
        return setStyle("font-weight:bold;");
    }
    /**
     * 斜体
     */
    public String setFontItalic(){
        return setStyle("font-spanNodeStyle:italic;");
    }
    /**
     * 删除线
     */
    public String setFontThrough(){
        return setStyle("text-decoration:line-through;");
    }

    /**
     * 下划线
     */
    public String setFontUnderline(){
        return setStyle("text-decoration:underline;");
    }

    /**
     * @param fontSize 字体大小
     * @return
     */
    public String setFontSize(float fontSize){
        return setStyle("font-size:"+fontSize+";");
    }

    /**
     * @param color 字体颜色
     * @return
     */
    public String setFontColor(String color){
        return setStyle("color:"+color+";");
    }

    /**
     * @param color 字背景颜色
     * @return
     */
    public String setFontBgColor(String color){
        return setStyle("background:"+color+";");
    }

}
