package com.junakiakter.jsonlistviewprc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

class CustomAdapter extends BaseAdapter {
    Context applicationContext;
    int sample;
    List<emplydemo> s;
    LayoutInflater inflater;


    public CustomAdapter(Context applicationContext, int sample, List<emplydemo> s) {
        this.applicationContext=applicationContext;
        this.sample=sample;
        this.s=s;

    }

    @Override
    public int getCount() {
        return s.size();
    }

    @Override
    public Object getItem(int position) {
        return s.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            inflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sample, parent, false);

        }

        TextView name, age, posi;
        name = convertView.findViewById(R.id.ageId);
        age = convertView.findViewById(R.id.ageId);
        posi = convertView.findViewById(R.id.positionId);

        name.setText(s.get(position).getName());
        age.setText(s.get(position).getAge());
        posi.setText(s.get(position).getPosition());
        return convertView;

    }

    }

