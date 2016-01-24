package com.example.android.resistance;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by manthan on 17/1/16.
 */
public class DisplayWarriorViewHolder extends RecyclerView.ViewHolder {

    TextView name;
    public DisplayWarriorViewHolder(View v){
        super(v);
        name=(TextView)v.findViewById(R.id.name);

    }
}
