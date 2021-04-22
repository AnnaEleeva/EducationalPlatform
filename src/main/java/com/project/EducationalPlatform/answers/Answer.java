package com.project.EducationalPlatform.answers;

public class Answer {
	private Long cardId;
	private String firstSide;
	private String secondSide;
	public String textAnswer;
	private Boolean truth;
	
	protected Answer(){
		
	}
	
	public Answer(Long cardId, String firstSide, String secondSide) {
		this.cardId = cardId;
		this.firstSide = firstSide;
		this.secondSide = secondSide;
	}

	public String getTextAnswer() {
		return textAnswer;
	}

	public void setTextAnswer(String textAnswer) {
		this.textAnswer = textAnswer;
	}

	public Boolean getTruth() {
		return truth;
	}

	public void setTruth(Boolean truth) {
		this.truth = truth;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getFirstSide() {
		return firstSide;
	}

	public void setFirstSide(String firstSide) {
		this.firstSide = firstSide;
	}

	public String getSecondSide() {
		return secondSide;
	}

	public void setSecondSide(String secondSide) {
		this.secondSide = secondSide;
	}
}
