/*
 * A server program to allow multiple clients to connect to it.
 * It create a thread to handle every client that connect to the server
 
 */
package multithreadserver;
import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
/**
 *
 * @author harisu
 */
public class MultiThreadServer extends Application {

    
    private TextArea ta = new TextArea();//to display ocntent
     //number of clients
    private int clientNo =0;
    @Override //overridthe start method in the Application nclass
    public void start(Stage primaryStage){
        //create a scene and place it in the stage
        Scene scene = new Scene(new ScrollPane(ta),450,200);
        primaryStage.setTitle("MultiThreaded Server");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        new Thread(()->{
            try{
                ServerSocket serverSocket = new ServerSocket(8000);
                ta.appendText("server Started at :"+ new Date()+"\n");
                while(true){
                    //listen for a new connection request
                    Socket socket = serverSocket.accept();
                    //increment client number for successful connection
                    clientNo++;
                    
                    Platform.runLater(()->{
                        //Display the client number
                        ta.appendText("Starting thread for client "+clientNo+ " at "+new Date()+"\n");
                        //find the client hostname and ip address
                        InetAddress inetAddress = socket.getInetAddress();
                        ta.appendText("client "+clientNo+"'s IP address is "+inetAddress.getHostAddress()+"\n");
                       ta.appendText("client "+clientNo+"'s Host name is  "+inetAddress.getHostName()+"\n");

                    });
                    //create and start a new thread
                    new Thread(new HandleAClient(socket)).start();
                }
            }
            catch(IOException ex){
                System.err.println(ex);
            }
            
        }).start();
    }
    
    //a Thread class for handliing a client
    class HandleAClient implements Runnable{
        private Socket socket;
         public HandleAClient(Socket socket){
             this.socket=socket;
         }
         public void run(){
             try{
                 //create data input and output streams
                 DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                 DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
                 //continously serve the client
                 while(true){
                     double radius = inputFromClient.readDouble();
                     double area = radius *radius *Math.PI;
                     //send computed area back to client
                     outputToClient.writeDouble(area);
                     Platform.runLater(()->{
                         ta.appendText("radius received from client is " +radius+"\n");
                         ta.appendText("Area computed is :"+area+"\n");
                         
                     });
                 }
             }
             catch(IOException ex){
                 ex.printStackTrace();
             }
         }
         
     
    }
    
         /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
