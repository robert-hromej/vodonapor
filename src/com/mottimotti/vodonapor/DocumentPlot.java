package com.mottimotti.vodonapor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.mottimotti.vodonapor.GraphObject.GraphObject;

import java.util.List;

public class DocumentPlot extends RelativeLayout {

    private List<View> children;

    public DocumentPlot(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
