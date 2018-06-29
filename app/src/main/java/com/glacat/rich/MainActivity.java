package com.glacat.rich;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.api.widget.Widget;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTt;

    private TextView mPreview;


    private ArrayList<String> styleList;
    private ArrayList<Node> nodes;
    private final String STYLE_BOLD = "STYLE_BOLD";
    private final String STYLE_ITALIC = "STYLE_ITALIC";
    private final String STYLE_STRICHLINE = "STYLE_STRICHLINE";
    private final String STYLE_UNDERLINE = "STYLE_UNDERLINE";
    private final String STYLE_FONTSIZE = "STYLE_FONTSIZE";
    private final String STYLE_FONTCOLOR = "STYLE_FONTCOLOR";
    private final String STYLE_FONTBGCOLOR = "STYLE_FONTBGCOLOR";

    private int styleStart;
    private int styleFontSize = 13;
    private String styleFontColor = "#06be6a";
    private String styleFontBgColor = "#ff2323";

    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Album.initialize(AlbumConfig.newBuilder(this)
                .setAlbumLoader(new MediaLoader())
                .build());


        mPreview = findViewById(R.id.prew);
        webView = findViewById(R.id.webView);

        webView.getSettings().setDefaultTextEncodingName("UTF-8");//设置默认为utf-8


        styleList = new ArrayList<>();
        nodes = new ArrayList<>();
        initEditText();

    }

    private void initEditText() {
        editTt = findViewById(R.id.editTt);
        editTt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                styleStart = start;
                if (charSequence==null||charSequence==""){
                    nodes.clear();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().substring(styleStart,editable.length()).contains("&img")){
                    SpanNode spanNode = new SpanNode();
                    SpanNodeStyle spanNodeStyle = new SpanNodeStyle();
                    spanNode.setSpanNodeStyle(spanNodeStyle);

                    if (styleList.contains(STYLE_BOLD)) {
                        editable.setSpan(new StyleSpan(Typeface.BOLD), styleStart, editable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spanNode.getSpanNodeStyle().setFontBold();
                    }
                    if (styleList.contains(STYLE_ITALIC)) {
                        editable.setSpan(new StyleSpan(Typeface.ITALIC), styleStart, editable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spanNode.getSpanNodeStyle().setFontItalic();
                    }
                    if (styleList.contains(STYLE_STRICHLINE)) {
                        editable.setSpan(new StrikethroughSpan(), styleStart, editable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spanNode.getSpanNodeStyle().setFontThrough();
                    }
                    if (styleList.contains(STYLE_UNDERLINE)) {
                        editable.setSpan(new UnderlineSpan(), styleStart, editable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spanNode.getSpanNodeStyle().setFontUnderline();
                    }
                    if (styleList.contains(STYLE_FONTSIZE)) {
                        editable.setSpan(new AbsoluteSizeSpan(spToPx(styleFontSize)), styleStart, editable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spanNode.getSpanNodeStyle().setFontSize(styleFontSize);
                    }
                    if (styleList.contains(STYLE_FONTCOLOR)) {
                        editable.setSpan(new ForegroundColorSpan(Color.parseColor(styleFontColor)), styleStart, editable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spanNode.getSpanNodeStyle().setFontColor(styleFontColor);
                    }

                    if (styleList.contains(STYLE_FONTBGCOLOR)) {
                        editable.setSpan(new BackgroundColorSpan(Color.parseColor(styleFontColor)), styleStart, editable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spanNode.getSpanNodeStyle().setFontBgColor(styleFontBgColor);
                    }
                    spanNode.setText(editable.toString().substring(styleStart,editable.length()));
                    nodes.add(spanNode);

                }
            }
        });

        editTt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    nodes.add(new BrNode());
                    return false;
                }
                return false;
            }
        });
    }


    private StyleSpan mStyleSpan;

    public int spToPx(float spValue) {
        float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public void bold(View view) {
        if (((CheckBox) view).isChecked()) {
            styleList.add(STYLE_BOLD);
        } else {
            styleList.remove(STYLE_BOLD);
        }
    }

    public void italic(View view) {
        if (((CheckBox) view).isChecked()) {
            styleList.add(STYLE_ITALIC);
        } else {
            styleList.remove(STYLE_ITALIC);
        }
    }

    public void strichLine(View view) {
        if (((CheckBox) view).isChecked()) {
            styleList.add(STYLE_STRICHLINE);
        } else {
            styleList.remove(STYLE_STRICHLINE);
        }
    }

    public void underLine(View view) {
        if (((CheckBox) view).isChecked()) {
            styleList.add(STYLE_UNDERLINE);
        } else {
            styleList.remove(STYLE_UNDERLINE);
        }
    }

    public void changeColor(View view) {
        if (((CheckBox) view).isChecked()) {
            styleList.add(STYLE_FONTCOLOR);
        } else {
            styleList.remove(STYLE_FONTCOLOR);
        }
    }


    public void head0(View view) {
        styleFontSize = 13;
        if (((RadioButton) view).isChecked()) {
            styleList.remove(STYLE_FONTSIZE);
        }
    }

    public void head1(View view) {
//        styleFontSize=24;
//        if (((RadioButton)view).isChecked()&&!styleList.contains(STYLE_FONTSIZE)){
//            styleList.add(STYLE_FONTSIZE);
//        }

        String html="";
        for (Node node :nodes){
            html+=node.getHtmlText();
        }

        mPreview.setText(html);
        webView.loadData(html, "text/html; charset=UTF-8", null);//这种写法可以正确解码

    }

    public void head2(View view) {
        styleFontSize = 20;
        if (((RadioButton) view).isChecked() && !styleList.contains(STYLE_FONTSIZE)) {
            styleList.add(STYLE_FONTSIZE);
        }
    }

    public void head3(View view) {
        styleFontSize = 16;
        if (((RadioButton) view).isChecked() && !styleList.contains(STYLE_FONTSIZE)) {
            styleList.add(STYLE_FONTSIZE);
        }
    }

    private ArrayList<AlbumFile> imgList = new ArrayList<>();

    public void insetImg(View view) {
        imgList.clear();
        Album.image(this)
                .multipleChoice()
                .camera(true)
                .columnCount(3)
                .selectCount(3)
                .widget(Widget
                        .newLightBuilder(this)
                        .title("选择图片")
                        .statusBarColor(Color.parseColor("#ffffff"))
                        .navigationBarColor(Color.parseColor("#ffffff"))
                        .toolBarColor(Color.parseColor("#ffffff"))
                        .build())
                .checkedList(imgList)
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        imgList.addAll(result);
                        appendImg();
                    }
                })
                .start();
    }

    String space = " ";

    //先选择图片上传，再选择上传的图片嵌入
    private void appendImg() {
        editTt.getText().append("\n");
        nodes.add(new BrNode());
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(spToPx(styleFontSize));
        float spaceTotal = (paint.measureText(space, 0, space.length())) * (imgList.size() - 1);
        int width = (int) ((editTt.getWidth() - editTt.getPaddingLeft() - editTt.getPaddingRight() - spaceTotal) /imgList.size())-10;
        for (int i = 0; i < imgList.size(); i++) {
            Drawable d = Drawable.createFromPath(imgList.get(i).getPath());
            float rate = (float) d.getIntrinsicWidth() / (float) d.getIntrinsicHeight();
            int height = (int) ((float) width / (float) rate);
            d.setBounds(0, 0, width, height);
            editTt.getText().append("&img");
            editTt.getText().setSpan(new ImageSpan(d), styleStart, editTt.getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            nodes.add(new ImageNode(imgList.get(i).getPath(),imgList.size()));
            if (imgList.size() > 1 && i != imgList.size() - 1) {
                editTt.getText().append(space);
//                nodes.add(new SpaceNode());
            }
        }
        editTt.getText().append("\n");
        nodes.add(new BrNode());
        editTt.setSelection(editTt.getText().length());
    }

}
