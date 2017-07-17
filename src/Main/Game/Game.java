package Main.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

  private final double minimumBet;
  private final double maximumBet;
  private final double smallBlind;
  private final double bigBlind;
  private final int amountOfPlayers;
  private Seat dealer;

  private Game(double minimumBet, double maximumBet, double smallBlind,
      double bigBlind, int amountOfPlayers){
    this.minimumBet = minimumBet;
    this.maximumBet = maximumBet;
    this.smallBlind = smallBlind;
    this.bigBlind = bigBlind;
    this.amountOfPlayers = amountOfPlayers;
  }

  public Game(double minimumBet, double maximumBet, double smallBlind,
      double bigBlind, String playerNames[]){
    this(minimumBet, maximumBet, smallBlind, bigBlind, playerNames.length);
    for(String name : playerNames){
       if(dealer == null){
         dealer = new Seat(new Player(name, smallBlind * 100));
       } else {
         dealer.setNext(new Seat(new Player(name, smallBlind * 100)));
         dealer = dealer.getNext();
       }
    }
  }
/* for simulate
  public Game(double minimumBet, double maximumBet, double smallBlind,
      double bigBlind){

  }*/

  public void run(){
    boolean again;
    //3a
    findDealer();
    do{
      Round current = new Round(dealer, amountOfPlayers);
      //Rule 1a
      current.play(minimumBet, maximumBet, smallBlind, bigBlind);
      again = playAgain();
      dealer = dealer.getNext();
    } while(again);
  }

  private void findDealer(){
    //TODO: Check
    Deck cards = new Deck();
    Seat newDealer;
    //Smallest possible card
    Card currentMax = new Card(2, Suit.DIAMONDS);
    for(int i = 0; i < amountOfPlayers; i++){
      dealer.deal(cards.deal(), 0);
      CardComparator cc = new CardComparator();
      if(cc.compare(currentMax, dealer.getCard(0)) == -1){
        //newCard > currentMax
        newDealer = dealer;
        currentMax = newDealer.getCard(0);
      }
      dealer = dealer.getNext();
    }
  }

  private boolean playAgain(){
    return true;
  }
}