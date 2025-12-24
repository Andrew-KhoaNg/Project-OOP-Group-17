package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
	
	public List<Card> cards = new ArrayList<>();
	
	public CardDeck(int n) {
		if (!(n>=1 && n<= 13)) {
			throw new IllegalArgumentException("Too many/Few cards");
		}
		for (int i=1;i<n+1;i++) {
			cards.add(new Card('S', i));
		}
		
		for (int i=1;i<n+1;i++) {
			cards.add(new Card('H', i));
		}
		
		for (int i=1;i<n+1;i++) {
			cards.add(new Card('D', i));
		}
		
		for (int i=1;i<n+1;i++) {
			cards.add(new Card('C', i));
		}
	}
	
	public CardDeck() {
		this(13);
	}
	
	public int getCardCount() {
		return cards.size();
	}
	
	public Card getCard(int n) {
		if (!(n >= 0 && n <= cards.size()-1)) {
			throw new IllegalArgumentException("Invalid number");
		}
		
		return cards.get(n);
	}
	
	public void shufflePerfectly() {
		List<Card> topHalf = new ArrayList<>(cards.subList(0, (cards.size()/2)));
		List<Card> bottomHalf = new ArrayList<>(cards.subList((cards.size()/2), cards.size()));
		cards.clear();
		for (int i = 0; i<topHalf.size(); i++) {
			cards.add(topHalf.get(i));
			cards.add(bottomHalf.get(i));
		}
	}
	
	public static void main(String[] args) {
		CardDeck deck = new CardDeck();
		Collections.shuffle(deck.cards);
		System.out.println(deck.cards);
	}
	
}
