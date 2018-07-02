package com.glacat.rich;

/**
 * Created by Administrator on 2018/6/29.
 */

public class SpanNode implements Node{

    private int startIndex;
    private String text;
    private SpanNodeStyle spanNodeStyle;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SpanNodeStyle getSpanNodeStyle() {
        if (this.spanNodeStyle==null){
            this.spanNodeStyle=new SpanNodeStyle();
        }
        return spanNodeStyle;
    }

    public void setSpanNodeStyle(SpanNodeStyle spanNodeStyle) {
        this.spanNodeStyle = spanNodeStyle;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    @Override
    public String getHtmlText() {
        return "<span style=\""+getSpanNodeStyle().getStyle()+"\">"+getText()+"</span>";
    }

}
