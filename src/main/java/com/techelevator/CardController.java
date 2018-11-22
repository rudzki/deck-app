package com.techelevator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.model.Card;
import com.techelevator.model.CardDao;

@Controller 
@SessionAttributes("sortedCardIds")
public class CardController {
	
	@Autowired
	private CardDao dao;

	@RequestMapping("/addCard")
	public String displayCardForm(HttpServletRequest req) {
		Map<Integer, String> categories = dao.listCategories();
		req.setAttribute("categories", categories);
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
	
	@RequestMapping("/studyDeck")
	public String studyDeck(HttpServletRequest req, ModelMap model) {

		List<Long> sortedCardIds = null;
				
		if (!model.containsKey("sortedCardIds")) {
			sortedCardIds = dao.getSortedCardIds();

		}	
		
		if (model.containsKey("sortedCardIds")) {
			sortedCardIds = (List<Long>) model.get("sortedCardIds");
			if (sortedCardIds.size() == 0) {
				sortedCardIds = dao.getSortedCardIds();
			}

		}	
		Long id = sortedCardIds.get(0);
		Card card = dao.getCard(id);
		
		String categoryName = dao.getCategoryName(card.getCategoryId());
		req.setAttribute("categoryName", categoryName);
		
		sortedCardIds.remove(0);
		req.setAttribute("averageScore", dao.getAverageScore(id));
		model.put("sortedCardIds", sortedCardIds);
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
	public String addCategory() {
		return "addCategory";
	}
	
	@RequestMapping(path="/addCategory", method=RequestMethod.POST)
	public String addCategory(@RequestParam String newCategoryName) {
		dao.createCategory(newCategoryName);
		return "redirect:/";
	}
	
}
