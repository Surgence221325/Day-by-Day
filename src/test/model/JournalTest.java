package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JournalTest {
    private JournalEntry j1;
    private JournalEntry j2;
    private JournalEntry j1X;
    private JournalEntry j2X;
    private Journal journal;

    @BeforeEach
    void runBefore(){
        j1 = new JournalEntry("test", 1, "hello");
        j2 = new JournalEntry( "test2", 10, "bye");
        j1X = new JournalEntry("test", 1, "hello");
        j2X = new JournalEntry( "test2", 10, "bye");
        journal = new Journal("Test", "123");
    }






    @Test
    public void JournalTest() {
        assertEquals(0, journal.getSize());
        assertEquals("Test", journal.getName());
        assertEquals("123", journal.getPassword());
    }

    @Test
    public void addEntryTest() {
        journal.addEntry(j1);
        assertEquals(1, journal.getSize());
        assertEquals("test", journal.getJournalEntry(1).getTitle());
        assertEquals(1, journal.getJournalEntry(1).getMood());
        assertEquals("hello", journal.getJournalEntry(1).getEntry());
    }

    @Test
    public void addMultipleEntryTest() {
        journal.addEntry(j1);
        journal.addEntry(j2);
        assertEquals(2, journal.getSize());
        assertEquals("test2", journal.getJournalEntry(2).getTitle());
        assertEquals(10, journal.getJournalEntry(2).getMood());
        assertEquals("bye", journal.getJournalEntry(2).getEntry());
    }

    @Test
    public void removeEntryTest() {
        journal.addEntry(j1);
        journal.addEntry(j2);
        journal.removeEntry(journal.getJournalEntry(1));
        assertEquals(1, journal.getSize());
        assertEquals("test2", journal.getJournalEntry(1).getTitle());
        assertEquals(10, journal.getJournalEntry(1).getMood());
        assertEquals("bye", journal.getJournalEntry(1).getEntry());
    }

    @Test
    public void removeMultipleEntryTest() {
        journal.addEntry(j1);
        journal.addEntry(j2);
        journal.removeEntry(journal.getJournalEntry(2));
        journal.removeEntry(journal.getJournalEntry(1));
        assertEquals(0, journal.getSize());
    }

    @Test
    public void titleSearch() {
        journal.addEntry(j1);
        journal.addEntry(j1X);
        journal.addEntry(j2);
        assertEquals(3, journal.getSize());
        assertEquals(2, journal.titleSearch("test").getSize());
        assertEquals("test", journal.titleSearch("test").getJournalEntry(1).getTitle());
    }

    @Test
    public void moodSearch() {
        journal.addEntry(j1);
        journal.addEntry(j1X);
        journal.addEntry(j2);
        assertEquals(3, journal.getSize());
        assertEquals(2, journal.moodSearch(1).getSize());
        assertEquals("test", journal.moodSearch(1).getJournalEntry(1).getTitle());
    }

    @Test
    public void getNamesTest() {
        journal.addEntry(j1);
        journal.addEntry(j1X);
        journal.addEntry(j2);
        assertEquals("test", journal.getNames().get(1));
        assertEquals("test2", journal.getNames().get(2));
    }


}