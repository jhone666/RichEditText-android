package com.glacat.rich;

/**
 * Created by Administrator on 2018/6/29.
 */

public class SpaceNode implements Node {

    private int startIndex;
    public SpaceNode(){

    }

    public SpaceNode(int count){
        for (int i=0;i<count;i++){
            this.setText(this.getText()+"&nbsp;");
        }
    }

    private String text="&nbsp;";

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    @Override
    public String getHtmlText(){
        return text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
