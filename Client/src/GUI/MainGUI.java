package GUI;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
import controller.UserController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 8529779600744952258L;

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtEmail;

	UserController userController = new UserController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();

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
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(900, 800));
		pack();
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnTakeQuiz = new JButton("Take Quiz");
		btnTakeQuiz.setBounds(210, 274, 440, 26);
		btnTakeQuiz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				User user = userController.findByName(txtName.getText());

				if (txtName.getText().equals("") || txtEmail.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Fields cannot be empty.", "User",
							JOptionPane.PLAIN_MESSAGE);
				} else {
					if (user == null) {
						User newUser = new User();

						newUser.setUserName(txtName.getText());
						newUser.setEmail(txtEmail.getText());

						if (!userController.insert(newUser))
							throw new RuntimeException();
					} else if (!user.getEmail().equals(txtEmail.getText())) {
						user.setEmail(txtEmail.getText());

						if (!userController.update(user))
							throw new RuntimeException();
					}

					user = userController.findByName(txtName.getText());

					userController.setCurrentUser(user);

					dispose();
					new QuizGUI().setVisible(true);
				}
			}
		});
		contentPane.setLayout(null);

		contentPane.add(btnTakeQuiz);

		JButton btnAdminLogin = new JButton("Admin Login");
		btnAdminLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Login().setVisible(true);
			}
		});
		btnAdminLogin.setBounds(89, 421, 167, 26);
		contentPane.add(btnAdminLogin);

		JLabel lblNa = new JLabel("Name");
		lblNa.setBounds(166, 122, 72, 16);
		contentPane.add(lblNa);

		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(177, 181, 72, 16);
		contentPane.add(lblEmail);

		txtName = new JTextField();
		txtName.setBounds(380, 129, 114, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setText("");
		txtEmail.setBounds(380, 181, 114, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
	}
}
