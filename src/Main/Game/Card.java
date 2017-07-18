package Main.Game;

public class Card {

  private final int value;
  private final Suit suite;

  Card(int value, Suit suite) {
    this.value = value;
    this.suite = suite;
  }

  int getValue() {
    return value;
  }

  Suit getSuite() {
    return suite;
  }

  @Override
  public String toString(){
    if(suite == Suit.EMPTY){
      return "";
    } else {
      return value + ", " + suite.toString();
    }
  }
}
