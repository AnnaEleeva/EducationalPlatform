package com.project.EducationalPlatform.decks;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.PersistenceConstructor;

@Entity
public class Deck {
	private Long deckId;
	private Long authorId;
	private String name;
	private int cardCount;
	
	public Deck() {
		cardCount = 0;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDeckId() {
		return deckId;
	}

	public void setDeckId(Long deckId) {
		this.deckId = deckId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCardCount() {
		return cardCount;
	}

	public void setCardCount(int cardCount) {
		this.cardCount = cardCount;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
}
