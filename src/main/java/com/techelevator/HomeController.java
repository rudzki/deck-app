package com.techelevator;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.model.Card;
import com.techelevator.model.CardDao;

@Controller 
public class HomeController {

	@Autowired
	private CardDao dao;
	
	@RequestMapping("/")
	public String displayHomePage(HttpServletRequest req) {
		List<Card> cards = dao.getAllCards();
		req.setAttribute("cards", cards);
		return "home";
	}
}
