package com.example.android.resistance;

import java.util.Date;

/**
 * Created by dell on 14/01/2016.
 */
public class WariorDetail {

    int _id;
    String _name;
    String _affiliation;
    String _species;
    String _gender;
    String _lastknownpresence;
    String _lastspottedon;

    public WariorDetail(int _id, String _name, String _affiliation, String _species, String _gender, String _lastknownpresence, String _lastspottedon) {
        this._id = _id;
        this._name = _name;
        this._affiliation = _affiliation;
        this._species = _species;
        this._gender = _gender;
        this._lastknownpresence = _lastknownpresence;
        this._lastspottedon = _lastspottedon;

    }

    public WariorDetail() {
    }

    public WariorDetail(String _name, String _affiliation, String _species, String _gender, String _lastknownpresence, String _lastspottedon) {
        this._name = _name;
        this._affiliation = _affiliation;
        this._species = _species;
        this._gender = _gender;
        this._lastknownpresence = _lastknownpresence;
        this._lastspottedon = _lastspottedon;
    }

    public int get_id() {

        return _id;
    }

    public void set_id(int _id) {

        this._id = _id;
    }

    public String get_name() {

        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_affiliation() {
        return _affiliation;
    }

    public void set_affiliation(String _affiliation) {
        this._affiliation = _affiliation;
    }


    public String get_species() {
        return _species;
    }

    public void set_species(String _species) {
        this._species = _species;
    }

    public String get_gender() {
        return _gender;
    }

    public void set_gender(String _gender) {
        this._gender = _gender;
    }

    public String get_lastknownpresence() {
        return _lastknownpresence;
    }

    public void set_lastknownpresence(String _lastknownpresence) {
        this._lastknownpresence = _lastknownpresence;
    }

    public String get_lastspottedon() {
        return _lastspottedon;
    }

    public void set_lastspottedon(String _lastspottedon) {

        this._lastspottedon = _lastspottedon;
    }
}
