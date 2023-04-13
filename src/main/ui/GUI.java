package ui;

import model.EventLog;
import model.Journal;
import model.JournalEntry;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


//GUI class which provide user interface for journaling.
public class GUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/myFile.json";
    private Journal journal;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private CardLayout cardLayout;
    private JPanel cards;
    private JPanel mainPanel;
    private JButton newEntryButton;
    private JButton newPromptEntryButton;
    private JButton removeButton;
    private JButton searchButton;
    private JButton saveButton;
    private JButton loadButton;




    //instantiates the initial fields and panel.
    public GUI() {
        journal = new Journal();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        mainPanel = new JPanel();


        mainPanelSetUp();
        mainPanelOnClose();

        cards.add(mainPanel, "Home");

        add(cards, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Day by Day Health");
        pack();
        setVisible(true);

    }

    private void mainPanelOnClose() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Iterator<model.Event> iterator = EventLog.getInstance().iterator();
                while (iterator.hasNext()) {
                    model.Event event = iterator.next();
                    System.out.println(event.toString());
                }
                System.out.println("finished printing event log");
            }
        });
    }

    //smaller method for mainpanel setup that adds buttons
    //MODIFIES: this
    //EFFECTS: setup for mainPanel
    private void mainPanelSetUp() {
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        mainPanel.setLayout(new BorderLayout());

        mainPanel.setBackground(new Color(180, 144, 250));

        createButton();


        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        buttonPanel.add(newEntryButton);
        buttonPanel.add(newPromptEntryButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);


        JScrollPane scrollPane = new JScrollPane(buttonPanel);


        ImageIcon imageIcon = new ImageIcon("./data/image2.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setPreferredSize(new Dimension(500, 900));
        mainPanel.add(imageLabel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.LINE_END);

    }


    //MODIFIES: this
    //EFFECTS: setup for newEntryPanel
    private void newEntryPanelSetUp(JPanel newEntryPanel) {

        newEntryPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        newEntryPanel.setLayout(new GridLayout(0, 1));
        newEntryPanel.setBackground(new Color(180, 144, 250));
        newEntryPanel.add(new JLabel("Enter Title:"));
        JTextField title = new JTextField();
        newEntryPanel.add(title);
        newEntryPanel.add(new JLabel("Enter Mood (1-10):"));
        JTextField mood = new JTextField();
        newEntryPanel.add(mood);
        newEntryPanel.add(new JLabel("Enter journal entry:"));
        JTextArea entry = new JTextArea();
        newEntryPanel.add(entry);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                journal.addEntry(new JournalEntry(title.getText(), Integer.parseInt(mood.getText()), entry.getText()));
                cardLayout.show(cards, "Home");
            }
        });
        newEntryPanel.add(submitButton);

        home(newEntryPanel);

    }




    //MODIFIES: this
    //EFFECTS: sets up the search panel.
    private void searchPanelSetUp(JPanel searchPanel) {

        searchPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        searchPanel.setLayout(new GridLayout(0, 1));
        searchPanel.setBackground(new Color(180, 144, 250));
        searchPanel.add(new JLabel("Search by:"));


        searchTitle(searchPanel);

        searchMood(searchPanel);

        searchAll(searchPanel);

        home(searchPanel);

    }

    //MODIFIES: this
    //EFFECTS: sets up searchTitle function.
    private void searchTitle(JPanel panel) {
        JButton searchTitle = new JButton("Title");
        searchTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel titleSearch = new JPanel();
                titleSearchSetUp(titleSearch);
                cards.add(titleSearch, "Title Search");
                cardLayout.show(cards, "Title Search");
            }
        });

        panel.add(searchTitle);

    }

    //MODIFIES: this
    //EFFECTS: sets up searchMood function.
    private void searchMood(JPanel panel) {
        JButton searchMood = new JButton("Mood");
        searchMood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel moodSearch = new JPanel();
                moodSearchSetUp(moodSearch);
                cards.add(moodSearch, "Mood Search");
                cardLayout.show(cards, "Mood Search");
            }
        });

        panel.add(searchMood);
    }

    //MODIFIES: this
    //EFFECTS: sets up search panel searchAll button.
    private void searchAll(JPanel panel) {
        JButton searchAll = new JButton("All");
        searchAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel allSearch = new JPanel();
                allSearchSetUp(allSearch);
                cards.add(allSearch, "All");
                cardLayout.show(cards, "All");
            }
        });
        panel.add(searchAll);
    }

    //MODIFIES: this
    //EFFECTS: sets up removePanel buttons.
    private void removePanelSetUp(JPanel removePanel) {

        removePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        removePanel.setLayout(new GridLayout(0, 1));
        removePanel.setBackground(new Color(180, 144, 250));
        removePanel.add(new JLabel("Remove by:"));


        removeTitle(removePanel);
        removeMood(removePanel);
        home(removePanel);
    }

    //MODIFIES: this
    //EFFECTS: sets up removeTitle function for removePanel
    private void removeTitle(JPanel panel) {

        JButton removeTitle = new JButton("Title");
        removeTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel titleRemove = new JPanel();
                titleRemoveSetUp(titleRemove);
                cards.add(titleRemove, "Title Remove");
                cardLayout.show(cards, "Title Remove");
            }
        });

        panel.add(removeTitle);
    }


    //MODIFIES: this
    //EFFECTS: sets up remove mood button for removePanel.
    private void removeMood(JPanel panel) {
        JButton removeMood = new JButton("Mood");
        removeMood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel moodRemove = new JPanel();
                moodRemoveSetUp(moodRemove);
                cards.add(moodRemove, "Remove Mood");
                cardLayout.show(cards, "Remove Mood");
            }
        });
        panel.add(removeMood);
    }


    //MODIFIES: this
    //EFFECTS: sets up titlesearch function by providing panel for title submission
    private void titleSearchSetUp(JPanel titleSearch) {
        titleSearch.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        titleSearch.setLayout(new GridLayout(0, 1));
        titleSearch.setBackground(new Color(180, 144, 250));

        titleSearch.add(new JLabel("Input Title"));
        JTextField title = new JTextField();
        titleSearch.add(title);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel displayPanel = new JPanel();
                displaySetUp(displayPanel, journal.titleSearch(title.getText()), title.getText());
                cards.add(displayPanel, "Display");
                cardLayout.show(cards, "Display");
            }
        });

        titleSearch.add(submitButton);
    }


    //REQUIRES: Mood is int
    //MODIFIES: this
    //EFfects: shows all entry from journal with mood = to desired
    private void moodSearchSetUp(JPanel moodSearch) {
        moodSearch.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        moodSearch.setLayout(new GridLayout(0, 1));
        moodSearch.setBackground(new Color(180, 144, 250));

        moodSearch.add(new JLabel("Input Mood (1-10)"));
        JTextField mood = new JTextField();
        moodSearch.add(mood);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel displayPanel = new JPanel();
                displaySetUp(displayPanel, journal.moodSearch(Integer.parseInt(mood.getText())),
                        Integer.toString(Integer.parseInt(mood.getText())));
                cards.add(displayPanel, "Display");
                cardLayout.show(cards, "Display");
            }
        });

        moodSearch.add(submitButton);
    }


    //EFFECTS: shows all entries in journal
    private void allSearchSetUp(JPanel allSearch) {
        allSearch.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        allSearch.setLayout(new GridLayout(0, 1));
        allSearch.setBackground(new Color(180, 144, 250));
        JTextArea entry = new JTextArea();
        allSearch.add(entry);




        for (int i = 1; i <= journal.getSize(); i++) {
            JournalEntry currentEntry = journal.getJournalEntry(i);
            JButton entryButton = new JButton(currentEntry.getTitle());
            entryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    entry.setText("Date: " + currentEntry.getDate() + "\n" + "Mood: " + currentEntry.getMood() + "\n"
                            + "Entry: "  + currentEntry.getEntry());
                }
            });
            allSearch.add(entryButton);
        }

        home(allSearch);
    }


    //MODIFIES: this
    //EFFECTS: sets up title remove, by deciding title and giving to removepanel.
    private void titleRemoveSetUp(JPanel titleRemove) {
        titleRemove.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        titleRemove.setLayout(new GridLayout(0, 1));
        titleRemove.setBackground(new Color(180, 144, 250));

        titleRemove.add(new JLabel("Input Title for removal"));
        JTextField title = new JTextField();
        titleRemove.add(title);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel displayPanelRemove = new JPanel();
                displayPanelRemoveSetUp(displayPanelRemove, journal.titleSearch(title.getText()), title.getText());
                cards.add(displayPanelRemove, "Display");
                cardLayout.show(cards, "Display");
            }
        });

        titleRemove.add(submitButton);
    }

    //Requires: mood must be int
    //MODIFIES: this
    //EFFECTS: sets up removing of mood panel selection
    private void moodRemoveSetUp(JPanel moodRemove) {
        moodRemove.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        moodRemove.setLayout(new GridLayout(0, 1));
        moodRemove.setBackground(new Color(180, 144, 250));

        moodRemove.add(new JLabel("Input Mood (1-10) for removal"));
        JTextField mood = new JTextField();
        moodRemove.add(mood);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel displayPanelRemove = new JPanel();
                displayPanelRemoveSetUp(displayPanelRemove, journal.moodSearch(Integer.parseInt(mood.getText())),
                        Integer.toString(Integer.parseInt(mood.getText())));
                cards.add(displayPanelRemove, "Display");
                cardLayout.show(cards, "Display");
            }
        });

        moodRemove.add(submitButton);
    }


    //MODIFIES: this
    //EFFECTS: sets up displaying all desired journals
    private void displaySetUp(JPanel displayPanel, Journal desired, String title) {
        displayPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        displayPanel.setLayout(new GridLayout(0, 1));
        displayPanel.setBackground(new Color(180, 144, 250));
        JLabel t = new JLabel("Searching for:  " + title);
        JTextArea entry = new JTextArea();
        displayPanel.add(t);
        displayPanel.add(entry);

        home(displayPanel);

        for (int i = 1; i <= desired.getSize(); i++) {
            JournalEntry currentEntry = desired.getJournalEntry(i);
            JButton entryButton = new JButton(currentEntry.getTitle());
            entryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    entry.setText("Date: " + currentEntry.getDate() + "\n" + "Mood: " + currentEntry.getMood() + "\n"
                            + "Entry: "  + currentEntry.getEntry());
                }
            });
            displayPanel.add(entryButton);
        }
    }


    //MODIFIES: this
    //EFFECTS: sets up displaypanel remove function
    private void displayPanelRemoveSetUp(JPanel displayPanel, Journal desired, String title) {
        displayPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        displayPanel.setLayout(new GridLayout(0, 1));
        displayPanel.setBackground(new Color(180, 144, 250));
        JLabel t = new JLabel("Searching for:  " + title);
        JTextArea entry = new JTextArea();
        displayPanel.add(t);
        displayPanel.add(entry);

        home(displayPanel);

        for (int i = 1; i <= desired.getSize(); i++) {
            JournalEntry currentEntry = desired.getJournalEntry(i);
            JButton entryButton = new JButton(currentEntry.getTitle() + " " + currentEntry.getDate());
            entryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    journal.removeEntry(currentEntry);
                    cardLayout.show(cards, "Home");
                }
            });
            displayPanel.add(entryButton);
        }
    }



    //MODIFIES: this
    //EFFECTS: sets up
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newEntryButton) {
            JPanel newEntryPanel = new JPanel();
            newEntryPanelSetUp(newEntryPanel);
            cards.add(newEntryPanel, "Submit New Entry");
            cardLayout.show(cards, "Submit New Entry");
        } else if (e.getSource() == newPromptEntryButton) {
            System.out.println("newPrompt - not yet finalized");
        } else if (e.getSource() == removeButton) {
            JPanel removePanel = new JPanel();
            removePanelSetUp(removePanel);
            cards.add(removePanel, "Remove");
            cardLayout.show(cards, "Remove");
        } else if (e.getSource() == searchButton) {
            JPanel searchPanel = new JPanel();
            searchPanelSetUp(searchPanel);
            cards.add(searchPanel, "Search");
            cardLayout.show(cards, "Search");
        } else if (e.getSource() == saveButton) {
            saveJournal();
        } else if (e.getSource() == loadButton) {
            loadJournal();
        }
    }

    //MODIFIES: this
    //EFFECTS: sets up mainPanel buttons
    public void createButton() {

        newEntryButton = new JButton("New Entry");
        newEntryButton.addActionListener(this);

        newPromptEntryButton = new JButton("New Prompted Entry");
        newPromptEntryButton.addActionListener(this);

        removeButton = new JButton("Remove Entry");
        removeButton.addActionListener(this);

        searchButton = new JButton("Search Entry");
        searchButton.addActionListener(this);

        saveButton = new JButton("Save Journal");
        saveButton.addActionListener(this);

        loadButton = new JButton("Load Journal");
        loadButton.addActionListener(this);

        newEntryButton.setPreferredSize(new Dimension(100, 30));
        newPromptEntryButton.setPreferredSize(new Dimension(100, 30));
        removeButton.setPreferredSize(new Dimension(100, 30));
        searchButton.setPreferredSize(new Dimension(100, 30));
        saveButton.setPreferredSize(new Dimension(100, 30));
        loadButton.setPreferredSize(new Dimension(100, 30));

    }

    //MODIFIES: this
    //EFFECTS: sets up home/return button for most panels
    private void home(JPanel panel) {
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Home");
            }
        });
        panel.add(returnButton);
    }


    //MODIFIES: JSON
    //EFFECTS: Writes current data of journal to JSON.
    private void saveJournal() {
        try {
            jsonWriter.open();
            jsonWriter.write(journal);
            jsonWriter.close();
            System.out.println("Saved " + journal.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads journal from file, only if password is correct
    private void loadJournal() {
        try {
            journal = jsonReader.read();
            System.out.println("Loaded " + journal.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
