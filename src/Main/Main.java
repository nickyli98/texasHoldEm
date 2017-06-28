package Main;

import static Main.Parsers.tryParseDouble;
import static Main.Parsers.tryParseInteger;

import Main.Game.Game;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  //Argument: Type, Minimum Bet, Maximum Bet
  //SmallBlind = BigBlind / 2
  //BigBlind = Minimum Bet
  public static void main(String[] args) throws IOException {
    if(args.length == 5){
      double minimumBet = tryParseDouble(args[1]);
      double maximumBet = tryParseDouble(args[2]);
      if(minimumBet < maximumBet && minimumBet > 0){
        if(args[0].equals("simulate")){
          //TODO
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
      System.out.println("Please enter the following while calling the program");
      System.out.println("Arguments: Type, Minimum Bet, Maximum Bet, Small Blind, Big Blind");
      throw new IllegalArgumentException();
    }
  }

  private static String[] getPlayerNames(int playerAmount) {
    String[] playerNames = new String[playerAmount];
    for(int i = 0; i < playerAmount; i++){
      try{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //TODO: Some sort of name check
        playerNames[i] = reader.readLine();
        reader.close();
      } catch (IOException e) {
        e.printStackTrace();
        throw new IllegalArgumentException("Main getPlayerNames Reader error");
      }
    }
    return playerNames;
  }
}
