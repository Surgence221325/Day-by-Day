# Day by Day

## *Tackle the day to day, day by day.*



Day by Day will facilitate daily user journaling, with a 
particular focus towards mental health related functionalities.
Any individual looking for personal growth, stress-relief, 
or reminders in the form of journaling, will benefit from this service.

My interest in mental health started back in my first degree,
where I volunteered as a supportive listener. My experiences as a volunteer
changed my perspective on mental health, and good mental health related habits.
One of the most valuable tools I gained from this experience, was the ability to break down my problems
into more digestible thoughts. Using various techniques, one is able to get down to the root
of their situation and progress in a healthy manner.

I wanted to provide those same functionalities to other people, in the form of directed journaling.
In this app, people will be able to free form journal, or follow written prompts. For example, one such
prompt might go as follows:

- How are you feeling?
- What is making you feel that way?
- Have you felt that way before?
- What can we do to make you feel slightly better?
- Can you use any techniques that you've used before in this situation?

Etc. (and subject to change)

Via **consistent** use, our users will achieve long term mental stability.


- As a user, I want to add a journal entry to my journal.
- As a user, I want to track my mental health over a period of time by rating it on a scale daily. 
- As a user, I want to view my journal entries, and refer back to them in the future.
- As a user, I want to customize my journal entries using stickers and drawings.
- As a user, I want to create different types of journals, some that are prompted.
  - new (#4 is not effective without gui, will implement in phase 2)
- As a user, I want to be able to save my journal entries (if I choose to).
- As a user, I want to be able to load my journal entries (if I choose to).
- As a user, I want to make sure my journal entries can only be accessed via password.


*Instructions for Grader*

how to generate the first of the two required actions that are related to the required user story 
- click on New Entry to add an entry to the journal, then submit
- you can view all the entries by clicking search entry and then all

how to generate the second of the two required actions

- you can filter these using Search Entry by either title or mood, or remove using Remove Entry 
  - title/mood and selection.
- 
where to find the visual component that was added to your project (e.g., background image, image added to button)
- background image found in ./data package

how does the user save the state of the application to file
- click save

how does the user load the state of the application from file
- click load




Phase 4: Task 3

There are a lot of changes I could've made in terms of refactoring my code for future scalability and current
readability. Unfortunately, I kind of stuck with mistakes I made at the beginning of my project fearing that refactoring
would break my code. To start, one objective for this journal project was to create multiple different "prompts," such
prompts would be easily created had I made JournalEntry an abstract class and extended it depending on the type of 
journalEntry desired. Similarly, I could create a super class for Journals or even another class allowing the user to
have many different types of journals. I also wanted to add free writing pages, and drawing, which I was unable to
accomplish.
