package controller;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.Message;

import Database_Layer.Database_Connection;
import model.Category;
import model.Question;
import model.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.ByteArrayOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import repository.GmailRepository;

public class GmailController extends UnicastRemoteObject implements GmailRepository {

	private static final long serialVersionUID = -2048689102966996732L;

	private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = "tokens";
	private static final String CREDENTIALS_FILE_PATH = "../resources/credentials.json";
	private static final List<String> SCOPES = Arrays.asList(GmailScopes.GMAIL_LABELS,GmailScopes.GMAIL_SEND);

	private static NetHttpTransport httpTransport;
	Gmail service;
	
	Database_Connection connection;

	public GmailController() throws GeneralSecurityException, IOException {
		super();
		
		connection = Database_Connection.getSingleConnection();

		httpTransport = GoogleNetHttpTransport.newTrustedTransport();

		service = new Gmail.Builder(httpTransport, JSON_FACTORY, getCredentials()).setApplicationName(APPLICATION_NAME)
				.build();

		String user = "me";
		ListLabelsResponse listResponse = service.users().labels().list(user).execute();
		List<Label> labels = listResponse.getLabels();
		if (labels.isEmpty()) {
			System.out.println("No labels found.");
		} else {
			System.out.println("Labels:");
			for (Label label : labels) {
				System.out.printf("- %s\n", label.getName());
			}
		}
	}

	private static Credential getCredentials() throws IOException {
		// Load client secrets.
		InputStream in = GmailController.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
				clientSecrets, SCOPES)
						.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
						.setAccessType("offline").build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	/**
	 * Create a MimeMessage using the parameters provided.
	 *
	 * @param to       email address of the receiver
	 * @param from     email address of the sender, the mailbox account
	 * @param subject  subject of the email
	 * @param bodyText body text of the email
	 * @return the MimeMessage to be used to send email
	 * @throws MessagingException
	 */
	public static MimeMessage createEmail(String to, String from, String subject, String bodyText)
			throws MessagingException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress(from));
		email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
		email.setSubject(subject);
		email.setText(bodyText);
		return email;
	}

	public static Message createMessageWithEmail(MimeMessage emailContent) throws MessagingException, IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		emailContent.writeTo(buffer);
		byte[] bytes = buffer.toByteArray();
		String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
		Message message = new Message();
		message.setRaw(encodedEmail);
		return message;
	}

	@Override
	public void sendMessage(String to, String subject, String bodyText)
			throws MessagingException, IOException, RemoteException {
		Message message = createMessageWithEmail(createEmail(to, "me", subject, bodyText));
		message = service.users().messages().send("me", message).execute();

		System.out.println("Message id: " + message.getId());
		System.out.println(message.toPrettyString());

	}

	@Override
	public List<User> getSelectedUsers(Category category) throws RemoteException {
		List<User> list = new ArrayList<User>();

		String stat = "SELECT `id`, `userName`, `email`, `marks`, `category1`, `category2` FROM `user` WHERE `category1`="+category.getId()+";";

		connection.query(stat);

		try {
			while (connection.resultSet.next()) {
				User user = new User();
				user.setId(connection.resultSet.getInt("id"));
				user.setUserName(connection.resultSet.getString("userName"));
				user.setEmail(connection.resultSet.getString("email"));
				user.setMarks(connection.resultSet.getInt("marks"));
				user.setCategory1(connection.resultSet.getInt("category1"));
				user.setCategory2(connection.resultSet.getInt("category2"));

				list.add(user);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return list;
	}

	

	

}
