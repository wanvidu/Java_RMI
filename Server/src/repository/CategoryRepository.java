package repository;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.Category;

public interface CategoryRepository extends Remote {

	public Category getCategory(int id) throws RemoteException;

	public List<Category> getAllCategory() throws RemoteException;
}
