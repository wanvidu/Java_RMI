package repository;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.Answer;

public interface AnswerRepostitory extends Remote {

	public Answer getAnswer(int id) throws RemoteException;

}
