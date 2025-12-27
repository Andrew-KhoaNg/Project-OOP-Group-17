package app;

public class Card {
	
	private char suit;
	private int faceValue;
	
	public Card(char card, int number) {
		if (!validCard(card, number)) {
			throw new IllegalArgumentException("Invalid card");
		}
		suit = card;
		faceValue = number;
	}
	
	private boolean validCard(char card, int number) {
		if (!(card == 'S' || card == 'H' || card == 'D' || card == 'C')) {
			return false;
		}
		if (!(number >= 1 & number <= 13)) {
			return false;
		}
		return true;
	}
	
	public char getSuit() {
		return suit;
	}
	
	public int getFace() {
		return faceValue;
	}
	
	public String toString() {
		return ""+suit+faceValue;
	}

}
