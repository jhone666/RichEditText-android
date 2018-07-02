package com.glacat.rich;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Administrator on 2018/6/29.
 */

public class SpanNodeStyle {

    //去重
    private HashMap<String,String> mClass=new HashMap<>();

    public String getStyle() {
        String style="";
        Iterator<String> iterator = mClass.values().iterator();
        while (iterator.hasNext()){
            style+=iterator.next();
        }
        return style;
    }

    /**
     * 粗体
     */
    public void setFontBold(){
        mClass.put("font_bold","font-weight:bold;");
    }
    /**
     * 斜体
     */
    public void setFontItalic(){
        mClass.put("font_italic","font-style:italic;");
    }
    /**
     * 删除线
     */
    public void setFontThrough(){
        mClass.put("font_through","text-decoration:line-through;");
    }

    /**
     * 下划线
     */
    public void setFontUnderline(){
        mClass.put("under_line","text-decoration:underline;");
    }

    /**
     * @param fontSize 字体大小
     * @return
     */
    public void setFontSize(float fontSize){
        mClass.put("font_size","font-size:"+fontSize+";");
    }

    /**
     * @param color 字体颜色
     * @return
     */
    public void setFontColor(String color){
        mClass.put("font_color","color:"+color+";");
    }

    /**
     * @param color 字背景颜色
     * @return
     */
    public void setFontBgColor(String color){
        mClass.put("font_bgcolor","background:"+color+";");
    }

}
