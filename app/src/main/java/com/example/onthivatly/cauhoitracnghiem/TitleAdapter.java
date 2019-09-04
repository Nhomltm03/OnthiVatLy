package com.example.onthivatly.cauhoitracnghiem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onthivatly.R;

import java.util.List;


public class TitleAdapter extends ArrayAdapter<ListTitle> {

    public TitleAdapter(Context context, int resource, List<ListTitle> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.list_title, null);
        }
        ListTitle p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txtTitle = (TextView) view.findViewById(R.id.textviewtitle);
            txtTitle.setText(p.title);

            ImageView imageView = view.findViewById(R.id.imagetitle);



        }
        return view;
    }

}