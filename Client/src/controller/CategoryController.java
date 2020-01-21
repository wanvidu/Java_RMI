package controller;

import java.rmi.Naming;
import java.util.List;

import model.Category;
import repository.CategoryRepository;;

public class CategoryController implements CategoryRepository {
	
	CategoryRepository categoryRepository;
	
	public CategoryController(){
		super();
	
		try {
			categoryRepository =   (CategoryRepository) Naming.lookup("rmi://localhost/category");
		} catch (Exception e) {
			System.out.println("A problem occured: "+e.toString());
			e.printStackTrace();
			System.out.println("Unable to connect to server.");
		} 
	}

	@Override
	public Category getCategory(int id) {
		return categoryRepository.getCategory(id);
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryRepository.getAllCategory();
	}

	

	

}
