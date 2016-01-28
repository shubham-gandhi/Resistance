package com.example.android.resistance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


public class DisplayWarriors extends ActionBarActivity {
    public static final String WARRIOR_NAME = "Warrior name";
    public static final String WARRIOR_ID= "Warrior id";
    public static final String WARRIOR_AFFILIATION = "Warrior affiliation";
    public static final String WARRIOR_SPECIES = "Warrior species";
    public static final String WARRIOR_GENDER = "Warrior gender";
    public static final String WARRIOR_LASTKNOWNPRESENCE = "Warrior last known presence";
    public static final String WARRIOR_LASTSPOTTEDON = "Warrior last spotted on";
    public static final WarriorDetail WARRIOR_DETAIL= new WarriorDetail();
    List<WarriorDetail> info;




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_warriors);
        final Intent intent= new Intent(this,Details.class);

        Context context = getApplicationContext();
        final DatabaseHandler databaseHandler = new DatabaseHandler(context, null, null, 1);
        info = databaseHandler.getAllWarriors();

        ArrayAdapter<WarriorDetail> warriorArrayAdapter =
                new ArrayAdapter<WarriorDetail>(this, android.R.layout.simple_list_item_1,info);

        ListView listView= (ListView) findViewById(android.R.id.list);



        listView.setAdapter(warriorArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                 WarriorDetail wariorDetails= info.get(position);
                intent.putExtra(WARRIOR_ID,wariorDetails.get_id());
                intent.putExtra(WARRIOR_NAME,wariorDetails.get_name());
                intent.putExtra(WARRIOR_SPECIES,wariorDetails.get_species());
                intent.putExtra(WARRIOR_GENDER,wariorDetails.get_gender());
                intent.putExtra(WARRIOR_LASTKNOWNPRESENCE,wariorDetails.get_lastknownpresence());
                intent.putExtra(WARRIOR_AFFILIATION,wariorDetails.get_affiliation());
                intent.putExtra(WARRIOR_LASTSPOTTEDON,wariorDetails.get_lastspottedon());


                startActivity(intent);
            }
        });

//        Iterator<WariorDetail> iterator= info.iterator();
//
//        StringBuilder sb= new StringBuilder();
//        while (iterator.hasNext()){
//
//            WariorDetail wariorDetail= iterator.next();
//            sb.append(wariorDetail.get_name());
//            sb.append("\n");
//            TextView tv= (TextView) findViewById(R.id.list);
//            tv.setText(sb.toString());
//
//        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_display_warriors, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    public void backToMainscreen(View view) {
//        Intent back =new Intent(this , MainActivity.class);
//        startActivity(back);
//        finish();
//    }

    public void addfloating(View view) {
        Intent add =new Intent(this , AddWarrior.class);
        startActivity(add);
        finish();

    }
}
