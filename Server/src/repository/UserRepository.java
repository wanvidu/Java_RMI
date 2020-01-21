package repository;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.User;;

public interface UserRepository extends Remote {
	public boolean delete(int id) throws RemoteException;

	public List<User> findAll() throws RemoteException;

	public User findById(int id) throws RemoteException;

	public User findByName(String name) throws RemoteException;

	public boolean insert(User user) throws RemoteException;

	public boolean update(User user) throws RemoteException;

	public long count() throws RemoteException;

	public boolean exists(int id) throws RemoteException;

	public void setCurrentUser(User user) throws RemoteException;

	public User getCurrentUser() throws RemoteException;
}
