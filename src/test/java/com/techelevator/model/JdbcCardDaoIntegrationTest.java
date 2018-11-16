package com.techelevator.model;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.model.JdbcCardDao;
import com.techelevator.model.Card;

public class JdbcCardDaoIntegrationTest extends DAOIntegrationTest {

	private JdbcCardDao dao;
	
	@Before
	public void setup() {
		dao = new JdbcCardDao(getDataSource());
	}
	
	@Before
	public void cleanDatabase() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		jdbcTemplate.update("DELETE FROM cards");
	}
	
	@Test
	public void saved_cards_are_returned_in_list_of_all_cards() {
		Card card1 = createCard("Good Cop", 5, "Good Cop's card", "Loved it.");
		Card card2 = createCard("Bad Cop", 1, "Bad Cop's card", "Hated it.");
		
		dao.save(card1);
		dao.save(card2);
		
		List<Card> results = dao.getAllCards();

		assertThat(results, hasItem(equalTo(card1)));
		assertThat(results, hasItem(equalTo(card2)));
		assertThat(results.size(), CoreMatchers.equalTo(2));
	}
	
	private Matcher<Card> equalTo(final Card expected) {
		return new TypeSafeMatcher<Card>() {

			public void describeTo(Description desc) {
				
			}

			protected boolean matchesSafely(Card card) {
				return nullOrEqual(expected.getId(), card.getId()) &&
						nullOrEqual(expected.getUsername(), card.getUsername()) &&
						nullOrEqual(expected.getRating(), card.getRating()) &&
						nullOrEqual(expected.getTitle(), card.getTitle()) &&
						nullOrEqual(expected.getText(), card.getText()) &&
						nullOrEqual(expected.getDateSubmitted(), card.getDateSubmitted());
						   
			}
			
			private boolean nullOrEqual(Object left, Object right) {
				return left != null ? left.equals(right) : right == null;
			}
		};
	}

	private Card createCard(String username, int rating, String title, String text) {
		Card card = new Card();
		card.setUsername(username);
		card.setRating(rating);
		card.setTitle(title);
		card.setText(text);
		card.setDateSubmitted(LocalDateTime.now());
		return card;
	}
}
