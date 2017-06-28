package Main.Game;

class Round {

  private final Seat dealer;
  private final Deck deck;
  private final Card[] tableCards;
  private double pot;

  Round(Seat dealer) {
    this.dealer = dealer;
    this.deck = new Deck();
    this.tableCards = new Card[5];
    pot = 0;
  }

  void play(double minimumBet, double maximumBet, double smallBlind, double bigBlind){
    //Rule 1b, 2a
    Seat current = dealer.getNext();
    setBlinds(current, smallBlind, bigBlind);
    //5
    for(int i = 0; i < 1; i++){
      while(current != dealer){
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
