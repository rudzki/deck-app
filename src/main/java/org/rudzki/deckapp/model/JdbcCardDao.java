package org.rudzki.deckapp.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcCardDao implements CardDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcCardDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Card> getAllCards() {
		List<Card> allCards = new ArrayList<>();
		String sqlSelectAllCards = "SELECT * FROM cards";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllCards);
		while(results.next()) {
			Card card = new Card();
			card.setId(results.getLong("id"));
			card.setCategoryId(results.getInt("category_id"));
			card.setCategoryName(getCategoryName(results.getInt("category_id")));
			card.setQuestion(results.getString("card_question"));
			card.setAnswer(results.getString("card_answer"));
			card.setDateSubmitted(results.getTimestamp("card_date").toLocalDateTime());
			allCards.add(card);
		}
		return allCards;
	}
	
	@Override
	public List<Card> getCardsByCategoryId(int categoryId) {
		List<Card> cardsInCategory = new ArrayList<>();
		String sqlCardsByCategory = "SELECT * FROM cards WHERE category_id=?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlCardsByCategory, categoryId);
		while(results.next()) {
			Card card = new Card();
			card.setId(results.getLong("id"));
			card.setCategoryId(results.getInt("category_id"));
			card.setCategoryName(getCategoryName(results.getInt("category_id")));
			card.setQuestion(results.getString("card_question"));
			card.setAnswer(results.getString("card_answer"));
			card.setDateSubmitted(results.getTimestamp("card_date").toLocalDateTime());
			cardsInCategory.add(card);
		}
		return cardsInCategory;		
	}

	
	@Override
	public Map<Long, Double> getSortedCards() {
		Map<Long, Double> sortedCards = new LinkedHashMap<Long, Double>();
		String sqlSortedCards = "SELECT cards.id AS id, avg(scores.score) FROM cards LEFT JOIN scores ON cards.id=scores.card_id GROUP BY cards.id ORDER BY avg(scores.score) ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSortedCards);
		while(results.next()) {
			long cardId = results.getLong("id");
			double avgScore = getAverageScore(cardId);
			sortedCards.put(cardId, avgScore);
		}
		return sortedCards;
	}
	
	@Override
	public Card getCard(long id) {
		String sqlSelectCard = "SELECT * FROM cards WHERE id=?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectCard, id);
		Card card = new Card();
		if(results.next()) {
			card.setId(results.getLong("id"));
			card.setCategoryId(results.getInt("category_id"));
			card.setCategoryName(getCategoryName(results.getInt("category_id")));
			card.setQuestion(results.getString("card_question"));
			card.setAnswer(results.getString("card_answer"));
			card.setCategoryId(results.getInt("category_id"));
			card.setDateSubmitted(results.getTimestamp("card_date").toLocalDateTime());
		}
		return card;
	}
	
	@Override
	public void deleteCard(long id) {
		String sqlDeleteCardScores = "DELETE FROM scores WHERE card_id=?";
		jdbcTemplate.update(sqlDeleteCardScores, id);
		String sqlDeleteCard = "DELETE FROM cards WHERE id=?";
		jdbcTemplate.update(sqlDeleteCard, id);	
	}

	@Override
	public void save(Card card) {
		Long id = getNextId();
		String sqlInsertCard = "INSERT INTO cards(id, card_question, card_answer, category_id, card_date) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sqlInsertCard, id, card.getQuestion(), card.getAnswer(), card.getCategoryId(), card.getDateSubmitted());
		card.setId(id);
	}
	
	@Override
	public void addScore(long cardId, int score) {
		// enforce max score: if provided score is over 2, set it as 2
		if (score > 2) {
			score = 2;
		}
		String sqlAddScore = "INSERT INTO scores(card_id, score) VALUES (?,?)";
		jdbcTemplate.update(sqlAddScore, cardId, score);
	}
	
	@Override
	public double getAverageScore(long cardId) {
		
		// average last five scores for a given card
		String sqlSelectScores = "SELECT avg(subquery.last_five_scores) as average FROM " + 
								"(SELECT scores.id, scores.score as last_five_scores FROM scores " + 
								"WHERE card_id=? ORDER BY id DESC limit 5) AS subquery;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectScores, cardId);
		double averageScore = 0;
		if(results.next()) {
			averageScore = results.getDouble("average");
		}
		return averageScore;
	}

	@Override
	public void createCategory(String name) {
		name = name.trim();
		name = WordUtils.capitalizeFully(name);
		if (name != "" && !categoryNameExists(name)) {
			String sqlCreateCategory = "INSERT INTO categories(name) VALUES (?)";
			jdbcTemplate.update(sqlCreateCategory, name);
		}
	}
	
	@Override
	public boolean categoryNameExists(String name) {
		name = name.trim();
		name = WordUtils.capitalizeFully(name);
		String sqlGetCategoryName = "SELECT name FROM categories WHERE name=?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCategoryName, name);	
		String categoryName = null;
		while (results.next()) {
			categoryName = results.getString("name");
		}
		if (categoryName != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean cardExists(long cardId) {
		String sqlSelectCard = "SELECT * FROM cards WHERE id=?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectCard, cardId);
		if (!results.isBeforeFirst() ) {    
		    return false;
		} 
		return true;
	}
	
	@Override
	public Map<Integer, String> listCategories() {
		String sqlListCategories = "SELECT * FROM categories ORDER BY name ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlListCategories);	
		Map<Integer, String> categories = new LinkedHashMap<Integer, String>();
		while (results.next()) {
			int id = results.getInt("id");
			String name = results.getString("name");
			categories.put(id, name);
		}
		return categories;
	}

	@Override
	public String getCategoryName(int id) {
		String sqlGetCategoryName = "SELECT name FROM categories WHERE id=?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCategoryName, id);	
		String categoryName = null;
		while (results.next()) {
			categoryName = results.getString("name");
		}
		return categoryName;		
	}
	
	private Long getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_card_id')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		Long id = null;
		if(results.next()) {
			id = results.getLong(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next forum post id from sequence");
		}
		return id;
	}

}
