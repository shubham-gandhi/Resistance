package com.example.android.resistance;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dell on 12/02/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    public static final String WARRIOR_NAME = "Warrior name";
    public static final String WARRIOR_ID = "Warrior id";
    public static final String WARRIOR_AFFILIATION = "Warrior affiliation";
    public static final String WARRIOR_SPECIES = "Warrior species";
    public static final String WARRIOR_GENDER = "Warrior gender";
    public static final String WARRIOR_LASTKNOWNPRESENCE = "Warrior last known presence";
    public static final String WARRIOR_LASTSPOTTEDON = "Warrior last spotted on";

    private LayoutInflater mInflater;
    private List<WarriorDetail> mData;
    private Context context;

    public RecyclerAdapter(Context context, List<WarriorDetail> Data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = Data;
        this.context= context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= mInflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        WarriorDetail currentObj= mData.get(position);
        holder.setData(currentObj, position);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title,description;
        ImageView photo,delete;
        int position;
        WarriorDetail current;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);



            title= (TextView) itemView.findViewById(R.id.txvTitle);
            description= (TextView) itemView.findViewById(R.id.txvDescription);
            delete= (ImageView) itemView.findViewById(R.id.item_delete);
            photo= (ImageView) itemView.findViewById(R.id.imgRow);


        }

        public void setData(WarriorDetail currentObj, int position) {

            this.title.setText(currentObj.get_name());
            this.description.setText(currentObj.get_affiliation());
            this.photo.setImageResource(R.drawable.androidparty);
            this.delete.setImageResource(R.drawable.ic_action_delete);
            this.current=currentObj;
            this.position=position;


        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(context,Details.class);

                WarriorDetail wariorDetails = mData.get(position);
                intent.putExtra(WARRIOR_ID, wariorDetails.get_id());
                intent.putExtra(WARRIOR_NAME, wariorDetails.get_name());
                intent.putExtra(WARRIOR_SPECIES, wariorDetails.get_species());
                intent.putExtra(WARRIOR_GENDER, wariorDetails.get_gender());
                intent.putExtra(WARRIOR_LASTKNOWNPRESENCE, wariorDetails.get_lastknownpresence());
                intent.putExtra(WARRIOR_AFFILIATION, wariorDetails.get_affiliation());
                intent.putExtra(WARRIOR_LASTSPOTTEDON, wariorDetails.get_lastspottedon());
            context.startActivity(intent);



        }
    }
}
