import java.io.*;
import java.util.ArrayList;

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
        students.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                students.add(new Student(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        Integer.parseInt(parts[2])
                ));
            }
        } catch (IOException e) {
            System.out.println("No previous data found.");
        }
    }
}
