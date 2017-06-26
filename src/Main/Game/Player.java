package Main.Game;

import Main.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Player {

  private final String name;
  private Double money;
  private Card c1;
  private Card c2;


  public Player(String name) {
    this.name = name;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try{
      String s = reader.readLine();
      System.out.println("Please enter your starting amount");
      money = Utils.tryParseDouble(s);
      if(money == null){
        throw new IllegalArgumentException("Please enter a valid amount of money");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void playersTurn(){
    //TODO deal with big/small, remember to reset
  }

  public Card getC1() {
    return c1;
  }

  public void setC1(Card c1) {
    this.c1 = c1;
  }

  public Card getC2() {
    return c2;
  }

  public void setC2(Card c2) {
    this.c2 = c2;
  }


}
