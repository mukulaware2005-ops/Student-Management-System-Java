# Student Management System (Java GUI)

A simple and structured Student Management System built using Java Swing with file-based storage. This application allows users to perform CRUD (Create, Read, Update, Delete) operations through a graphical user interface.

## Features

* Add student
* View students in table format
* Search student by ID
* Update student details
* Delete student
* Refresh data
* Clear input fields

## Technologies Used

* Java
* Java Swing (GUI)
* File Handling (BufferedReader, BufferedWriter)

## Project Structure

```id="p9h2jc"
StudentManagementSystem/
│── Student.java
│── Main.java
│── StudentGUI.java
│── students.txt
```

## How It Works

* Student data is stored in `students.txt`
* Data loads when the application starts
* All changes are saved instantly

## How to Run

### Compile

```id="z3c5fi"
javac *.java
```

### Run

```id="c6w3k1"
java StudentGUI
```

## GUI Overview

* Input fields for ID, Name, Age
* Buttons for all operations
* Table to display records
* Row click fills input fields

## Requirements

* Java JDK 8 or above
* IntelliJ IDEA, Eclipse, or terminal

## Future Enhancements

* Login system
* Dark theme
* MySQL integration
* Web version

## Author

Mukul Aware

## Notes

Suitable for academic projects, Java GUI learning, and CRUD practice.
