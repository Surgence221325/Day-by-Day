package model;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

public class Journal {
    private String name;
    private ArrayList<JournalEntry> journals;

    //EFFECTS: creates a new Journal
    public Journal(String name) {
        this.name = name;
        journals = new ArrayList<>();
    }

    public Journal() {
        this.name = "";
        journals = new ArrayList<>();
    }

    //REQUIRES: Unique journal entry (can't add the same object).
    //MODIFIES: this journal.
    //EFFECTS: adds journal entry to journal.
    public void addEntry(JournalEntry j) {

        journals.add(j);
    }

    //REQUIRES: Journal entry to exist in journal.
    //MODIFIES: this
    //EFFECTS: removes journal entry from journal.
    public void removeEntry(JournalEntry j) {

        journals.remove(j);
    }

    //EFFECTS: Creates a new journal, desiredTitle, that has all journal entries from original with title desired
    public Journal titleSearch(String desired) {
        Journal desiredTitle = new Journal();
        for (JournalEntry j: this.getJournals()) {
            if (desired.equals(j.getTitle())) {
                desiredTitle.addEntry(j);
            }
        }
        return desiredTitle;
    }

    //EFFECTS: Creates a new journal, desiredMood, that has all journal entries with desired mood
    public Journal moodSearch(int desired) {
        Journal desiredMood = new Journal();
        for (JournalEntry j: this.getJournals()) {
            if (j.getMood() == desired) {
                desiredMood.addEntry(j);
            }
        }
        return desiredMood;
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
    public ArrayList<JournalEntry> getJournals() {
        return journals;
    }

    //REQUIRES: that the desired entry number exists in the list.
    //EFFECTS: returns journal entry.
    public JournalEntry getJournalEntry(int num) {
        return journals.get((num - 1));
    }


    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("journals", journals);
        return json;
    }

    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (JournalEntry j : journals) {
            jsonArray.put(j.toJson());
        }
        return jsonArray;
    }

    public String getName() {
        return this.name;
    }
}


