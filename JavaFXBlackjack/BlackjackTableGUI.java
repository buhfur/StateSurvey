import javafx.application.Application;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.event.ActionEvent;
import javafx.scene.text.*;
import javafx.event.EventHandler;


import java.util.*;



public class BlackjackTableGUI extends Application{
  public static Button hitButton = new Button("hit");
  public static Player dealer = new Player();
  public static Player user = new Player();
  public static Button standButton = new Button("stand");
  public static Button startButton = new Button("Start");
  public static StackPane userPane = new StackPane();
  public static StackPane dealerPane = new StackPane();
  public static VBox scores = new VBox();
  public static VBox paneBox = new VBox(200);
  public static HBox buttons = new HBox(20);
  public static HBox dealerLabelBox = new HBox(100);
  public static HBox playerLabelBox = new HBox(100);
  public static HBox playerCardBox = new HBox();
  public static HBox dealerCardBox = new HBox();
  public static boolean isPlayersTurn = false; 
  public static Label PlayerScore = new Label("Player Score: ");

  public static Label DealerScore = new Label("Dealer Score: ");
  public static Label result = new Label("Test"); //shows who won the game 
  public static Label DealerHand = new Label("Dealer Hand");
  public static Label DealerValue = new Label("Dealer Value: ");
  public static Label PlayerHand = new Label("Player Hand");
  public static Label PlayerValue = new Label("Player Value: ");
   @Override public void start(Stage stage){
      StackPane root = new StackPane();

     

      Canvas canvas = new Canvas(800,800);

    
//      userPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
 //     dealerPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

      scores.setStyle("-fx-background-color : green");
      canvas.setStyle("-fx-background-color : green");

      Label PlayerScore = new Label("Player Score: ");
      Label DealerScore = new Label("Dealer Score: ");
      Label result = new Label("Test"); //shows who won the game 
      Label DealerHand = new Label("Dealer Hand");
      Label DealerValue = new Label("Dealer Value: ");
      Label PlayerHand = new Label("Player Hand");
      Label PlayerValue = new Label("Player Value: ");

      dealerLabelBox.getChildren().addAll(DealerHand, DealerValue);
      playerLabelBox.getChildren().addAll(PlayerHand, PlayerValue);
      paneBox.getChildren().addAll(dealerPane,userPane);

      startButton.setDisable(false);
      hitButton.setDisable(true);
      standButton.setDisable(true);
    
      dealerPane.setAlignment(dealerLabelBox, Pos.TOP_CENTER);
      dealerPane.setAlignment(dealerCardBox, Pos.CENTER);
      userPane.setAlignment(playerLabelBox, Pos.TOP_CENTER);
      userPane.setAlignment(playerCardBox, Pos.CENTER);
      dealerPane.getChildren().addAll(dealerLabelBox, dealerCardBox);
      userPane.getChildren().addAll(playerLabelBox, playerCardBox);
      buttons.getChildren().addAll(startButton,hitButton,standButton);
      scores.getChildren().addAll(PlayerScore,DealerScore,result);

      PlayerScore.setTextFill(Color.WHITE);
      PlayerValue.setTextFill(Color.WHITE);
      DealerScore.setTextFill(Color.WHITE);
      DealerValue.setTextFill(Color.WHITE);
      DealerHand.setTextFill(Color.WHITE);
      PlayerHand.setTextFill(Color.WHITE);

      Font font = Font.font("Arial", FontWeight.BOLD, 14);

      PlayerScore.setFont(font); 
      PlayerValue.setFont(font); 
      DealerScore.setFont(font); 
      DealerValue.setFont(font); 
      DealerHand.setFont(font);
      PlayerHand.setFont(font); 

      playerLabelBox.setAlignment(Pos.TOP_CENTER);
      dealerLabelBox.setAlignment(Pos.TOP_CENTER);
      playerCardBox.setAlignment(Pos.BOTTOM_CENTER);
      dealerCardBox.setAlignment(Pos.BOTTOM_CENTER);

      scores.setAlignment(Pos.BOTTOM_LEFT);
      buttons.setAlignment(Pos.BOTTOM_CENTER);
      result.setAlignment(Pos.BOTTOM_RIGHT);
      paneBox.setAlignment(Pos.CENTER);

      root.getChildren().add(canvas);
      root.getChildren().add(result);
      root.getChildren().add(scores); 
      root.getChildren().add(paneBox);
      root.getChildren().add(buttons);
      root.setStyle("-fx-background-color: green");
      Scene scene = new Scene(root, 800,800);
      stage.setScene(scene);
      

      /*****************************
          Game 
      
       */

      
      startButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
          startGame(); 

          updateHand(dealer, dealerCardBox, DealerValue);
          updateHand(user, playerCardBox, PlayerValue);
        }
    });
    standButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
          dealer.stand(0); //zero when the player stands  
          
          dealer.hit();
          System.out.println("Dealer hit");
          dealer.bust();
          updateHand(dealer, dealerCardBox, DealerValue);
          

          endGame();
         
        }
    });

      hitButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
          if(user.valueOfHand() == 21 && dealer.valueOfHand() == 21){
            Alert push = new Alert(AlertType.INFORMATION);
            push.setContentText("Push! its a draw");
            push.show();
          } 
          if( dealer.stand(user.valueOfHand())){
              standButton.setDisable(true);
              user.hit();
              updateHand(user, playerCardBox, PlayerValue);
              if(user.valueOfHand() == 21){
                endGame();
              }
              user.bust();
          }else{
            dealer.hit();
            user.hit();
            user.bust();
            dealer.bust();
          }

          endGame();
        }
    });

      stage.show();

   }

   public void startGame(){
      /*Reset the game Deck 
      - Clear the player and dealer hands and hand values 
      - Give the dealer one Card 
      - Disable Start button, enable Hit and Stand Buttons 
      - Clear previous game result 
      */

    startButton.setDisable(true);
    hitButton.setDisable(false);
    standButton.setDisable(false);
    dealer.clearHand();
    user.clearHand();
    user.hit();
    user.hit();
    user.bust();
    dealer.bust();
    dealer.hit();
    dealer.hit();

    user.bust();
    dealer.bust();
   }
   public void updateHand(Player p, HBox box, Label handValue){
    handValue.setText("Value : " + Integer.toString(p.valueOfHand())); // update the label 
    box.getChildren().clear();
    for (Card cardInHand: p.getHand()){
      //add the Card to the box 
      box.setMargin(cardInHand, new Insets(20,20,20,20));
      box.getChildren().add(cardInHand); // add the card imageview to the HBox 
      
    } 
   }
   public static void endGame(){
      //determine the winner if there is any 

      Alert gameOver = new Alert(AlertType.INFORMATION);
      if(user.bust() == true && dealer.bust() != true){
        System.out.println("Dealer wins!");
        gameOver.setContentText(String.format("Dealer wins! Value of user hand : %d\nValue of dealer hand: %d", user.valueOfHand(),dealer.valueOfHand()));
        gameOver.show(); 
        dealer.clearHand();
        user.clearHand();
        hitButton.setDisable(true);
        standButton.setDisable(true);
        startButton.setDisable(false);
        
      }else if(user.bust() != true && dealer.bust() == true){
        System.out.printf("Player wins! Value of user hand : %d\nValue of dealer hand: %d", user.valueOfHand(),dealer.valueOfHand());
        gameOver.setContentText(String.format("Value of user hand : %d\nValue of dealer hand: %d", user.valueOfHand(),dealer.valueOfHand()));
        
        gameOver.show();
        dealer.clearHand();
        user.clearHand();
        hitButton.setDisable(true);
        standButton.setDisable(true);
        startButton.setDisable(false);

      }
   }

}

class Player{
  ArrayList<Card> hand = new ArrayList<Card>();
  static Deck deck = new Deck(); // resets the game deck once initialized 

  public Player(){
      //dealt cards twice

  }

  public ArrayList<Card> getHand(){
    return hand;
  }
  public int valueOfHand(){
    int handValue = 0;

    for(Card cardInHand : hand){
      handValue += cardInHand.valueOf();  // return an integer of the value of a specific instance of the "card" class
      //System.out.println(cardInHand.valueOf());
    }
    return handValue;
  }

  public void clearHand(){
    hand.clear();
  }
// for non user players
  public boolean stand(int otherPlayerValue){
      boolean willStand = false;
      Random random = new Random();

      if(otherPlayerValue > valueOfHand()){
        willStand = true;
      }else if(valueOfHand() > 16){
        //generate number between 1 and zero 
        
        willStand = (random.nextInt(2) > 0) ? true : false;

      }

      return willStand;
  }
  public void hit(){

    if(hand.size() <= 12){
      hand.add(deck.dealCard());
    }
  }
  public boolean bust(){
    boolean isBust = false;
    if(valueOfHand() > 21){
      return true; //true is a bust? or false?
    }else if ( valueOfHand() == 21){
      BlackjackTableGUI.endGame();
      return true;
      
    }
    return isBust;
  }
}


class Card extends ImageView{
    public static String[] faces = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    public static int HEIGHT = 130; //height of the image on the card 
    String face;
    public Image cardImage;

    public Card(String cardFace){
        //set image for the card 
        face = cardFace; 
        cardImage = new Image(String.format("%s.png",cardFace));
        //System.out.printf("added image to card: %s.png \n", cardFace);
        setImage(cardImage);
        setFitHeight(HEIGHT);
        setPreserveRatio(true);
    }

    public String getFace(){
        return face;
    }
    
    public int valueOf(){
        
      
      if(face == "2"){
        return 2;
      }else if(face == "3"){
        return 3;
      }else if(face == "4"){
        return 4;
      }else if(face == "5"){

        return 5;
      }else if(face == "6"){

        return 6;
      }else if(face == "7"){

        return 7;
      }else if(face == "8"){

        return 8;
      }else if(face == "9"){

        return 9;
      }else if(face == "10"){

        return 10;
      }else if(face == "A"){

        return 11;
      }else if(face == "J"){

        return 10;
      }else if(face == "K"){

        return 10;
      }else if(face == "Q"){

        return 10;
      }

        return 0; 
    }


}

class Deck{
    public ArrayList<Card> cards = new ArrayList<Card>();
    public Deck(){
//        reset(); 
        Random random = new Random();
        for(int i = 0; i <= 52; i++){

          int randNum = random.nextInt(13);
          Card newCard = new Card(Card.faces[randNum]); 
          cards.add(newCard);
        }
    } 

    public void reset(){
        cards.clear();
    }

    public Card dealCard(){
      //generate the first card on the top of the deck and delete that element  
      Card dealtCard = cards.get(0);
      cards.remove(0);
      return dealtCard;
    }
}