package persistence;

import model.Journal;
import model.JournalEntry;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTester {

    @Test
    void testWriterInvalidFile() {
        try {
            Journal j = new Journal("Test", "123");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Journal j = new Journal("Test", "123");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(j);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            j = reader.read();
            assertEquals("Test", j.getName());
            assertEquals(0, j.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Journal j = new Journal("Test", "123");
            j.addEntry(new JournalEntry("Test1", 1, "\nHello"));
            j.addEntry(new JournalEntry("Test2", 10, "\nHello hi"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(j);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            j = reader.read();
            assertEquals("Test", j.getName());
            ArrayList<JournalEntry> journals = j.getJournals();
            assertEquals(2, journals.size());
            String date = LocalDate.now().toString();
            checkEntry(date, "Test1", 1, "\nHello", journals.get(0));
            checkEntry(date, "Test2", 10, "\nHello hi", journals.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
