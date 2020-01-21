package repository;

import java.rmi.Remote;

import model.Answer;

public interface AnswerRepostitory extends Remote {
	
	public Answer getAnswer(int id);

}
