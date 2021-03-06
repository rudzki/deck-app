//package org.rudzki.deckapp.model;
//
//import static org.hamcrest.CoreMatchers.hasItem;
//import static org.junit.Assert.assertThat;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.hamcrest.CoreMatchers;
//import org.hamcrest.Description;
//import org.hamcrest.Matcher;
//import org.hamcrest.TypeSafeMatcher;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import org.rudzki.deckapp.DAOIntegrationTest;
//import org.rudzki.deckapp.model.JdbcCardDao;
//import org.rudzki.deckapp.model.Card;
//
//public class JdbcCardDaoIntegrationTest extends DAOIntegrationTest {
//
//	private JdbcCardDao dao;
//	
//	@Before
//	public void setup() {
//		dao = new JdbcCardDao(getDataSource());
//	}
//	
//	@Before
//	public void cleanDatabase() {
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
//		jdbcTemplate.update("DELETE FROM cards");
//	}
//	@Test
//	public void saved_cards_are_returned_in_list_of_all_cards() {
//		Card card1 = createCard("Good Cop", (long)5, 1, "Loved it.");
//		Card card2 = createCard("Bad Cop", (long)1, 2, "Hated it.");
//		
//		dao.save(card1);
//		dao.save(card2);
//		
//		List<Card> results = dao.getAllCards();
//
//		assertThat(results, hasItem(equalTo(card1)));
//		assertThat(results, hasItem(equalTo(card2)));
//		assertThat(results.size(), CoreMatchers.equalTo(2));
//	}
//	
//	private Matcher<Card> equalTo(final Card expected) {
//		return new TypeSafeMatcher<Card>() {
//
//			public void describeTo(Description desc) {
//				
//			}
//
//			protected boolean matchesSafely(Card card) {
//				return nullOrEqual(expected.getId(), card.getId()) &&
//						nullOrEqual(expected.getAnswer(), card.getAnswer()) &&
//						nullOrEqual(expected.getCategoryId(), card.getCategoryId()) &&
//						nullOrEqual(expected.getQuestion(), card.getQuestion()) &&
//						nullOrEqual(expected.getDateSubmitted(), card.getDateSubmitted());
//						   
//			}
//			
//			private boolean nullOrEqual(Object left, Object right) {
//				return left != null ? left.equals(right) : right == null;
//			}
//		};
//	}
//
//	private Card createCard(String answer, long id, int categoryId, String question) {
//		Card card = new Card();
//		card.setAnswer(answer);
//		card.setId(id);
//		card.setCategoryId(categoryId);
//		card.setQuestion(question);
//		card.setDateSubmitted(LocalDateTime.now());
//		return card;
//	}
//}
