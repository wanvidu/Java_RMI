package GUI;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import controller.QuestionController;
import controller.AnswerController;

import model.Question;
import model.Answer;

public class QuizGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6386918126879005281L;

	private JPanel contentPane;

	static QuestionController questionController = new QuestionController();
	AnswerController answerController = new AnswerController();

	int questionCount;
	int questionIndex;

	Question question;

	Answer answer1;
	Answer answer2;
	Answer answer3;
	Answer answer4;
	Answer answer5;
	Answer answer6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizGUI frame = new QuizGUI();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	void setup() {
		questionCount = questionController.getNumberOfQuestions();
		questionIndex = 1;

		displayQuestion(1);
	}

	public void displayQuestion(int id) {
		question = new Question();

		question = questionController.getQuestion(id);

		lblQuestionNumber.setText(String.valueOf(questionIndex));
		lblQuestion.setText("<html><p>"+question.getQuestion_text()+"</html></p>");

		answer1 = new Answer();
		answer1 = answerController.getAnswer(question.getAnswer_1_id());
		rdbtnAnswer1.setText(answer1.getAnswer_text());

		answer2 = new Answer();
		answer2 = answerController.getAnswer(question.getAnswer_2_id());
		rdbtnAnswer2.setText(answer2.getAnswer_text());

		answer3 = new Answer();
		answer3 = answerController.getAnswer(question.getAnswer_3_id());
		rdbtnAnswer3.setText(answer3.getAnswer_text());

		answer4 = new Answer();
		answer4 = answerController.getAnswer(question.getAnswer_4_id());
		rdbtnAnswer4.setText(answer4.getAnswer_text());

		answer5 = new Answer();
		answer5 = answerController.getAnswer(question.getAnswer_5_id());
		rdbtnAnswer5.setText(answer5.getAnswer_text());

		answer6 = new Answer();
		answer6 = answerController.getAnswer(question.getAnswer_6_id());
		rdbtnAnswer6.setText(answer6.getAnswer_text());
	}

	/**
	 * Create the frame.
	 */

	JLabel lblQuestionNumber;
	JLabel lblQuestion;
	JRadioButton rdbtnAnswer1;
	JRadioButton rdbtnAnswer2;
	JRadioButton rdbtnAnswer3;
	JRadioButton rdbtnAnswer4;
	JRadioButton rdbtnAnswer5;
	JRadioButton rdbtnAnswer6;
	JButton btnPrevious;
	JButton btnNext;
	ButtonGroup buttonGroup;

	public QuizGUI() {

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

		lblQuestionNumber = new JLabel("New label");
		lblQuestionNumber.setBounds(48, 26, 88, 100);
		contentPane.add(lblQuestionNumber);

		lblQuestion = new JLabel("New label");
		lblQuestion.setBounds(80, 26, 702, 100);
		contentPane.add(lblQuestion);

		rdbtnAnswer1 = new JRadioButton("New radio button");
		rdbtnAnswer1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				questionController.submitAnswer(question.getId(), answer1.getId());
			}
		});
		rdbtnAnswer1.setBounds(124, 108, 223, 24);
		contentPane.add(rdbtnAnswer1);

		rdbtnAnswer2 = new JRadioButton("New radio button");
		rdbtnAnswer2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				questionController.submitAnswer(question.getId(), answer2.getId());
			}
		});
		rdbtnAnswer2.setBounds(124, 136, 243, 24);
		contentPane.add(rdbtnAnswer2);

		rdbtnAnswer3 = new JRadioButton("New radio button");
		rdbtnAnswer3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				questionController.submitAnswer(question.getId(), answer3.getId());
			}
		});
		rdbtnAnswer3.setBounds(124, 164, 243, 24);
		contentPane.add(rdbtnAnswer3);

		rdbtnAnswer4 = new JRadioButton("New radio button");
		rdbtnAnswer4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				questionController.submitAnswer(question.getId(), answer4.getId());
			}
		});
		rdbtnAnswer4.setBounds(124, 192, 243, 24);
		contentPane.add(rdbtnAnswer4);

		rdbtnAnswer5 = new JRadioButton("New radio button");
		rdbtnAnswer5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				questionController.submitAnswer(question.getId(), answer5.getId());
			}
		});
		rdbtnAnswer5.setBounds(124, 220, 243, 24);
		contentPane.add(rdbtnAnswer5);

		rdbtnAnswer6 = new JRadioButton("New radio button");
		rdbtnAnswer6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				questionController.submitAnswer(question.getId(), answer6.getId());
			}
		});
		rdbtnAnswer6.setBounds(124, 248, 254, 24);
		contentPane.add(rdbtnAnswer6);

		buttonGroup = new ButtonGroup();

		buttonGroup.add(rdbtnAnswer1);
		buttonGroup.add(rdbtnAnswer2);
		buttonGroup.add(rdbtnAnswer3);
		buttonGroup.add(rdbtnAnswer4);
		buttonGroup.add(rdbtnAnswer5);
		buttonGroup.add(rdbtnAnswer6);

		btnPrevious = new JButton("<<");
		btnPrevious.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (questionIndex > 1 && questionIndex <= questionCount) {
						buttonGroup.clearSelection();
						questionIndex--;
						displayQuestion(questionIndex);
					}
			}
		});
		btnPrevious.setBounds(32, 386, 119, 26);
		contentPane.add(btnPrevious);

		btnNext = new JButton(">>");
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ButtonModel buttonModel = buttonGroup.getSelection();

				if (buttonModel == null) {
					JOptionPane.showMessageDialog(rootPane, "No radio button is selected", "Quiz",
							JOptionPane.PLAIN_MESSAGE);
				}

				else {
					if (questionIndex >= 1 && questionIndex < questionCount) {
						buttonGroup.clearSelection();
						questionIndex++;
						displayQuestion(questionIndex);
					} else if (questionIndex == questionCount) {
						int reply = JOptionPane.showConfirmDialog(null, "Do you want to submit this?", "Quiz",
								JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {
							dispose();
							MarksGUI marksGUI=new MarksGUI();
							marksGUI.setVisible(true);
							marksGUI.addWindowListener(new WindowAdapter() {
					            @Override
					            public void windowClosing(WindowEvent e) {
					                questionController.resetQuiz();
					            }
					        });
						}
					}
				}
			}
		});
		btnNext.setBounds(421, 386, 119, 26);
		contentPane.add(btnNext);
		
		JButton btnHome = new JButton("Home");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				questionController.resetQuiz();
				dispose();
				new MainGUI().setVisible(true);
			}
		});
		btnHome.setBounds(694, 247, 119, 26);
		contentPane.add(btnHome);

		setup();
	}
}
