/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s01;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * FileStudent - A class responsible for file handling operations for student
 * data. It allows reading from a file, writing a list of students to a file
 * (sorted by name), and appending new students to the file list.
 *
 * Author: Truong Hoang Khang - CE190729
 */
public class FileStudent {

    private List<Student> studentsList = new ArrayList<>(); // Internal student list

    /**
     * Reads student data from "Student.txt" file and updates the internal list.
     *
     * Algorithm: 1. Clear existing student list. 2. If file exists, read and
     * parse each line. 3. For valid lines (4 fields), create Student and add to
     * list.
     */
    public void read() {
        studentsList.clear(); // Clear the list

        try {
            File myFile = new File("Student.txt");

            if (!myFile.exists()) {
                return; // Do nothing if file doesn't exist
            }

            Scanner sc = new Scanner(myFile);

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] students = data.split("#");

                if (students.length == 4) {
                    String code = students[0];
                    String name = students[1];
                    String date = students[2];
                    double point = Double.parseDouble(students[3]);

                    studentsList.add(new Student(code, name, date, point));
                }
            }

            sc.close();
        } catch (Exception ex) {
            System.out.println("An error occurred while reading the student file.");
        }
    }

    /**
     * Writes the internal student list to a file, sorted alphabetically by
     * name.
     *
     * @param fileName name of the file to write to
     */
    public void writeFile(String fileName) {
        studentsList.sort(Comparator.comparing(Student::getName)); // Sort by name

        try (FileWriter fw = new FileWriter(fileName)) {
            for (Student stu : studentsList) {
                fw.write(stu.toStringFormatted() + "\n");
            }
        } catch (Exception ex) {
            System.out.println("An error occurred while writing to the file: " + ex.getMessage());
        }
    }

    /**
     * Adds a new student to the list and saves the updated list to file.
     *
     * @param fileName the file path to save data
     * @param student the new student to be added
     */
    public void addNew(String fileName, Student student) {
        read(); // Load current data into studentsList
        studentsList.add(student); // Add new student
        writeFile(fileName); // Save updated list
    }
}
