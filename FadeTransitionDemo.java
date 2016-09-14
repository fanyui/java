/*
 demonstrate the fading of an ellipse
 */
package fadetransitiondemo;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author fanyui
 */
public class FadeTransitionDemo extends Application {
@Override
public void start(Stage primaryStage){
    Pane pane = new Pane();
    Ellipse ellipse = new Ellipse(10,10 ,100,50);
    ellipse.setFill(Color.BLUE);
    ellipse.setStroke(Color.BLACK);
    ellipse.centerXProperty().bind(pane.widthProperty().divide(2));
    ellipse.centerYProperty().bind(pane.heightProperty().divide(2));
    ellipse.radiusXProperty().bind(pane.widthProperty().multiply(0.5));
    ellipse.radiusYProperty().bind(pane.heightProperty().multiply(0.5));
    pane.getChildren().add(ellipse);
    
    //apply the fade transition to the ellipse
    FadeTransition ft = new FadeTransition(Duration.millis(3000),ellipse);
    ft.setFromValue(1.0);
    ft.setToValue(0.1);
    ft.setCycleCount(Timeline.INDEFINITE);
    ft.setAutoReverse(true);
    ft.play();
    
    //control animation
    ellipse.setOnMousePressed(e->ft.pause());
    ellipse.setOnMouseReleased(e->ft.play());
    
    //create the scene 
    Scene scene = new Scene(pane,250,300);
    primaryStage.setTitle("fading of an ellipse");
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
