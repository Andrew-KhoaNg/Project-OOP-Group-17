package app;

import java.util.Collections;

public class Game { 
    
    public Player player, dealer; 
    public CardDeck deck;       
    public Card card3, card4, card5, dCard3, dCard4, dCard5 = null; 
    public int currentBet;       
    
    public Game(int initialMoney) { 
        
        if (initialMoney > 0) {
            this.deck = new CardDeck();
            Collections.shuffle(deck.cards); 
            
            this.player = new Player(initialMoney, deck.cards.remove(0), deck.cards.remove(0));
            this.dealer = new Player(0, deck.cards.remove(0), deck.cards.remove(0));
        } else {
            throw new IllegalArgumentException("Amount must be a number higher than zero");
        }
    }
    
    public void placeBet(int amount) { 	
        if (amount < 1 || amount > this.player.getMoney() || amount != (int) amount) {
            throw new IllegalArgumentException("Illegal bet");
        }
        this.player.setMoney(this.player.getMoney() - amount);
        this.currentBet = amount;
    }
    
    public void cashOut() {
        if (isBlackJack()) {
            this.player.setMoney((int) Math.round(((this.player.getMoney() + this.currentBet + this.currentBet * 1.5))));
        } else if (getDealerSum() > 21 || isVictory()){
            this.player.setMoney(this.player.getMoney() + (this.currentBet) * 2);
        } else if (isDraw()) {
            this.player.setMoney(this.player.getMoney() + this.currentBet);
        }
    }
    
    public int calculatePlayerRawSum() {
        int sum = 0;
        sum += getCardValue(this.player.getCard1());
        sum += getCardValue(this.player.getCard2());
        
        if (this.card3 == null) return sum;
        sum += getCardValue(this.card3);
        
        if (this.card4 == null) return sum;
        sum += getCardValue(this.card4);
        
        if (this.card5 == null) return sum;
        return sum + getCardValue(this.card5);
    }

    private int getCardValue(Card card) {
        int val = Integer.parseInt(card.toString().substring(1));
        if (val > 9) return 10;
        if (val == 1) return 11; // Mặc định Át là 11
        return val;
    }

    private int getCardValueNoAce(Card card) {
        int val = Integer.parseInt(card.toString().substring(1));
        if (val > 10) return 10;
        return val;
    }
    
    public int getPlayerSum() {
        int sum = calculatePlayerRawSum();
        
        if (sum > 21 && Integer.parseInt(this.player.getCard1().toString().substring(1)) == 1) sum -= 10;
        if (sum > 21 && Integer.parseInt(this.player.getCard2().toString().substring(1)) == 1) sum -= 10;
        if (sum > 21 && card3 != null && Integer.parseInt(this.card3.toString().substring(1)) == 1) sum -= 10;
        if (sum > 21 && card4 != null && Integer.parseInt(this.card4.toString().substring(1)) == 1) sum -= 10;
        if (sum > 21 && card5 != null && Integer.parseInt(this.card5.toString().substring(1)) == 1) sum -= 10;
        
        return sum;
    }

    public int getPlayerSumNoAces() {
        int sum = 0;
        sum += getCardValueNoAce(this.player.getCard1());
        sum += getCardValueNoAce(this.player.getCard2());
        if (this.card3 != null) sum += getCardValueNoAce(this.card3);
        if (this.card4 != null) sum += getCardValueNoAce(this.card4);
        if (this.card5 != null) sum += getCardValueNoAce(this.card5);
        return sum;
    }
    
    public int getDealerSumNoAces() {
        int sum = 0;
        sum += getCardValueNoAce(this.dealer.getCard1());
        sum += getCardValueNoAce(this.dealer.getCard2());
        if (this.dCard3 != null) sum += getCardValueNoAce(this.dCard3);
        if (this.dCard4 != null) sum += getCardValueNoAce(this.dCard4);
        if (this.dCard5 != null) sum += getCardValueNoAce(this.dCard5);
        return sum;
    }
    
    public int calculateDealerRawSum() {
        int sum = 0;
        sum += getCardValue(this.dealer.getCard1());
        sum += getCardValue(this.dealer.getCard2());
        if (this.dCard3 == null) return sum;
        sum += getCardValue(this.dCard3);
        if (this.dCard4 == null) return sum;
        sum += getCardValue(this.dCard4);
        if (this.dCard5 == null) return sum;
        return sum + getCardValue(this.dCard5);
    }
    
    public int getDealerSum() {
        int sum = calculateDealerRawSum();
        
        if (sum > 21 && Integer.parseInt(this.dealer.getCard1().toString().substring(1)) == 1) sum -= 10;
        if (sum > 21 && Integer.parseInt(this.dealer.getCard2().toString().substring(1)) == 1) sum -= 10;
        if (sum > 21 && dCard3 != null && Integer.parseInt(this.dCard3.toString().substring(1)) == 1) sum -= 10;
        if (sum > 21 && dCard4 != null && Integer.parseInt(this.dCard4.toString().substring(1)) == 1) sum -= 10;
        if (sum > 21 && dCard5 != null && Integer.parseInt(this.dCard5.toString().substring(1)) == 1) sum -= 10;
        
        return sum;
    }
    
    public boolean isBlackJack() {
        if (getPlayerSum() == 21 && card3 == null && card4 == null && card5 == null) {
            return true;
        }
        return false;
    }
    
    public boolean isScoreValid() { 
        return getPlayerSum() <= 21;
    }
    
    public void playerDrawCard() { 
        if (getPlayerSum() <= 21) {
            if (this.card3 == null) {
                this.card3 = this.deck.cards.remove(0);
                return;
            }
            if (this.card4 == null) {
                this.card4 = this.deck.cards.remove(0);
                return;
            }
            if (this.card5 == null) {
                this.card5 = this.deck.cards.remove(0);
                return;
            }
        }
        throw new IllegalArgumentException("Can't draw kort");
    }
    
    public void dealerDrawCard() { 
        if (getDealerSum() < 17) {
            if (this.dCard3 == null) {
                this.dCard3 = this.deck.cards.remove(0);
                return;
            }
            if (this.dCard4 == null) {
                this.dCard4 = this.deck.cards.remove(0);
                return;
            }
            if (this.dCard5 == null) {
                this.dCard5 = this.deck.cards.remove(0);
                return;
            }
        }
        throw new IllegalArgumentException("Dealer can't draw kort");
    }
    
    public boolean isVictory() {
        return getPlayerSum() > getDealerSum();
    }
    
    public boolean isDraw() {
        return getPlayerSum() == getDealerSum();
    }
    
    public static void main(String[] args) {
        Game game = new Game(500);
        System.out.println(game.player.getCard1());
        System.out.println(game.player.getCard2());
        System.out.println(game.getPlayerSum());
        System.out.println(game.card3);
        
        game.playerDrawCard();
        System.out.println(game.card3);
        
        game.playerDrawCard();
        System.out.println(game.card4);
        
        game.playerDrawCard();
        System.out.println(game.card5);
        
        System.out.println(game.getPlayerSum());
    }
}