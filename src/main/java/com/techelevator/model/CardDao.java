package com.techelevator.model;

import java.util.List;

public interface CardDao {
	public List<Card> getAllCards();
	public List<Long> getSortedCardIds();
	public Card getCard(long id);
	public void addScore(long cardId, int score);
	public double getAverageScore(long cardId);
	public void save(Card card);
}
