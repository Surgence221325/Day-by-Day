package ui;

import model.Journal;
import model.JournalEntry;
import model.Journal.*;
import model.JournalEntry.*;

import java.util.ArrayList;
import java.util.Locale;
import java.util.LinkedList;
import java.util.Scanner;

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
        String command =  null;

        init();
        Scanner name = new Scanner(System.in);
        System.out.println("Enter UserName");

        String userName = name.nextLine();
        System.out.println("Welcome " + userName);

        while (running) {
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
            //promptEntry();
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
        journal.addEntry(new JournalEntry());
        System.out.print(journal.getNames());
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

    private String getEntry1(int num, Journal desired) {
        return desired.getJournalEntry(num).getEntry();
    }

    private void promptEntry() {
        // stub
    }
}
