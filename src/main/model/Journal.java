package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class Journal {
    private LinkedList<JournalEntry> journals;

    //EFFECTS: creates a new Journal
    public Journal() {
        journals = new LinkedList<>();
    }

    //MODIFIES: this journal.
    //EFFECTS: adds journal entry to journal.
    public void addEntry(JournalEntry j) {
        journals.add(j);
    }


    //EFFECTS: returns size of journal.
    public int getSize() {
        return journals.size();
    }

    //EFFECTS: returns a list of names of journal entries in journal.
    public LinkedList<String> getNames() {
        LinkedList<String> names = new LinkedList<>();
        for (JournalEntry j: journals) {
            names.add(j.getTitle());
        }
        return names;
    }

    //EFFECTS: returns journal.
    public LinkedList<JournalEntry> getJournals() {
        return journals;
    }

    //REQUIRES: that the desired entry number exists in the list.
    //EFFECTS: returns journal entry.
    public JournalEntry getJournalEntry(int num) {
        return journals.get((num - 1));
    }
}
