package com.example.android.resistance;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;


public class AddWarrior extends ActionBarActivity {

    String affiliation, species = "Human", gender, lastKnownPresense = "Alderaan", date;
    Button btn;
    int year_x;
    int month_x;
    int day_x;
    static final int dialog_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_warrior);


        final Calendar cal = Calendar.getInstance();

        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        date = day_x + "/" + month_x + "/" + year_x;
        showDialogOnButtonClicked();
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_add_warrior, menu);
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

    public void showDialogOnButtonClicked() {
        btn = (Button) findViewById(R.id.button_date_picker);

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(dialog_id);

                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == dialog_id)
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);

        return null;

    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;

            date = day_x + "/" + month_x + "/" + month_x;


        }
    };


    //for taking responce of gender radio button
    public void onGenderRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked

        switch (view.getId()) {
            case R.id.male_radio_button:
                if (checked)
                    gender = "male";
                break;
            case R.id.female_radio_button:
                if (checked)
                    gender = "female";

                break;
            case R.id.machine_radio_button:
                if (checked)
                    gender = "machine";

                break;
        }
    }

    //for taking responce of affiliation radio button
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked


        switch (view.getId()) {
            case R.id.lightside_radio_button:
                if (checked)
                    affiliation = "light side";
                break;
            case R.id.darkside_radio_button:
                if (checked)
                    affiliation = "dark side";

                break;
        }
    }


    public void added(View view) {


        //fetching a name string
        EditText nameEntered = (EditText) findViewById(R.id.name_description_view);
        Editable fullName = nameEntered.getText();
        String name = fullName.toString();

        Spinner spinner = (Spinner) findViewById(R.id.spinner_species);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.species, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                species = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner_lastknownpresence);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.planets, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                lastKnownPresense = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //toast message for successful warrior creation
        Context context = getApplicationContext();
        CharSequence text = "successfully added new warrior";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        WariorDetail warrior = new WariorDetail(name, affiliation, species, gender, lastKnownPresense, date);
        DatabaseHandler dbh = new DatabaseHandler(context, null, null, 1);
        dbh.addWarior(warrior);

        //Intent leading to main screen
        Intent addedWarrior = new Intent(this, MainActivity.class);
        startActivity(addedWarrior);
        finish();


    }


}
