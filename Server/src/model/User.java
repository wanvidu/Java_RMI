package model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -2282501158449258662L;

	private int id;
	private String userName;
	private String email;
	private int marks;
	private int category1;
	private int category2;

	public User() {
		super();
	}

	public User(int id, String userName, String email, int marks, int category1, int category2) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.marks = marks;
		this.category1 = category1;
		this.category2 = category2;
	}

	public User(String userName, String email, int marks, int category1, int category2) {
		super();
		this.userName = userName;
		this.email = email;
		this.marks = marks;
		this.category1 = category1;
		this.category2 = category2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public int getCategory1() {
		return category1;
	}

	public void setCategory1(int category1) {
		this.category1 = category1;
	}

	public int getCategory2() {
		return category2;
	}

	public void setCategory2(int category2) {
		this.category2 = category2;
	}
}
