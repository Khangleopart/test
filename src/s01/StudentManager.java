/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * StudentManager - This class is responsible for managing student-related
 * operations.
 *
 * Key functionalities include: - Adding new students with validation and file
 * persistence - Searching for students by name (case-insensitive) - Displaying
 * the student list from memory - Menu system for user interaction
 *
 * Author: Truong Hoang Khang - CE190729
 */
public class StudentManager {

    private final String fileName = "Student.txt";      // File name where student data is saved
    private final FileStudent file = new FileStudent(); // Object handling file operations (read/write)
    private final Scanner sc = new Scanner(System.in);  // Scanner instance to read user input
    private List<Student> studentList = new ArrayList<>(); // In-memory list to hold student objects

    /**
     * enterStudentList - Allows user to enter a new student. It validates input
     * fields and updates both memory and file.
     */
    private void enterStudentList() {
        System.out.println("Enter new student:");

        // Get unique and valid student code
        String code = this.checkCode("Student code: ");

        // Get valid student name (validated in MyLib)
        String name = MyLib.inputName("Student name: ");

        // Get valid date of birth (age must be between 18–26)
        String date = MyLib.inputDateOfBirth("Date of birth (dd-MMM-yyyy): ", 18, 26);

        // Get student point (validated to be within allowed range)
        double point = MyLib.inputPoint("Student point: ");

        // Create new student and add to list
        Student student = new Student(code, name, date, point);
        studentList.add(student);                 // Add to in-memory list
        file.addNew(fileName, student);           // Save new student to file

        System.out.println("Student added successfully!");
    }

    /**
     * checkCode - Prompts the user to input a student code and checks: - Not
     * empty - Not duplicated (ignoring case)
     *
     * @param message Prompt message
     * @return A unique and valid student code
     */
    public String checkCode(String message) {
        while (true) {
            System.out.print(message);           // Display input message
            String code = sc.nextLine().trim();  // Read and trim user input

            // Validation checks
            if (code.isEmpty()) {
                System.out.println("Code cannot be empty.");
            } else if (isSameCode(code)) {
                System.out.println("Student code already exists.");
            } else {
                return code;  // Valid and unique code
            }
        }
    }

    /**
     * isSameCode - Checks whether a given student code already exists in the
     * list. Comparison is done in a case-insensitive manner.
     *
     * @param code Student code to check
     * @return true if code exists, false otherwise
     */
    private boolean isSameCode(String code) {
        for (Student s : studentList) {
            // Compare codes ignoring case
            if (s.getCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false; // Not found
    }

    /**
     * lookupStudent - Prompts the user to enter a name and searches for
     * matching students. Displays all students whose names contain the input
     * (case-insensitive).
     */
    private void lookupStudent() {
        // Get search keyword from user
        String searchName = MyLib.inputString("Please enter student name: ");
        boolean found = false;  // Flag to track whether a match is found

        for (Student s : studentList) {
            // If student name contains the search keyword (case-insensitive)
            if (s.getName().toLowerCase().contains(searchName.toLowerCase())) {
                System.out.println("\nFound student:");
                System.out.println(s); // Print matching student
                found = true;
            }
        }

        if (!found) {
            // Inform user if no student matched
            System.out.println("No student found: " + searchName);
        }
    }

    /**
     * displayStudentList - Displays all student entries currently stored in
     * memory.
     */
    private void displayStudentList() {
        // Check if list is empty
        if (studentList.isEmpty()) {
            System.out.println("Student list is empty.");
            System.out.println("---------------------------");
        }

        // Display formatted student list
        System.out.println("Student list:");
        System.out.println("---------------------------");
        for (Student s : studentList) {
            System.out.println(s); // Display student info (toString override)
            System.out.println("---------------------------");
        }
    }

    /**
     * menu - Displays the main menu and handles user input for operations. It
     * continues to loop until the user chooses to exit.
     */
    public void menu() {
        file.read(); // Load student data from file at the beginning
        // Menu loop
        while (true) {
            // Display options
            System.out.println("\n1. Enter student list");
            System.out.println("2. Look up student");
            System.out.println("3. Display student list");
            System.out.println("4. Exit\n");

            // Get user choice
            String input = MyLib.inputString("Please choose menu (1 – 4): ");

            // Handle user choice using switch-case
            switch (input) {
                case "1":
                    enterStudentList(); // Add new student
                    break;
                case "2":
                    lookupStudent();    // Search for student
                    break;
                case "3":
                    displayStudentList(); // Show all students
                    break;
                case "4":
                    System.out.println("Exiting program...");
                    return; // Exit program
                default:
                    System.out.println("Invalid choice. Please enter from 1 to 4.");
            }
        }
    }
}
