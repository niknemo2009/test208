package com.parking;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Startup extends JFrame {
    Container container;//container for all content

    public Startup(DBController dbController){
        super("Private parking");

        //Параметри для розташуання форми в центрі екрану
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = ProgramScreenSize.WIDTH.getSize();
        int sizeHeight = ProgramScreenSize.HEIGHT.getSize();
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        this.setBounds(locationX, locationY, sizeWidth, sizeHeight);//розташування форми
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(sizeWidth, sizeHeight);
        this.setVisible(true);
        this.setResizable(false);

        //Контейнер для додавання елементів
        container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.setPreferredSize(new Dimension(sizeWidth, sizeHeight));

        //Основні елементи, з яких складається програма
        Authorization authorization = new Authorization();
        Registration registration = new Registration();

        //JPanel, які мають бути показані в залежності від обраних опцій
        JPanel authorizationPanel = authorization.getMainPanelAuthorization();
        JPanel registrationPanel = registration.getMainPanelRegistration();

        //Початкові налаштування
        container.add(authorizationPanel);
        container.revalidate();

        //АВТОРИЗАЦІЯ
        //Buttons
        JButton registerButton = authorization.getRegisterButton();
        JButton continueButton = authorization.getContinueButton();

        //Data
        JTextField loginField = authorization.getLoginField();
        JPasswordField passwordField = authorization.getPasswordField();

        //Event listeners
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.remove(authorizationPanel);
                container.repaint();
                container.add(registrationPanel);
                container.revalidate();
            }
        });
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //PARKING INFO
                String login = loginField.getText().trim();
                String password = new String(passwordField.getPassword());

                if(login.length() > 0 && password.length() > 0){
                    int parkingId = dbController.authorization(login, password);
                    if(parkingId != 0){
                        dbController.setCurrentParkingId(parkingId);

                        ParkingInfo parkingInfo = new ParkingInfo(dbController, container, authorizationPanel);
                        Container infoPanel = parkingInfo.getContainer();

                        //Update view
                        container.remove(authorizationPanel);
                        container.repaint();
                        container.add(infoPanel);
                        container.revalidate();
                    }else{//неправильно введені пароль або логін
                        JOptionPane.showMessageDialog(null, "Invalid login or password");
                        authorization.clearForm();
                    }
                }else{//якщо при авторизації не заповнили поля
                    JOptionPane.showMessageDialog(null, "Fill all fields!");
                }
            }
        });

        //РЕЄСТРАЦІЯ
        //Buttons
        JButton returnFromRegistration = registration.getReturnButton();
        JButton registerNewParking = registration.getRegisterButton();

        //Text fields
        JTextField newLogin = registration.getLoginField();
        JTextField newPassword = registration.getPasswordField();
        JTextField placesQuantityField = registration.getPlacesQuantityField();
        JTextField placePriceField = registration.getPlacePriceField();

        //Event listeners
        returnFromRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.remove(registrationPanel);
                container.repaint();
                container.add(authorizationPanel);
                container.revalidate();
            }
        });

        registerNewParking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(registration.isValid()){
                    String login = newLogin.getText();
                    String password = newPassword.getText();
                    int placesQuantity = Integer.parseInt(placesQuantityField.getText());
                    float placePrice = Float.parseFloat(placePriceField.getText());

                    int parkingId = dbController.registration(login, password, placesQuantity, placePrice);
                    dbController.setCurrentParkingId(parkingId);

                    ParkingInfo parkingInfo = new ParkingInfo(dbController, container, authorizationPanel);
                    Container infoPanel = parkingInfo.getContainer();

                    //Update view
                    container.remove(registrationPanel);
                    container.repaint();
                    container.add(infoPanel);
                    container.revalidate();
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid registration data!");
                    registration.clearForm();
                }
            }
        });
    }
}
