package Main;

import Main.Game.Choice;
import Main.Game.ChoiceEnum;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Parsers {

  public static Integer tryParseInteger(String s) {
    return Integer.parseInt(s);
  }

  public static Double tryParseDouble(String s) {
    return Double.parseDouble(s);
  }

  public static Choice getPlayerChoice() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    ChoiceEnum choice = ChoiceEnum.INVALID;
    String[] splitted = {};
    while(choice == ChoiceEnum.INVALID){
      System.out.println("Please choose one of the following");
      System.out.println("Fold, Call, Check, Raise ___");
      String s = br.readLine();
      splitted = s.split("\\s+");
      choice = getChoice(splitted);
    }
    if(choice == ChoiceEnum.RAISE){
      return new Choice(choice, tryParseDouble(splitted[1]));
    }
    return new Choice(choice, 0.0);
  }

  private static ChoiceEnum getChoice(String[] choiceArgs){
    if(choiceArgs.length == 1){
      final List<String> validChoices = new ArrayList<>(
          Collections.singletonList("fold, call, check"));
      switch(choiceArgs[0].toLowerCase()){
        case "fold":
          return ChoiceEnum.FOLD;
        case "call":
          return ChoiceEnum.CALL;
        case "check":
          return ChoiceEnum.CHECK;
        default:
          return ChoiceEnum.INVALID;
      }
    }
    if(choiceArgs.length == 2 && choiceArgs[0].toLowerCase().equals("raise")){
      return ChoiceEnum.RAISE;
    }
    return ChoiceEnum.INVALID;
  }
}
