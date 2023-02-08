package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class Journal {
    private LinkedList<JournalEntry> journals;

    public Journal() {
        journals = new LinkedList<>();
    }

    public void addEntry(JournalEntry j) {
        journals.add(j);
    }


    public int getSize() {
        return journals.size();
    }

    public LinkedList<String> getNames() {
        LinkedList<String> names = new LinkedList<>();
        for (JournalEntry j: journals) {
            names.add(j.getTitle());
        }
        return names;
    }

    public LinkedList<JournalEntry> getJournals() {
        return journals;
    }

    public JournalEntry getJournalEntry(int num) {
        return journals.get((num - 1));
    }
}
