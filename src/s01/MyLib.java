/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

/**
 * MyLib - A utility class providing methods for validated input from the user
 * including strings, names, points, unique codes, and date of birth. Includes
 * validation logic for each input type.
 *
 * Author: Truong Hoang Khang - CE190729
 */
public class MyLib {

    private static Scanner sc = new Scanner(System.in);  // Scanner object for reading user input

    /**
     * Reads a non-empty string from the user. Re-prompts if input is empty.
     *
     * Algorithm: 1. Prompt the user for input. 2. Trim the input and check if
     * it is non-empty. 3. If valid, return the string. Otherwise, re-prompt.
     *
     * @param message The prompt message displayed to the user.
     * @return A validated non-empty string.
     */
    public static String inputString(String message) {
        while (true) {
            System.out.print(message);  // Display prompt
            String input = sc.nextLine().trim();  // Read and trim input
            if (!input.isEmpty()) {
                return input;  // Valid non-empty string
            }
            System.out.println("Input cannot be empty.");  // Error message
        }
    }

    /**
     * Reads a valid name containing only letters and spaces.
     *
     * Algorithm: 1. Prompt the user for a name. 2. Trim and validate using
     * regular expression. 3. Re-prompt if invalid or empty.
     *
     * @param message The prompt message displayed to the user.
     * @return A validated name string.
     */
    public static String inputName(String message) {
        while (true) {
            System.out.print(message);  // Prompt for name
            String name = sc.nextLine().trim();  // Read and trim input
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");  // Empty name error
            } else if (!name.matches("^[a-zA-Z\\s]+$")) {  // Regex for letters and spaces only
                System.out.println("Name must contain only letters.");  // Invalid character error
            } else {
                return name;  // Valid name
            }
        }
    }

    /**
     * Reads a double point between 0 and 10 inclusive.
     *
     * Algorithm: 1. Prompt for input and trim whitespace. 2. Parse to double
     * and check range. 3. Catch format exceptions and re-prompt on error.
     *
     * @param message The prompt message displayed to the user.
     * @return A validated double value in [0, 10].
     */
    public static double inputPoint(String message) {
        while (true) {
            System.out.print(message);  // Prompt for point
            String input = sc.nextLine().trim();  // Read and trim input

            if (input.isEmpty()) {
                System.out.println("Point cannot be empty.");  // Empty input error
                continue;
            }

            try {
                double point = Double.parseDouble(input);  // Try to parse input to double
                if (point >= 0 && point <= 10) {
                    return point;  // Valid point
                } else {
                    System.out.println("Point must be between 0 and 10.");  // Out of range
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");  // Parsing error
            }
        }
    }

    /**
     * Reads a date of birth string in "dd-MMM-yyyy" format and validates age
     * range.
     *
     * Algorithm: 1. Prompt user for date in strict "dd-MMM-yyyy" format. 2.
     * Parse input string to Date object. 3. Convert to LocalDate and compute
     * age. 4. Validate if age is between minAge and maxAge. 5. Return valid
     * date string or re-prompt.
     *
     * @param message The prompt message displayed to the user.
     * @param minAge The minimum allowed age (inclusive).
     * @param maxAge The maximum allowed age (inclusive).
     * @return A validated date string in "dd-MMM-yyyy" format.
     */
    public static String inputDateOfBirth(String message, int minAge, int maxAge) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");  // Define date format
        sdf.setLenient(false);  // Disable leniency for strict format checking

        while (true) {
            System.out.print(message);  // Prompt for date of birth
            String input = sc.nextLine().trim();  // Read and trim input

            if (input.isEmpty()) {
                System.out.println("Date of Birth cannot be empty.");  // Empty input
                continue;
            }

            try {
                Date birthDate = sdf.parse(input);  // Parse string to Date object

                // Convert Date to LocalDate
                LocalDate birthLocal = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate today = LocalDate.now();  // Get current date
                int age = Period.between(birthLocal, today).getYears();  // Calculate age in years

                if (age < minAge || age > maxAge) {
                    int currentYear = today.getYear();
                    int minYear = currentYear - maxAge;  // Earliest valid year
                    int maxYear = currentYear - minAge;  // Latest valid year
                    System.out.println("Student must be between " + minYear + " and " + maxYear + " years old.");  // Age out of range
                } else {
                    return input;  // Valid age and date
                }
            } catch (ParseException e) {
                System.out.println("Invalid Date Of Birth format. Please use format dd-MMM-yyyy.");  // Format error
            }
        }
    }
}
