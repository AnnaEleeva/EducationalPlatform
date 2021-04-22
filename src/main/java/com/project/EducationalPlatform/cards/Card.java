package com.project.EducationalPlatform.cards;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Card {
	private Long cardId;
	private Long deckId;
	private int learningDay = 1;
	private int boxNum = 1;
	private String firstSide;
	private String secondSide;

	public Card() {
	}

	protected Card(Long deckId) {
		super();
		this.deckId = deckId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Long getDeckId() {
		return deckId;
	}

	public void setDeckId(Long deckId) {
		this.deckId = deckId;
	}

	public int getLearningDay() {
		return learningDay;
	}

	public void setLearningDay(int learningDay) {
		this.learningDay = learningDay;
	}

	public int getBoxNum() {
		return boxNum;
	}

	public void setBoxNum(int boxNum) {
		this.boxNum = boxNum;
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
