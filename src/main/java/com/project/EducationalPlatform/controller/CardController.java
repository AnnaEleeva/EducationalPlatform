package com.project.EducationalPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.project.EducationalPlatform.answers.Answer;
import com.project.EducationalPlatform.answers.AnswerForm;
import com.project.EducationalPlatform.cards.Card;
import com.project.EducationalPlatform.cards.CardService;
import com.project.EducationalPlatform.replanConfigs.ReplanConfig;
import com.project.EducationalPlatform.replanConfigs.ReplanConfigForm;

@Controller
public class CardController {

	@Autowired
	private CardService cardService;
	
	@RequestMapping("/{userId}/decks/{deckId}/cards")
	public String viewDeckCards(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "deckId") Long deckId, Model model) {
		List<Card> listCards = cardService.listAllByDeckId(deckId);
		model.addAttribute("listCards", listCards);
		
		return "deck_cards";
	}
	
	@RequestMapping("/{userId}/decks/{deckId}/cards/fsrepeat")
	public String viewDeckCardsFirstSideRepeat(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "deckId") Long deckId, Model model) {
		List<Card> repeatListCards = cardService.listAllForRepeatByDeckId(deckId);
		AnswerForm answerForm = new AnswerForm();
		for (var i = 0; i < repeatListCards.size(); i++) {
			answerForm.addAnswer(new Answer(repeatListCards.get(i).getCardId(),
					repeatListCards.get(i).getFirstSide(),
					repeatListCards.get(i).getSecondSide()));
		}
		model.addAttribute("answerForm", answerForm);
		model.addAttribute("userId", userId);
		model.addAttribute("deckId", deckId);
		
		return "repeat_first_side_cards";
	}
	
	@RequestMapping("/{userId}/decks/{deckId}/cards/ssrepeat")
	public String viewDeckCardsSecondSideRepeat(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "deckId") Long deckId, Model model) {
		List<Card> repeatListCards = cardService.listAllForRepeatByDeckId(deckId);
		AnswerForm answerForm = new AnswerForm();
		for (var i = 0; i < repeatListCards.size(); i++) {
			answerForm.addAnswer(new Answer(repeatListCards.get(i).getCardId(),
					repeatListCards.get(i).getFirstSide(),
					repeatListCards.get(i).getSecondSide()));
		}
		model.addAttribute("answerForm", answerForm);
		model.addAttribute("userId", userId);
		model.addAttribute("deckId", deckId);
		
		return "repeat_second_side_cards";
	}
	
	@RequestMapping("/{userId}/decks/{deckId}/cards/fsrepeat/control")
	public String viewDeckCardsFirstSideRepeatControl(
			@ModelAttribute("answerForm") AnswerForm answerForm,
			@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "deckId") Long deckId, Model model) {
		model.addAttribute("answerForm", answerForm);
		
		return "repeat_first_side_cards_control";
	}
	
	@RequestMapping("/{userId}/decks/{deckId}/cards/ssrepeat/control")
	public String viewDeckCardsSecondSideRepeatControl(
			@ModelAttribute("answerForm") AnswerForm answerForm,
			@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "deckId") Long deckId, Model model) {
		model.addAttribute("answerForm", answerForm);
		
		return "repeat_second_side_cards_control";
	}
	
	@RequestMapping("/{userId}/decks/{deckId}/cards/fsrepeat/replanning")
	public String viewDeckCardsFirstSideRepeatReplanning(
			@ModelAttribute("answerForm") AnswerForm answerForm,
			@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "deckId") Long deckId, Model model) {
		ReplanConfigForm configForm = new ReplanConfigForm();
		String[] messageArray = new String[answerForm.getAnswers().size()];
		for (var i = 0; i < answerForm.getAnswers().size(); i++) {
			configForm.addConfig(new ReplanConfig(answerForm.getAnswers().get(i).getCardId()));
			if (answerForm.getAnswers().get(i).getTruth())
				messageArray[i] = "Рекомендуется не понижать интервал (сейчас 1 раз в " +
						cardService.getBoxNumOnId(answerForm.getAnswers().get(i).getCardId()) +
						" дня/дней)";
			else messageArray[i] = "Рекомендуется понизить интервал (сейчас 1 раз в " +
					cardService.getBoxNumOnId(answerForm.getAnswers().get(i).getCardId()) +
					" дня/дней)";
		}
		
		
		model.addAttribute("answerForm", answerForm);
		model.addAttribute("configForm", configForm);
		model.addAttribute("messageArray", messageArray);
		
		return "repeat_first_side_cards_replanning";
	}
	
	@RequestMapping("/{userId}/decks/{deckId}/cards/ssrepeat/replanning")
	public String viewDeckCardsSecondSideRepeatReplanning(
			@ModelAttribute("answerForm") AnswerForm answerForm,
			@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "deckId") Long deckId, Model model) {
		ReplanConfigForm configForm = new ReplanConfigForm();
		String[] messageArray = new String[answerForm.getAnswers().size()];
		for (var i = 0; i < answerForm.getAnswers().size(); i++) {
			configForm.addConfig(new ReplanConfig(answerForm.getAnswers().get(i).getCardId()));
			if (answerForm.getAnswers().get(i).getTruth())
				messageArray[i] = "Рекомендуется не понижать интервал (сейчас 1 раз в " +
						cardService.getBoxNumOnId(answerForm.getAnswers().get(i).getCardId()) +
						" дня/дней)";
			else messageArray[i] = "Рекомендуется понизить интервал (сейчас 1 раз в " +
					cardService.getBoxNumOnId(answerForm.getAnswers().get(i).getCardId()) +
					" дня/дней)";
		}
		
		
		model.addAttribute("answerForm", answerForm);
		model.addAttribute("configForm", configForm);
		model.addAttribute("messageArray", messageArray);
		
		return "repeat_second_side_cards_replanning";
	}
	
	@RequestMapping("/{userId}/decks/{deckId}/cards/new")
	public String viewCardCreate(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "deckId") Long deckId, Model model) {
		Card card = new Card();
		card.setDeckId(deckId);
		model.addAttribute("card", card);
		model.addAttribute("userId", userId);
		model.addAttribute("deckId", deckId);
		
		return "card_create";
	}
	
	@RequestMapping(value = "/{userId}/decks/{deckId}/cards/repeat/replanning/save", method = RequestMethod.POST)
	public String saveReplanConfigs(@ModelAttribute("configForm") ReplanConfigForm configForm,
			@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "deckId") Long deckId) {
		for (var i = 0; i < configForm.getConfigs().size(); i++)
		{
			cardService.saveReplanUpdates(configForm.getConfigs().get(i).getCardId(),
					configForm.getConfigs().get(i).getNewBoxNum());
			cardService.resetTrainingDay(configForm.getConfigs().get(i).getCardId());
		}
		
		return "redirect:/" + userId + "/decks/" + deckId + "/cards";
	}

	@RequestMapping(value = "/{userId}/decks/{deckId}/cards/card_save", method = RequestMethod.POST)
	public String saveCard(@ModelAttribute("card") Card card,
			@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "deckId") Long deckId) {
		cardService.save(card);
		
		return "redirect:/" + userId + "/decks/" + deckId + "/cards";
	}
}
