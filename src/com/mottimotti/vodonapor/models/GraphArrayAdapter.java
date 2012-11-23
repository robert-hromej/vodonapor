package com.mottimotti.vodonapor.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import com.mottimotti.vodonapor.R;
import com.mottimotti.vodonapor.views.Graph;

public class GraphArrayAdapter extends ArrayAdapter<Graph> {

    public GraphArrayAdapter(Context context, Graph[] items) {
        super(context, R.layout._row, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout rowView = (LinearLayout) inflater.inflate(R.layout._row, parent, false);

        rowView.addView(getItem(position));

        return rowView;
    }
}
