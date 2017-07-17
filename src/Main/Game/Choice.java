package Main.Game;

public class Choice {

  private final ChoiceEnum choice;
  private final double raiseAmount;

  public Choice(ChoiceEnum choice, double raiseAmount) {
    this.choice = choice;
    this.raiseAmount = raiseAmount;
  }

  public ChoiceEnum getChoice() {
    return choice;
  }

  public double getRaiseAmount() {
    return raiseAmount;
  }
}
