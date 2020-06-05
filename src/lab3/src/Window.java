import com.company.Avto;
import com.company.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Collection;
import java.util.Vector;

import javax.swing.*;

import javax.swing.border.BevelBorder;

public class Window extends JFrame {
    Manager_program ms = Manager_program.getInstance();
    private JList userList;
    JButton button1;
    JLabel label1, label2, label3;
    JTextField textField1;
    Us us = new Us();

    public Window()
    {
        setLayout(new FlowLayout());
        // Вход в систему
        button1 = new JButton("Войти в систему ");
        label1 = new JLabel("Введите имя и фамилию");
        textField1 = new JTextField(40);
        label2 = new JLabel("Войти в систему жр");
        label3 = new JLabel();
        add(label1);
        add(textField1);
        add(button1);

        add(label2);


        button1.addActionListener(us);
        Vector<String> big = new Vector<String>();
        for (int i=0; i < 7; i++) {
            big.add("  ~ " + i + ". ~");
        }

        JList<String> bigList = new JList<String>(big);
        add(bigList);
        JList<String> bigList1 = new JList<String>(big);


        Vector<User> st = new Vector<User>(ms.getAllusers());
        JPanel right = new JPanel();
        right.add(new JScrollPane(userList));
        // Задаем layout и задаем "бордюр" вокруг панели
        right.setLayout(new BorderLayout());
        right.setBorder(new BevelBorder(BevelBorder.RAISED));
        // Верхняя панель
        right.add(new JLabel("Атомобилисты"), BorderLayout.NORTH);
        // выводим данные
        userList = new JList(st);
        right.add(new JScrollPane(userList), BorderLayout.CENTER);
        getContentPane().add(right, BorderLayout.NORTH);

            Vector<Avto> str = new Vector<Avto>(ms.getAvto());
            JPanel right1 = new JPanel();
            right.add(new JScrollPane(userList));
            add(bigList1);
            // Задаем layout и задаем "бордюр" вокруг панели
            right1.setLayout(new BorderLayout());
            right1.setBorder(new BevelBorder(BevelBorder.RAISED));
            // Верхняя панель
            right1.add(new JLabel("Автомобили"), BorderLayout.NORTH);
            // выводим данные
            userList = new JList(str);
            right1.add(new JScrollPane(userList));
            getContentPane().add(right1, BorderLayout.NORTH);
        add(label3);

        setBounds(100, 100, 900, 460);
    }

    public class Us implements ActionListener{

        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == button1 )
            {
                String name_user = (textField1.getText());
                System.out.print(name_user);
                label3.setText(name_user);
            }

        }

    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Window wd = new Window();
                wd.setDefaultCloseOperation(EXIT_ON_CLOSE);
                wd.setVisible(true);
                wd.setTitle("Автопарк");
            }
        });
    }

}
