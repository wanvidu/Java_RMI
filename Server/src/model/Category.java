package model;

import java.io.Serializable;

public class Category implements Serializable {

	private static final long serialVersionUID = -8584834441521355363L;

	private int id;
	private String category_name;
	private String description;

	public Category() {
		super();
	}

	public Category(int id, String category_name, String description) {
		super();
		this.id = id;
		this.category_name = category_name;
		this.description = description;
	}

	public Category(String category_name, String description) {
		super();
		this.category_name = category_name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
