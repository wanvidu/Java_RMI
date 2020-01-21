package GUI;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CategoryController;
import model.Category;
import model.User;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class EmailPreviewGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4306100093024160558L;
	private JPanel contentPane;
	
	CategoryController categoryController;


	public EmailPreviewGUI(User user,String date,String time,String venue) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(700, 600));
		pack();
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		categoryController=new CategoryController();

		JButton btnHome = new JButton("Home");
		btnHome.setBounds(12, 529, 96, 26);
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		contentPane.add(btnHome);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(32, 25, 613, 459);
		contentPane.add(textPane);
		
		Category category=categoryController.getCategory(user.getCategory1());

		
		String content="To : "+user.getEmail()+"\n\n"+
				"To inform abouth upcoming funcion\n\n\n"+
				"\tYou, "+user.getUserName()+" successfully completed the quiz. "+
				"Your mark is "+user.getMarks()+". "+
				"Most suitable category for you is "+category.getCategory_name()+".\n\n\n"+
				category.getCategory_name()+"\n\n"+category.getDescription()+"\n\n\n"+
				"\tTo inform you more abouth this chosen category a meeting will be held on "+
				date+" on "+time+" at "+venue+".\n\n"+
				"We expect your attendance.\n\n"+
				"Thank you.";
		textPane.setText(content);
	}
}
