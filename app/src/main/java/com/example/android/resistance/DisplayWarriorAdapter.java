package com.example.android.resistance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by manthan on 17/1/16.
 */
public class DisplayWarriorAdapter extends RecyclerView.Adapter<DisplayWarriorViewHolder> {

    DatabaseHandler dbh;
    Context c;
    public DisplayWarriorAdapter(Context context){
        c=context;
    }
    @Override
    public DisplayWarriorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        dbh=new DatabaseHandler(c,null,null,1);
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new DisplayWarriorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DisplayWarriorViewHolder holder, int position) {
        holder.name.setText(dbh.getAllWarriors().get(position).get_name());
        Log.e("Display",dbh.getAllWarriors().get(position).get_name());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
