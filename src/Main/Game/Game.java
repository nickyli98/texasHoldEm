package Main.Game;

import static Main.Utils.tryParseDouble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

  private int amountOfPlayers;
  private final Deck deck;
  private Seat dealer;
  private final Double minimumBet;
  private final Double maximumBet;

  public Game(int amountOfPlayers) throws IOException {
    this.amountOfPlayers = amountOfPlayers;
    this.deck = new Deck();
    dealer = null;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Please enter a minimum bet");
    minimumBet = tryParseDouble(reader.readLine());
    System.out.println("Please enter a maximum bet");
    maximumBet = tryParseDouble(reader.readLine());
    if(minimumBet == null || maximumBet == null || minimumBet > maximumBet
        || minimumBet < 0 || maximumBet < 0){
      throw new IllegalArgumentException("Please enter a valid minimum and maximum bet");
    }

    initiate();
  }

  private void initiate(){
    Seat first = null;
    for(int i = 0; i < amountOfPlayers; i++){
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      try{
        System.out.println("Please enter a name");
        String name = reader.readLine();
        if(dealer == null){
          dealer = new Seat(new Player(name));
          first = dealer;
        } else {
          dealer.setNext(new Seat(new Player(name)));
          dealer = dealer.getNext();
        }
      } catch (IOException e) {
        System.out.println("Main.Game.Main.Game.java - initiate() BR error");
        return;
      }
    }
    dealer.setNext(first);
    dealer = dealer.getNext();
    //Everyone will be deal a card, as per 3
    while(dealer != first){
      dealer.getPlayer().setC1(deck.deal());
    }
    //TODO: Finding the dealer, as per 3a
    dealer.getNext().setSmall(true);
    dealer.getNext().getNext().setBig(true);
  }

  public void run(){

  }
}