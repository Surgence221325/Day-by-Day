package persistence;

import model.JournalEntry;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTester {
    protected void checkEntry(String date, String title, int mood, String entry, JournalEntry je)  {
        assertEquals(date, je.getDate());
        assertEquals(title, je.getTitle());
        assertEquals(mood, je.getMood());
        assertEquals(entry, je.getEntry());
    }
}
