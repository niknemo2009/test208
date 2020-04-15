package com.parking;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Registration extends JFrame {
    private JTextField loginField;
    private JTextField placesQuantityField;
    private JTextField placePriceField;
    private JButton registerButton;
    private JPanel mainPanelRegistration;
    private JTextField passwordField;
    private JButton returnButton;

    private JLabel headerRegistration;
    private JLabel passwordLabel;
    private JLabel quantityLabel;
    private JLabel priceLabel;
    private JLabel loginLabel;

    //Validation
    private boolean valid = false;
    private boolean loginIsValid = false;
    private boolean passwordIsValid = false;
    private boolean placeIsValid = false;
    private boolean priceIsValid = false;

    public Registration() {
        setContentPane(mainPanelRegistration);

        //Оформлення
        headerRegistration.setFont(ProgramFonts.HEADERS.getFont());
        headerRegistration.setForeground(ProgramColors.HEADERS.getColor());

        loginLabel.setFont(ProgramFonts.LABELS.getFont());
        loginField.setFont(ProgramFonts.LABELS.getFont());

        passwordLabel.setFont(ProgramFonts.LABELS.getFont());
        passwordField.setFont(ProgramFonts.LABELS.getFont());

        quantityLabel.setFont(ProgramFonts.LABELS.getFont());
        placesQuantityField.setFont(ProgramFonts.LABELS.getFont());

        priceLabel.setFont(ProgramFonts.LABELS.getFont());
        placePriceField.setFont(ProgramFonts.LABELS.getFont());

        registerButton.setFont(ProgramFonts.BUTTONS.getFont());
        registerButton.setBackground(ProgramColors.BUTTONS.getColor());

        returnButton.setFont(ProgramFonts.BUTTONS.getFont());
        returnButton.setBackground(ProgramColors.BUTTONS.getColor());

        //Form validation
        loginField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                if(strIsValid(loginField.getText())){
                    loginIsValid = true;
                    loginField.setForeground(ProgramColors.TRUE.getColor());
                }else{
                    loginIsValid = false;
                    loginField.setForeground(ProgramColors.FALSE.getColor());
                }

                valid = loginIsValid && passwordIsValid && placeIsValid && priceIsValid;
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                if(strIsValid(passwordField.getText())){
                    passwordIsValid = true;
                    passwordField.setForeground(ProgramColors.TRUE.getColor());
                }else{
                    passwordIsValid = false;
                    passwordField.setForeground(ProgramColors.FALSE.getColor());
                }

                valid = loginIsValid && passwordIsValid && placeIsValid && priceIsValid;
            }
        });
        placesQuantityField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                if(isIntNumber(placesQuantityField.getText())){
                    placeIsValid = true;
                    placesQuantityField.setForeground(ProgramColors.TRUE.getColor());
                }else{
                    placeIsValid = false;
                    placesQuantityField.setForeground(ProgramColors.FALSE.getColor());
                }

                valid = loginIsValid && passwordIsValid && placeIsValid && priceIsValid;
            }
        });
        placePriceField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                if(isFloatNumber(placePriceField.getText())){
                    priceIsValid = true;
                    placePriceField.setForeground(ProgramColors.TRUE.getColor());
                }else{
                    priceIsValid = false;
                    placePriceField.setForeground(ProgramColors.FALSE.getColor());
                }

                valid = loginIsValid && passwordIsValid && placeIsValid && priceIsValid;
            }
        });
    }

    //Методи для роботи з класом
    @Override
    public boolean isValid() {
        return valid;
    }
    public void clearForm(){
        loginField.setText(null);
        passwordField.setText(null);
        placesQuantityField.setText(null);
        placePriceField.setText(null);
    }
    public JPanel getMainPanelRegistration() {
        return mainPanelRegistration;
    }
    public JTextField getLoginField() {
        return loginField;
    }
    public JTextField getPasswordField() {
        return passwordField;
    }
    public JTextField getPlacesQuantityField() {
        return placesQuantityField;
    }
    public JTextField getPlacePriceField() {
        return placePriceField;
    }
    public JButton getReturnButton() {
        return returnButton;
    }
    public JButton getRegisterButton() {
        return registerButton;
    }

    //Допоміжні методи
    private boolean isFloatNumber(String str){
        int digits=0;

        for(int i = 0 ; i < str.length(); i++){
            if(Character.isDigit(str.charAt(i)) || str.charAt(i) == '.'){
                digits++;
            }
        }

        return digits == str.length();
    }
    private  boolean isIntNumber(String str){
        int digits=0;

        for(int i = 0 ; i < str.length(); i++){
            if(Character.isDigit(str.charAt(i))){
                digits++;
            }
        }

        return digits == str.length();
    }
    private boolean strIsValid(String str){
        if(str.trim().length() > 3){
            for(int i = 0; i < str.trim().length(); i++){
                if(str.charAt(i) == ' '){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }
}
