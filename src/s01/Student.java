/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s01;

/*
 * Student - A class representing a student entity with attributes
 * including code, name, date of birth, and point. Provides constructors,
 * getters, setters, and formatted string representations.
 * 
 * Author: Truong Hoang Khang - CE190729
 */
public class Student {

    private String code;   // Student code, unique identifier
    private String name;   // Student full name
    private String date;   // Date of birth as a String (format: dd-MMM-yyyy)
    private double point;  // Student point (score)

    /**
     * Constructor to initialize a Student object with all attributes.
     *
     * @param code Student code.
     * @param name Student name.
     * @param date Date of birth as a string.
     * @param point Student point (score).
     */
    public Student(String code, String name, String date, double point) {
        this.code = code;     // Assign student code
        this.name = name;     // Assign student name
        this.date = date;     // Assign date of birth
        this.point = point;   // Assign student point
    }

    /**
     * Gets the student code.
     *
     * @return The student's unique code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the student code.
     *
     * @param code The unique student code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the student's name.
     *
     * @return The student's full name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the student's name.
     *
     * @param name The full name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the student's date of birth.
     *
     * @return The date of birth as a string.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the student's date of birth.
     *
     * @param date The date of birth string to set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the student's point (score).
     *
     * @return The student's point.
     */
    public double getPoint() {
        return point;
    }

    /**
     * Sets the student's point (score).
     *
     * @param point The point value to set.
     */
    public void setPoint(double point) {
        this.point = point;
    }

    /**
     * Returns a compact formatted string representation of the student, with
     * fields separated by '#' suitable for file storage.
     *
     * @return A single-line string containing all student attributes separated
     * by '#'.
     */
    public String toStringFormatted() {
        return this.code + "#" + this.name + "#" + this.date + "#" + this.point;
    }

    /**
     * Overrides the default toString method to provide a multi-line
     * human-readable string representation of the student.
     *
     * @return A formatted string detailing student's code, name, date of birth,
     * and point.
     */
    @Override
    public String toString() {
        return "Student code: " + this.code + "\n"
                + "Student name: " + this.name + "\n"
                + "Date of birth: " + this.date + "\n"
                + "Student point: " + String.format("%.1f", this.point);
    }
}
