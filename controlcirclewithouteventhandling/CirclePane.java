/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlcirclewithouteventhandling;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.BorderPane;
import javafx.event.Event;
import javafx.event.EventHandler;


/**
 *
 * @author fanyui
 */
public class CirclePane extends StackPane {
    private Circle circle = new Circle(50);
    public CirclePane(){
        getChildren().add(circle);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
    }
    public void enlarge(){
        circle.setRadius(circle.getRadius() + 2);
    }
    public void shrink(){
                circle.setRadius(circle.getRadius()>2?circle.getRadius()-2:circle.getRadius());

    }
    
}
