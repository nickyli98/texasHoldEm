package Main.Game;

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
    setBlinds(dealer.getNext(), smallBlind, bigBlind);
    //5
    dealCards(dealer.getNext());
    //6
    //TODO fix this, big, small blind are forced to bet twice
    playRound(dealer.getNext().getNext().getNext(), bigBlind);
    //8
    deck.deal();
    //9
    for(int i = 0; i < 3; i++){
      tableCards[i] = deck.deal();
    }
    //11
    playRound(dealer.getNext(), 0);
    //13
    deck.deal();
    //14
    tableCards[3] = deck.deal();
    //16
    playRound(dealer.getNext(),0);
    //18
    deck.deal();
    //19
    tableCards[4] = deck.deal();
    //21
    playRound(dealer.getNext(),0);
  }

  private void dealCards(Seat begin){
    for(int i = 0; i < 2; i++){
      //TODO: Check if circular array working as intended
      for(int j = 0; j < amountOfPlayers; j++){
        begin.getPlayer().deal(deck.deal(), i);
        begin = begin.getNext();
      }
    }
  }

  private void playRound(Seat begin, double currentBet){
    for(int player = 0; player < amountOfPlayers; player++){
      pot += begin.getPlayer().playersTurn(currentBet);
      if(begin.getPlayer().raised()){
        currentBet += begin.getPlayer().raisedBy();
      }
    }
  }

  //2a, 4a, 4b
  private void setBlinds(Seat current, double smallBlind, double bigBlind){
    pot += current.getPlayer().bet(smallBlind);
    pot += current.getNext().getPlayer().bet(bigBlind);
  }

}
