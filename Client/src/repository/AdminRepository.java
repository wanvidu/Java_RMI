package repository;

import java.rmi.Remote;
import java.util.List;

import model.Admin;

public interface AdminRepository extends Remote {
	public boolean delete(int id);

	public List<Admin> findAll();

	public Admin findById(int id);

	public Admin findByName(String name);

	public boolean insert(Admin user);

	public boolean update(Admin user);
	
	public long count();
	
	public boolean exists(int id);
}
