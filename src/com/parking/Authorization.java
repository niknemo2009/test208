package com.parking;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Authorization extends JFrame{
    private JPanel mainPanelAuthorization;
    private JTextField loginField;
    private JButton continueButton;
    private JButton registerButton;
    private JPasswordField passwordField;
    private JLabel headerAuthorization;
    private JLabel labelName;
    private JLabel labelPassword;
    private JLabel suggestionLabel;

    public Authorization(){
        setContentPane(mainPanelAuthorization);

        //Оформлення
        headerAuthorization.setFont(ProgramFonts.HEADERS.getFont());
        headerAuthorization.setForeground(ProgramColors.HEADERS.getColor());

        labelName.setFont(ProgramFonts.LABELS.getFont());
        loginField.setFont(ProgramFonts.LABELS.getFont());

        labelPassword.setFont(ProgramFonts.LABELS.getFont());
        passwordField.setFont(ProgramFonts.LABELS.getFont());

        continueButton.setFont(ProgramFonts.BUTTONS.getFont());
        continueButton.setBackground(ProgramColors.BUTTONS.getColor());
        suggestionLabel.setFont(ProgramFonts.LABELS.getFont());
        registerButton.setFont(ProgramFonts.BUTTONS.getFont());
        registerButton.setBackground(ProgramColors.BUTTONS.getColor());
    }

    public void clearForm(){
        passwordField.setText(null);
        loginField.setText(null);
    }
    public JPanel getMainPanelAuthorization() {
        return mainPanelAuthorization;
    }
    public JTextField getLoginField() {
        return loginField;
    }
    public JPasswordField getPasswordField() {
        return passwordField;
    }
    public JButton getContinueButton() {
        return continueButton;
    }
    public JButton getRegisterButton() {
        return registerButton;
    }
}
