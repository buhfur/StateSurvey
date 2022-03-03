import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.beans.value.*;
import javafx.scene.control.*; 
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text; 
import javafx.scene.control.TextField; 
import javafx.stage.Stage;  
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.*;
// color selecting gui - Ryan McVicker 9/24/2021
        
   
public class ColorSelectionGUI extends Application {

   
    @Override public void start(Stage stage){
        BorderPane root = new BorderPane();
        GridPane centerPane = new GridPane(); // grid pane holds the checkbox and radio buttons
        Scene mainScene = new Scene(root);
        root.setMinSize(400,400);


        Label north = new Label("North");
       
        Label south = new Label("South");
        Label east = new Label("East");
        Label west = new Label("West");
        Label locations = new Label("Locations");
        Label bColors = new Label("Backround Colors");

    
        north.setStyle("-fx-background-color: green;");
        south.setStyle("-fx-background-color: green");
        east.setStyle("-fx-background-color: green");
        west.setStyle("-fx-background-color: green");
        
        CheckBox nBox = new CheckBox("North");
        CheckBox sBox = new CheckBox("South");
        CheckBox eBox = new CheckBox("East");
        CheckBox wBox = new CheckBox("West");


        ToggleGroup group = new ToggleGroup();
        RadioButton salmon = new RadioButton("salmon");
        salmon.setToggleGroup(group);
        salmon.setStyle("-fx-text-fill: salmon");
        RadioButton springGreen = new RadioButton("green");
        springGreen.setStyle("-fx-text-fill: green");
        springGreen.setUserData("green");
        springGreen.setToggleGroup(group);
        RadioButton orange = new RadioButton("orange");
        orange.setUserData("orange");

        orange.setStyle("-fx-text-fill: orange");
        orange.setToggleGroup(group);
        RadioButton cyan = new RadioButton("cyan");
        cyan.setUserData("cyan");
        cyan.setStyle("-fx-text-fill: cyan");
        cyan.setToggleGroup(group);
        VBox rButtons = new VBox(4);
        rButtons.getChildren().addAll(bColors,salmon,springGreen,orange,cyan);

        VBox lButtons = new VBox(4); // vertical gap = 4 
        lButtons.getChildren().addAll(locations,nBox,sBox,eBox,wBox);

        HBox centerBox = new HBox(15); // horizontal gap = 15
        centerBox.getChildren().addAll(lButtons, rButtons);

     
        //set listeners for radio buttons 
        root.setTop(north);
        root.setBottom(south);
        root.setRight(east);
        root.setLeft(west);
        root.setCenter(centerBox);
        stage.setScene(mainScene);
        stage.show();
       //must do this for all objects 
        north.setAlignment(Pos.CENTER);
        south.setAlignment(Pos.CENTER);
        east.setAlignment(Pos.CENTER);
        west.setAlignment(Pos.CENTER);
        centerBox.setAlignment(Pos.CENTER);
       
  
     

        north.setMinHeight(40);
        north.setMinWidth(stage.getWidth());
        
        south.setMinHeight(40);
        south.setMinWidth(stage.getWidth());

        west.setMinHeight(stage.getHeight());
        west.setMinWidth(40);

        east.setMinHeight(stage.getHeight());
        east.setMinWidth(40);

        ArrayList<CheckBox> checkBoxList = new ArrayList<CheckBox>();
        ArrayList<Label> labelList = new ArrayList<Label>();

        labelList.add(north);
        labelList.add(south);
        labelList.add(east);
        labelList.add(west);

        checkBoxList.add(nBox);
        checkBoxList.add(sBox);
        checkBoxList.add(wBox);
        checkBoxList.add(eBox);


        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle o, Toggle n){

                RadioButton colorRadio = (RadioButton) group.getSelectedToggle();
                if(colorRadio != null){
                    System.out.println("this section works");
                    // read the checkboxes text 
                    for(CheckBox box : checkBoxList){

                        //check if the check box is selected
                        if (box.isSelected()){

                            String checkBoxText = box.getText();

                            for(Label banLabel : labelList){
                                System.out.println(String.format("checkBoxText: %s \n banLabel.getText() : %s",checkBoxText,banLabel.getText()));
                                if( checkBoxText == banLabel.getText()){
                                    banLabel.setStyle(String.format("-fx-background-color : %s", colorRadio.getText()));
                                }
                            }
                        }
                    }
                }
            }
    });
    }

    
 
}
