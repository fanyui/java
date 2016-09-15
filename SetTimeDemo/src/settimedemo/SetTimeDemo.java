/*
a clock with interface that can set the time temporally
 */
package settimedemo;
import java.util.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 *
 * @author fanyui
 */
public class SetTimeDemo extends Application {

    @Override
public void start(Stage primaryStage){
    VBox vBox = new VBox();
    vBox.setSpacing(10);
    vBox.setAlignment(Pos.CENTER);//end of vbox definition
    HBox hBox = new HBox();
    hBox.setSpacing(5);
    hBox.setAlignment(Pos.CENTER);//end of hBox definition
    setTime clock = new setTime();
    //labels for hour minute and second
    Label lHour = new Label("hour");
    Label lMinute = new Label("minute");
    Label lSecond = new Label("second");
    //textfield for the control of hour minute and se cond
    TextField tHour = new TextField();
    tHour.setMaxWidth(50);
    TextField tMinute = new TextField();
    tMinute.setMaxWidth(50);
     TextField tSecond = new TextField();
     tSecond.setMaxWidth(50);
     //adding time setting parameters to the  hBox
     hBox.getChildren().addAll(lHour,tHour,lMinute,tMinute,lSecond,tSecond);
    
    Button btnNew = new Button("set");
    btnNew.setOnMouseClicked(e->{
        clock.setHour(Integer.parseInt(tHour.getText()));
        clock.setMinute(Integer.parseInt(tMinute.getText()));
        clock.setSecond(Integer.parseInt(tSecond.getText()));
     });

    vBox.getChildren().addAll(clock,hBox,btnNew);
    //create a handler for animation
    EventHandler<ActionEvent> eventHandler = e->{
        clock.setCurrentTime();//set the new clock time
        
   
    };
    //create and animation for a rurnning clock
    Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000),eventHandler));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play();//start the animation
    
    //create a scene and place it in the stage
    Scene scene = new Scene(vBox,400,500);
    primaryStage.setTitle("temporal clock display");
    primaryStage.setScene(scene);
    primaryStage.show();
}
    public static void main(String[] args) {
        // TODO code application logic here
        Application.launch(args);
        
        
    }
    
}
