package com.example.mydairy.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydairy.R;

public class GraphAdapter extends ArrayAdapter {

    private String[] Title_Graph;
    private Integer[] Image_Graph;
    private Activity context;

    public GraphAdapter(Activity context, String[] title_Graph, Integer[] image_Graph) {
        super(context, R.layout.row_graph_item, title_Graph);
        this.context = context;
        this.Title_Graph = title_Graph;
        this.Image_Graph = image_Graph;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.row_graph_item, null, true);
        TextView GraphTitle = (TextView) row.findViewById(R.id.list_graphtxt);
        ImageView GraphImage = (ImageView) row.findViewById(R.id.list_graphimg);

        GraphTitle.setText(Title_Graph[position]);
        GraphImage.setImageResource(Image_Graph[position]);
        return  row;
    }
}
