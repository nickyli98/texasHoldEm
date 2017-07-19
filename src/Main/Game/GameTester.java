package Main.Game;

public class GameTester {

  public static void printPlayers(Seat dealer, int amountOfPlayers){
    for(int i = 0; i < amountOfPlayers; i++){
      if(dealer.getPlayer().inGame()){
        System.out.println(dealer.getPlayer().toString());
        dealer = dealer.getNext();
        System.out.println();
      }
    }
  }

}
