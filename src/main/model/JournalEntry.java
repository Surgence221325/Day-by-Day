package model;


import java.util.Date;
import java.util.Scanner;

public class JournalEntry {
    private Scanner words;
    private Date date;
    private int mood;

    public JournalEntry() {
        words = new Scanner(System.in);
        date = new Date();
        mood = 0;
        writeJournal();
    }

    private void writeJournal() {
        System.out.println("Date:" + date);
        System.out.println("Please rate your mood on a scale of 1-10 (10 being amazing!)");
        mood = Integer.parseInt(words.next());
        if (mood <= 5) {
            System.out.println("Although you may not be feeling great now, I hope you feel better after the "
                    + "journaling session!");
        } else {
            System.out.println("Happy you are feeling great, I hope this journaling session makes it even better!");
        }
        System.out.println("Begin Entry");
        words.useDelimiter("Finish Entry");
        String response = words.next();
    }
}
