package Main.Game;

import static Main.Parsers.getPlayerChoice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Player {

  private final String name;
  private double money;
  private Card[] cards;
  private double alreadyBetted;
  private Choice choice;

  Player(String name, double money) {
    this.name = name;
    this.money = money;
    this.cards = new Card[2];
  }

  double playersTurn(double currentBet) {
    try {
      choice = getPlayerChoice();
      switch(choice.getChoice()){
        case RAISE:
          if(currentBet + choice.getRaiseAmount() > money){
            //Doesnt have enough money
            System.out.println("You have: " + money);
            System.out.println("Current bet is: " + currentBet);
            System.out.println("You cannot raise by: " + choice.getRaiseAmount());
            return playersTurn(currentBet);
          } else if(choice.getRaiseAmount() < 1){
            //TODO deal with minimum bets, maximum bets
          } else {
            return bet(currentBet + choice.getRaiseAmount());
          }
        case CHECK:
          //TODO: deal with blinds
          if(Double.compare(currentBet, 0) == 0){
            return 0;
          } else {
            System.out.println("You cannot check a bet greater than 0");
            System.out.println("Call, raise, or fold");
            return playersTurn(currentBet);
          }
        case CALL:
          if(currentBet > money){
            System.out.println("You all-in");
            return bet(money);
          } else {
            return bet(currentBet);
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
    return cards[pos];
  }

  boolean raised(){
    return choice.getChoice() == ChoiceEnum.RAISE;
  }

  double raisedBy(){
    assert raised();
    return choice.getRaiseAmount();
  }
}
