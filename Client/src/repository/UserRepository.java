package repository;

import java.rmi.Remote;
import java.util.List;

import model.User;;

public interface UserRepository extends Remote {
	public boolean delete(int id);

	public List<User> findAll();

	public User findById(int id);

	public User findByName(String name);

	public boolean insert(User user);

	public boolean update(User user);

	public long count();

	public boolean exists(int id);

	public void setCurrentUser(User user);

	public User getCurrentUser();
}
