package repository;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.Category;
import model.Question;

public interface QuestionRepostitory extends Remote {

	public int getNumberOfQuestions() throws RemoteException;

	public Question getQuestion(int id) throws RemoteException;

	public void submitAnswer(int id, int answer_id) throws RemoteException;

	public int getTotal() throws RemoteException;

	public void resetQuiz() throws RemoteException;

	public List<Category> getCategories() throws RemoteException;

	public String[][] getCategorieCount() throws RemoteException;
}
