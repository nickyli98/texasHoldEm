package Main.Game;

public class Seat {

  private Seat next;
  private final Player player;

  Seat(Player player) {
    this.player = player;
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

  double play(double currentBet, double minimumBet, double maximumBet){
    if(player.inGame()){
      return player.playersTurn(currentBet, minimumBet, maximumBet);
    } else {
      return 0;
    }
  }

}
