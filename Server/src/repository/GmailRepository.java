package repository;

import java.util.List;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.mail.MessagingException;

import model.User;
import model.Category;

public interface GmailRepository extends Remote {
	public void sendMessage(String to, String subject, String bodyText)
			throws MessagingException, IOException, RemoteException;
	
	public List<User> getSelectedUsers(Category category) throws RemoteException;
}
