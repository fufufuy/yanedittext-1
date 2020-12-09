package com.govee.fuyanedittext;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.zhy.android.percent.support.PercentRelativeLayout;

/**
 * Create by fuyan on 2020/12/9
 * $
 */
public class ClearEditText extends PercentRelativeLayout {
    private MyEdit editText;
    private TextView[] textViews;
    private static int MAX = 4;
    private String inputText;
    public ClearEditText(Context context) {
        this(context,null);
    }
    public ClearEditText(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }
    public ClearEditText(Context context, AttributeSet attrs,int defStyleAttr){
        super(context, attrs,defStyleAttr);
        View.inflate(context,R.layout.view_clear_edittext,this);
        textViews = new TextView[MAX];
        textViews[0] = findViewById(R.id.tv_1);
        textViews[1] = findViewById(R.id.tv_2);
        textViews[2] = findViewById(R.id.tv_3);
        textViews[3] = findViewById(R.id.tv_4);
        editText = findViewById(R.id.edit_text);
        setEditTextListener();
    }

    private void setEditTextListener(){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            inputText = editText.getText().toString().trim();
            if (inputListener!=null){
                if (inputText.length()>=MAX){
                    inputListener.inputComplete();
                }else {
                    inputListener.inputContent();
                }
            }
            for (int i=0;i<MAX;i++){
                if (i<inputText.length()){
                    textViews[i].setText(String.valueOf(inputText.charAt(i)));
                    textViews[i].setCursorVisible(true);
                    editText.setSelection(i);
              }else {
                    textViews[i].setText("");
                }
            }
            }
        });
    }
    private InputListener inputListener;
    public interface InputListener{
        void inputComplete();
        void inputContent();
    }
    public void setInputListener(InputListener inputListener){
        this.inputListener = inputListener;
    }
    public void showInput(){
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        showInputMethod(editText);
    }
    public static void showInputMethod(View v) {
        if (v == null) return;
        InputMethodManager inputmanger = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputmanger == null) return;
        inputmanger.showSoftInput(v, 0);
    }
}
