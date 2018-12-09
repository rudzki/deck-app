package org.rudzki.deckapp.model;


import java.util.List;
import java.util.Map;

public interface CardDao {
	public List<Card> getAllCards();
	public Map<Long, Double> getSortedCards();
	public List<Card> getCardsByCategoryId(int categoryId);
	public Card getCard(long id);
	public void addScore(long cardId, int score);
	public double getAverageScore(long cardId);
	public void save(Card card);
	public boolean categoryNameExists(String name);
	public boolean cardExists(long cardId);
	public void createCategory(String name);
	public String getCategoryName(int id);
	public Map<Integer, String> listCategories();
}
