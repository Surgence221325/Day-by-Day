package ui;

import model.Journal;
import model.JournalEntry;
import model.Journal.*;
import model.JournalEntry.*;

import java.util.*;

// Journaling app with capabilities to create new entries, save and search entries based on date/mood.
public class JournalApp {
    private Journal journal;
    private Scanner input;
    private String userName;

    //runs startJournal
    public JournalApp() {
        startJournal();
    }

    private void startJournal() {
        boolean running = true;
        String command;

        init();
        System.out.println("Enter UserName");

        this.userName = input.next();
        System.out.println("Welcome " + userName);

        while (running) {
            input.reset();
            displayOptions();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                running = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("See you Tomorrow!");
    }

    private void init() {
        journal = new Journal();
        input = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("n")) {
            newEntry();
        } else if (command.equals("s")) {
            searchEntry();
        } else if (command.equals("p")) {
            promptEntry();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayOptions() {
        System.out.println("\nSelect from:");
        System.out.println("\tN -> New Basic Entry");
        System.out.println("\tS -> Search entries");
        System.out.println("\tP -> Prompted journaling");
        System.out.println("\tQ -> Quit");
    }

    // how to create new journal each time?
    private void newEntry() {
        input.useDelimiter(System.lineSeparator());
        Date today = new Date();
        System.out.println("Date:" + today);
        System.out.println("Please decide the Journal title, for easy future search");
        String title = input.next();
        int mood;
        do {
            System.out.println("Please rate your mood on a scale of 1-10 (10 being amazing!)");
            mood = input.nextInt();
        } while ((mood > 10 || mood < 1));
        if (mood <= 5) {
            System.out.println("Although you may not be feeling great now, I hope you feel better after the "
                    + "journaling session!");
        } else {
            System.out.println("Happy you are feeling great, I hope this journaling session makes it even better!");
        }
        System.out.println("Begin Entry, type /F to finish.");
        String entry = input.useDelimiter("/F").next();
        journal.addEntry(new JournalEntry(title, mood, entry));
        System.out.print(journal.getNames());
        input.nextLine();
    }


    private void searchEntry() {
        System.out.println("\nSearch by:");
        System.out.println("\tM -> Mood");
        System.out.println("\tT -> Title");
        System.out.println("\tG -> Go Back");
        System.out.println("\tQ -> Quit");
        String searcher = input.next();
        processSearch(searcher);
    }

    private void processSearch(String searcher) {
        if (searcher.equals("M")) {
            System.out.println("what mood would you like to search for?");
            int desired = Integer.parseInt(input.next());
            moodSearch(desired);
        } else if (searcher.equals("T")) {
            System.out.println(journal.getNames());
            System.out.println("enter desired title");
            String desired = input.next();
            titleSearch(desired);
        } else if (searcher.equals("G")) {
            displayOptions();
            String command = input.next();
            processCommand(command);
        } else {
            System.out.println("Selection not valid...");
        }
    }



    private void moodSearch(int desired) {
        Journal desiredMood = new Journal();
        for (JournalEntry j: journal.getJournals()) {
            if (j.getMood() == desired) {
                desiredMood.addEntry(j);
            }
        }
        System.out.println(desiredMood.getNames());
        System.out.println("Please select one of the above journals, the first journal on the left is entried #1"
                + " each journal there after has number +1 the previous");
        int selected = input.nextInt();
        getEntry1(selected, desiredMood);

    }

    private void titleSearch(String desired) {
        Journal desiredTitle = new Journal();
        for (JournalEntry j: journal.getJournals()) {
            if (desired.equals(j.getTitle())) {
                desiredTitle.addEntry(j);
            }
        }
        if (desiredTitle.getSize() == 0) {
            System.out.println("Sorry desired journal does not exist, please watch spelling");
        } else {
            System.out.println(desiredTitle.getNames());
            System.out.println("Please select one of the above journals, the first journal on the left is entried #1"
                    + " each journal there after has number +1 the previous");
            int selected = input.nextInt();
            getEntry1(selected, desiredTitle);
        }
    }

    private void getEntry1(int num, Journal desired) {
        desired.getJournalEntry(num).getEntry();
    }


    private void promptEntry() {
        input.useDelimiter(System.lineSeparator());
        Date today = new Date();
        System.out.println("Date:" + today);
        System.out.println("Please decide the Journal title, for easy future search");
        String title = input.next();
        int mood;
        do {
            System.out.println("Please rate your mood on a scale of 1-10 (10 being amazing!)");
            mood = input.nextInt();
        } while ((mood > 10 || mood < 1));
        if (mood <= 5) {
            System.out.println("Although you may not be feeling great now, I hope you feel better after the "
                    + "journaling session!");
        } else {
            System.out.println("Happy you are feeling great, I hope this journaling session makes it even better!");
        }
        System.out.println("Describe your emotions using 3 key words.Type /F to finish.");
        String entry = "Describe your emotions using 3 key words.Type /F to finish." + " "
                + input.useDelimiter("/F").next();
        System.out.println("Imagine you are walking up a hill, with these emotions as rocks in your backpack. "
                + "Which emotion, when taken out will decrease your load the most. Type /F to finish.");
        entry = entry + "Imagine you are walking up a hill, with these emotions as rocks in your backpack. "
                + "Which emotion, when taken out will decrease your load the most. Type /F to finish."
                + input.useDelimiter("/F").next();
        System.out.println("What is the first step you can take to decreasing that emotion? Type /F to finish.");
        entry = entry + "What is the first step you can take to decreasing that emotion? Type /F to finish."
                + input.useDelimiter("/F").next();
        journal.addEntry(new JournalEntry(title, mood, entry));
        System.out.print(journal.getNames());
        input.nextLine();
    }
}
