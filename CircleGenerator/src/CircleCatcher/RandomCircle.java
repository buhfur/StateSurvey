//Ryan McVicker 
package CircleCatcher;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import java.util.*;
//each circle must have random radius and random fill color
public class RandomCircle extends Circle{
    public static float radius = (float)  Math.random() * (50 - 20 + 1) + 20;
    public RandomCircle(int screenX, int screenY){
        /*It may be a good idea to pass the window width and height into the RandomCircle constructor to 
        use it for random point calculations
        */

        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN,Color.ORANGE, Color.CYAN,Color.CRIMSON, Color.DARKGREEN, Color.DARKGOLDENROD, Color.DARKGRAY, Color.DARKMAGENTA, Color.DARKOLIVEGREEN,Color.BROWN, Color.ROSYBROWN, Color.SEAGREEN, Color.SEASHELL, Color.SIENNA,Color.SILVER};
        Random rand = new Random();
        Color randColor = colors[rand.nextInt(colors.length)];

        float rad = (float)  Math.random() * (50 - 20 + 1) + 20;

        setRadius(rad);
        setFill(randColor);
        


        // length : 600 px
        // width : 400 px         

    }


}
