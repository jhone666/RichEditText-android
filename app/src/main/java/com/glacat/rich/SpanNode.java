package com.glacat.rich;

/**
 * Created by Administrator on 2018/6/29.
 */

public class SpanNode implements Node{

    private String text;
    private SpanNodeStyle spanNodeStyle;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SpanNodeStyle getSpanNodeStyle() {
        return spanNodeStyle;
    }

    public void setSpanNodeStyle(SpanNodeStyle spanNodeStyle) {
        this.spanNodeStyle = spanNodeStyle;
    }

    @Override
    public String getHtmlText() {
        return "<span style=\""+getSpanNodeStyle().getStyle()+"\">"+getText()+"</span>";
    }
}
