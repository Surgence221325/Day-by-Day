package model;


import java.util.Date;
import java.util.Scanner;
import ui.JournalApp.*;

public class JournalEntry {
    private Scanner words;
    private Date date;
    private int mood;
    private String response;
    private String title;
    private String entry;

    //REQUIRES: mood be an integer input
    //EFFECTS: creates a new JournalEntry with title, mood, and entry.
    public JournalEntry(String title, int mood, String entry) {
        words = new Scanner(System.in);
        date = new Date();
        mood = 0;
        response = "";
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

    //EFFECTS: returns date, mood, and entry for a Journal entry.
    public void getEntry() {
        System.out.println("Date " + this.date);
        System.out.println("Mood was: " + this.mood);
        System.out.println("Text is: " + this.entry);
    }
}
