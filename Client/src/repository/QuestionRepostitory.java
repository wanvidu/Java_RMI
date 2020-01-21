package repository;

import java.rmi.Remote;
import java.util.List;

import model.Category;
import model.Question;

public interface QuestionRepostitory extends Remote {
	
	public int getNumberOfQuestions();

	public Question getQuestion(int id); 
	
	public void submitAnswer(int id, int answer_id) ;
	
	public int getTotal();
	
	public void resetQuiz();
	
	public List<Category> getCategories();
	
	public String[][] getCategorieCount();
}
