package ui;

import model.Journal;
import model.JournalEntry;
import model.Journal.*;
import model.JournalEntry.*;

import java.util.Locale;
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
        JournalEntry n1 = new JournalEntry();
        journal.addEntry(n1);
        System.out.print(journal);
    }

    private void searchEntry(){
        //stub
    }

    private void promptEntry(){
        // stub
    }
}
