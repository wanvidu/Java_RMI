package model;

import java.io.Serializable;

public class Question implements Serializable {
	private static final long serialVersionUID = 754125741;

	private int id;
	private String question_text;
	private int category;
	private int answer_1_id;
	private int answer_2_id;
	private int answer_3_id;
	private int answer_4_id;
	private int answer_5_id;
	private int answer_6_id;

	public Question() {
		super();
	}

	public Question(int id, String question_text, int category, int answer_1_id, int answer_2_id, int answer_3_id,
			int answer_4_id, int answer_5_id, int answer_6_id) {
		super();
		this.id = id;
		this.question_text = question_text;
		this.category = category;
		this.answer_1_id = answer_1_id;
		this.answer_2_id = answer_2_id;
		this.answer_3_id = answer_3_id;
		this.answer_4_id = answer_4_id;
		this.answer_5_id = answer_5_id;
		this.answer_6_id = answer_6_id;
	}

	public Question(String question_text, int category, int answer_1_id, int answer_2_id, int answer_3_id,
			int answer_4_id, int answer_5_id, int answer_6_id) {
		super();
		this.question_text = question_text;
		this.category = category;
		this.answer_1_id = answer_1_id;
		this.answer_2_id = answer_2_id;
		this.answer_3_id = answer_3_id;
		this.answer_4_id = answer_4_id;
		this.answer_5_id = answer_5_id;
		this.answer_6_id = answer_6_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion_text() {
		return question_text;
	}

	public void setQuestion_text(String question_text) {
		this.question_text = question_text;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getAnswer_1_id() {
		return answer_1_id;
	}

	public void setAnswer_1_id(int answer_1_id) {
		this.answer_1_id = answer_1_id;
	}

	public int getAnswer_2_id() {
		return answer_2_id;
	}

	public void setAnswer_2_id(int answer_2_id) {
		this.answer_2_id = answer_2_id;
	}

	public int getAnswer_3_id() {
		return answer_3_id;
	}

	public void setAnswer_3_id(int answer_3_id) {
		this.answer_3_id = answer_3_id;
	}

	public int getAnswer_4_id() {
		return answer_4_id;
	}

	public void setAnswer_4_id(int answer_4_id) {
		this.answer_4_id = answer_4_id;
	}

	public int getAnswer_5_id() {
		return answer_5_id;
	}

	public void setAnswer_5_id(int answer_5_id) {
		this.answer_5_id = answer_5_id;
	}

	public int getAnswer_6_id() {
		return answer_6_id;
	}

	public void setAnswer_6_id(int answer_6_id) {
		this.answer_6_id = answer_6_id;
	}

}
