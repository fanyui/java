/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ballpane;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
/**
 *
 * @author fanyui
 */
public class BounceBallControl extends Application{
    @Override 
    public void start(Stage primaryStage){
        BallPane ballPane = new BallPane();
        
        ballPane.setOnMousePressed(e->ballPane.pause());
        ballPane.setOnMouseReleased(e->ballPane.play());
        //changing the speed of the animation
        ballPane.setOnKeyPressed(e->{
            if(e.getCode()== KeyCode.DOWN){
                ballPane.decreaseSpeed();
            }
            else if(e.getCode() == KeyCode.UP){
                        ballPane.increaseSpeed();
                        }
            
        });
        //create a scene and put it in the stage
        Scene scene = new Scene(ballPane,250,150);
        primaryStage.setTitle("bouncing Ball Control");
        primaryStage.setScene(scene);
        primaryStage.show();
        ballPane.requestFocus();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
