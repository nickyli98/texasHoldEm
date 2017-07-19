package Main.Game;

public class Seat {

  private Seat next;
  private final Player player;

  public Seat(Player player) {
    this.player = player;
  }

  public Seat getNext() {
    return next;
  }

  public void setNext(Seat next) {
    this.next = next;
  }

  public Player getPlayer() {
    return player;
  }

  public double play(double currentBet, double minimumBet, double maximumBet){
    if(player.inGame()){
      return player.playersTurn(currentBet, minimumBet, maximumBet);
    } else {
      return 0;
    }
  }

}
