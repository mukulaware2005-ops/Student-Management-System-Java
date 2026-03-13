import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static String FILE_NAME = "students.txt";
    static ArrayList<Student> students = new ArrayList<>();

    public static void saveToFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Student s : students) {
                writer.write(s.getId() + "," + s.getName() + "," + s.getAge());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    public static void loadFromFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);

                students.add(new Student(id, name, age));
            }

        } catch (IOException e) {
            System.out.println("No previous data found.");
        }
    }

    public static void main(String[] args) {

        loadFromFile();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Update Student");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent(sc);
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    deleteStudent(sc);
                    break;

                case 4:
                    searchStudent(sc);
                    break;

                case 5:
                    updateStudent(sc);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void addStudent(Scanner sc) {

        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        Student s = new Student(id, name, age);
        students.add(s);

        saveToFile();

        System.out.println("Student Added Successfully!");
    }

    public static void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student s : students) {
            System.out.println("ID: " + s.getId() +
                    ", Name: " + s.getName() +
                    ", Age: " + s.getAge());
        }
    }

    public static void deleteStudent(Scanner sc) {

        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();

        boolean found = false;

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getId() == id) {

                students.remove(i);
                saveToFile();

                found = true;

                System.out.println("Student Deleted Successfully!");
                break;
            }
        }

        if (!found) {
            System.out.println("Student Not Found!");
        }
    }

    public static void searchStudent(Scanner sc) {

        System.out.print("Enter Student ID to search: ");
        int id = sc.nextInt();

        boolean found = false;

        for (Student s : students) {

            if (s.getId() == id) {

                System.out.println("Student Found:");
                System.out.println("ID: " + s.getId() +
                        ", Name: " + s.getName() +
                        ", Age: " + s.getAge());

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student Not Found!");
        }
    }

    public static void updateStudent(Scanner sc) {

        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean found = false;

        for (Student s : students) {

            if (s.getId() == id) {

                System.out.print("Enter New Name: ");
                s.setName(sc.nextLine());

                System.out.print("Enter New Age: ");
                s.setAge(sc.nextInt());

                saveToFile();

                System.out.println("Student Updated Successfully!");

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student Not Found!");
        }
    }
}