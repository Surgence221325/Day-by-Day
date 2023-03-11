package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


class JournalEntryTest {

    JournalEntry J1 = new JournalEntry("test", 1, "hello");
    JournalEntry J2 = new JournalEntry("test2", 10, "bye");


    @Test
    public void journalEntryTest() {
        assertEquals("test", J1.getTitle());
        assertEquals(1, J1.getMood());
        assertEquals("hello", J1.getEntry());
        assertEquals("test2", J2.getTitle());
    }
}
