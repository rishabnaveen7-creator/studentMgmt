package com.studentmgmt.service;



import com.studentmgmt.exception.StudentNotFoundException;
import com.studentmgmt.model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Business logic for managing students (in-memory storage).
 * A LinkedHashMap gives O(1) lookup by ID while keeping insertion order.
 */
public class StudentService {
    private final Map<Integer, Student> students = new LinkedHashMap<>();
    private int nextId = 1;

    public Student addStudent(String name, int age, String course, double marks) {
        Student s = new Student(nextId++, name, age, course, marks);
        students.put(s.getId(), s);
        return s;
    }

    public Student getById(int id) {
        Student s = students.get(id);
        if (s == null) {
            throw new StudentNotFoundException(id);
        }
        return s;
    }

    public List<Student> getAll() {
        return new ArrayList<>(students.values());
    }

    public List<Student> searchByName(String keyword) {
        String key = keyword.toLowerCase();
        List<Student> result = new ArrayList<>();
        for (Student s : students.values()) {
            if (s.getName().toLowerCase().contains(key)) {
                result.add(s);
            }
        }
        return result;
    }

    public void updateStudent(int id, String name, Integer age, String course, Double marks) {
        Student s = getById(id);
        if (name != null) s.setName(name);
        if (age != null) s.setAge(age);
        if (course != null) s.setCourse(course);
        if (marks != null) s.setMarks(marks);
    }

    public void deleteStudent(int id) {
        getById(id); // throws if missing
        students.remove(id);
    }

    public List<Student> sortedByMarksDesc() {
        List<Student> list = getAll();
        list.sort(Comparator.comparingDouble(Student::getMarks).reversed());
        return list;
    }

    public List<Student> sortedByName() {
        List<Student> list = getAll();
        list.sort(Comparator.comparing((Student s) -> s.getName().toLowerCase()));
        return list;
    }

    public Optional<Student> topper() {
        return students.values().stream().max(Comparator.comparingDouble(Student::getMarks));
    }

    public double averageMarks() {
        return students.values().stream().mapToDouble(Student::getMarks).average().orElse(0.0);
    }

    public int count() {
        return students.size();
    }
}
