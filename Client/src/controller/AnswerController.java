package controller;

import java.rmi.Naming;

import model.Answer;
import repository.AnswerRepostitory;

public class AnswerController implements AnswerRepostitory {
	
	AnswerRepostitory answerRepostitory;
	
	public AnswerController(){
		super();
	
		try {
			answerRepostitory =   (AnswerRepostitory) Naming.lookup("rmi://localhost/answer");
		} catch (Exception e) {
			System.out.println("A problem occured: "+e.toString());
			e.printStackTrace();
			System.out.println("Unable to connect to server.");
		} 
	}

	@Override
	public Answer getAnswer(int id) {
		return answerRepostitory.getAnswer(id);
	}

	

}
