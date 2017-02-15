/*
 *A client program to connect to the tictactoe server and play tic tac toe over a network
 */
package tictactoeserver;
import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author harisu
 */
public class TicTacToeClient extends Application implements TicTacToeConstants {
    private boolean myTurn = false;
    private char myToken = ' ';
    private char otherToken = ' ';
    private Cell[][] cell = new Cell[3][3];
    private Label lblTitle = new Label();
    private Label lblStatus = new Label();
    private int rowSelected;
    private int columnSelected;
    
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    
    //continue to play
    private boolean continueToPlay = true;
    private boolean waiting = true;
    private String host = "localhost";
    @Override
    public void start(Stage primaryStage){
        GridPane pane = new GridPane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pane.add(cell[i][j]= new Cell(i,j), j, i);
                
            }
        }
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(lblTitle);
        borderPane.setCenter(pane);
        borderPane.setBottom(lblStatus);
        
        //create a scene and place it in the stage
        Scene scene = new Scene(borderPane,320,350);
        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        connectToServer();
    }
    
    private void connectToServer(){
        try{
            //create socket to connect to server
            Socket socket = new Socket(host,8000);
            fromServer =new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        //controll the game on a seperate thread
        new Thread(()->{
            try{
                //get notification from server
                int player = fromServer.readInt();
                //is it player 1 or 2
                if(player == PLAYER1){
                    myToken = 'X';
                    otherToken = 'O';
                    Platform.runLater(()->{
                        lblTitle.setText("Player 1 with token 'X'");
                        lblStatus.setText("Waiting for Player2 to join");
                        
                    });
                    //receive start up notification from server and discard
                    fromServer.readInt();
                    //the other player has joined
                    Platform.runLater(()->lblStatus.setText("Player 2 has joined .I start first"));
                    //it is my turn
                    myTurn = true;
                }
                else if(player ==PLAYER2){
                    myToken = 'O';
                    otherToken = 'X';
                    Platform.runLater(()->{
                        lblTitle.setText("Player 2 with token 'O'");
                        lblStatus.setText("Waiting for player  1 to move ");
                    });
                }
                //Continue to play
                while(continueToPlay){
                    if(player ==PLAYER1){
                        waitForPlayerAction();//wait for player 1 to move
                        sendMove();
                        receiveInfoFromServer();
                    }
                    else if(player == PLAYER2){
                        receiveInfoFromServer();
                        waitForPlayerAction();
                        sendMove();
                    }
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }).start();
    }
    private void waitForPlayerAction() throws InterruptedException {
        while(waiting){
            Thread.sleep(100);
        }
            waiting = true;
        
    }
        //send this players move to the serrver
        private void sendMove() throws IOException{
            toServer.writeInt(rowSelected);
            toServer.writeInt(columnSelected);
        }
        //receive infomation from server
        private void receiveInfoFromServer() throws IOException{
            int status = fromServer.readInt();
            if(status == PLAYER1_WON){
                continueToPlay = false;
                if(myToken =='X'){
                    Platform.runLater(()->lblStatus.setText("I won! (X)"));
           
                }
                else if(myToken =='O'){
                    Platform.runLater(()->lblStatus.setText("Player 1 (X) has won!"));
                    receiveMove();
                }
             }
            else if(status==DRAW){
                //no winner and game over
                continueToPlay = false;
                Platform.runLater(()->lblStatus.setText("Game is Over no Winner!"));
                if(myToken == 'O'){
                    receiveMove();
                }
            }
            else{
                receiveMove();
                Platform.runLater(()->lblStatus.setText("My turn"));
                myTurn = true;
            }
        }
        private  void receiveMove() throws IOException{
            //get the other players move
            int row = fromServer.readInt();
            int column = fromServer.readInt();
            Platform.runLater(()->cell[row][column].setToken(otherToken));
            
        }
         //an innner class for a cell
        public class Cell extends Pane{
            //indicate row and column for this cell in the board
            private int row;
            private int column;
                    //token used for the cell
                    private char token = ' ';
                    public Cell(int row, int column){
                        this.row= row;
                        this.column = column;
                        this.setPrefSize(2000,2000);//not what happens without this
                        setStyle("-fx-border-color:black");//set cell border
                        this.setOnMouseClicked(e->handleMouseClick());
                    }
                    public char getToken(){
                        return token;
                    }
                    public void setToken(char c){
                        token = c;
                        repaint();
                    }
                    protected void repaint(){
                        if(token == 'X'){
                            Line line1 = new Line(10,10,this.getWidth()-10,this.getHeight()-10);
                            line1.endXProperty().bind(this.widthProperty().subtract(10));
                            line1.endYProperty().bind(this.heightProperty().subtract(10));
                            Line line2 = new Line(10,this.getHeight()-10,this.getWidth()-10,10);
                            line2.startYProperty().bind(this.heightProperty().subtract(10));
                            line2.endXProperty().bind(this.widthProperty().subtract(10));
                            
                            //add a line to the pane
                            this.getChildren().addAll(line1,line2);
                        }
                        else if(token =='O'){
                            Ellipse ellipse = new Ellipse(this.getWidth()/2,this.getHeight()/2,this.getWidth()/2-10,this.getHeight()/2-10);
                            ellipse.centerXProperty().bind(this.widthProperty().divide(2));
                            ellipse.centerYProperty().bind(this.heightProperty().divide(2));
                            ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
                            ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
                            ellipse.setStroke(Color.BLACK);
                            ellipse.setFill(Color.WHITE);
                            
                            getChildren().add(ellipse); //add the ellipse in the pane
                            
                        }
                    }
                    private void handleMouseClick(){
                        //if cell is not occupied and the player has the rurn
                        if(token == ' ' && myTurn){
                            setToken(myToken); //set the players token to the cell
                            myTurn = false; 
                            rowSelected = row;
                            columnSelected = column;
                            lblStatus.setText("Waiting for the other player to move");
                            waiting = false ;//just completed a successful move
                                    
                        }
                    }
        }
        
        public static void main(String[] args){
            Application.launch(args);
        }
}
