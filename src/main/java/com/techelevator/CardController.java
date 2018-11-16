package com.techelevator;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.model.Card;
import com.techelevator.model.CardDao;

@Controller 
@SessionAttributes("sortedCardIds")
public class CardController {
	
	@Autowired
	private CardDao dao;

	@RequestMapping("/addCard")
	public String displayCardForm() {
		return "addCard";
	}
	
	@RequestMapping("/viewCard")
	public String displayCard(HttpServletRequest req) {
		String idAsString = req.getParameter("id");
		long id = Long.parseLong(idAsString);
		Card card = dao.getCard(id);
		req.setAttribute("card", card);
		req.setAttribute("averageScore", dao.getAverageScore(id));
		return "cardDetail";
	}
	
	@RequestMapping("/studyDeck")
	public String studyDeck(HttpServletRequest req, ModelMap model) {
		List<Long> sortedCardIds = dao.getSortedCardIds();
		Long id = sortedCardIds.get(0);
		Card card = dao.getCard(id);
		sortedCardIds.remove(0);
		//List<Long> list = (List<Long>) model.get(sortedCardIds);
		model.put("sortedCardIds", sortedCardIds);
		model.put("currentCardId", id);
		model.put("card", card);
		return "studyDeck";
	}
	
	@RequestMapping(path="/advanceDeck", method=RequestMethod.POST)
	public String advanceDeck(HttpServletRequest req, ModelMap model) {
		List<Long> sortedCardIds = (List<Long>)model.get("sortedCardIds");
		Long id = sortedCardIds.get(0);
		Card card = dao.getCard(id);
		sortedCardIds.remove(0);
		//List<Long> list = (List<Long>) model.get(sortedCardIds);
		model.put("sortedCardIds", sortedCardIds);
		model.put("currentCardId", id);
		model.put("card", card);
		return "studyDeck";
	}
	
	@RequestMapping(path="/publishCard", method=RequestMethod.POST)
	public String publishCard(HttpServletRequest req) {
		Card card = new Card();
		
		String question = req.getParameter("question");
		String answer = req.getParameter("answer");
		String categoryIdAsString = req.getParameter("categoryId");
		int categoryId = Integer.parseInt(categoryIdAsString);
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		if (question == null || answer == null) {
			return "addCardError";
		}
		
		card.setQuestion(question);
		card.setAnswer(answer);
		card.setCategoryId(categoryId);
		card.setDateSubmitted(currentDateTime);
		dao.save(card);
		
		return "redirect:/";
	}
	
}
