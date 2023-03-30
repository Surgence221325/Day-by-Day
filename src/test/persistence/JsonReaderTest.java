package persistence;

import model.Journal;
import model.JournalEntry;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTester{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Journal j = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyJournal() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            Journal j = reader.read();
            assertEquals("zaid", j.getName());
            assertEquals(0, j.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testJournalStandard() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            Journal j = reader.read();
            assertEquals("zaid", j.getName());
            assertEquals("123", j.getPassword());
            ArrayList<JournalEntry> journals = j.getJournals();
            assertEquals(2, journals.size());
            String date = LocalDate.now().toString();
            checkEntry(date, "Test", 1, "\nHello", journals.get(0));
            checkEntry(date, "Test2", 10, "\nHello hi", journals.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
