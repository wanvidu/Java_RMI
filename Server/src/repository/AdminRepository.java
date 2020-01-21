package repository;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.Admin;

public interface AdminRepository extends Remote {
	public boolean delete(int id) throws RemoteException;

	public List<Admin> findAll() throws RemoteException;

	public Admin findById(int id) throws RemoteException;

	public Admin findByName(String name) throws RemoteException;

	public boolean insert(Admin user) throws RemoteException;

	public boolean update(Admin user) throws RemoteException;

	public long count() throws RemoteException;

	public boolean exists(int id) throws RemoteException;
}
