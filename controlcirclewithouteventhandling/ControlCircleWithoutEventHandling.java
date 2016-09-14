/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlcirclewithouteventhandling;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.BorderPane;
/**
 *
 * @author fanyui
 */
public class ControlCircleWithoutEventHandling extends Application {
     CirclePane circlePane = new CirclePane();
    
        @Override
        public void start(Stage primaryStage){
            StackPane pane = new StackPane();
            Circle circle = new Circle(50);
            circle.setStroke(Color.BLUE);
            circle.setFill(Color.WHITE);
            pane.getChildren().add(circle);
            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.setAlignment(Pos.CENTER);
            Button btnEnlarge = new Button("Enlarge");
            Button btnShrink = new Button("Shrink");
            hBox.getChildren().add(btnEnlarge);
            hBox.getChildren().add(btnShrink);
            
            //create and register the handler
            btnEnlarge.setOnAction(new EnlargeHandler());
            
            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(pane);
            borderPane.setBottom(hBox);
            BorderPane.setAlignment(hBox, Pos.CENTER);
            
            //create a scene an place it in a stage
            Scene scene = new Scene(borderPane,200,150);
            primaryStage.setTitle("control cirble");
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
 class EnlargeHandler implements EventHandler<ActionEvent>{
    @Override
    public void handle(ActionEvent e){
       circlePane.enlarge();
       
        //import the handle class to enlarg the circle
    }
}