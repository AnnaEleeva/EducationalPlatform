package com.project.EducationalPlatform.decks;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeckRepository extends JpaRepository<Deck, Long> {
	@Query(value = "select * from deck d where d.author_id=?1", nativeQuery = true)
	List<Deck> listAllByUser(Long userId);

	@Query(value = "update deck d set d.card_count=d.card_count+1 where d.deck_id = ?1;", nativeQuery = true)
	void increaseCardsCount(Long userId);
}
