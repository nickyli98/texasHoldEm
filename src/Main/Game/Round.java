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
    Seat current = dealer.getNext();
    setBlinds(current, smallBlind, bigBlind);
    //5
    for(int i = 0; i < 2; i++){
      //TODO: Check if circular array working as intended
      for(int j = 0; j < amountOfPlayers; j++){
        current.deal(deck.deal(), i);
        current = current.getNext();
      }
    }
  }

  //2a, 4a, 4b
  private void setBlinds(Seat current, double smallBlind, double bigBlind){
    pot += current.bet(smallBlind);
    pot += current.getNext().bet(bigBlind);
  }

}
