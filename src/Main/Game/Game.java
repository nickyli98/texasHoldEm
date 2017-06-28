package Main.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

  private final double minimumBet;
  private final double maximumBet;
  private final double smallBlind;
  private final double bigBlind;
  private Seat dealer;

  private Game(double minimumBet, double maximumBet, double smallBlind,
      double bigBlind){
    this.minimumBet = minimumBet;
    this.maximumBet = maximumBet;
    this.smallBlind = smallBlind;
    this.bigBlind = bigBlind;
  }

  public Game(double minimumBet, double maximumBet, double smallBlind,
      double bigBlind, String playerNames[]){
    this(minimumBet, maximumBet, smallBlind, bigBlind);
    for(String name : playerNames){
       if(dealer == null){
         dealer = new Seat(new Player(name));
       } else {
         dealer.setNext(new Seat(new Player(name)));
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
      Round current = new Round(dealer);
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
    Seat current = dealer;
    //Smallest possible card
    Card currentMax = new Card(2, Suit.DIAMONDS);
    while(current != dealer){
      current.deal(cards.deal(), 0);
      CardComparator cc = new CardComparator();
      if(cc.compare(currentMax, current.getCard(0)) == -1){
        //newCard > currentMax
        newDealer = current;
        currentMax = newDealer.getCard(0);
      }
      current = dealer.getNext();
    }
  }

  private boolean playAgain(){
    return true;
  }
}