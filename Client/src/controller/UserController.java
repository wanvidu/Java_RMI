package controller;

import java.rmi.Naming;
import java.util.List;


import model.User;
import repository.UserRepository;

public class UserController implements UserRepository {
	
	UserRepository userRepository;
	
	User currentUser;

	public UserController() {
		super();
		
		try {
			userRepository =   (UserRepository) Naming.lookup("rmi://localhost/user");
		} catch (Exception e) {
			System.out.println("A problem occured: "+e.toString());
			e.printStackTrace();
			System.out.println("Unable to connect to server.");
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			return userRepository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<User> findAll() {
		try {
			return userRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public User findById(int id)  {
		try {
			return userRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public User findByName(String name)  {
		try {
			return userRepository.findByName(name);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean insert(User user) {
		try {
			return userRepository.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean update(User user) {
		try {
			return userRepository.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public long count() {
		try {
			return userRepository.count();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean exists(int id) {
		try {
			return userRepository.exists(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setCurrentUser(User user) {
		userRepository.setCurrentUser(user);
	}

	@Override
	public User getCurrentUser() {
		return userRepository.getCurrentUser();
	}

	
}
