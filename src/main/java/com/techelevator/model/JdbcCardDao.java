package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

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
			card.setQuestion(results.getString("card_question"));
			card.setAnswer(results.getString("card_answer"));
			card.setDateSubmitted(results.getTimestamp("card_date").toLocalDateTime());
			allCards.add(card);
		}
		return allCards;
	}
	
	@Override
	public List<Long> getSortedCardIds() {
		List<Long> sortedCardIds = new ArrayList<>();
		String sqlSortedCardIds = "SELECT cards.id AS id, avg(scores.score) FROM cards JOIN scores ON cards.id=scores.card_id GROUP BY cards.id ORDER BY avg(scores.score) ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSortedCardIds);
		while(results.next()) {
			sortedCardIds.add(results.getLong("id"));
		}
		return sortedCardIds;
	}
	
	@Override
	public Card getCard(long id) {
		String sqlSelectCard = "SELECT * FROM cards WHERE id=?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectCard, id);
		Card card = new Card();
		if(results.next()) {
			card.setId(results.getLong("id"));
			card.setQuestion(results.getString("card_question"));
			card.setAnswer(results.getString("card_answer"));
			card.setCategoryId(results.getInt("category_id"));
			card.setDateSubmitted(results.getTimestamp("card_date").toLocalDateTime());
		}
		return card;
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
		String sqlAddScore = "INSERT INTO scores(card_id, score) VALUES (?,?)";
		jdbcTemplate.update(sqlAddScore, cardId, score);
	}
	
	@Override
	public double getAverageScore(long cardId) {
		String sqlSelectScores = "SELECT avg(score) AS average FROM scores WHERE card_id=?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectScores, cardId);
		double averageScore = 0;
		if(results.next()) {
			averageScore = results.getDouble("average");
		}
		return averageScore;
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
