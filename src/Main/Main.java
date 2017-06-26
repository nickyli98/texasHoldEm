package Main;

import static Main.Utils.tryParseInteger;

import Main.Game.Game;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    if(args.length == 1){
      if(args[0].equals("simulate")){

      } else {
        Integer playerAmount = tryParseInteger(args[0]);
        if (playerAmount == null || playerAmount > 10 || playerAmount < 2){
          throw new IllegalArgumentException("Please enter a number between 2 and 10");
        } else {
          Game current = new Game(playerAmount);
          current.run();
        }
      }
    }
  }


}
