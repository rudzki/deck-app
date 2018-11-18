package com.techelevator.model;

import java.util.List;
import java.util.Map;

public interface CardDao {
	public List<Card> getAllCards();
	public List<Long> getSortedCardIds();
	public List<Card> getCardsByCategoryId(int categoryId);
	public Card getCard(long id);
	public void addScore(long cardId, int score);
	public double getAverageScore(long cardId);
	public void save(Card card);
	public void createCategory(String name);
	public String getCategoryName(int id);
	public Map<Integer, String> listCategories();
}
