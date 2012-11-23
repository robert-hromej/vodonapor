package com.mottimotti.vodonapor.controllers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.mottimotti.vodonapor.R;
import com.mottimotti.vodonapor.models.GraphArrayAdapter;
import com.mottimotti.vodonapor.views.Graph;
import com.mottimotti.vodonapor.views.GraphParams;
import com.mottimotti.vodonapor.views.GraphType;

public class RightPanel extends LinearLayout implements View.OnClickListener, AdapterView.OnItemLongClickListener {

    private final ListView listView;

    private final Graph[] graphs;

    private Listener listener;

    public RightPanel(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) inflater.inflate(R.layout.right_panel, this);

        listView = (ListView) findViewById(R.id.objectList);

        graphs = new Graph[]{
                new Graph(context, new GraphParams(), GraphType.BlueTriangle),
                new Graph(context, new GraphParams(), GraphType.YellowRect),
                new Graph(context, new GraphParams(), GraphType.GreenCircle)};

        GraphArrayAdapter adapter = new GraphArrayAdapter(context, graphs);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(this);

        findViewById(R.id.openCloseBtn).setOnClickListener(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        listener.onAdd(graphs[i].copy());

        return true;
    }

    @Override
    public void onClick(View view) {
        listView.setVisibility((listView.getVisibility() == VISIBLE) ? GONE : VISIBLE);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public static interface Listener {
        public void onAdd(Graph graph);
    }

}
