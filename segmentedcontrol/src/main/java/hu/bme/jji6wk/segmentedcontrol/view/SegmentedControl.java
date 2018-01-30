package hu.bme.jji6wk.segmentedcontrol.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import hu.bme.jji6wk.segmentedcontrol.R;

/**
 * Created by peti on 2018. 01. 30..
 */

public class SegmentedControl extends LinearLayout {
    private int mSegmentCount;
    private List<SegmentedButton> buttons;

    public SegmentedControl(Context context) {
        super(context);
        init();
    }

    public SegmentedControl(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        attrsSet(context, attrs);
    }

    public SegmentedControl(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        attrsSet(context, attrs);
    }

    private void init() {
        buttons = new ArrayList<>();

        this.setGravity(Gravity.CENTER | Gravity.TOP);

        for (int i = 0; i < this.getChildCount(); i++) {
            SegmentedButton segmentedButton;
            segmentedButton = (SegmentedButton) this.getChildAt(i);
            segmentedButton.init();
            if (i == 0) {
                segmentedButton.setPosition(SegmentedButton.Position.left);
            }
            else if(i == this.getChildCount() - 1) {
                segmentedButton.setPosition(SegmentedButton.Position.right);
            }
            else{
                segmentedButton.setPosition(SegmentedButton.Position.middle);
            }
            /*
            segmentedButton.setText("Back");
            segmentedButton.setLayoutParams(new LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
                    */
            buttons.add(segmentedButton);
        }
        buttons.get(0).setSelected(true);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    protected  void setOthersUnselected(SegmentedButton activeButton){
        for(SegmentedButton segmentedButton : buttons){
            if(activeButton != segmentedButton){
                segmentedButton.setSelected(false);
            }
        }
    }

    private void attrsSet(Context context, @Nullable AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SegmentedControl,
                0, 0);

        try {
            mSegmentCount = a.getInt(R.styleable.SegmentedControl_segmentCount, 2);
        } finally {
            a.recycle();
        }
    }
}
