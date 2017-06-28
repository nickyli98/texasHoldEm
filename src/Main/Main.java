package Main;

import static Main.Parsers.tryParseDouble;
import static Main.Parsers.tryParseInteger;

import Main.Game.Game;
import java.io.IOException;

public class Main {

  //Argument: Type, Minimum Bet, Maximum Bet, Small Blind, Big Blind
  public static void main(String[] args) throws IOException {
    if(args.length == 5){
      double minimumBet = tryParseDouble(args[1]);
      double maximumBet = tryParseDouble(args[2]);
      double smallBlind = tryParseDouble(args[3]);
      double bigBlind = tryParseDouble(args[4]);
      if(checkArguments(minimumBet, maximumBet, smallBlind, bigBlind)){
        if(args[0].equals("simulate")){

        } else {
          int playerAmount = tryParseInteger(args[0]);
          if (playerAmount > 10 || playerAmount < 2){
            throw new IllegalArgumentException("Please enter a number between 2 and 10");
          } else {
            Game current = new Game(playerAmount, minimumBet, maximumBet, smallBlind, bigBlind);
            current.run();
          }
        }
      }
    } else {
      System.out.println("Please enter the following while calling the program");
      System.out.println("Arguments: Type, Minimum Bet, Maximum Bet, Small Blind, Big Blind");
      throw new IllegalArgumentException();
    }
  }

  //Cond1: Maximum bet > minimum bet
  //Cond2: Small blind < Big blind
  //Cond3: Small, big blind both within limits
  private static boolean checkArguments(double minimumBet, double maximumBet, double smallBlind,
      double bigBlind) {
    boolean cond1 = minimumBet < maximumBet;
    boolean cond2 = smallBlind < bigBlind;
    boolean cond3 = smallBlind > minimumBet && bigBlind < maximumBet;
    return cond1 && cond2 && cond3;
  }

}
