package com.project.EducationalPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.project.EducationalPlatform.decks.Deck;
import com.project.EducationalPlatform.decks.DeckService;

@Controller
public class DeckController {

	@Autowired
	private DeckService deckService;
	
	@RequestMapping("/{userId}/decks")
	public String viewUserDecks(@PathVariable(name = "userId") Long userId, Model model) {
		List<Deck> listDecks = deckService.listAllByUser(userId);
		model.addAttribute("listDecks", listDecks);
		model.addAttribute("userId", userId);
		
		return "user_decks";
	}
	
	@RequestMapping("/{userId}/decks/new")
	public String viewDeckCreate(@PathVariable(name = "userId") Long userId, Model model) {
		Deck deck = new Deck();
		deck.setAuthorId(userId);
		model.addAttribute("deck", deck);
		model.addAttribute("userId", userId);
		
		return "deck_create";
	}
	
	@RequestMapping(value = "/{userId}/decks/deck_save", method = RequestMethod.POST)
	public String saveDeck(@ModelAttribute("deck") Deck deck,
			@PathVariable(name = "userId") Long userId) {
		deckService.save(deck);
		
		return "redirect:/" + userId + "/decks";
	}
}
