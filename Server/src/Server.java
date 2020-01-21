import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.GeneralSecurityException;

import javax.mail.MessagingException;

import repository.UserRepository;
import controller.UserController;

import repository.AdminRepository;
import controller.AdminController;

import repository.QuestionRepostitory;
import controller.QuestionController;

import repository.AnswerRepostitory;
import controller.AnswerController;

import repository.CategoryRepository;
import controller.CategoryController;

import repository.GmailRepository;
import controller.GmailController;

public class Server {

	public static void main(String[] args) throws GeneralSecurityException, IOException, MessagingException {
		System.out.println("Attempting to start the Server...");
		try {
			Registry registry = LocateRegistry.createRegistry(1099);

			UserRepository user = new UserController();
			registry.rebind("user", user);

			AdminRepository admin = new AdminController();
			registry.rebind("admin", admin);

			QuestionRepostitory question = new QuestionController();
			registry.rebind("question", question);

			AnswerRepostitory answer = new AnswerController();
			registry.rebind("answer", answer);

			CategoryRepository category = new CategoryController();
			registry.rebind("category", category);

			GmailRepository email = new GmailController();
			registry.rebind("email", email);

			System.out.println("Service started.");

		} catch (RemoteException e) {
			System.out.println("An error occured: " + e.toString());
			e.printStackTrace();
		}
	}

}
