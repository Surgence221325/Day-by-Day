package model;


import java.util.Date;

public class JournalEntry {
    private Date date;
    private int mood;
    private String title;
    private String entry;

    //REQUIRES: mood be an integer input, date must be a date.
    //EFFECTS: creates a new JournalEntry with date, title, mood, and entry.
    public JournalEntry(Date date, String title, int mood, String entry) {
        this.date = date;
        this.title = title;
        this.mood = mood;
        this.entry = entry;
    }

    //EFFECTS: returns mood for a journalentry.
    public int getMood() {
        return this.mood;
    }

    //EFFECTS: returns date for a journalentry.
    public Date getDate() {
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




}
