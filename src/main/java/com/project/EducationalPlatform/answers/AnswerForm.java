package com.project.EducationalPlatform.answers;

import java.util.ArrayList;
import java.util.List;

public class AnswerForm {
	private List<Answer> answers;
	
	public AnswerForm() {
		answers = new ArrayList<Answer>();
	}
	
	public void addAnswer(Answer answer){
		answers.add(answer);
	}
	
	public List<Answer> getAnswers(){
		return answers;
	}
	
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}
