package com.studentmgmt.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(int id) {
        super("No student found with ID: " + id);
    }
}
