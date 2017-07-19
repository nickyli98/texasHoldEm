package GameTester;

import static Main.NameGetter.isValidName;
import static Main.NameGetter.nameFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Main.Game.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MethodTests {

  @Test
  public void CardComparatorTest(){
    CardComparator tester = new CardComparator();
    Card a = new Card(2, Suit.DIAMONDS);
    Card b = new Card(1, Suit.SPADES);
    Card c = new Card(13, Suit.DIAMONDS);
    Card d = new Card(1, Suit.HEARTS);
    Card e = new Card(6, Suit.CLUBS);
    Card f = new Card(2, Suit.HEARTS);

    assertTrue(tester.compare(b, d) == 0, "Same cards should be equal");
    assertTrue(tester.compare(b, a) > 0, "Ace is bigger than all else");
    assertTrue(tester.compare(a, c) < 0, "2 is smaller than King");
    assertTrue(tester.compare(e, f) > 0, "6 is greater than 2");
    assertTrue(tester.compare(f, a) == 0, "Same cards are equal");
    assertTrue(tester.compare(b, c) > 0, "Ace is bigger than all else");
    assertTrue(tester.compare(c, e) > 0, "King is greater than 6");
  }

  @Test
  public void SeatPlayerTest(){
    Seat initial = new Seat(new Player("Test0", 0));
    Seat current = initial;
    for(int i = 1; i < 10; i++){
      current.setNext(new Seat(new Player("Test" + i, i * 100)));
      current = current.getNext();
    }
    current.setNext(initial);

    for(int i = 0; i < 100; i++){
      assertNotNull(current.getNext(), "Circular array should iterate infinitely");
      current = current.getNext();
    }

    current = initial;

    for(int i = 0; i <10; i++){
      System.out.println(i + " - " + current.getPlayer().getMoney());
      assertEquals(Double.compare(current.getPlayer().getMoney(), i * 100), 0);
      current = current.getNext();
    }
  }

  @Test
  public void DeckTest(){
    Deck tester = new Deck();

    List<Card> diamonds = new ArrayList<>();
    List<Card> spades = new ArrayList<>();
    List<Card> clubs = new ArrayList<>();
    List<Card> hearts = new ArrayList<>();

    for(int i = 0; i < 52; i++){
      Card c = tester.deal();
      assertTrue(c.getSuite() != Suit.EMPTY);
      switch(c.getSuite()){
        case DIAMONDS:
          diamonds.add(c);
          break;
        case SPADES:
          spades.add(c);
          break;
        case HEARTS:
          hearts.add(c);
          break;
        case CLUBS:
          clubs.add(c);
          break;
      }
    }

    assertEquals(diamonds.size(), 13, "All suits must have 13 cards");
    assertEquals(spades.size(), 13, "All suits must have 13 cards");
    assertEquals(clubs.size(), 13, "All suits must have 13 cards");
    assertEquals(hearts.size(), 13, "All suits must have 13 cards");

    List<Integer> oneToThirteen = new ArrayList<>();

    for(int i = 1; i < 14; i++){
      oneToThirteen.add(i);
    }
    for(Card c : diamonds){
      assertTrue(oneToThirteen.remove((Integer) c.getValue()), "Deck should contain one of each");
    }
    assertEquals(oneToThirteen.size(), 0);

    for(int i = 1; i < 14; i++){
      oneToThirteen.add(i);
    }
    for(Card c : hearts){
      assertTrue(oneToThirteen.remove((Integer) c.getValue()), "Deck should contain one of each");
    }
    assertEquals(oneToThirteen.size(), 0);

    for(int i = 1; i < 14; i++){
      oneToThirteen.add(i);
    }
    for(Card c : clubs){
      assertTrue(oneToThirteen.remove((Integer) c.getValue()), "Deck should contain one of each");
    }
    assertEquals(oneToThirteen.size(), 0);

    for(int i = 1; i < 14; i++){
      oneToThirteen.add(i);
    }
    for(Card c : spades){
      assertTrue(oneToThirteen.remove((Integer) c.getValue()), "Deck should contain one of each");
    }
    assertEquals(oneToThirteen.size(), 0);
  }

  @Test
  public void NameGetterTest(){
    String mike = "Mike";
    String mike1 = "Mike ";
    String mike2 = "mIkE";
    String inv = "Tes-t";
    String fullName = "Test Test";
    String fullName1 = "Jack Smith";
    String fullName2 = "Jack Smith ";
    String fullName3 = "Jack-Smith";

    assertEquals(mike, nameFormatter(mike));
    assertEquals(mike, nameFormatter(mike1));
    assertEquals(mike2, nameFormatter(mike2));
    assertEquals(fullName, nameFormatter(fullName));
    assertEquals(fullName1, nameFormatter(fullName1));
    assertEquals(fullName1, nameFormatter(fullName2));

    assertFalse(isValidName(inv));
    assertFalse(isValidName(fullName3));

    assertTrue(isValidName(mike));
    assertTrue(isValidName(mike1));
    assertTrue(isValidName(mike2));
    assertTrue(isValidName(fullName1));
    assertTrue(isValidName(fullName2));
  }

}
