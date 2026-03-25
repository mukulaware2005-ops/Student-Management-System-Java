import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentGUI {

    JFrame frame;
    JTextField idField, nameField, ageField;
    JTable table;
    DefaultTableModel model;

    public StudentGUI() {

        frame = new JFrame("Student Management System");
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();

        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Age:"));
        formPanel.add(ageField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 6, 10, 10));

        JButton add = new JButton("Add");
        JButton update = new JButton("Update");
        JButton delete = new JButton("Delete");
        JButton search = new JButton("Search");
        JButton clear = new JButton("Clear");
        JButton refresh = new JButton("Refresh");

        buttonPanel.add(add);
        buttonPanel.add(update);
        buttonPanel.add(delete);
        buttonPanel.add(search);
        buttonPanel.add(clear);
        buttonPanel.add(refresh);

        model = new DefaultTableModel(new String[]{"ID", "Name", "Age"}, 0);
        table = new JTable(model);

        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        Main.loadFromFile();
        refreshTable();

        // ADD
        add.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());

                for (Student s : Main.students) {
                    if (s.getId() == id) {
                        JOptionPane.showMessageDialog(frame, "ID exists!");
                        return;
                    }
                }

                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());

                Main.students.add(new Student(id, name, age));
                Main.saveToFile();
                refreshTable();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        // UPDATE
        update.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());

            for (Student s : Main.students) {
                if (s.getId() == id) {
                    s.setName(nameField.getText());
                    s.setAge(Integer.parseInt(ageField.getText()));
                    Main.saveToFile();
                    refreshTable();
                    return;
                }
            }

            JOptionPane.showMessageDialog(frame, "Not Found!");
        });

        // DELETE
        delete.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());

            boolean found = Main.students.removeIf(s -> s.getId() == id);

            if (found) {
                Main.saveToFile();
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(frame, "Not Found!");
            }
        });

        // SEARCH
        search.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());

            for (Student s : Main.students) {
                if (s.getId() == id) {
                    JOptionPane.showMessageDialog(frame,
                            "Name: " + s.getName() + "\nAge: " + s.getAge());
                    return;
                }
            }

            JOptionPane.showMessageDialog(frame, "Not Found!");
        });

        // CLEAR
        clear.addActionListener(e -> {
            idField.setText("");
            nameField.setText("");
            ageField.setText("");
        });

        // REFRESH
        refresh.addActionListener(e -> refreshTable());

        // TABLE CLICK
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                idField.setText(model.getValueAt(row, 0).toString());
                nameField.setText(model.getValueAt(row, 1).toString());
                ageField.setText(model.getValueAt(row, 2).toString());
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void refreshTable() {
        model.setRowCount(0);
        for (Student s : Main.students) {
            model.addRow(new Object[]{s.getId(), s.getName(), s.getAge()});
        }
    }

    public static void main(String[] args) {
        new StudentGUI();
    }
}
