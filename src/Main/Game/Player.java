package Main.Game;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Player {

  private final String name;
  private double money;

  Player(String name, double money) {
    this.name = name;
    this.money = money;
  }

  void playersTurn(){
    //TODO deal with big/small, remember to reset
  }

  double bet(){
    return 1;
  }

  double bet(double amount) {
    if(amount < money){
      money -= amount;
      return amount;
    } else {
      //TODO Fix
      throw new IllegalStateException("Ran out of money");
    }
  }
}
