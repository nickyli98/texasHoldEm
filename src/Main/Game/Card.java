package Main.Game;

public class Card {

  private final int value;
  private final Suit suite;

  public Card(int value, Suit suite) {
    this.value = value;
    this.suite = suite;
  }

  public int getValue() {
    return value;
  }

  public Suit getSuite() {
    return suite;
  }
}
