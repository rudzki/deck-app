package org.rudzki.deckapp.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.rudzki.deckapp.model.Card;
import org.rudzki.deckapp.model.CardDao;

@Controller 
@SessionAttributes("sortedCards")
public class CardController {
	
	@Autowired
	private CardDao dao;
	
	@ModelAttribute("categories")
	public Map<Integer, String> listCategories() {
		return dao.listCategories();
	}
	
	@RequestMapping("/addCard")
	public String displayCardForm(HttpServletRequest req) {
		return "addCard";
	}
	
	@RequestMapping("/card/{id}")
	public String displayCard(HttpServletRequest req, @PathVariable("id") long id) {
		Card card = dao.getCard(id);
		if (!dao.cardExists(id)) {
			return "redirect:/";
		}
		req.setAttribute("card", card);
		req.setAttribute("averageScore", dao.getAverageScore(id));
		return "cardDetail";
	}
	
	//USE POST REQUEST (method=RequestMethod.POST)
	@RequestMapping("/deleteCard/{id}")
	public String deleteCard(HttpServletRequest req, @PathVariable("id") long id) {
		dao.deleteCard(id);
		return "redirect:/";
	}
	
	
	@RequestMapping("/deleteDeck/{categoryId}")
	public String deleteDeck(HttpServletRequest req, @PathVariable("categoryId") int categoryId) {
		dao.deleteCategory(categoryId);
		return "redirect:/";
	}
	
	
	@RequestMapping("/deck/{categoryId}")
	public String displayCardsInCategory(HttpServletRequest req, @PathVariable("categoryId") int categoryId) {
		if (!dao.listCategories().containsKey(categoryId)) {
			return "redirect:/";
		}
		
		List<Card> cards = dao.getCardsByCategoryId(categoryId);
		String categoryName = dao.getCategoryName(categoryId);
		req.setAttribute("cards", cards);
		req.setAttribute("categoryId", categoryId);
		req.setAttribute("categoryName", categoryName);
		return "viewDeck";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/studyDeck")
	public String studyDeck(HttpServletRequest req, ModelMap model) {

		Map<Long, Double> sortedCards = null;
				
		if (!model.containsKey("sortedCards")) {
			sortedCards = dao.getSortedCards();

		}	
		
		if (model.containsKey("sortedCards")) {
			
			sortedCards = (Map<Long, Double>) model.get("sortedCards");
			if (sortedCards.size() == 0) {
				sortedCards = dao.getSortedCards();
				model.put("sortedCards", sortedCards);
				
				// log completed study session
				LocalDateTime currentDateTime = LocalDateTime.now();
				dao.logStudySession(currentDateTime);
				
				return "studyDeckFinished";
			}
		}	
		
		Entry<Long, Double> currentCard = sortedCards.entrySet().iterator().next();
		Long id = currentCard.getKey();
		Card card = dao.getCard(id);
		String categoryName = dao.getCategoryName(card.getCategoryId());
		req.setAttribute("averageScore", dao.getAverageScore(id));
		model.put("sortedCards", sortedCards);
		model.put("currentCardId", id);
		model.put("card", card);
		return "studyDeck";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(path="/scoreCard", method=RequestMethod.POST)
	public String scoreCard(@RequestParam long cardId, @RequestParam int score, ModelMap model) {
		dao.addScore(cardId, score);
		Map<Long, Double> sortedCards = (Map<Long, Double>) model.get("sortedCards");
		sortedCards.remove(cardId);
		return "redirect:/studyDeck";
	}
	
	@RequestMapping(path="/publishCard", method=RequestMethod.POST)
	public String publishCard(@RequestParam String question, @RequestParam String answer, @RequestParam int categoryId) {
		question = Jsoup.clean(question, Whitelist.none());
		answer = Jsoup.clean(answer, Whitelist.none());

		Card card = new Card();
		LocalDateTime currentDateTime = LocalDateTime.now();
		card.setQuestion(question);
		card.setAnswer(answer);
		card.setCategoryId(categoryId);
		card.setDateSubmitted(currentDateTime);
		dao.save(card);
		return "redirect:/";
	}
	
	@RequestMapping(path="/addCategory", method=RequestMethod.GET)
	public String addCategory(HttpServletRequest req) {
		return "addCategory";
	}
	
	@RequestMapping(path="/addCategory", method=RequestMethod.POST)
	public String addCategory(@RequestParam String newCategoryName) {
		newCategoryName = Jsoup.clean(newCategoryName, Whitelist.none());
		dao.createCategory(newCategoryName);
		return "redirect:/";
	}
	
}
