package com.studentmgmt;

import com.studentmgmt.exception.StudentNotFoundException;
import com.studentmgmt.model.Student;
import com.studentmgmt.service.StudentService;
import com.studentmgmt.util.InputHelper;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final StudentService service = new StudentService();
    private static InputHelper in;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        in = new InputHelper(sc);

        seedSampleData();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = in.readInt("Enter choice: ", 1, 10);
            try {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> printTable(service.getAll());
                    case 3 -> searchById();
                    case 4 -> searchByName();
                    case 5 -> updateStudent();
                    case 6 -> deleteStudent();
                    case 7 -> printTable(service.sortedByMarksDesc());
                    case 8 -> printTable(service.sortedByName());
                    case 9 -> showStatistics();
                    case 10 -> {
                        System.out.println("Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("  Invalid choice.");
                }
            } catch (StudentNotFoundException e) {
                System.out.println("  Error: " + e.getMessage());
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
        System.out.println(" 1. Add student");
        System.out.println(" 2. View all students");
        System.out.println(" 3. Search by ID");
        System.out.println(" 4. Search by name");
        System.out.println(" 5. Update student");
        System.out.println(" 6. Delete student");
        System.out.println(" 7. Sort by marks (high to low)");
        System.out.println(" 8. Sort by name (A-Z)");
        System.out.println(" 9. Statistics (topper & average)");
        System.out.println("10. Exit");
    }

    private static void addStudent() {
        String name = in.readNonEmpty("Name   : ");
        int age = in.readInt("Age    : ", 3, 100);
        String course = in.readNonEmpty("Course : ");
        double marks = in.readDouble("Marks  : ", 0, 100);
        Student s = service.addStudent(name, age, course, marks);
        System.out.println("  Student added with ID " + s.getId());
    }

    private static void searchById() {
        int id = in.readInt("Enter ID: ", 1, Integer.MAX_VALUE);
        printTable(List.of(service.getById(id)));
    }

    private static void searchByName() {
        String key = in.readNonEmpty("Enter name (or part of it): ");
        printTable(service.searchByName(key));
    }

    private static void updateStudent() {
        int id = in.readInt("Enter ID to update: ", 1, Integer.MAX_VALUE);
        Student s = service.getById(id);
        System.out.println("Current: " + s);
        System.out.println("(Press Enter to keep the existing value)");

        String name = in.readOptional("New name   : ");
        String ageStr = in.readOptional("New age    : ");
        String course = in.readOptional("New course : ");
        String marksStr = in.readOptional("New marks  : ");

        try {
            Integer age = ageStr == null ? null : Integer.valueOf(ageStr);
            Double marks = marksStr == null ? null : Double.valueOf(marksStr);
            service.updateStudent(id, name, age, course, marks);
            System.out.println("  Student updated.");
        } catch (NumberFormatException e) {
            System.out.println("  Invalid number entered. Update cancelled.");
        }
    }

    private static void deleteStudent() {
        int id = in.readInt("Enter ID to delete: ", 1, Integer.MAX_VALUE);
        service.deleteStudent(id);
        System.out.println("  Student deleted.");
    }

    private static void showStatistics() {
        if (service.count() == 0) {
            System.out.println("  No students yet.");
            return;
        }
        System.out.println("Total students : " + service.count());
        System.out.printf("Average marks  : %.2f%n", service.averageMarks());
        service.topper().ifPresent(t ->
                System.out.println("Topper         : " + t.getName() + " (" + t.getMarks() + ")"));
    }

    private static void printTable(List<Student> list) {
        if (list.isEmpty()) {
            System.out.println("  No records found.");
            return;
        }
        String line = "+------+----------------------+-----+------------------+--------+-------+";
        System.out.println(line);
        System.out.println("| ID   | Name                 | Age | Course           | Marks  | Grade |");
        System.out.println(line);
        list.forEach(System.out::println);
        System.out.println(line);
    }

    private static void seedSampleData() {
        service.addStudent("Arjun Rao", 20, "Computer Science", 88.5);
        service.addStudent("Meera Nair", 21, "Information Tech", 92.0);
        service.addStudent("Rahul Verma", 19, "Electronics", 67.25);
    }
}
