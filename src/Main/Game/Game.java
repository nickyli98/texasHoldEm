package Main.Game;

public class Game {

  private final double minimumBet;
  private final double maximumBet;
  private final double smallBlind;
  private final double bigBlind;
  private final int amountOfPlayers;
  private Seat dealer;

  private Game(double minimumBet, double maximumBet, double smallBlind,
      double bigBlind, int amountOfPlayers){
    this.minimumBet = minimumBet;
    this.maximumBet = maximumBet;
    this.smallBlind = smallBlind;
    this.bigBlind = bigBlind;
    this.amountOfPlayers = amountOfPlayers;
  }

  public Game(double minimumBet, double maximumBet, double smallBlind,
      double bigBlind, String playerNames[]){
    this(minimumBet, maximumBet, smallBlind, bigBlind, playerNames.length);
    Seat first = null;
    for(String name : playerNames){
       if(dealer == null){
         dealer = new Seat(new Player(name, smallBlind * 100));
         first = dealer;
       } else {
         dealer.setNext(new Seat(new Player(name, smallBlind * 100)));
         dealer = dealer.getNext();
       }
    }
    dealer.setNext(first);
    dealer = dealer.getNext();
  }
/* for simulate
  public Game(double minimumBet, double maximumBet, double smallBlind,
      double bigBlind){

  }*/

  public void run(){
    boolean again;
    //3a
    findDealer();
    /*do{

    } while(again);*/
    Round current = new Round(dealer, amountOfPlayers);
    //Rule 1a
    current.play(minimumBet, maximumBet, smallBlind, bigBlind);
    again = playAgain();
    dealer = dealer.getNext();
  }

  private void findDealer(){
    Deck cards = new Deck();
    Seat current = dealer;
    //Smallest possible card
    Card currentMax = new Card(2, Suit.DIAMONDS);
    for(int i = 0; i < amountOfPlayers; i++){
      current.getPlayer().deal(cards.deal(), 0);
      CardComparator cc = new CardComparator();
      if(cc.compare(currentMax, current.getPlayer().getCard(0)) < 0){
        dealer = current;
        currentMax = dealer.getPlayer().getCard(0);
      }
      current = current.getNext();
    }
  }

  private boolean playAgain(){
    //TODO
    return true;
  }
}