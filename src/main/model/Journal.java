package model;

import java.util.ArrayList;

public class Journal {
    private ArrayList<JournalEntry> journals;

    public Journal() {
        journals = new ArrayList<>();
    }

    public void addEntry(JournalEntry j) {
        journals.add(j);

    }

}
