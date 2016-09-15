/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clockanimation;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.util.Duration;

/**
 *
 * @author fanyui
 */
public class ClockAnimation extends Application {
@Override
public void start(Stage primaryStage){
    ClockPane clock = new ClockPane();
    
    //create a handler for animation
    EventHandler<ActionEvent> eventHandler = e->{
        clock.setCurrentTime();//set the new clock time
    };
    //create and animation for a rurnning clock
    Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000),eventHandler));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play();//start the animation
    
    //create a scene and place it in the stage
    Scene scene = new Scene(clock,250,50);
    primaryStage.setTitle("temporal clock display");
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
