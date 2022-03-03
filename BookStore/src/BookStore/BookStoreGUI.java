package BookStore;
import java.util.*;
import javafx.application.Application;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.stage.FileChooser;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.event.ActionEvent;
import javafx.scene.text.*;
import javafx.event.EventHandler;
//import javafx.collections.FXCollections;
import javafx.collections.*;
import java.io.*;

import BookStore.Book;

public class BookStoreGUI extends Application{

    @Override public void start(Stage stage){
        ArrayList<Book> books = new ArrayList<Book>();

        BorderPane root = new BorderPane();
        Label welcomeLabel = new Label("Welcome to the PFW online Book Store!");
        stage.setTitle("Book Store Shopping cart"); 
        //sets the default directory to the current working directory for the file chooser
        FileChooser fileChooser = new FileChooser();
        File projectDirectory = new File(System.getProperty("user.dir")); 
        fileChooser.setInitialDirectory(projectDirectory);

        //any changes that occur in the observable list will be seen by the listview
        ListView<String> bookListView = new ListView<String>();
        ListView<String> shoppingCartView = new ListView<String>();
        
        Label availableBooksLabel = new Label("Available Books");
        Label shoppingCartLabel= new Label("Shopping Cart");

        VBox layout = new VBox(10);
        VBox layout2 = new VBox(10);
        HBox listViewBox = new HBox(10);

        //new Insets(Top, right, bottom ,left)
        layout.setPadding(new Insets(50,40,20,20));
        layout.getChildren().addAll(welcomeLabel,availableBooksLabel,bookListView);

        layout2.setPadding(new Insets(50,40,20,20));
        layout2.getChildren().addAll(shoppingCartLabel,shoppingCartView);
        listViewBox.getChildren().addAll(layout,layout2);
        MenuBar menuBar = new MenuBar(); 
        Menu menuFile = new Menu("File");
        Menu menuShopping = new Menu("Shopping");
        MenuItem exit = new MenuItem("Exit");
        MenuItem loadBooks = new MenuItem("Load Books");
        MenuItem addSelectedBook = new MenuItem("Add Selected Book");
        MenuItem removeSelectedBook = new MenuItem("Remove Selected Book");
        MenuItem clearCart = new MenuItem("Clear Cart");
        MenuItem checkOut = new MenuItem("Check out");



        exit.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                System.exit(0);
            }
        });

        loadBooks.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
           
                try{
                    File selectedBookFile = fileChooser.showOpenDialog(stage);
                    //read the file here and pass in some data to a list view
                    BufferedReader br = new BufferedReader(new FileReader(selectedBookFile));
                    String data;
                
                    while((data = br.readLine()) != null){
                        //create book objects and add them to the arrayList 
                        String title = data.split(",")[0];
                        double price = Double.parseDouble(data.split(",")[1]);
                        books.add(new Book(title, price));
                        bookListView.getItems().addAll(title);
                    }
                }catch(IOException err){
                    System.out.println(e);
                }
                
            }

        }); 

        addSelectedBook.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                shoppingCartView.getItems().add(bookListView.getSelectionModel().getSelectedItem().toString());
            }
        });

        removeSelectedBook.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
               shoppingCartView.getItems().remove(bookListView.getSelectionModel().getSelectedItem()); 
            }
        });

        clearCart.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                //clear the shoppingCartView
                shoppingCartView.getItems().clear();
            }
        });

        checkOut.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                //calculate the subtotal with sales tax 7%


                VBox costBox = new VBox(10);
                //calculate subtotal 
                double subTotal = 0;
                double salesTax = 0;
                double total = 0;
                for(Book book : books){
                   subTotal += book.price;
                }
                salesTax = subTotal * 0.07;
                total = salesTax + subTotal;

                Label subTotalLabel = new Label(String.format("Subtotal : %.2f",subTotal));
                Label salesTaxLabel = new Label(String.format("Sales Tax : %.2f", salesTax));
                Label totalLabel = new Label(String.format("Total : %.2f",total));

                Button okButton = new Button("OK");
              
                costBox.getChildren().addAll(subTotalLabel,salesTaxLabel,totalLabel,okButton);
                Scene checkoutScene = new Scene(costBox,100,100);
                Stage checkoutStage = new Stage();
                okButton.setOnAction(new EventHandler<ActionEvent>(){
                    @Override public void handle(ActionEvent e){
                        checkoutStage.close();
                    }
                });
                checkoutStage.setScene(checkoutScene);
                checkoutStage.show();
            }
        });

        menuFile.getItems().addAll(exit, loadBooks);
        menuShopping.getItems().addAll(addSelectedBook,removeSelectedBook,clearCart,checkOut);
        menuBar.getMenus().addAll(menuFile,menuShopping);

        root.setTop(menuBar);
        root.setCenter(listViewBox);

        Scene scene = new Scene(root,500,500);
        stage.setScene(scene);
        stage.show();
    }
}
