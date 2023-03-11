package model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JournalTest {
    Date x = new Date();
    JournalEntry J1 = new JournalEntry("test", 1, "hello");
    JournalEntry J2 = new JournalEntry( "test2", 10, "bye");
    JournalEntry J1x = new JournalEntry("test", 1, "hello");
    JournalEntry J2x = new JournalEntry( "test2", 10, "bye");
    Journal journal = new Journal("Test");


    @Test
    public void JournalTest() {
        assertEquals(0, journal.getSize());

    }

    @Test
    public void addEntryTest() {
        journal.addEntry(J1);
        assertEquals(1, journal.getSize());
        assertEquals("test", journal.getJournalEntry(1).getTitle());
        assertEquals(1, journal.getJournalEntry(1).getMood());
        assertEquals("hello", journal.getJournalEntry(1).getEntry());
    }

    @Test
    public void addMultipleEntryTest() {
        journal.addEntry(J1);
        journal.addEntry(J2);
        assertEquals(2, journal.getSize());
        assertEquals("test2", journal.getJournalEntry(2).getTitle());
        assertEquals(10, journal.getJournalEntry(2).getMood());
        assertEquals("bye", journal.getJournalEntry(2).getEntry());
    }

    @Test
    public void removeEntryTest() {
        journal.addEntry(J1);
        journal.addEntry(J2);
        journal.removeEntry(journal.getJournalEntry(1));
        assertEquals(1, journal.getSize());
        assertEquals("test2", journal.getJournalEntry(1).getTitle());
        assertEquals(10, journal.getJournalEntry(1).getMood());
        assertEquals("bye", journal.getJournalEntry(1).getEntry());
    }

    @Test
    public void removeMultipleEntryTest() {
        journal.addEntry(J1);
        journal.addEntry(J2);
        journal.removeEntry(journal.getJournalEntry(2));
        journal.removeEntry(journal.getJournalEntry(1));
        assertEquals(0, journal.getSize());
    }

    @Test
    public void titleSearch() {
        journal.addEntry(J1);
        journal.addEntry(J1x);
        journal.addEntry(J2);
        assertEquals(3, journal.getSize());
        assertEquals(2, journal.titleSearch("test").getSize());
        assertEquals("test", journal.titleSearch("test").getJournalEntry(1).getTitle());
    }

    @Test
    public void moodSearch() {
        journal.addEntry(J1);
        journal.addEntry(J1x);
        journal.addEntry(J2);
        assertEquals(3, journal.getSize());
        assertEquals(2, journal.moodSearch(1).getSize());
        assertEquals("test", journal.moodSearch(1).getJournalEntry(1).getTitle());
    }

    @Test
    public void getNamesTest() {
        journal.addEntry(J1);
        journal.addEntry(J1x);
        journal.addEntry(J2);
        assertEquals("test", journal.getNames().get(1));
        assertEquals("test2", journal.getNames().get(2));
    }

}