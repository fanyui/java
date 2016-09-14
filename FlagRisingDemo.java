/*
demonstate the rising of a flage as an animation
 */
package flagrisingdemo;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Line;
import javafx.scene.image.ImageView;
import javafx.util.Duration; 


/**
 *
 * @author fanyui
 */
public class FlagRisingDemo extends Application {
@Override //override the start method of the application class
public void start(Stage primaryStage){
    Pane pane = new Pane();
   
    ImageView imageView = new ImageView("photo/me.jpg");
    
    pane.getChildren().add(imageView);
    

    //creating a path transtion to be taken by the image
    PathTransition pt = new PathTransition(Duration.millis(10000),new Line(100,200,100,0),imageView);
    pt.setCycleCount(5);
    pt.play();//start the animation
    imageView.setOnMouseClicked(e->pt.stop());
    //create a scene and place it in the stage
    Scene scene = new Scene(pane, 250,250);
    primaryStage.setTitle("Rising up a flag in Mbankolo");
    primaryStage.setScene(scene);
    primaryStage.show();
    
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Application.launch(args);
    }
    
}
