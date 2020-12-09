package com.govee.fuyanedittext;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;

/**
 * Create by fuyan on 2020/12/9
 * $
 */
public class MyEdit extends AppCompatEditText {
    private long lastTime = 0;
    public MyEdit(@NonNull Context context) {
        this(context,null);
    }
    public MyEdit(Context context, AttributeSet attrs){
        this(context, attrs,0);
    }
    public MyEdit(Context context,AttributeSet attrs,int defStyleAttr){
        super(context, attrs, defStyleAttr);
        this.setBackground(null);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        this.setSelection(this.getText().length());
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTime < 500) {
                lastTime = currentTime;
                return true;
            } else {
                lastTime = currentTime;
            }
        }
        return super.onTouchEvent(event);
    }
}
