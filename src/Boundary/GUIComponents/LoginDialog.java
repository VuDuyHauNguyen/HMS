package Boundary.GUIComponents;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Authentication;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class LoginDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JTextField txtEmail;
	private JPasswordField passwordField;

	/**
	 * Create the dialog.
	 */
	public LoginDialog(JFrame parent, boolean modal) {
		super(parent, modal);
		
		setTitle("Login to Hospital Management System");
		setBounds(100, 100, 300, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbEmail = new JLabel("Email");
			lbEmail.setBounds(27, 15, 46, 14);
			contentPanel.add(lbEmail);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setBounds(27, 40, 73, 14);
			contentPanel.add(lblPassword);
		}
		
		txtEmail = new JTextField();
		txtEmail.setBounds(102, 11, 150, 20);
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(102, 37, 150, 17);
		contentPanel.add(passwordField);
		{
			JButton btnLogin = new JButton("Login");
			btnLogin.setBounds(179, 65, 73, 23);
			contentPanel.add(btnLogin);
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {	
					
					
					if(Authentication.login(txtEmail.getText(), String.valueOf(passwordField.getPassword())) ==
							Authentication.SUCCESS_AUTHENTICATION) {
						setVisible(false);
						dispose();
					}
				}
			});
			btnLogin.setActionCommand("OK");
			getRootPane().setDefaultButton(btnLogin);
		}
	}
	
	public void showDialog() {
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
}
