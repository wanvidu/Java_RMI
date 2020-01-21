package model;

import java.io.Serializable;

public class Answer  implements Serializable {
	
	private static final long serialVersionUID = 535434353;
	
	private int id;
	private String answer_text;
	private int value;
	
	public Answer() {
		super();
	}

	public Answer(int id, String answer_text, int value) {
		super();
		this.id = id;
		this.answer_text = answer_text;
		this.value = value;
	}

	public Answer(String answer_text, int value) {
		super();
		this.answer_text = answer_text;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswer_text() {
		return answer_text;
	}

	public void setAnswer_text(String answer_text) {
		this.answer_text = answer_text;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
