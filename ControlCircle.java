/*this program controls the growth and shrinking of a circel with and interface
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlcircle;
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
public class ControlCircle extends Application {
private CirclePane circlePane = new CirclePane();
@Override
public void start(Stage primaryStage){
    //holds two buttons in an hbox
    HBox hBox = new HBox();
    hBox.setSpacing(10);
    hBox.setAlignment(Pos.CENTER);
    Button btnEnlarge = new Button("Enlarge");
    Button btnShrink = new Button("Shrink");
    hBox.getChildren().add(btnEnlarge);
    hBox.getChildren().add(btnShrink);
    
    //create and register the handler
    btnEnlarge.setOnAction(new EnlargeHandler());
    btnShrink.setOnAction(new ShrinkHandler());
    
    
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(circlePane);
    borderPane.setBottom(hBox);
    borderPane.setAlignment(hBox,Pos.CENTER);
    
    //create scene and put it in the stage
    Scene scene = new Scene(borderPane, 200,150);
    primaryStage.setTitle("controlling circle with button");
    primaryStage.setScene(scene);
    primaryStage.show();
    
}
//handler when enlarge button is clicked
    class EnlargeHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent e){
            circlePane.enlarge();
        }
    }
    //handler when shrink button is clicked
    class ShrinkHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent e){
            circlePane.shrink();
        }
    }
    //holds properties of a circle that can be manipulated
class CirclePane extends StackPane{
    private Circle circle = new Circle(50);
    public CirclePane(){
        getChildren().add(circle);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
    }
    public void enlarge(){
circle.setRadius(circle.getRadius()+2);
    }
    public void shrink(){
                circle.setRadius(circle.getRadius() > 2 ? circle.getRadius() - 2 : circle.getRadius());

    }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Application.launch(args);
    }
    
}
