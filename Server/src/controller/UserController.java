package controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import repository.UserRepository;
import Database_Layer.Database_Connection;

public class UserController extends UnicastRemoteObject implements UserRepository {

	private static final long serialVersionUID = 3201506929365882251L;

	Database_Connection connection;

	User currentUser;

	public UserController() throws RemoteException {
		super();

		connection = Database_Connection.getSingleConnection();
	}

	@Override
	public boolean delete(int id) {
		String stat = "DELETE FROM user WHERE id=" + id + ";";

		try {
			return connection.modify(stat);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<User>();

		String stat = "SELECT `id`, `userName`, `email`, `marks`, `category1`, `category2` FROM `user`;";

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

	@Override
	public User findById(int id) throws RemoteException {
		String stat = "SELECT `id`, `userName`, `email`, `marks`, `category1`, `category2` FROM `user` WHERE `id`=" + id
				+ ";";

		try {
			connection.query(stat);

			if (connection.resultSet.next()) {
				User user = new User();
				user.setId(connection.resultSet.getInt("id"));
				user.setUserName(connection.resultSet.getString("userName"));
				user.setEmail(connection.resultSet.getString("email"));
				user.setMarks(connection.resultSet.getInt("marks"));
				user.setCategory1(connection.resultSet.getInt("category1"));
				user.setCategory2(connection.resultSet.getInt("category2"));

				return user;

			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public User findByName(String name) throws RemoteException {
		String stat = "SELECT `id`, `userName`, `email`, `marks`, `category1`, `category2` FROM `user` WHERE `userName`='"
				+ name + "'";

		try {
			connection.query(stat);

			if (connection.resultSet.next()) {
				User user = new User();
				user.setId(connection.resultSet.getInt("id"));
				user.setUserName(connection.resultSet.getString("userName"));
				user.setEmail(connection.resultSet.getString("email"));
				user.setMarks(connection.resultSet.getInt("marks"));
				user.setCategory1(connection.resultSet.getInt("category1"));
				user.setCategory2(connection.resultSet.getInt("category2"));

				return user;

			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean insert(User user) throws RemoteException {
		String stat = "INSERT INTO `user`(`userName`, `email`) VALUES ('" + user.getUserName() + "','" + user.getEmail()
				+ "')";

		try {
			return connection.modify(stat);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean update(User user) throws RemoteException {
		String marks = (user.getMarks() == 0) ? "null" : String.valueOf(user.getMarks());
		String caregory1 = (user.getCategory1() == 0) ? "null" : String.valueOf(user.getCategory1());
		String caregory2 = (user.getCategory2() == 0) ? "null" : String.valueOf(user.getCategory2());

		String stat = "UPDATE `user` SET `userName`='" + user.getUserName() + "',`email`='" + user.getEmail()
				+ "',`marks`=" + marks + ",`category1`=" + caregory1 + ",`category2`=" + caregory2 + " WHERE `id`='"
				+ user.getId() + "';";

		try {
			return connection.modify(stat);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public long count() throws RemoteException {
		String stat = "SELECT COUNT(*) AS 'count' FROM `user`";

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
	public boolean exists(int id) throws RemoteException {
		String stat = "SELECT * FROM `user` WHERE `id`='" + id + "'";

		try {
			connection.query(stat);

			return connection.resultSet.next() ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setCurrentUser(User user) {
		currentUser = new User();

		currentUser.setId(user.getId());
		currentUser.setUserName(user.getUserName());
		currentUser.setEmail(user.getEmail());
	}

	@Override
	public User getCurrentUser() {
		return currentUser;
	}

}
