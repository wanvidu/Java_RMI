package repository;

import java.rmi.Remote;
import java.util.List;

import model.Category;
import model.User;

public interface GmailRepository extends Remote {
	public void sendMessage(String to, String subject, String bodyText);
	
	public List<User> getSelectedUsers(Category category);
}
