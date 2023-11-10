package com.company;



import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);

            System.out.println("Welcome to the CGPA Calculator");

            // Ask the user for the number of courses
            System.out.print("Enter the number of courses: ");
            int numCourses = input.nextInt();

            double totalGradePoints = 0.0;
            int totalCourseUnit = 0;

            for (int i = 1; i <= numCourses; i++) {
                System.out.println("Enter details for Course " + i);

                // Ask for the course name
                System.out.print("Course Name: ");
                String courseName = input.next();

                // Ask for the credit hours
                System.out.print("Course Unit: ");
                int CourseUnit = input.nextInt();

                // Ask for the grade earned (assuming a 7.0 scale)
                System.out.print("Grade (7.0 Scale): ");
                double grade = input.nextDouble();

                // Calculate the grade points for this course
                double gradePoints = CourseUnit * grade;

                // Add to the total grade points and credit hours
                totalGradePoints += gradePoints;
                totalCourseUnit += CourseUnit;
            }

            // Calculate the CGPA
            double cgpa = totalGradePoints / totalCourseUnit;

            System.out.println("Your CGPA is: " + cgpa);

            input.close();
        }
    }

