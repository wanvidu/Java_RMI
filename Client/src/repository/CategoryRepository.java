package repository;

import java.rmi.Remote;
import java.util.List;

import model.Category;

public interface CategoryRepository  extends Remote{
	
	public Category getCategory(int id) ;

	public List<Category> getAllCategory();
}
