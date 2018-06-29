package com.glacat.rich;

/**
 * Created by Administrator on 2018/6/29.
 */

public class ImageNode implements Node {

    private String src="http://g.hiphotos.baidu.com/image/h%3D300/sign=fb8af6169d2397ddc9799e046983b216/0823dd54564e92584fbb491f9082d158cdbf4eb0.jpg";
    private float width=100;

    public ImageNode(String src,float size){
//        this.src=src;
        this.width=((float) 1/(float) size)*100-1;
    }

    @Override
    public String getHtmlText() {
        return "<img src=\""+this.src+"\" style=\"display:block;float:left;margin-left:4px;width:"+width+"%;\"/>";
    }
}
