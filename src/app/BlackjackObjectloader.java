package app;

public class BlackjackObjectloader {
	
	private int money;
	private String identifier;
	
	public BlackjackObjectloader(String identifier, int money) {
		this.money = money;
		this.identifier = identifier;
	}

	public int getMoney() {
		return money;
	}

	public String getIdentifier() {
		return identifier;
	}
	

}
