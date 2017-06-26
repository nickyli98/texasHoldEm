package Main;

public class Utils {

  public static Integer tryParseInteger(String s) {
    try {
      return Integer.parseInt(s);
    } catch (NumberFormatException e) {
      e.printStackTrace();
      System.out.println("Integer parse error");
      return null;
    }
  }

  public static Double tryParseDouble(String s) {
    try {
      return Double.parseDouble(s);
    } catch (NumberFormatException e) {
      e.printStackTrace();
      System.out.println("Double parse error");
      return null;
    }
  }

}
