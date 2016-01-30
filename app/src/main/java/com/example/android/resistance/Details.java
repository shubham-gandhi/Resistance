package com.example.android.resistance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        TextView details = (TextView) findViewById(R.id.detailsText);

        String nameofwarrior = "name:-  " + getIntent().getStringExtra(DisplayWarriors.WARRIOR_NAME)
                + "\n\n" + "affiliation:-  " + getIntent().getStringExtra(DisplayWarriors.WARRIOR_AFFILIATION)
                + "\n\n" + "species:-  " + getIntent().getStringExtra(DisplayWarriors.WARRIOR_SPECIES)
                + "\n\n" + "gender:-  " + getIntent().getStringExtra(DisplayWarriors.WARRIOR_GENDER)
                + "\n\n" + "last known presence:-  " + getIntent().getStringExtra(DisplayWarriors.WARRIOR_LASTKNOWNPRESENCE)
                + "\n\n" + "last spotted on:-  " + getIntent().getStringExtra(DisplayWarriors.WARRIOR_LASTSPOTTEDON);


        details.setText(nameofwarrior);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {

            Context c = getApplicationContext();
            DatabaseHandler databaseHandler = new DatabaseHandler(c, null, null, 1);
            Bundle extras = getIntent().getExtras();

            //fetching ID passed as extras thus getting warriordetail to be deleted
            WarriorDetail warriorDetail = databaseHandler.getWarrior(extras.getInt(DisplayWarriors.WARRIOR_ID));
            databaseHandler.deleteWarrior(warriorDetail);

            //setting intent to Display Warrior Activity
            Intent newIntent = new Intent(this, DisplayWarriors.class);
            startActivity(newIntent);

            //toast
            Context context = getApplicationContext();
            CharSequence text = "successfully deleted warrior";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
