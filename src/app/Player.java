package app;

public class Player { 
    
    private int money; 
    private Card card1, card2; 
    
    // Getter và Setter cho money
    public int getMoney() {
       return money;
    }
    public void setMoney(int money) {
       this.money = money;
    }
    // Getter và Setter cho card1
    public Card getCard1() {
       return card1;
    }
    public void setCard1(Card card1) {
       this.card1 = card1;
    }
    // Getter và Setter cho card2
    public Card getCard2() {
       return card2;
    }
    public void setCard2(Card card2) {
       this.card2 = card2;
    }
    // Constructor
    public Player(int money, Card card1, Card card2) {
       this.money = money;
       this.card1 = card1;
       this.card2 = card2;
    }
}
