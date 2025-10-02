# Haru Chatbot - User Guide

## 1. Overview
Haru is a command-line task management chatbot. It lets you:
- Add **Todos**, **Deadlines**, and **Events**.
- **Mark/unmark** tasks as done.
- **List**, **find**, and **delete** tasks.
- Persist tasks automatically across sessions.

---

## 2. Getting Started
1. Ensure **Java 17+** is installed.
2. Compile (if needed):
   ```bash
   javac -d bin $(find src -name "*.java")
    ```

---

## 3. Commands

### Add Tasks
- Todo — `todo <description>` — `todo Read book` — Task without date/time 
- Deadline — `deadline <description> /by <d/m/yyyy HHmm>` — `deadline Submit report /by 5/10/2025 2359` — Use d/m/yyyy HHmm format 
- Event — `event <description> /from <d/m/yyyy HHmm> /to <d/m/yyyy HHmm>` — `event Meeting /from 5/10/2025 1400 /to 5/10/2025 1500` — Start must be before end 

### List Tasks
- `list` — Shows all tasks with numbers and status.

### Mark / Unmark
- Mark — `mark <task number>` — Marks task as done
- Unmark — `unmark <task number>` — Marks task as not done 

### Delete Task
- `delete <task number>` — Removes task from the list.

### Search
- Find by keyword — `find <keyword>` — Searches task descriptions for a match 
- Find by date — `finddate <d/m/yyyy>`— Finds deadlines/events on a specific date 

### Exit
- `bye` — Saves tasks and exits the program.

---

## 4. Tips
- Commands are **case-insensitive**.
- Task numbers are **1-indexed**.
- Invalid arguments show **usage hints**.
- Data is auto-saved in `HaruData/savefile.txt`.

---

## 5. Quick Example
```bash
> todo Read book
New Todo added:
[ ] Read book
```
```bash

> deadline Submit report /by 5/10/2025 2359
New Deadline added:
[ ] Submit report (Deadline: Oct 5 2025 11:59PM)
```
```bash
> event Meeting /from 5/10/2025 1400 /to 5/10/2025 1500
New Event added:
[ ] Meeting (Event from: Oct 5 2025 02:00PM, to: Oct 5 2025 03:00PM)
```
```bash
> list
Here is your list:
1. [ ] Read book
2. [ ] Submit report (Deadline: Oct 5 2025 11:59PM)
3. [ ] Meeting (Event from: Oct 5 2025 02:00PM, to: Oct 5 2025 03:00PM)
```
```bash
> mark 1
Task Marked as done:
[X] Read book
```
```bash
> finddate 5/10/2025
Here are the deadlines/tasks on the following date:
1. [ ] Submit report (Deadline: Oct 5 2025 11:59PM)
2. [ ] Meeting (Event from: Oct 5 2025 02:00PM, to: Oct 5 2025 03:00PM)
```
```bash
> bye
Bye! Have a wonderful day ahead :))
```