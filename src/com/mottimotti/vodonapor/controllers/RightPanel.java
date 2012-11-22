package com.mottimotti.vodonapor.controllers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.mottimotti.vodonapor.GraphObject.GraphObject;
import com.mottimotti.vodonapor.GraphObject.GraphParams;
import com.mottimotti.vodonapor.GraphObject.GraphType;
import com.mottimotti.vodonapor.R;
import com.mottimotti.vodonapor.models.GraphObjectAdapter;

public class RightPanel extends LinearLayout implements View.OnClickListener, AdapterView.OnItemLongClickListener {

    private ListView listView;

    private GraphObject[] objects;

    private OnAddListener onAddListener;

    public RightPanel(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) inflater.inflate(R.layout.right_panel, this);

        listView = (ListView) findViewById(R.id.objectList);

        objects = new GraphObject[]{
                new GraphObject(context, new GraphParams(0, 0, 100, 100), GraphType.BlueTriangle),
                new GraphObject(context, new GraphParams(0, 0, 100, 100), GraphType.YellowRect),
                new GraphObject(context, new GraphParams(0, 0, 100, 100), GraphType.GreenCircle)};

        GraphObjectAdapter adapter = new GraphObjectAdapter(context, objects);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(this);

        findViewById(R.id.openCloseBtn).setOnClickListener(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        onAddListener.onAdd(objects[i].copy());

        return true;
    }

    @Override
    public void onClick(View view) {
        listView.setVisibility((listView.getVisibility() == VISIBLE) ? GONE : VISIBLE);
    }

    public void setOnAddListener(OnAddListener onAddListener) {
        this.onAddListener = onAddListener;
    }

    public static interface OnAddListener {
        public void onAdd(GraphObject graphObject);
    }

}
