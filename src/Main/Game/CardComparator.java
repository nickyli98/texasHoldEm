package Main.Game;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {

  @Override
  public int compare(Card c1, Card c2) {
    int v1 = c1.getValue();
    int v2 = c2.getValue();
    if(v1 == v2){
      return 0;
    }
    if(v1 == 1){
      return 1;
    }
    if(v2 == 1){
      return -1;
    }
    //Neither cards are Ace
    return Integer.compare(v1, v2);
  }
}
