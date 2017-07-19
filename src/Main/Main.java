package Main;

import static Main.NameGetter.getPlayerNames;
import static Main.Parsers.tryParseDouble;
import static Main.Parsers.tryParseInteger;

import Main.Game.Game;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    if(args.length == 3){
      double minimumBet = tryParseDouble(args[1]);
      double maximumBet = tryParseDouble(args[2]);
      if(minimumBet < maximumBet && minimumBet > 0){
        if(args[0].equals("simulate")){
          //TODO
          System.out.println("TODO");
        } else {
          int playerAmount = tryParseInteger(args[0]);
          if (playerAmount > 10 || playerAmount < 2){
            throw new IllegalArgumentException("Please enter a number between 2 and 10");
          } else {
            String[] playerNames = getPlayerNames(playerAmount);
            Game current = new Game(minimumBet, maximumBet, minimumBet / 2, minimumBet, playerNames);
            current.run();
          }
        }
      }
    } else {
      System.out.println("Please enter one of the following while calling the program");
      System.out.println("1. Amount of players, Minimum bet, Maximum bet");
      System.out.println("2. Simulate");
      throw new IllegalArgumentException();
    }
  }

  //TODO move this to somewhere else


}
