/*
 * A server Application to play for playing a 
 *tictactoe game over a network

 */
package tictactoeserver;
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
public class TicTacToeServer extends Application implements TicTacToeConstants{
        private int sessionNo = 1; //number a session
    @Override
    public void start(Stage primaryStage){
        TextArea taLog = new TextArea();
        //create a scene and place it in the stage
        Scene scene = new Scene(new ScrollPane(taLog),450,200);
        primaryStage.setTitle("Tic Tac Toe Server");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        new Thread(()->{
            try{
                //create a socket
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(()->taLog.appendText(new Date()+" :server stated at socket 8000"));
                //ready to create a session for ever two players
                
                while(true){
                    Platform.runLater(()->taLog.appendText(new Date()+": wait for players to join Session"+sessionNo+"\n"));
                    //connect to player 1
                    Socket player1 = serverSocket.accept();
                    
                    Platform.runLater(()->{
                        taLog.appendText(new Date()+": Player1 join session"+sessionNo+"\n");
                        taLog.appendText("Player1's IP address "+player1.getInetAddress().getHostAddress()+"\n");
                        
                    });
                    
                    //notify that the player is player 1
                    new DataOutputStream(player1.getOutputStream()).writeInt(PLAYER1);
                    
                    //connect to player 2
                    Socket player2 = serverSocket.accept();
                    Platform.runLater(()->{
                        taLog.appendText(new Date()+":Player 2 joined session "+sessionNo+"\n");
                        taLog.appendText("Player2's IP address "+player2.getInetAddress().getHostAddress()+"\n");
                        
                    });
                    
                    //notify that the playerr is player 2
                    new DataOutputStream(player2.getOutputStream()).writeInt(PLAYER2);
                    
                    //display the session and increment the session number
                    Platform.runLater(()->
                    taLog.appendText(new Date()+": Start a thread for session "+sessionNo++ +"\n"));
                    
                    //launch a new Thread for this session of tow players
                    new Thread(new HandleASession(player1,player2)).start();
                    
                    
                    
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }).start();
        
    }
    
    class HandleASession implements Runnable, TicTacToeConstants{
        private Socket player1;
        private Socket player2;
        //create an innitialize cells
        private char[][] cell = new char[3][3];
        private DataInputStream fromPlayer1;
        private DataOutputStream toPlayer1;
        private DataOutputStream toPlayer2;
        private DataInputStream fromPlayer2;
        //continue to play
        private boolean continueToPlay = true;
        
        //construct a thread
        public HandleASession(Socket player1,Socket player2){
            this.player1=player1;
            this.player2=player2;
            
            //innitialize cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    cell[i][j] = ' ';
                }
            }
        }
            @Override//implement the run method for the thread
             public void run(){
                //create datainput and output streams
                try{
                    DataInputStream fromPlayer1 = new DataInputStream(player1.getInputStream());
                    DataOutputStream toPlayer1 = new DataOutputStream(player1.getOutputStream());
                    DataInputStream fromPlayer2 = new DataInputStream(player2.getInputStream());
                    DataOutputStream toPlayer2 = new DataOutputStream(player2.getOutputStream());
                    //write anything to notify player1 to start
                    toPlayer1.writeInt(1);
                    //continously serve the players and report game status to  players
                    while(true){
                        //receive a move from player1
                        int row = fromPlayer1.readInt();
                        int column = fromPlayer1.readInt();
                        cell[row][column]='X';
                        //check if player one wins
                        if(isWon('X')){
                            toPlayer1.writeInt(PLAYER1_WON);
                            toPlayer2.writeInt(PLAYER1_WON);
                            sendMove(toPlayer2,row,column);
                            break;
                        }
                        else if(isFull()){
                            //check if all cells ar full
                            toPlayer1.writeInt(DRAW);
                            toPlayer2.writeInt(DRAW);
                            sendMove(toPlayer2,row,column);
                            break;
                            
                        }
                        else{
                            //notify player tow to take turn
                            toPlayer2.writeInt(CONTINUE);
                            sendMove(toPlayer2,row,column);
                        }
                        //receive a move from player 2
                        row = fromPlayer2.readInt();
                        column = fromPlayer2.readInt();
                        cell[row][column] = 'O';
                        
                        //check if player 2 wins
                        if(isWon('O')){
                            toPlayer1.writeInt(PLAYER2_WON);
                            toPlayer2.writeInt(PLAYER2_WON);
                            sendMove(toPlayer1,row,column);
                            break;
                            
                        }
                        else{
                            //notify player 1 to take turn
                            toPlayer1.writeInt(CONTINUE);
                            //send player2's selected row and columns to player 1
                            sendMove(toPlayer1,row,column);
                        }
                    }
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
                
        }
             private void sendMove(DataOutputStream out, int row, int column) throws IOException{
                 out.writeInt(row);//send row
                 out.writeInt(column);//send column
             }
             private boolean isFull(){
                 for (int i = 0; i < 3; i++) 
                     for (int j = 0; j < 3; j++) 
                         if(cell[i][j] == ' ')
                             return false; //there is an empty cell
                         return true; //cell are all filled
                     
                
             }
             //determine if a player wins
             private boolean isWon(char token){
                 //check all rows
                 for (int i = 0; i < 3; i++) 
                     if((cell[i][0] == token) && (cell[i][1] == token )&& (cell[i][2] == token)){
                         return true;
                     }
                     
                 //check all columns
                 for (int j = 0; j < 3; j++) 
                     if((cell[0][j] == token) && (cell[1][j] == token) && (cell[2][j] == token)){
                         return true;
                     }
                     //check major diagonal
                     if((cell[0][0] == token) && (cell[1][1] == token) && (cell[2][2]==token)){
                         return true;
                     }
                     //check for sub diagonal
                     if((cell[0][2] == token) && (cell[1][1] == token) && (cell[2][0] == token)){
                         return true;
                     }
                     return false;
                 }
                 
              }
             
    
    
//    public static void main(String[] args) {
//        Application.launch(args);
//    }
//    
}
