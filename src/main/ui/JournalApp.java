package ui;

import model.Journal;
import model.JournalEntry;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

// Journaling app with capabilities to create new entries, save and search entries based on date/mood.
public class JournalApp {
    private static final String JSON_STORE = "./data/myFile.json";
    private Journal journal;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //runs StartJournal and initializes journal, scanner, and JSON.
    public JournalApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        System.out.println("Enter UserName");
        String userName = input.nextLine();
        System.out.println("Welcome " + userName);
        System.out.println("Enter password");
        String password = input.nextLine();
        System.out.println("Password is: " + password);
        journal = new Journal(userName, password);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        startJournal();
    }

    //startsJournal, all states return here.
    private void startJournal() {
        boolean running = true;
        String command;

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

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("n")) {
            newEntry();
        } else if (command.equals("s")) {
            searchEntry();
        } else if (command.equals("p")) {
            promptEntry();
        } else if (command.equals("r")) {
            removeEntry();
        } else if (command.equals("f")) {
            saveJournal();
        } else if (command.equals("l")) {
            loadJournal();
        } else if (command.equals("q")) {
            return;
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayOptions() {
        System.out.println("\nSelect from:");
        System.out.println("\tN -> New Basic Entry");
        System.out.println("\tS -> Search entries");
        System.out.println("\tR -> Remove entries");
        System.out.println("\tP -> Prompted journaling");
        System.out.println("\tF -> Save File");
        System.out.println("\tL -> Load File");
        System.out.println("\tQ -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: Creates a new journal entry, and adds it to the journal.
    private void newEntry() {
        input.useDelimiter(System.lineSeparator());
        System.out.println("Date:" + LocalDate.now());
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

    // EFFECTS: displays options for user.
    private void searchEntry() {
        System.out.println("\nSearch by:");
        System.out.println("\tM -> Mood");
        System.out.println("\tT -> Title");
        System.out.println("\tA -> All Journals");
        System.out.println("\tG -> Go Back");
        String searcher = input.next().toUpperCase();
        processSearch(searcher);
    }

    //Displays option to remove entries
    private void removeEntry() {
        System.out.println("\nRemove by:");
        System.out.println("\tM -> Mood");
        System.out.println("\tT -> Title");
        System.out.println("\tG -> Go Back");
        String destroyer = input.next().toUpperCase();
        processDestroy(destroyer);
    }

    //MODIFIES: JSON
    //EFFECTS: Writes current data of journal to JSON.
    private void saveJournal() {
        try {
            jsonWriter.open();
            jsonWriter.write(journal);
            jsonWriter.close();
            System.out.println("Saved " + journal.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads journal from file, only if password is correct
    private void loadJournal() {
        try {
            journal = jsonReader.read();
            System.out.println("Loaded " + journal.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        System.out.println("What is the password to " + journal.getName());
        input.nextLine();
        String determinant = input.nextLine();
        if (determinant.equals(journal.getPassword())) {
            System.out.println("Password correct, welcome back!");
            return;
        } else {
            System.out.println("password incorrect, returning to main menu");
            try {
                new JournalApp();
            } catch (FileNotFoundException e) {
                System.out.println("Unable to run application: file not found");
            }
        }
    }

    //EFFECTS: processes option for user, directing to proper method.
    //NOTE: The length of this function is due to the long sout statements,
    // I thought it would just be more confusing to split it up.
    @SuppressWarnings("methodlength")
    private void processSearch(String searcher) {
        if (searcher.equals("M")) {
            System.out.println("what mood would you like to search for?");
            int desired = Integer.parseInt(input.next());
            moodSearchDisplay(journal.moodSearch(desired));
        } else if (searcher.equals("T")) {
            System.out.println(journal.getNames());
            System.out.println("enter desired title");
            input.nextLine();
            String desired = input.nextLine();
            titleSearchDisplay(journal.titleSearch(desired));
        } else if (searcher.equals("A")) {
            System.out.println(journal.getNames());
            if (journal.getSize() == 0) {
                System.out.println("journal is empty");
            } else {
                display(journal);
                int wanted = input.nextInt();
                overall(journal.getJournalEntry(wanted));
            }
        } else if (searcher.equals("G")) {
            return;
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //EFFECTS: displays and processes options for removing entries.
    private void processDestroy(String destroyer) {
        if (destroyer.equals("M")) {
            System.out.println("what mood would you like to remove?");
            int desired = Integer.parseInt(input.next());
            moodDestroyDisplay(journal.moodSearch(desired));
            System.out.println("finished removing mood: " + Integer.toString(desired));
            System.out.println(journal.getNames());
        } else if (destroyer.equals("T")) {
            System.out.println(journal.getNames());
            System.out.println("enter desired title to remove");
            input.nextLine();
            String desired = input.nextLine();
            titleDestroyDisplay(journal.titleSearch(desired));
            System.out.println("finished removing title: " + desired);
            System.out.println(journal.getNames());
        } else if (destroyer.equals("G")) {
            return;
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES: journal
    //EFFECTS: Displays list of desired entries and removes selected journal entry.
    private void moodDestroyDisplay(Journal desiredMood) {
        if (desiredMood.getSize() == 0) {
            System.out.println("Sorry desired mood is not entered");
        } else {
            System.out.println(desiredMood.getNames());
            display(desiredMood);
            System.out.println("Please select one of the above journals, the first journal on the left is entried #1"
                    + " each journal there after has number +1 the previous");
            int selected = input.nextInt();
            journal.removeEntry(desiredMood.getJournalEntry(selected));
        }
    }

    //EFFECTS: Displays journal as easily digestiable option menu for user.
    private void display(Journal journal) {
        for (int i = 1; i < (1 + journal.getSize()); i++) {
            System.out.println("Option " + Integer.toString(i) + ": " + journal.getJournalEntry(i).getTitle());
        }
    }




    //EFFECTS: Displays list of desired mood based entries.
    private void moodSearchDisplay(Journal desiredMood) {
        if (desiredMood.getSize() == 0) {
            System.out.println("Sorry desired mood is not entered");
        } else {
            System.out.println(desiredMood.getNames());
            display(desiredMood);
            System.out.println("Please select one of the above journals, the first journal on the left is entried #1"
                    + " each journal there after has number +1 the previous");
            int selected = input.nextInt();
            overall(desiredMood.getJournalEntry(selected));
        }
    }

    //EFFECTS: prints list of desired title entry.
    private void titleSearchDisplay(Journal desiredTitle) {
        if (desiredTitle.getSize() == 0) {
            System.out.println("Sorry desired journal does not exist, please watch spelling");
        } else {
            System.out.println(desiredTitle.getNames());
            display(desiredTitle);
            System.out.println("Please select one of the above journals, the first journal is entried #1"
                    + " each journal there after has number +1 the previous");
            int selected = input.nextInt();
            overall(desiredTitle.getJournalEntry(selected));
        }
    }

    //MODIFIES: this
    //EFFECTS: Prints list of desired entries and removes desired.
    private void titleDestroyDisplay(Journal desiredTitle) {
        if (desiredTitle.getSize() == 0) {
            System.out.println("Sorry desired journal does not exist, please watch spelling");
        } else {
            System.out.println(desiredTitle.getNames());
            display(desiredTitle);
            System.out.println("Please select one of the above journals, the first journal is entried #1"
                    + " each journal there after has number +1 the previous");
            int selected = input.nextInt();
            journal.removeEntry(desiredTitle.getJournalEntry(selected));
        }
    }

    //EFFECTS: prints date, mood, and entry of a JournalEntry
    public void overall(JournalEntry j) {
        System.out.println("The date was:" + j.getDate());
        System.out.println("The mood was:" + j.getMood());
        System.out.println("The entry was:" + j.getEntry());
    }

    //EFFECTS: Creates prompt journal entry and adds it to journal.
    //MODIFIES: this
    //NOTE: The length of this function is due to the long sout statements,
    // I thought it would just be more confusing to split it up.
    @SuppressWarnings("methodlength")
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
