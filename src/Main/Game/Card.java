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

  @Override
  public String toString(){
    if(suite == Suit.EMPTY){
      return "";
    } else {
      String val;
      switch(value){
        case 13:
          val = "King";
          break;
        case 12:
          val = "Queen";
          break;
        case 11:
          val = "Jack";
          break;
        default:
          val = String.valueOf(value);
          break;
      }
      return val + ", " + suite.toString();
    }
  }
}
