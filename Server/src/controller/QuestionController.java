package controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Database_Layer.Database_Connection;

import model.Question;
import repository.QuestionRepostitory;

import model.Answer;
import controller.AnswerController;

import model.Category;
import controller.CategoryController;

public class QuestionController extends UnicastRemoteObject implements QuestionRepostitory {

	private static final long serialVersionUID = 325741258;

	Database_Connection connection;

	Map<Question, Answer> map = new HashMap<Question, Answer>();

	public QuestionController() throws RemoteException {
		super();

		connection = Database_Connection.getSingleConnection();
	}

	@Override
	public int getNumberOfQuestions() throws RemoteException {
		String stat = "SELECT COUNT(*) AS 'count' FROM `Questions`";

		try {
			connection.query(stat);

			if (connection.resultSet.next()) {
				return connection.resultSet.getInt("count");

			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Question getQuestion(int id) throws RemoteException {
		String stat = "SELECT `id`, `question`, `category`, `answer_1`, `answer_2`, `answer_3`, `answer_4`, `answer_5`, `answer_6` FROM `Questions` WHERE `id`="
				+ id + ";";

		try {
			connection.query(stat);

			if (connection.resultSet.next()) {

				Question question = new Question();

				question.setId(connection.resultSet.getInt("id"));
				question.setQuestion_text(connection.resultSet.getString("question"));
				question.setCategory(connection.resultSet.getInt("category"));
				question.setAnswer_1_id(connection.resultSet.getInt("answer_1"));
				question.setAnswer_2_id(connection.resultSet.getInt("answer_2"));
				question.setAnswer_3_id(connection.resultSet.getInt("answer_3"));
				question.setAnswer_4_id(connection.resultSet.getInt("answer_4"));
				question.setAnswer_5_id(connection.resultSet.getInt("answer_5"));
				question.setAnswer_6_id(connection.resultSet.getInt("answer_6"));

				return question;

			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void submitAnswer(int id, int answer_id) throws RemoteException {
		map.put(getQuestion(id), new AnswerController().getAnswer(answer_id));
	}

	@Override
	public int getTotal() throws RemoteException {
		int count = 0;

		for (Answer answer : map.values()) {
			count += answer.getValue();
		}

		return count;
	}

	@Override
	public void resetQuiz() throws RemoteException {
		map.clear();
	}

	@Override
	public List<Category> getCategories() throws RemoteException {
		Map<Category, Integer> categoryMap = new HashMap<Category, Integer>();

		CategoryController categoryController = new CategoryController();

		for (Category category : categoryController.getAllCategory()) {
			categoryMap.put(category, 0);
		}

		for (Map.Entry<Question, Answer> entry : map.entrySet()) {
			for (Category category : categoryMap.keySet()) {
				if (category.getId() == entry.getKey().getCategory()) {
					categoryMap.put(category, categoryMap.get(category) + entry.getValue().getValue());
				}
			}
		}

		int max = Collections.max(categoryMap.values());

		List<Category> list = new ArrayList<Category>();

		for (Map.Entry<Category, Integer> entry : categoryMap.entrySet()) {

			if (entry.getValue() == max) {
				list.add(entry.getKey());
			}
		}

		return list;

	}

	@Override
	public String[][] getCategorieCount() throws RemoteException {
		String[][] list = new String[6][4];

		String stat = "select q.id,c.count,q.Category,q.description from (SELECT `category1` as 'category', COUNT(*) as 'count' FROM `user` GROUP BY `category1` UNION SELECT `category2` as 'category', COUNT(*) as 'count' FROM `user` GROUP BY `category2`) c RIGHT JOIN Question_Categories q ON c.category=q.id;";

		try {
			connection.query(stat);

			int i = 0;

			while (connection.resultSet.next()) {

				list[i][0] = (connection.resultSet.getString("id"));
				list[i][1] = (connection.resultSet.getString("count"));
				list[i][2] = (connection.resultSet.getString("category"));
				list[i][3] = (connection.resultSet.getString("description"));

				i++;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return list;
	}

}
