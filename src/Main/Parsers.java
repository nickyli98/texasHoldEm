package Main;

public class Parsers {

  public static Integer tryParseInteger(String s) {
    try {
      return Integer.parseInt(s);
    } catch (NumberFormatException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Not a number");
    }
  }

  public static Double tryParseDouble(String s) {
    try {
      return Double.parseDouble(s);
    } catch (NumberFormatException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Not a number");
    }
  }

}
