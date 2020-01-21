package GUI;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.AdminController;
import model.Admin;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private static final long serialVersionUID = 994895331067032702L;
	
	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword;

	private AdminController adminController=new AdminController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(500, 400));
		pack();
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setBounds(71, 70, 98, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(71, 137, 72, 16);
		contentPane.add(lblNewLabel_1);

		txtUserName = new JTextField();
		txtUserName.setBounds(225, 68, 114, 20);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Admin admin = adminController.findByName(txtUserName.getText());
				
				if (txtUserName.getText().equals("") || String.valueOf(txtPassword.getPassword()).equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Fields cannot be empty.", "Login",
							JOptionPane.PLAIN_MESSAGE);
				} else if (admin == null) {
					JOptionPane.showMessageDialog(rootPane, "User does not exist.", "Login", JOptionPane.PLAIN_MESSAGE);
				} else {
					if (admin.getPassword().equals(String.valueOf(txtPassword.getPassword()))) {
						dispose();
						new AdminGUI().setVisible(true);
					} else {
						JOptionPane.showMessageDialog(rootPane, "Password does not match.", "Login",
								JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});
		btnLogin.setBounds(220, 187, 119, 26);
		contentPane.add(btnLogin);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(225, 135, 114, 20);
		contentPane.add(txtPassword);
	}
}
