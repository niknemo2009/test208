package View.Panels;

import javax.swing.*;
import java.awt.*;

public class AuthorizationView extends JFrame {
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton logInButton;
    private JLabel titleLabel;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JPanel authorizationPanel;

    public AuthorizationView() {
        this.setContentPane(this.authorizationPanel);
    }

    public void reset() {
        this.loginField.setText(null);
        this.passwordField.setText(null);
    }

    public JTextField getLoginField() {
        return loginField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLogInButton() {
        return logInButton;
    }

    public JPanel getAuthorizationPanel() {
        return authorizationPanel;
    }


}
