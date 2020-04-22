package lr4;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;

public class Window extends JFrame {
        DefaultTableModel dm;

        Comparator<Student> weightComparator = new WeightComparator();
        Comparator<Student> heightComparator = new HeightComparator();
        Comparator<Student> surnameComparator = new SurnameComparator();

        Student test = new Student();

        JTable dataTable;
        private JPanel panel;
        private JTextField surnameTextField;
        private JTextField weightTextField;
        private JTextField heightTextField;
        private JButton surnameCompareButton;
        private JButton heightCompareButton;
        private JButton weightCompareButton;
        private JTextField groupTextField;
        private JButton changeGroupButton;
        private JButton addStudentButton;
        private JButton findButton;
        private JButton restoreButton;

        private void createColumns(){
            dm = (DefaultTableModel) dataTable.getModel();
            dm.addColumn("Surname");
            dm.addColumn("Weight");
            dm.addColumn("Height");
            dm.addColumn("Group");
        }

        private void populate(String surname, Double weight, Double height, Integer group){
            String[]rowData = {surname, weight.toString(), height.toString(), group.toString()};
            dm.addRow(rowData);
        }

        public Window(){
            setSize(800,600);
            setTitle("Students");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);
            add(panel);
            setVisible(true);

            createColumns();
            for(Student item : Student.data){
                populate(item.surname, item.weight, item.height, item.group);
            }

            EventListener event  = new EventListener();

            changeGroupButton.addActionListener(event);
            addStudentButton.addActionListener(event);
            surnameCompareButton.addActionListener(event);
            weightCompareButton.addActionListener(event);
            heightCompareButton.addActionListener(event);
            restoreButton.addActionListener(event);
            findButton.addActionListener(event);

            dataTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    surnameTextField.setText(dataTable.getValueAt(dataTable.getSelectedRow(), 0).toString());
                    weightTextField.setText(dataTable.getValueAt(dataTable.getSelectedRow(), 1).toString());
                    heightTextField.setText(dataTable.getValueAt(dataTable.getSelectedRow(), 2).toString());
                    groupTextField.setText(dataTable.getValueAt(dataTable.getSelectedRow(), 3).toString());
                }
            });
        }

        public class EventListener implements ActionListener {
            public void actionPerformed(ActionEvent action){

                if(action.getSource() == changeGroupButton){
                    Integer group = Integer.parseInt(groupTextField.getText());

                    dm.setValueAt(group , dataTable.getSelectedRow(), 3);
                    test.changeGroup(surnameTextField.getText(), group);
                }

                if(action.getSource() == addStudentButton){
                    Double weight = Double.parseDouble(weightTextField.getText());
                    Double height = Double.parseDouble(heightTextField.getText());
                    Integer group = Integer.parseInt(groupTextField.getText());

                    boolean flag = true;

                    for (Student item : Student.data){
                        if(item.surname.equals(surnameTextField.getText())
                                && item.group.equals(group)){
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        populate(surnameTextField.getText(), weight, height, group);
                        test.addStudent(surnameTextField.getText(), weight, height, group);
                    }
                    surnameTextField.setText("");
                    weightTextField.setText("");
                    heightTextField.setText("");
                    groupTextField.setText("");

                }

                if(action.getSource() == weightCompareButton){
                    int group = Integer.parseInt(groupTextField.getText());
                    ArrayList<Student> sorted;
                    sorted = test.sortGroup(weightComparator, group);
                    while(dm.getRowCount() > 0){
                        dm.removeRow(0);
                    }
                    for(Student item : sorted){
                        populate(item.surname, item.weight, item.height, item.group);
                    }
                }

                if(action.getSource() == heightCompareButton){
                    int group = Integer.parseInt(groupTextField.getText());
                    ArrayList<Student> sorted;
                    sorted = test.sortGroup(heightComparator, group);
                    while(dm.getRowCount() > 0){
                        dm.removeRow(0);
                    }
                    for(Student item : sorted){
                        populate(item.surname, item.weight, item.height, item.group);
                    }
                }

                if(action.getSource() == surnameCompareButton){
                    int group = Integer.parseInt(groupTextField.getText());
                    ArrayList<Student> sorted;
                    sorted = test.sortGroup(surnameComparator, group);
                    while(dm.getRowCount() > 0){
                        dm.removeRow(0);
                    }
                    for(Student item : sorted){
                        populate(item.surname, item.weight, item.height, item.group);
                    }
                }

                if(action.getSource() == findButton){
                    ArrayList<Student> toFind;
                    toFind = test.findStudent(surnameTextField.getText());
                    while(dm.getRowCount() > 0){
                        dm.removeRow(0);
                    }
                    for(Student item : toFind){
                        populate(item.surname, item.weight, item.height, item.group);
                    }
                }

                if(action.getSource() == restoreButton){
                    while(dm.getRowCount() > 0){
                        dm.removeRow(0);
                    }
                    for(Student item : Student.data){
                        populate(item.surname, item.weight, item.height, item.group);
                    }
                }
            }
        }
    }

