package com.mottimotti.vodonapor.controllers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mottimotti.vodonapor.GraphObject.GraphObject;
import com.mottimotti.vodonapor.R;

public class Header extends LinearLayout {

    public Header(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) inflater.inflate(R.layout.header, this);
    }
}
