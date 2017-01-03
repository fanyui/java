/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectedccircles;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author harisu
 */
public class ConnectedcCircles extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override //overrides the start method in the application
    public void start(Stage primaryStage){
        //create a scene and place it in the stage
        Scene scene = new Scene(new CirclePane(),450,350);
        primaryStage.setTitle("Connected circles");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    class CirclePane extends Pane{
        public CirclePane(){
            this.setOnMouseClicked(e->{
              if(!isInsideACircle(new Point2D(e.getX(),e.getY()))){
                  //add a new circle
                  getChildren().add(new Circle(e.getX(),e.getY(),20));
                  colorIfConnected();
              }
            
            });
        }
        private boolean isInsideACircle(Point2D p){
            for(Node circle:this.getChildren())
                if(circle.contains(p))
                    
                    return true;
            return false;
        }
        //color all circles if they are connected
        private void colorIfConnected(){
            if(getChildren().size() ==0)
                return;//no circle in the pane
            //build and edge
            java.util.List<AbstractGraph.Edge> edges = new java.util.ArrayList<>();
            for(int i = 0;i < getChildren().size();i++)
                for(int j = i+1; j< getChildren().size();j++)
                    if(overlaps((Circle)(getChildren().get(i)),(Circle)(getChildren().get(j)))){
                    edges.add(new AbstractGraph.Edge(i,j));
                    edges.add(new AbstractGraph.Edge(j,i));
                    
                }
                    //create a graph with circles as vertices
                    Graph<Node> graph = new UnweightedGraph<>((java.util.List<Node>)getChildren(), edges);
                    AbstractGraph<Node>.Tree tree = graph.dfs(0); //dfs tree
                    boolean isAllCirclesConnected = getChildren().size() == tree.getNumberOfVerticesFound();
                    for(Node circle:  getChildren()){
                        if(isAllCirclesConnected){
                            ((Circle)circle).setFill(Color.RED);
                        }
                        else{
                            ((Circle)circle).setStroke(Color.BLACK);
                            ((Circle)circle).setFill(Color.WHITE);
                        }
                    }              
            }
        
    }
            public static boolean overlaps(Circle circle1, Circle circle2){
                return new Point2D(circle1.getCenterX(),circle1.getCenterY()).distance(circle2.getCenterX(),circle2.getCenterY()) <= circle1.getRadius() + circle2.getRadius();
            }
        }
        
        


