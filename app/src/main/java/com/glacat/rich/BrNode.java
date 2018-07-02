package com.glacat.rich;

/**
 * Created by Administrator on 2018/6/29.
 */

public class BrNode implements Node{

    private int startIndex;

    public BrNode(){}

    public BrNode(int count){
        for (int i=0;i<count;i++){
            this.setText(this.getText()+"<br />");
        }
    }

    private String text="<br />";



    private void setText(String text) {
        this.text = text;
    }

    private String getText() {
        return text;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    @Override
    public String getHtmlText() {
        return text;
    }
}
