package CircleCatcher;
//Ryan McVicker
// IDEA : send the circle flying from corner to corner 
import javafx.application.Application;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.*;
import javafx.animation.RotateTransition; 
import javafx.animation.TranslateTransition; 
import javafx.scene.Group; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Polygon; 
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
	
public class CircleCatcher extends Application{

    @Override public void start(Stage stage){
	//  Math.floor(Math.random() * (max - min ) + min);
        Pane root = new Pane();
        Scene scene = new Scene(root, 600,400); 

        //create circles when the user clicks 
        
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
        
	    @Override
	    public void handle(MouseEvent mouseEvent) {

			Circle newCircle = new RandomCircle(600, 400); 
			TranslateTransition transition = new TranslateTransition();
			transition.setDuration(Duration.seconds(3));
			float rad = (float)newCircle.getRadius();
			float cexY =  (float)Math.random() * (400 + -400 + 1) + rad;
			float cexX =  (float)Math.random() * (600 - rad + 1) + rad;

			transition.setToX(cexX);
			transition.setToY(cexY);

			transition.setNode(newCircle);
			transition.play();
			// put the circle in the middle of the screen
			newCircle.relocate(300,200);
			root.getChildren().addAll(newCircle);
		 
		
		}
	    });
	 

		stage.setScene(scene);
		stage.show();
    

	
	}

}
