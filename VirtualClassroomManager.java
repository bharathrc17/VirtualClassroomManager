import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Classroom {
    private String name;

    public Classroom(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Classroom " + name;
    }
}

class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student " + id;
    }
}

class Assignment {
    private String details;

    public Assignment(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "Assignment: " + details;
    }
}
public class VirtualClassroomManager {
    private Map<String, Classroom> classrooms;
    private Map<Integer, Student> students;
    private Map<String, List<Assignment>> assignments;
    private int studentIdCounter = 1;
    private Scanner scanner;

    public VirtualClassroomManager() {
        classrooms = new HashMap<>();
        students = new HashMap<>();
        assignments = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void addClassroom(String className) {
        classrooms.put(className, new Classroom(className));
        System.out.println("Classroom Addition: " + classrooms.get(className) + " has been created.");
    }

    public void addStudent(String className, String studentName) {
        if (classrooms.containsKey(className)) {
            int studentId = studentIdCounter++;
            students.put(studentId, new Student(studentId, studentName));
            System.out.println("Student Addition: " + students.get(studentId) + " has been enrolled in " + className + ".");
        } else {
            System.out.println("Classroom " + className + " not found.");
        }
    }

    public void scheduleAssignment(String className, String assignmentDetails) {
        if (classrooms.containsKey(className)) {
            assignments.computeIfAbsent(className, k -> new ArrayList<>()).add(new Assignment(assignmentDetails));
            System.out.println("Assignment Scheduled: Assignment for " + className + " has been scheduled.");
        } else {
            System.out.println("Classroom " + className + " not found.");
        }
    }

    public void submitAssignment(int studentId, String className, String assignmentDetails) {
        if (students.containsKey(studentId) && classrooms.containsKey(className)) {
            List<Assignment> classroomAssignments = assignments.getOrDefault(className, new ArrayList<>());
            Assignment submittedAssignment = new Assignment(assignmentDetails);
            if (classroomAssignments.contains(submittedAssignment)) {
                System.out.println("Assignment Submission: Assignment submitted by " +
                        students.get(studentId) + " in " + className + ".");
            } else {
                System.out.println("Assignment " + assignmentDetails + " not found in " + className + ".");
            }
        } else {
            System.out.println("Student or Classroom not found.");
        }
    }

    public static void main(String[] args) {
        VirtualClassroomManager manager = new VirtualClassroomManager();
        Scanner inputScanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nVirtual Classroom Manager");
            System.out.println("1. Add Classroom");
            System.out.println("2. Add Student");
            System.out.println("3. Schedule Assignment");
            System.out.println("4. Submit Assignment");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = inputScanner.nextInt();
            inputScanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter classroom name: ");
                    String className = inputScanner.nextLine();
                    manager.addClassroom(className);
                    break;
                case 2:
                    System.out.print("Enter classroom name: ");
                    String studentClassName = inputScanner.nextLine();
                    System.out.print("Enter student name: ");
                    String studentName = inputScanner.nextLine();
                    manager.addStudent(studentClassName, studentName);
                    break;
                case 3:
                    System.out.print("Enter classroom name: ");
                    String assignmentClassName = inputScanner.nextLine();
                    System.out.print("Enter assignment details: ");
                    String assignmentDetails = inputScanner.nextLine();
                    manager.scheduleAssignment(assignmentClassName, assignmentDetails);
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    int studentId = inputScanner.nextInt();
                    inputScanner.nextLine();  // Consume newline character
                    System.out.print("Enter classroom name: ");
                    String submitClassName = inputScanner.nextLine();
                    System.out.print("Enter assignment details: ");
                    String submitAssignmentDetails = inputScanner.nextLine();
                    manager.submitAssignment(studentId, submitClassName, submitAssignmentDetails);
                    break;
                case 5:
                    inputScanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
