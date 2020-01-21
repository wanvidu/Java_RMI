package controller;

import java.rmi.Naming;
import java.util.List;

import model.Category;
import model.Question;
import repository.QuestionRepostitory;

public class QuestionController implements QuestionRepostitory {
	
	QuestionRepostitory questionRepostitory;

	public QuestionController() {
		super();
		
		try {
			questionRepostitory =   (QuestionRepostitory) Naming.lookup("rmi://localhost/question");
		} catch (Exception e) {
			System.out.println("A problem occured: "+e.toString());
			e.printStackTrace();
			System.out.println("Unable to connect to server.");
		} 
	}

	@Override
	public int getNumberOfQuestions() {
		return questionRepostitory.getNumberOfQuestions();
	}

	@Override
	public Question getQuestion(int id) {
		return questionRepostitory.getQuestion(id);
	}

	@Override
	public void submitAnswer(int id, int answer_id) {
		questionRepostitory.submitAnswer(id, answer_id);
	}

	@Override
	public int getTotal() {
		return questionRepostitory.getTotal();
	}

	@Override
	public void resetQuiz() {
		questionRepostitory.resetQuiz();
	}

	@Override
	public List<Category> getCategories() {
		return questionRepostitory.getCategories();
	}

	@Override
	public String[][] getCategorieCount() {
		return questionRepostitory.getCategorieCount();
	}
	
	

}
