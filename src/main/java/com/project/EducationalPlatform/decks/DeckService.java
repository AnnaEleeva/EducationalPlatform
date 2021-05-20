package com.project.EducationalPlatform.decks;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeckService {

	@Autowired
	private DeckRepository repo;
	
	public void increaseCardsCount(Long deckId) {
		repo.increaseCardsCount(deckId);
	}
	
	public List<Deck> listAllByUser(Long userId){
		return repo.listAllByUser(userId);
	}
	
	public List<Deck> listAll() {
		return repo.findAll();
	}
	
	public void save(Deck deck) {
		repo.save(deck);
	}
	
	public Deck get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
}
