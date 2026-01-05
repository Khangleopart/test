/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s01;

/**
 * S01 - Main class to run the Student Management application.
 *
 * This class serves as the entry point for launching the student management
 * system. It initializes required components and invokes the interactive menu
 * for the user.
 *
 * Author: Truong Hoang Khang - CE190729
 */
public class S01 {

    /**
     * The main method - entry point of the application.
     *
     * Algorithm: 1. Create an instance of StudentManager, which handles all
     * student-related operations. 2. Call the menu() method to show the user
     * interface and respond to user input.
     *
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Step 1: Create a new instance of StudentManager
        StudentManager manager = new StudentManager();

        // Step 2: Launch the interactive menu for managing students
        manager.menu();
        ////hihihihihi
    }
}
