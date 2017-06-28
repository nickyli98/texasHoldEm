package Main.Game;

public class Seat {

  private Seat next;
  private final Player player;
  private Card[] cards;

  Seat(Player player) {
    this.player = player;
    cards = new Card[2];
  }

  Seat getNext() {
    return next;
  }

  void setNext(Seat next) {
    this.next = next;
  }

  Player getPlayer() {
    return player;
  }

  double play(){
    return 1.0;
  }

  void deal(Card c, int pos){
    cards[pos] = c;
  }

  Card getCard(int pos){
    return cards[pos];
  }

  double bet(){
    return 1;
  }

  //For blinds, forces a player to bet
  double bet(double amount){
    return player.bet(amount);
  }
}
