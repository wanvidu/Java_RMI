package controller;


import java.rmi.Naming;
import java.util.List;

import model.Category;
import model.User;
import repository.GmailRepository;

public class GmailController implements GmailRepository {
	
	GmailRepository gmailRepository;
	
	public GmailController() {
		super();
		
		try {
			gmailRepository =   (GmailRepository) Naming.lookup("rmi://localhost/email");
		} catch (Exception e) {
			System.out.println("A problem occured: "+e.toString());
			e.printStackTrace();
			System.out.println("Unable to connect to server.");
		} 
	}

	@Override
	public void sendMessage(String to, String subject, String bodyText) {
		gmailRepository.sendMessage(to, subject, bodyText);
	}

	@Override
	public List<User> getSelectedUsers(Category category) {
		return gmailRepository.getSelectedUsers(category);
	}

	
}
