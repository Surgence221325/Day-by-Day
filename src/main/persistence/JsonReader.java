package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Journal;
import model.JournalEntry;
import org.json.*;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Journal from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Journal read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseJournal(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses journal from JSON object and returns it
    private Journal parseJournal(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String password = jsonObject.getString("password");
        Journal j = new Journal(name, password);
        addJournals(j, jsonObject);
        return j;
    }

    // MODIFIES: journal
    // EFFECTS: parses journal (field) from JSON object and entries to journal
    private void addJournals(Journal j, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("journals");
        for (Object json : jsonArray) {
            JSONObject nextJournal = (JSONObject) json;
            addThingy(j, nextJournal);
        }
    }

    // MODIFIES: journal
    // EFFECTS: parses journalentries from JSON object and adds it to journal
    private void addThingy(Journal j, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        String title = jsonObject.getString("title");
        int mood = jsonObject.getInt("mood");
        String entry = jsonObject.getString("entry");
        JournalEntry je = new JournalEntry(title, mood, entry);
        j.addEntry(je);
    }
}
