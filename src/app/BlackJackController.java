package app;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BlackJackController {

	@FXML private TextField nameInputField;
	@FXML private Label playerScoreLabel, dealerScoreLabel, outcome, pot, bank, info, bet, identifier, writeName, saveInfo;
	@FXML private ImageView playerCardImg1, playerCardImg2, playerCardImg3, playerCardImg4, playerCardImg5, dealerCardImg1, dealerCardImg2, dealerCardImg3, dealerCardImg4, dealerCardImg5;
	@FXML private Button clearBet, chip1, chip10, chip25, chip100, chip500, confirmBetButton, hit, stand, backToMenuButton, backFromLoadButton, load, load1, load2, load3, load4, enterName, newGame;
	@FXML private AnchorPane start;
	private String number;

	Game game = null;
	BlackjackIO io1 = new BlackjackIO();
	BlackjackIO io2 = new BlackjackIO();
	BlackjackIO io3 = new BlackjackIO();
	BlackjackIO io4 = new BlackjackIO();

	public void onStartNewGame() {
		try {
			if (nameInputField.getText().length() == 0) {
				throw new IllegalArgumentException("Write name");
			}
			game = new Game(500);
			bank.setText(Integer.toString(game.player.getMoney()));
            identifier.setText(nameInputField.getText());
			playerCardImg1.setImage(null);
            playerCardImg2.setImage(null);
            playerCardImg3.setImage(null);
            playerCardImg4.setImage(null);
            playerCardImg5.setImage(null);
            dealerCardImg1.setImage(null);
            dealerCardImg2.setImage(null);
            dealerCardImg3.setImage(null);
            dealerCardImg4.setImage(null);
            dealerCardImg5.setImage(null);
			playerScoreLabel.setText("");
            dealerScoreLabel.setText("");
            nameInputField.setText("");bet.setText("");info.setText("");pot.setText("");outcome.setText("");
			confirmBetButton.setVisible(false);hit.setVisible(false);stand.setVisible(false);start.setVisible(false);
		} catch (Exception e) {
			info.setText("Write a name");
		}
	}

	public void enterNameClick() {
		try {
			if (nameInputField.getText().length() == 0) {
				throw new IllegalArgumentException("Write a name");
			}
			nameInputField.setVisible(false);
			writeName.setVisible(false);
			enterName.setVisible(false);
			load1.setVisible(true);
			load2.setVisible(true);
			load3.setVisible(true);
			load4.setVisible(true);
			load.setVisible(false);
			saveInfo.setVisible(true);
		} catch (Exception e) {
			info.setText(e.getMessage());
		}
	}
	
	public void newGameClick() {
		try {
			newGame.setVisible(false);
			load.setVisible(false);
			nameInputField.setVisible(true);
			writeName.setVisible(true);
			enterName.setVisible(true);
			if (new File("spill1.txt").isFile()) {
				BlackjackObjectloader l1 = io1.load("spill1.txt");
				load1.setText(l1.getIdentifier());
			}
			if (new File("spill2.txt").isFile()) {
				BlackjackObjectloader l2 = io2.load("spill2.txt");
				load2.setText(l2.getIdentifier());
			}
			if (new File("spill3.txt").isFile()) {
				BlackjackObjectloader l3 = io3.load("spill3.txt");
				load3.setText(l3.getIdentifier());
			}
			if (new File("spill4.txt").isFile()) {
				BlackjackObjectloader l4 = io4.load("spill4.txt");
				load4.setText(l4.getIdentifier());
			}
		} catch (Exception e) {
			info.setText(e.getMessage());
		}
		
	}
	
	public void clickLoad() {
		try {
			load1.setVisible(true);
			load2.setVisible(true);
			load3.setVisible(true);
			load4.setVisible(true);
			load.setVisible(false);
			newGame.setVisible(false);
			backFromLoadButton.setVisible(true);
			
			if (new File("spill1.txt").isFile()) {
				BlackjackObjectloader l1 = io1.load("spill1.txt");
				load1.setText(l1.getIdentifier());
			}
			if (new File("spill2.txt").isFile()) {
				BlackjackObjectloader l2 = io2.load("spill2.txt");
				load2.setText(l2.getIdentifier());
			}
			if (new File("spill3.txt").isFile()) {
				BlackjackObjectloader l3 = io3.load("spill3.txt");
				load3.setText(l3.getIdentifier());
			}
			if (new File("spill4.txt").isFile()) {
				BlackjackObjectloader l4 = io4.load("spill4.txt");
				load4.setText(l4.getIdentifier());
			}
		} catch (Exception e) {
			
		}
	}

	public void onStartNewGame(String identifier, int money) {
		try {
			if (identifier == null || identifier== "") {
				throw new IllegalArgumentException("No load available");
			}
			game = new Game(money);
			bank.setText(Integer.toString(game.player.getMoney()));this.identifier.setText(identifier);
			playerCardImg1.setImage(null);
            playerCardImg2.setImage(null);
            playerCardImg3.setImage(null);
            playerCardImg4.setImage(null);
            playerCardImg5.setImage(null);
            dealerCardImg1.setImage(null);
            dealerCardImg2.setImage(null);
            dealerCardImg3.setImage(null);
            dealerCardImg4.setImage(null);
            dealerCardImg5.setImage(null);
			playerScoreLabel.setText("");
            dealerScoreLabel.setText("");
            nameInputField.setText("");bet.setText("");info.setText("Welcome back!");pot.setText("");outcome.setText("");
			confirmBetButton.setVisible(false);hit.setVisible(false);stand.setVisible(false);start.setVisible(false);
		} catch (Exception e) {
			info.setText("No load");
		}
	}


	public void betClick() {
		try {
			info.setText("");
			if (pot.getText().length() != 0) {
				throw new IllegalArgumentException("Money already in pot");
			}
			game.placeBet(Integer.parseInt(bet.getText()));
			bank.setText(Integer.toString(game.player.getMoney()));
			pot.setText(bet.getText());
			bet.setText("");
			Image b1 = new Image("/app/card/" + game.player.getCard1().toString() + ".png");
			playerCardImg1.setImage(b1);
			Image b2 = new Image("/app/card/" + game.player.getCard2().toString() + ".png");
			playerCardImg2.setImage(b2);
			Image bd1 = new Image("/app/card/" + game.dealer.getCard1().toString() + ".png");
			dealerCardImg1.setImage(bd1);
			Image bak = new Image("/app/card/backside.png");
			dealerCardImg2.setImage(bak);
			playerCardImg3.setImage(null);
            playerCardImg4.setImage(null);
            playerCardImg5.setImage(null);
            dealerCardImg3.setImage(null);
            dealerCardImg4.setImage(null);
            dealerCardImg5.setImage(null);
			outcome.setText("");
			playerScoreLabel.setText(Integer.toString(game.getPlayerSum()));
			dealerScoreLabel.setText("");info.setText("");
			hit.setVisible(true);stand.setVisible(true);
            confirmBetButton.setVisible(false);
			if (game.isBlackJack()) {
				game.cashOut();
				outcome.setText("Blackjack!");
				bank.setText(Integer.toString(game.player.getMoney()));
				info.setText("");pot.setText("");bet.setText("");
				game = new Game(game.player.getMoney());
				hit.setVisible(false);stand.setVisible(false);
			}
		} catch (Exception e) {
			info.setText("");
		}
	}

	public void hitClick() {
		try {
			info.setText("");
			if ((pot.getText().length() == 0)) {
				throw new IllegalArgumentException("Place bet first");
			}
			game.playerDrawCard();
			if (playerCardImg3.getImage() == null) {
				Image b3 = new Image("/app/card/" + game.card3.toString() + ".png");
				playerCardImg3.setImage(b3);
			}
			if (playerCardImg4.getImage() == null && game.card4 != null) {
				Image b4 = new Image("/app/card/" + game.card4.toString() + ".png");
				playerCardImg4.setImage(b4);
			}
			if (playerCardImg5.getImage() == null && game.card5 != null) {
				Image b5 = new Image("/app/card/" + game.card5.toString() + ".png");
				playerCardImg5.setImage(b5);
			}
			playerScoreLabel.setText(Integer.toString(game.getPlayerSum()));
			if (!(game.isScoreValid())) {
				if (game.player.getMoney() == 0) {
					info.setText("No more money");
					outcome.setText("You lost...");
					pot.setText("");bet.setText("");
					hit.setVisible(false);stand.setVisible(false);
					return;
				}
				outcome.setText("You lost...");
				bank.setText(Integer.toString(game.player.getMoney()));
				info.setText("");pot.setText("");bet.setText("");
				game = new Game(game.player.getMoney());
				hit.setVisible(false);stand.setVisible(false);
			}
		} catch (Exception e) {
			info.setText(e.getMessage());
		}
	}

	public void clickStand() {
		try {
			info.setText("");
			if (pot.getText().length() == 0) {
				throw new IllegalArgumentException("Place bet first");
			}
			Image bd2 = new Image("/app/card/" + game.dealer.getCard2().toString() + ".png");
			dealerCardImg2.setImage(bd2);
			dealerScoreLabel.setText(Integer.toString(game.getDealerSum()));
			while (game.getDealerSum() < 17) {
				game.dealerDrawCard();
				if (dealerCardImg3.getImage() == null) {
					Image bd3 = new Image("/app/card/" + game.dCard3.toString() + ".png");
					dealerCardImg3.setImage(bd3);
				}
				if (dealerCardImg4.getImage() == null && game.dCard4 != null) {
					Image bd4 = new Image("/app/card/" + game.dCard4.toString() + ".png");
					dealerCardImg4.setImage(bd4);
				}
				if (dealerCardImg5.getImage() == null && game.dCard5 != null) {
					Image bd5 = new Image("/app/card/" + game.dCard5.toString() + ".png");
					dealerCardImg5.setImage(bd5);
				}
				dealerScoreLabel.setText(Integer.toString(game.getDealerSum()));
				if (dealerCardImg5.getImage() != null) {
					break;
				}
			}
			if (game.isVictory() || game.getDealerSum() > 21) {
				game.cashOut();
				outcome.setText("You won!");
				bank.setText(Integer.toString(game.player.getMoney()));
				info.setText("");pot.setText("");bet.setText("");
				game = new Game(game.player.getMoney());
				hit.setVisible(false);stand.setVisible(false);
			} else if (game.isDraw()) {
				game.cashOut();
				outcome.setText("It's a draw");
				bank.setText(Integer.toString(game.player.getMoney()));
				info.setText("");pot.setText("");bet.setText("");
				game = new Game(game.player.getMoney());
				hit.setVisible(false);stand.setVisible(false);
			} else {
				if (game.player.getMoney() == 0) {
					info.setText("No more money...");
					pot.setText("");bet.setText("");
					outcome.setText("You lost...");
					hit.setVisible(false);stand.setVisible(false);
					return;
				}
				outcome.setText("You lost...");
				bank.setText(Integer.toString(game.player.getMoney()));
				info.setText("");pot.setText("");bet.setText("");
				game = new Game(game.player.getMoney());
				hit.setVisible(false);stand.setVisible(false);
			}
		} catch (Exception e) {
			info.setText(e.getMessage());
		}
	}

	public void addBet500() {
		try {
			if (pot.getText() != "") {
				throw new IllegalArgumentException();
			}
			if (Integer.parseInt(bank.getText()) < 500) {
				throw new IllegalArgumentException("Too poor...");
			}
			if (bet.getText() != "") {
				int sum = Integer.parseInt(bet.getText())+500;
				bet.setText(Integer.toString(sum));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-500));
			} else {
				bet.setText(Integer.toString(500));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-500));
			}
			if (bet.getText().length() != 0) {
				confirmBetButton.setVisible(true);
			}
		} catch (Exception e) {
			if (e.getMessage() == null) {
			} else {
			}
		}
	}

	public void addBet100() {
		try {
			if (pot.getText() != "") {
				throw new IllegalArgumentException();
			}
			if (Integer.parseInt(bank.getText()) < 100 || pot.getText() != "") {
				throw new IllegalArgumentException("Too poor...");
			}
			if (bet.getText() != "") {
				int sum = Integer.parseInt(bet.getText())+100;
				bet.setText(Integer.toString(sum));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-100));
			} else {
				bet.setText(Integer.toString(100));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-100));
			}
			if (bet.getText().length() != 0) {
				confirmBetButton.setVisible(true);
			}
		} catch (Exception e) {
			if (e.getMessage() == null) {
			} else {
			}
		}
	}

	public void addBet50() {
		try {
			if (pot.getText() != "") {
				throw new IllegalArgumentException();
			}
			if (Integer.parseInt(bank.getText()) < 50 || pot.getText() != "") {
				throw new IllegalArgumentException("Too poor...");
			}
			if (bet.getText() != "") {
				int sum = Integer.parseInt(bet.getText())+50;
				bet.setText(Integer.toString(sum));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-50));
			} else {
				bet.setText(Integer.toString(50));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-50));
			}
			if (bet.getText().length() != 0) {
				confirmBetButton.setVisible(true);
			}
		} catch (Exception e) {
			if (e.getMessage() == null) {
			} else {
			}
		}
	}

	public void addBet25() {
		try {
			if (pot.getText() != "") {
				throw new IllegalArgumentException();
			}
			if (Integer.parseInt(bank.getText()) < 25 || pot.getText() != "") {
				throw new IllegalArgumentException("Too poor...");
			}
			if (bet.getText() != "") {
				int sum = Integer.parseInt(bet.getText())+25;
				bet.setText(Integer.toString(sum));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-25));
			} else {
				bet.setText(Integer.toString(25));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-25));
			}
			if (bet.getText().length() != 0) {
				confirmBetButton.setVisible(true);
			}
		} catch (Exception e) {
			if (e.getMessage() == null) {
			} else {
			}
		}
	}

	public void addBet10() {
		try {
			if (pot.getText() != "") {
				throw new IllegalArgumentException();
			}
			if (Integer.parseInt(bank.getText()) < 10 || pot.getText() != "") {
				throw new IllegalArgumentException("Too poor...");
			}
			if (bet.getText() != "") {
				int sum = Integer.parseInt(bet.getText())+10;
				bet.setText(Integer.toString(sum));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-10));
			} else {
				bet.setText(Integer.toString(10));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-10));
			}
			if (bet.getText().length() != 0) {
				confirmBetButton.setVisible(true);
			}
		} catch (Exception e) {
			if (e.getMessage() == null) {
			} else {
			}
		}
	}

	public void addBet1() {
		try {
			if (pot.getText() != "") {
				throw new IllegalArgumentException();
			}
			if (Integer.parseInt(bank.getText()) < 1 || pot.getText() != "") {
				throw new IllegalArgumentException("Too poor...");
			}
			if (bet.getText() != "") {
				int sum = Integer.parseInt(bet.getText())+1;
				bet.setText(Integer.toString(sum));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-1));
			} else {
				bet.setText(Integer.toString(1));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-1));
			}
			if (bet.getText().length() != 0) {
				confirmBetButton.setVisible(true);
			}
		} catch (Exception e) {
			if (e.getMessage() == null) {
			} else {
			}
		}
	}

	public void onClearBet() {
		try {
			if (bet.getText() == "") {
				return;
			}
			int sum = Integer.parseInt(bet.getText()) + Integer.parseInt(bank.getText());
			bank.setText(Integer.toString(sum));
			bet.setText("");
			confirmBetButton.setVisible(false);
		} catch (Exception e) {
			if (e.getMessage() == null) {
			} else {
			}
		}
	}

	public void goBackToMenu() {
		try {
			if (hit.isVisible()) {
				throw new IllegalArgumentException("Play hand first!");
			}
			if (bet.getText().length() != 0) {
				throw new IllegalArgumentException("Money in the pot! Press '0'");
			}
			newGame.setVisible(true);
			start.setVisible(true);
			load1.setVisible(false);
			load2.setVisible(false);
			load3.setVisible(false);
			load4.setVisible(false);
			load.setVisible(true);
			saveInfo.setVisible(false);
			backFromLoadButton.setVisible(false);
			save();
		} catch (Exception e) {
			info.setText(e.getMessage());
		}
	}
	
	public void goBackToMenu2() {
		try {
			newGame.setVisible(true);
			start.setVisible(true);
			load1.setVisible(false);
			load2.setVisible(false);
			load3.setVisible(false);
			load4.setVisible(false);
			load.setVisible(true);
			saveInfo.setVisible(false);
			backFromLoadButton.setVisible(false);
		} catch (Exception e) {
			info.setText(e.getMessage());
		}
	}


	public void save() {
		try {
			if (this.number == "1") {
				io1.save("spill1.txt", identifier.getText(), Integer.parseInt(bank.getText()));
				return;
			}
			if (this.number == "2") {
				io2.save("spill2.txt", identifier.getText(), Integer.parseInt(bank.getText()));
				return;
			}
			if (this.number == "3") {
				io3.save("spill3.txt", identifier.getText(), Integer.parseInt(bank.getText()));
				return;
			}
			if (this.number == "4") {
				io4.save("spill4.txt", identifier.getText(), Integer.parseInt(bank.getText()));
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			info.setText("Something failed");
		}
	}

	public void load1() {
		try {
			if (nameInputField.getText().length() != 0) {
				load1.setText(nameInputField.getText());
				number = "1";
				info.setText(number);
				onStartNewGame();
				return;
			}
			if(!(new File("spill1.txt").isFile())) {
				number ="1";
				onStartNewGame();
				return;
			}
			BlackjackObjectloader loader = io1.load("spill1.txt");
			if (loader.getMoney() == 0) {
				load1.setText("0 money");
				number = "1";
				return;
			}
			load1.setText(loader.getIdentifier());
			number = "1";
			onStartNewGame(loader.getIdentifier(), loader.getMoney());
			backFromLoadButton.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
			info.setText("Can't load");
		}
	}
	
	public void load2() {
		try {
			if (nameInputField.getText().length() != 0){
				load2.setText(nameInputField.getText());
				number = "2";
				onStartNewGame();
				return;
			}
			if(!(new File("spill2.txt").isFile())) {
				number = "2";
				onStartNewGame();
				return;
			}
			BlackjackObjectloader loader2 = io2.load("spill2.txt");
			if (loader2.getMoney() == 0) {
				load2.setText("0 penger");
				number = "2";
				onStartNewGame();
				return;
			}
			load2.setText(loader2.getIdentifier());
			number = "2";
			onStartNewGame(loader2.getIdentifier(), loader2.getMoney());
			backFromLoadButton.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
			info.setText("Can't load");
		}
	}
	
	public void load3() {
		try {
			if (nameInputField.getText().length() != 0){
				load3.setText(nameInputField.getText());
				number = "3";
				onStartNewGame();
				return;
			}
			if(!(new File("spill3.txt").isFile())) {
				number = "3";
				onStartNewGame();
				return;
			}
			BlackjackObjectloader loader3 = io3.load("spill3.txt");
			if (loader3.getMoney() == 0) {
				load3.setText("0 penger");
				number = "3";
				onStartNewGame();
				return;
			}
			load3.setText(loader3.getIdentifier());
			number = "3";
			onStartNewGame(loader3.getIdentifier(), loader3.getMoney());
			backFromLoadButton.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
			info.setText("Can't load");
		}
	}
	
	public void load4() {
		try {
			if (nameInputField.getText().length() != 0){
				load4.setText(nameInputField.getText());
				number = "4";
				onStartNewGame();
				return;
			}
			if(!(new File("spill4.txt").isFile())) {
				number = "4";
				onStartNewGame();
				return;
			}
			BlackjackObjectloader loader4 = io4.load("spill4.txt");
			if (loader4.getMoney() == 0) {
				load4.setText("0 penger");
				number = "4";
				onStartNewGame();
				return;
			}
			load4.setText(loader4.getIdentifier());
			number = "4";
			onStartNewGame(loader4.getIdentifier(), loader4.getMoney());
			backFromLoadButton.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
			info.setText("Can't load");
		}
	}








}
