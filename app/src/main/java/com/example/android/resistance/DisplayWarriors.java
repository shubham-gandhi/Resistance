package com.example.android.resistance;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class DisplayWarriors extends AppCompatActivity {
    public static final String WARRIOR_NAME = "Warrior name";
    public static final String WARRIOR_ID = "Warrior id";
    public static final String WARRIOR_AFFILIATION = "Warrior affiliation";
    public static final String WARRIOR_SPECIES = "Warrior species";
    public static final String WARRIOR_GENDER = "Warrior gender";
    public static final String WARRIOR_LASTKNOWNPRESENCE = "Warrior last known presence";
    public static final String WARRIOR_LASTSPOTTEDON = "Warrior last spotted on";
    public static final WarriorDetail WARRIOR_DETAIL = new WarriorDetail();
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
        final Intent intent = new Intent(this, Details.class);

        Context context = getApplicationContext();
        final DatabaseHandler databaseHandler = new DatabaseHandler(context, null, null, 1);
        info = databaseHandler.getAllWarriors();


//        Intent i1= new Intent(this, DisplayWarriors.class);
//        startActivity(i1);


        ArrayAdapter<WarriorDetail> warriorArrayAdapter =
                new ArrayAdapter<WarriorDetail>(this, android.R.layout.simple_list_item_1, info);

        ListView listView = (ListView) findViewById(android.R.id.list);


        listView.setAdapter(warriorArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                WarriorDetail wariorDetails = info.get(position);
                intent.putExtra(WARRIOR_ID, wariorDetails.get_id());
                intent.putExtra(WARRIOR_NAME, wariorDetails.get_name());
                intent.putExtra(WARRIOR_SPECIES, wariorDetails.get_species());
                intent.putExtra(WARRIOR_GENDER, wariorDetails.get_gender());
                intent.putExtra(WARRIOR_LASTKNOWNPRESENCE, wariorDetails.get_lastknownpresence());
                intent.putExtra(WARRIOR_AFFILIATION, wariorDetails.get_affiliation());
                intent.putExtra(WARRIOR_LASTSPOTTEDON, wariorDetails.get_lastspottedon());


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_warriors, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_deleteAll) {

//        finish();
            deleteAllWarriors();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllWarriors() {
        DialogInterface.OnClickListener dialogClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int button) {
                        if (button == DialogInterface.BUTTON_POSITIVE) {
                            //Insert Data management code here
                            DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext(), null, null, 1);
                            databaseHandler.deleteAllWarriors();

                            Toast.makeText(DisplayWarriors.this,
                                    getString(R.string.all_deleted),
                                    Toast.LENGTH_SHORT).show();

                            //Activity refresh
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);


                        }
                    }
                };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.are_you_sure))
                .setPositiveButton(getString(android.R.string.yes), dialogClickListener)
                .setNegativeButton(getString(android.R.string.no), dialogClickListener)
                .show();

    }

//    public void backToMainscreen(View view) {
//        Intent back =new Intent(this , MainActivity.class);
//        startActivity(back);
//        finish();
//    }

    public void addfloating(View view) {
        Intent add = new Intent(this, AddWarrior.class);
        startActivity(add);
        finish();

    }
}
