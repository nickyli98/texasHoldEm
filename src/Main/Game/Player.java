package Main.Game;

import static Main.Parsers.getPlayerChoice;

import java.io.IOException;
import java.nio.DoubleBuffer;

class Player {

  private final String name;
  private double money;
  private Card[] cards;
  private double alreadyBetted;
  private Choice choice = new Choice(ChoiceEnum.CHECK, 0);

  Player(String name, double money) {
    this.name = name;
    this.money = money;
    this.cards = new Card[2];
  }

  double playersTurn(double currentBet, double minimumBet, double maximumBet) {
    System.out.println(name + "'s cards:");
    System.out.println(printCards());
    System.out.println("You have: " + money);
    System.out.println("Current bet is $" + currentBet);
    try {
      choice = getPlayerChoice();
      switch(choice.getChoice()){
        case RAISE:
          if(currentBet + choice.getRaiseAmount() > money){
            //Doesnt have enough money
            System.out.println("You have: " + money);
            System.out.println("Current bet is: " + currentBet);
            System.out.println("You cannot raise by: " + choice.getRaiseAmount());
            return playersTurn(currentBet, minimumBet, currentBet);
          } else if(choice.getRaiseAmount() < minimumBet || choice.getRaiseAmount() + currentBet > maximumBet){
            System.out.println("Invalid bet. Current bet: " + currentBet);
            System.out.println("You raised by: " + choice.getRaiseAmount());
            System.out.println("Minimum bet: " + minimumBet + " Maximum bet: " + maximumBet);
            return playersTurn(currentBet, minimumBet, maximumBet);
          } else {
            return bet(currentBet + choice.getRaiseAmount());
          }
        case CHECK:
          if(Double.compare(currentBet, alreadyBetted) == 0){
            //Blinds
            return 0;
          } else if(Double.compare(currentBet, 0) == 0){
            return 0;
          } else {
            System.out.println("You cannot check a bet greater than 0");
            System.out.println("Call, raise, or fold");
            return playersTurn(currentBet, minimumBet, currentBet);
          }
        case CALL:
          if(currentBet > money){
            System.out.println("You all-in");
            return bet(money);
          } else {
            return bet(currentBet - alreadyBetted);
          }
      }
    } catch (IOException e) {
      System.out.println("Player choice error");
      e.printStackTrace();
    }
    return 0;
  }

  boolean inGame(){
    return choice.getChoice() != ChoiceEnum.FOLD;
  }

  double bet(double amount) {
    if(amount < money){
      alreadyBetted += amount;
      money -= amount;
      return amount;
    } else {
      //TODO Fix
      throw new IllegalStateException("Ran out of money");
    }
  }

  void deal(Card c, int pos) {
    cards[pos] = c;
  }

  Card getCard(int pos){
    assert(pos == 1 || pos == 0);
    Card c = cards[pos];
    if(c == null){
      return new Card(0, Suit.EMPTY);
    } else {
      return c;
    }
  }

  boolean raised(){
    return choice.getChoice() == ChoiceEnum.RAISE;
  }

  double raisedBy(){
    assert raised();
    return choice.getRaiseAmount();
  }

  @Override
  public String toString(){
    return name + " - $" + money + " Cards: " + printCards();
  }

  private String printCards() {
    String first = getCard(0).toString();
    String second = getCard(1).toString();
    if(first.equals("")){
      return "No cards found";
    } else if(second.equals("")){
      return first;
    } else {
      return first + ", " + second;
    }
  }

  void resetAlreadyBetted(){
    alreadyBetted = 0;
  }

}
