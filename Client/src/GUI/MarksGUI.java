package GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.QuestionController;
import model.Category;
import model.User;
import controller.CategoryController;
import controller.UserController;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class MarksGUI extends JFrame {

	private static final long serialVersionUID = 2479598236263702104L;

	private JPanel contentPane;
	
	static QuestionController questionController = new QuestionController();
	CategoryController categoryController=new CategoryController();
	UserController userController= new UserController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MarksGUI frame = new MarksGUI();
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
	public MarksGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(900, 800));
		pack();
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                questionController.resetQuiz();
            }
        });
		
		JLabel lblScore = new JLabel();
		lblScore.setFont(new Font("Dialog", Font.BOLD, 18));
		lblScore.setBounds(89, 46, 164, 31);
		contentPane.add(lblScore);
		lblScore.setText(String.valueOf(questionController.getTotal()));
		
		JButton btnHome = new JButton("Home");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				questionController.resetQuiz();
				dispose();
				new MainGUI().setVisible(true);
			}
		});
		btnHome.setBounds(670, 48, 119, 26);
		contentPane.add(btnHome);
		
		List<Category> list=questionController.getCategories();
		
		int x=50;
		int y=120;
		
		for (Category category : list) {
			
			JLabel jLabel=new JLabel();
			jLabel.setBounds(x,y,800,50);
			contentPane.add(jLabel);
			jLabel.setText(category.getCategory_name());
			
			JLabel jLabel1=new JLabel();
			jLabel1.setBounds(x,y+20,800,200);
			contentPane.add(jLabel1);
			jLabel1.setText("<html><p>"+category.getDescription()+"</p></html>");
			
			y+=200;
		}
		
		User user=userController.getCurrentUser();
		
		user.setMarks(questionController.getTotal());
		user.setCategory1(list.get(0).getId());
		if(list.size()==2)
			user.setCategory2(list.get(1).getId());
		
		if(!userController.update(user))
			throw new RuntimeException();
	}

}
