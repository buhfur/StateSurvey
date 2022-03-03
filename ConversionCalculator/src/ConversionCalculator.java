// conversion calculator using javafx - Ryan McVicker
import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.PasswordField; 
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField; 
import javafx.stage.Stage;  
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ConversionCalculator extends Application {


    //units for conversion 

    public final double INCH_CM = 2.54;
    public final double TO_YARDS= 0.02777778;
    public final double TO_METERS = 0.0254;  
   


 // javafx stages are the windows 
   @Override public void start(Stage stage){

       stage.setTitle("Conversion Calculator");
       GridPane origin = new GridPane();
       origin.setPadding(new Insets(10,10,10,10));
       Scene firstScene = new Scene(origin);
        origin.setHgap(5); // padding 
        origin.setVgap(5);
       origin.setMinSize(500,100);
       stage.setScene(firstScene);

       Text centLabel = new Text("Centimeters: ");
       Text inchesLabel = new Text("Inches: ");
       Text metersLabel = new Text("Meters: ");
       Text yardsLabel = new Text("Yards: ");
    
       //text boxes: putting comments so my eyes arent sore while looking at this

       TextField centTextField = new TextField();
       TextField inchesTextField = new TextField();
       TextField metersTextField = new TextField();
       TextField yardsTextField = new TextField();

       //set all textfields to 0.0
       centTextField.setText("0.0");
       inchesTextField.setText("0.0");
       metersTextField.setText("0.0");
       yardsTextField.setText("0.0");

        //buttons and event listeners
        Button clearButton = new Button("Clear");
        Button calculateButton = new Button("Calculate");
        Button exitButton = new Button("Exit");

        clearButton.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override public void handle(ActionEvent e){
                centTextField.setText("0.0");
                inchesTextField.setText("0.0");
                metersTextField.setText("0.0");
                yardsTextField.setText("0.0");
            }
        });

        calculateButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                //convert inches to centimeters,yards etc.
                double inchToCm = Double.parseDouble(inchesTextField.getText()) * INCH_CM;
                String cmString = String.format("%.2f", inchToCm);
                centTextField.setText(cmString);

                double inchToM = Double.parseDouble(inchesTextField.getText())  * TO_METERS;
                String mString = String.format("%.2f", inchToM);
                metersTextField.setText(mString);


                double inchToY = Double.parseDouble(inchesTextField.getText())  * TO_YARDS;
                String yString = String.format("%.2f", inchToY);
                yardsTextField.setText(yString);

            }

        });


        exitButton.setOnAction( new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                System.exit(0);
            }
        });
        // origin.add(object, col, row)
        origin.add(centLabel, 0, 0);
        origin.add(centTextField, 1, 0);
        origin.add(inchesLabel, 3, 0);
        origin.add(inchesTextField,4, 0 );
        origin.add(metersLabel,0, 2);
        origin.add(metersTextField,1, 2);
        origin.add(yardsLabel, 3, 2);
        origin.add(yardsTextField, 4, 2);
        origin.add(clearButton, 5, 0);
        origin.add(calculateButton, 5, 1);
        origin.add(exitButton, 5, 2);

        stage.show();
   }

}
