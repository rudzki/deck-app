package org.rudzki.deckapp.controllers;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.rudzki.deckapp.model.Card;
import org.rudzki.deckapp.model.CardDao;

@Controller 
public class HomepageController {

	@Autowired
	private CardDao dao;
		
	@RequestMapping("/")
	public String displayHomePage(HttpServletRequest req) {
		List<Card> cards = dao.getAllCards();
		Map<Integer, String> categories = dao.listCategories();
		req.setAttribute("cards", cards);
		req.setAttribute("categories", categories);
		return "home";
	}
}
