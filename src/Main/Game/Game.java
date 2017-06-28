package Main.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

  private int amountOfPlayers;
  private final Deck deck;
  private final double minimumBet;
  private final double maximumBet;
  private final double smallBlind;
  private final double bigBlind;
  private Seat dealer;

  public Game(int amountOfPlayers, double minimumBet, double maximumBet, double smallBlind,
      double bigBlind){
    this.amountOfPlayers = amountOfPlayers;
    this.minimumBet = minimumBet;
    this.maximumBet = maximumBet;
    this.smallBlind = smallBlind;
    this.bigBlind = bigBlind;
    this.deck = new Deck();
  }

  public Game(int amountOfPlayers, double minimumBet, double maximumBet, double smallBlind,
      double bigBlind, String playerNames[]){
    this(amountOfPlayers, minimumBet, maximumBet, smallBlind, bigBlind);
    assert(playerNames.length == amountOfPlayers);
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
  public Game(int amountOfPlayers, double minimumBet, double maximumBet, double smallBlind,
      double bigBlind, ){

  }*/


  private void initiate(){

  }

  public void run(){
    initiate();

  }
}