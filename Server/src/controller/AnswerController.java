package controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

import Database_Layer.Database_Connection;
import model.Answer;
import repository.AnswerRepostitory;

public class AnswerController extends UnicastRemoteObject implements AnswerRepostitory {

	private static final long serialVersionUID = 1475425877;

	Database_Connection connection;

	public AnswerController() throws RemoteException {
		super();

		connection = Database_Connection.getSingleConnection();
	}

	@Override
	public Answer getAnswer(int id) throws RemoteException {
		String stat = "SELECT `id`, `answer`, `value` FROM `Question_Answers` WHERE `id`=" + id + ";";

		try {
			connection.query(stat);

			if (connection.resultSet.next()) {

				Answer answer = new Answer();

				answer.setId(connection.resultSet.getInt("id"));
				answer.setAnswer_text(connection.resultSet.getString("answer"));
				answer.setValue(connection.resultSet.getInt("value"));

				return answer;

			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
