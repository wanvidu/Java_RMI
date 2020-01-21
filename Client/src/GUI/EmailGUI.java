package GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.GmailController;

import model.Category;
import model.User;
import controller.CategoryController;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.optionalusertools.TimeChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.github.lgooddatepicker.zinternaltools.TimeChangeEvent;
import javax.swing.JTextField;

public class EmailGUI extends JFrame {

	private static final long serialVersionUID = 4354231192186548638L;
	
	private JPanel contentPane;
	
	GmailController gmailController;
	CategoryController categoryController;
	private JTable table;
	
	String dateString="";
	String timeString="";
	private JTextField txtVenue;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmailGUI frame = new EmailGUI();
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
	public EmailGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(900, 800));
		pack();
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		gmailController=new GmailController();
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(26, 729, 96, 26);
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new AdminGUI().setVisible(true);
			}
		});
		
		contentPane.add(btnHome);
		
		categoryController=new CategoryController();
		List<Category> categoryList=categoryController.getAllCategory();
		
		String columns[]={"ID","Username","Email","Marks","Category"};
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		DefaultTableModel userDefaultTableModel=(DefaultTableModel)table.getModel();
		userDefaultTableModel.setColumnIdentifiers(columns);
		table.setBounds(46, 118, 800, 175);
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		JScrollPane sp=new JScrollPane(table);
		sp.setBounds(46, 118,800, 175);
		contentPane.add(sp);
		
		JComboBox<Category> comboBox = new JComboBox<Category>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel tableModel=(DefaultTableModel)table.getModel();
				tableModel.setRowCount(0);
				
				Object selectedCategory=comboBox.getSelectedItem();
				
				List<User> userList=gmailController.getSelectedUsers((Category)selectedCategory);
				
				for (User user : userList) {
					String temp[]=new String[columns.length];
					
					temp[0]=String.valueOf(user.getId());
					temp[1]=user.getUserName();
					temp[2]=user.getEmail();
					temp[3]=String.valueOf(user.getMarks());
					
					Category category=categoryController.getCategory(user.getCategory1());
					
					temp[4]=category.getCategory_name();
					
					System.out.println(Arrays.toString(temp));
					
					tableModel.addRow(temp);
				}
				
				System.out.println("------------------------------");
				
				table.setModel(tableModel);
				
				tableModel.fireTableDataChanged();

			}
		});
		
		for (Category category : categoryList) {
			comboBox.addItem(category);
		}
		
		comboBox.setBounds(205, 38, 141, 32);
		contentPane.add(comboBox);
		
		JLabel lblSelectCatego = new JLabel("Select Category");
		lblSelectCatego.setBounds(46, 42, 169, 16);
		contentPane.add(lblSelectCatego);		
		JLabel lblNewLabel = new JLabel("Date Time");
		lblNewLabel.setBounds(46, 366, 96, 26);
		contentPane.add(lblNewLabel);
		
		DateTimePicker dateTimePicker = new DateTimePicker();
		dateTimePicker.getDatePicker().addDateChangeListener(new DateChangeListener() {
			public void dateChanged(DateChangeEvent arg0) {
				dateString= dateTimePicker.getDatePicker().getText();
				System.out.println(dateString);
			}
		});
		dateTimePicker.getTimePicker().addTimeChangeListener(new TimeChangeListener() {
			public void timeChanged(TimeChangeEvent arg0) {
				timeString= dateTimePicker.getTimePicker().getText();
				System.out.println(timeString);
			}
		});
		dateTimePicker.setBounds(150,365, 300, 30);
		contentPane.add(dateTimePicker);
		
		JLabel lblVenue = new JLabel("Venue");
		lblVenue.setBounds(46, 452, 72, 16);
		contentPane.add(lblVenue);
		
		txtVenue = new JTextField();
		txtVenue.setBounds(150, 444, 196, 32);
		contentPane.add(txtVenue);
		txtVenue.setColumns(10);
		
		JButton btnNewButton = new JButton("Preview");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (dateString.equals("")||timeString.equals("")||txtVenue.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Fields cannot be empty.", "Email",
							JOptionPane.PLAIN_MESSAGE);
				}else {
					Object selectedCategory=comboBox.getSelectedItem();
					
					List<User> userList=gmailController.getSelectedUsers((Category)selectedCategory);
					
					new EmailPreviewGUI(userList.get(0), dateString,timeString,txtVenue.getText()).setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(233, 590, 119, 26);
		contentPane.add(btnNewButton);
		
		JButton btnSendEmail = new JButton("Send Email");
		btnSendEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Do you want to send these emails?", "Email",
						JOptionPane.YES_NO_OPTION);

				if (reply == JOptionPane.YES_OPTION) {
					Object selectedCategory=comboBox.getSelectedItem();
					
					List<User> userList=gmailController.getSelectedUsers((Category)selectedCategory);
					
					for (User user : userList) {
						Category category=categoryController.getCategory(user.getCategory1());
						
						String bodyText="\tYou, "+user.getUserName()+" successfully completed the quiz. "+
								"Your mark is "+user.getMarks()+". "+
								"Most suitable category for you is "+category.getCategory_name()+".\n\n\n"+
								category.getCategory_name()+"\n\n"+category.getDescription()+"\n\n\n"+
								"\tTo inform you more abouth this chosen category a meeting will be held on "+
								dateString+" on "+timeString+" at "+txtVenue.getText()+".\n\n"+
								"We expect your attendance.\n\n"+
								"Thank you.";
						
						gmailController.sendMessage(user.getEmail(), "To inform abouth upcoming funcion.", bodyText);
					}
				}
			}
		});
		btnSendEmail.setBounds(729, 706, 119, 26);
		contentPane.add(btnSendEmail);
	}
}

		
		