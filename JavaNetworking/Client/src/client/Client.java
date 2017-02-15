/*
 * A client program to send and a radius to the server so that the 
 * server can comput the area of the circle with the given radius
 * It also receives the computed area from the server
 */
package client;
import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

        
/**
 *
 * @author harisu
 */
public class Client extends Application{
    //Io streams
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;
    @Override //override the start method of the application class
    public void start(Stage primaryStage){
        //panel p to hold the label and text field
        BorderPane paneForTextField = new BorderPane();
        paneForTextField.setPadding(new Insets(5,5,5,5));
        paneForTextField.setStyle("-fx-border-color: green");
        paneForTextField.setLeft(new Label("Enger a radiuis"));
         TextField tf = new TextField();
         tf.setAlignment(Pos.BOTTOM_RIGHT);
         paneForTextField.setCenter(tf);
          
         BorderPane mainPane = new BorderPane();
                 //text Area to display content
          TextArea ta = new TextArea();
          mainPane.setCenter(new ScrollPane(ta));
          mainPane.setTop(paneForTextField);
           
          //create a scene and place it in the stage
          Scene scene = new Scene(mainPane,450,200);
          primaryStage.setTitle("Client");
          primaryStage.setScene(scene);
          primaryStage.show();//display the stage
          tf.setOnAction(e->{
              try{
                  //get the radius of the textfield
                  double radius = Double.parseDouble(tf.getText().trim());
                  //send the radius to the server
                  toServer.writeDouble(radius);
                  toServer.flush();
                  //Get the area from the server
                  double area = fromServer.readDouble();
                  //display result from server in the text Area
                  ta.appendText("Radius is " +radius +"\n");
                  ta.appendText("Area received from server is "+area+"\n");
                  
                  
              }
              catch(IOException ex){
                  System.err.println(ex);
              }
          });
          try{
              Socket socket = new Socket("localhost",8000);
              //create a n input stream 
              fromServer = new DataInputStream(socket.getInputStream());
              //create aoutput stream
              toServer = new DataOutputStream(socket.getOutputStream());
          }
          catch (IOException ex){
              ta.appendText(ex.toString()+"\n");
          }
    }
            

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
