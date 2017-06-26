package Main.Game;

public class Seat {

  private Seat next;
  private final Player player;
  private boolean small;
  private boolean big;

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

  public void setSmall(boolean small) {
    this.small = small;
  }

  public void setBig(boolean big) {
    this.big = big;
  }
}
