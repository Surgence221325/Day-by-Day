package model;


import org.json.JSONObject;

import java.time.*;

//A journalentry created with date, title, mood, and entry.
public class JournalEntry {
    private String date;
    private String title;
    private int mood;
    private String entry;

    //REQUIRES: mood be an integer input, date must be a date, other entries must be standard strings.
    //EFFECTS: creates a new JournalEntry with date, title, mood, and entry.
    public JournalEntry(String title, int mood, String entry) {
        this.date = LocalDate.now().toString();
        this.title = title;
        this.mood = mood;
        this.entry = entry;
    }


    //EFFECTS: returns mood for a journalentry.
    public int getMood() {
        return this.mood;
    }

    //EFFECTS: returns date for a journalentry.
    public String getDate() {
        return this.date;
    }

    //EFFECTS: returns title for a journalentry.
    public String getTitle() {
        return this.title;
    }

    //EFFECTS: returns entry for a JournalEntry.
    public String getEntry() {
        return this.entry;
    }

    //Returns journalentry as jsonobject.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", date);
        json.put("title", title);
        json.put("mood", mood);
        json.put("entry", entry);
        return json;
    }



}
