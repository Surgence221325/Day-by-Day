package model;


import java.util.Date;
import java.util.Scanner;

public class JournalEntry {
    private Scanner words;
    private Date date;
    private int mood;
    private String response;
    private String title;
    private String entry;

    public JournalEntry() {
        words = new Scanner(System.in);
        date = new Date();
        mood = 0;
        response = "";
        title = null;
        entry = null;
        writeJournal();

    }

    private void writeJournal() {
        System.out.println("Date:" + date);
        System.out.println("Please decide the Journal title, for easy future search");
        title = words.nextLine();
        do {
            System.out.println("Please rate your mood on a scale of 1-10 (10 being amazing!)");
            mood = words.nextInt();
        } while ((mood > 10 || mood < 1));


        if (mood <= 5) {
            System.out.println("Although you may not be feeling great now, I hope you feel better after the "
                    + "journaling session!");
        } else {
            System.out.println("Happy you are feeling great, I hope this journaling session makes it even better!");
        }
        System.out.println("Begin Entry");
        entry = words.useDelimiter("Finish Entry").next();
    }

    public int getMood() {
        return this.mood;
    }

    public Date getDate() {
        return this.date;
    }

    public String getTitle() {
        return this.title;
    }

    public String getEntry() {
        System.out.println(entry);
        return this.entry;
    }
}
