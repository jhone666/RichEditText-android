package com.glacat.rich;

/**
 * Created by Administrator on 2018/6/29.
 */

public class SpaceNode implements Node {

    public SpaceNode(){

    }

    public SpaceNode(int count){
        for (int i=0;i<count;i++){
            this.setText(this.getText()+"&nbsp;");
        }
    }

    private String text="&nbsp;";

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
