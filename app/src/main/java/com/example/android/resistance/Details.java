package com.example.android.resistance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        TextView details = (TextView) findViewById(R.id.detailsText);

        String nameofwarrior=           "name:-  " + getIntent().getStringExtra(DisplayWarriors.WARRIOR_NAME)
                               + "\n\n" + "affiliation:-  " + getIntent().getStringExtra(DisplayWarriors.WARRIOR_AFFILIATION)
                               + "\n\n" + "species:-  " + getIntent().getStringExtra(DisplayWarriors.WARRIOR_SPECIES)
                               + "\n\n" + "gender:-  " + getIntent().getStringExtra(DisplayWarriors.WARRIOR_GENDER)
                               + "\n\n" + "last known presence:-  " + getIntent().getStringExtra(DisplayWarriors.WARRIOR_LASTKNOWNPRESENCE)
                               + "\n\n" + "last spotted on:-  " + getIntent().getStringExtra(DisplayWarriors.WARRIOR_LASTSPOTTEDON);


       details.setText(nameofwarrior);



    }

    public void deleteWarrior(View view) {
        Context c=getApplicationContext();


        DatabaseHandler databaseHandler= new DatabaseHandler(c,null,null,1);
        Bundle extras = getIntent().getExtras();


        WarriorDetail warriorDetail=databaseHandler.getWarrior(extras.getInt(DisplayWarriors.WARRIOR_ID));

          databaseHandler.deleteWarrior(warriorDetail);

        Intent newIntent= new Intent(this, DisplayWarriors.class);
        startActivity(newIntent);

        Context context = getApplicationContext();
        CharSequence text = "successfully deleted warrior";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();



    }
}
