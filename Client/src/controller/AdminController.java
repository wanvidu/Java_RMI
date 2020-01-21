package controller;

import java.rmi.Naming;
import java.util.List;

import model.Admin;
import repository.AdminRepository;

public class AdminController implements AdminRepository {
	
	AdminRepository adminRepository;
	
	public AdminController(){
		super();

		try {
			adminRepository =   (AdminRepository) Naming.lookup("rmi://localhost/admin");
		} catch (Exception e) {
			System.out.println("A problem occured: "+e.toString());
			e.printStackTrace();
			System.out.println("Unable to connect to server.");
		} 
	}

	@Override
	public boolean delete(int id) {
		try {
			return adminRepository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Admin> findAll() {
		try {
			return adminRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Admin findById(int id) {
		try {
			return adminRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Admin findByName(String name) {
		try {
			return adminRepository.findByName(name);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean insert(Admin user) {
		try {
			return adminRepository.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean update(Admin user) {
		try {
			return adminRepository.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public long count() {
		try {
			return adminRepository.count();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean exists(int id) {
		try {
			return adminRepository.exists(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
