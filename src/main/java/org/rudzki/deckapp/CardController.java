package org.rudzki.deckapp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

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
	
	@RequestMapping("/viewCard")
	public String displayCard(HttpServletRequest req) {
		String idAsString = req.getParameter("id");
		long id = Long.parseLong(idAsString);
		Card card = dao.getCard(id);
		String categoryName = dao.getCategoryName(card.getCategoryId());
		req.setAttribute("categoryName", categoryName);
		req.setAttribute("card", card);
		req.setAttribute("averageScore", dao.getAverageScore(id));
		return "cardDetail";
	}
	
	@RequestMapping("/viewCategory")
	public String displayCardsInCategory(HttpServletRequest req) {
		String categoryIdAsString = req.getParameter("id");
		int categoryId = Integer.parseInt(categoryIdAsString);
		if (!dao.listCategories().containsKey(categoryId)) {
			return "redirect:/";
		}
		
		List<Card> cards = dao.getCardsByCategoryId(categoryId);
		String categoryName = dao.getCategoryName(categoryId);
		req.setAttribute("cards", cards);
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
			}
		}	
		
		Entry<Long, Double> currentCard = sortedCards.entrySet().iterator().next();
		Long id = currentCard.getKey();
		Card card = dao.getCard(id);
		String categoryName = dao.getCategoryName(card.getCategoryId());
		req.setAttribute("categoryName", categoryName);
		//sortedCards.remove(id);
		req.setAttribute("averageScore", dao.getAverageScore(id));
		model.put("sortedCards", sortedCards);
		model.put("currentCardId", id);
		model.put("card", card);
		return "studyDeck";
	}
	
	@RequestMapping(path="/scoreCard", method=RequestMethod.POST)
	public String scoreCard(HttpServletRequest req, ModelMap model) {
		String cardIdAsString = req.getParameter("cardId");
		String scoreAsString = req.getParameter("score");
		int score = Integer.parseInt(scoreAsString);
		long cardId = Long.parseLong(cardIdAsString);
		dao.addScore(cardId, score);
		Map<Long, Double> sortedCards = (Map<Long, Double>) model.get("sortedCards");
		sortedCards.remove(cardId);
		return "redirect:/studyDeck";
	}
	
	@RequestMapping(path="/publishCard", method=RequestMethod.POST)
	public String publishCard(@RequestParam String question, @RequestParam String answer, @RequestParam int categoryId) {
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
		dao.createCategory(newCategoryName);
		return "redirect:/";
	}
	
}
