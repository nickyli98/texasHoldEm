package Main.Game;

import static Main.Game.GameTester.printPlayers;

class Round {

  private final Seat dealer;
  private final int amountOfPlayers;
  private final Deck deck;
  private final Card[] tableCards;
  private double pot;

  Round(Seat dealer, int amountOfPlayers) {
    this.dealer = dealer;
    this.deck = new Deck();
    this.tableCards = new Card[5];
    pot = 0;
    this.amountOfPlayers = amountOfPlayers;
  }

  void play(double minimumBet, double maximumBet, double smallBlind, double bigBlind){
    //Rule 1b, 2a
    printPlayers(dealer, amountOfPlayers);
    System.out.println();
    setBlinds(dealer.getNext(), smallBlind, bigBlind);
    printPlayers(dealer, amountOfPlayers);
    System.out.println();
    //5
    dealCards(dealer.getNext());
    printPlayers(dealer, amountOfPlayers);
    System.out.println();
    //6
    playRound(dealer.getNext().getNext().getNext(), bigBlind, minimumBet, maximumBet);
    printPlayers(dealer, amountOfPlayers);
    System.out.println();
    resetAlreadyBetted();
    //8
    deck.deal();
    //9
    for(int i = 0; i < 3; i++){
      tableCards[i] = deck.deal();
    }
    printCommunityCards();
    //11
    playRound(dealer.getNext(), 0, minimumBet, maximumBet);
    resetAlreadyBetted();
    printPlayers(dealer, amountOfPlayers);
    System.out.println();
    //13
    deck.deal();
    //14
    tableCards[3] = deck.deal();
    printCommunityCards();
    //16
    playRound(dealer.getNext(),0, minimumBet, maximumBet);
    resetAlreadyBetted();
    //18
    deck.deal();
    //19
    tableCards[4] = deck.deal();
    printCommunityCards();
    //21
    playRound(dealer.getNext(),0, minimumBet, maximumBet);
    resetAlreadyBetted();
    System.out.println("Round over");
    printCommunityCards();
    printPlayers(dealer, amountOfPlayers);
  }

  private void dealCards(Seat begin){
    for(int i = 0; i < 2; i++){
      for(int j = 0; j < amountOfPlayers; j++){
        begin.getPlayer().deal(deck.deal(), i);
        begin = begin.getNext();
      }
    }
  }

  private void playRound(Seat begin, double currentBet, double minimumBet, double maximumBet){
    for(int player = 0; player < amountOfPlayers; player++){
      pot += begin.play(currentBet, minimumBet, maximumBet);
      if(begin.getPlayer().raised()){
        currentBet += begin.getPlayer().raisedBy();
        //Reset for loop for everyone to call/fold the raise
        player = 0;
      }
      begin = begin.getNext();
    }
  }

  //2a, 4a, 4b
  private void setBlinds(Seat current, double smallBlind, double bigBlind){
    pot += current.getPlayer().bet(smallBlind);
    pot += current.getNext().getPlayer().bet(bigBlind);
  }

  private void resetAlreadyBetted(){
    Seat current = dealer;
    for(int i = 0; i < amountOfPlayers; i++){
      current.getPlayer().resetAlreadyBetted();
      current = current.getNext();
    }
  }

  private void printCommunityCards(){
    for(int i = 0; i < 5; i++){
      if(tableCards[i] != null){
        System.out.println(tableCards[i].toString());
      }
    }
    System.out.println();
  }

}
