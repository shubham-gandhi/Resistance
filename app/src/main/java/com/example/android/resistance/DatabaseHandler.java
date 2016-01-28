package com.example.android.resistance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 14/01/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "WarriorManager.db";

    // Warrior table name
    private static final String TABLE_Warrior = "Warrior";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AFFILIATION = "affiliation";
    private static final String KEY_SPECIES = "species";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_LAST_KNOWN_PRESENCE_ = "LastKnownPresence";
    private static final String KEY_LAST_SPOTTED_ON = "LastSpottedOn";


    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);


    }

    // creating table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_Warrior_TABLE = "CREATE TABLE " + TABLE_Warrior + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_AFFILIATION + " TEXT," + KEY_SPECIES + " TEXT,"
                + KEY_GENDER + " TEXT," + KEY_LAST_KNOWN_PRESENCE_ + " TEXT,"
                + KEY_LAST_SPOTTED_ON + " TEXT" + ")";
        db.execSQL(CREATE_Warrior_TABLE);

    }


    //upgrading table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Warrior);

        // Create tables again
        onCreate(db);

    }

    // Adding new warrior
    public void addWarior(WarriorDetail warrior) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, warrior.get_name()); // Warrior Name
        values.put(KEY_AFFILIATION, warrior.get_affiliation());
        values.put(KEY_SPECIES, warrior.get_species());
        values.put(KEY_GENDER, warrior.get_gender());
        values.put(KEY_LAST_KNOWN_PRESENCE_, warrior.get_lastknownpresence());
        values.put(KEY_LAST_SPOTTED_ON, warrior.get_lastspottedon());

        // Inserting Row
        db.insert(TABLE_Warrior, null, values);
        Log.e("Database entry added", values.toString());
        db.close(); // Closing database connection
    }

    //Getting single warrior
    public WarriorDetail getWarrior(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_Warrior, new String[]{KEY_ID,
                        KEY_NAME, KEY_SPECIES, KEY_AFFILIATION, KEY_GENDER, KEY_LAST_KNOWN_PRESENCE_, KEY_LAST_SPOTTED_ON}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        WarriorDetail warrior = new WarriorDetail(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));


        // return warrior
        return warrior;
    }

    // Getting All warriors
    public List<WarriorDetail> getAllWarriors() {
        List<WarriorDetail> warriorList = new ArrayList<WarriorDetail>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Warrior;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                WarriorDetail warrior = new WarriorDetail();
                warrior.set_id(Integer.parseInt(cursor.getString(0)));
                warrior.set_name(cursor.getString(1));
                warrior.set_affiliation(cursor.getString(2));
                warrior.set_species(cursor.getString(3));
                warrior.set_gender(cursor.getString(4));
                warrior.set_lastknownpresence(cursor.getString(5));
                warrior.set_lastspottedon(cursor.getString(6));
                // Adding contact to list
                warriorList.add(warrior);
            } while (cursor.moveToNext());
        }

        // return warrior list
        return warriorList;
    }

    // Getting warrior Count
    public int getWarriorCount() {
        String countQuery = "SELECT  * FROM " + TABLE_Warrior;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Updating single Warrior
    public int updateWarrior(WarriorDetail warrior) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, warrior.get_name());
        values.put(KEY_AFFILIATION, warrior.get_affiliation());
        values.put(KEY_SPECIES, warrior.get_species());
        values.put(KEY_GENDER, warrior.get_gender());
        values.put(KEY_LAST_KNOWN_PRESENCE_, warrior.get_lastknownpresence());
        values.put(KEY_LAST_SPOTTED_ON, warrior.get_lastspottedon());

        // updating row
        return db.update(TABLE_Warrior, values, KEY_ID + " = ?",
                new String[]{String.valueOf(warrior.get_id())});
    }

    // Deleting single warrior

    public void deleteWarrior(WarriorDetail warrior) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Warrior, KEY_ID + " = ?",
                new String[]{String.valueOf(warrior.get_id())});
        db.close();
    }
}
