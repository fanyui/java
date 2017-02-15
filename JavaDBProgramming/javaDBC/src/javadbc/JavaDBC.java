/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbc;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;
import java.sql.DriverManager;

/**
 *
 * @author harisu
 */
public class JavaDBC extends Application {
private PreparedStatement statement;
private CallableStatement callableStatement;
private TextField tfssn = new TextField();
private TextField tfCourseId = new TextField();
private Label lblStatus = new Label();
private TextField tfFirstName = new TextField();
private TextField tfSecondName = new TextField();
//Override the start method of the application class
public void start(Stage primaryStage){
    initializeDB();
    Button btnShowGrade = new Button("Show Grade");
    Button btnFindStud = new Button("Find Student");
    //for showing grade
    HBox hbox = new HBox(5);
    hbox.getChildren().addAll(new Label("SSN"),tfssn,new Label("Course ID"),tfCourseId,(btnShowGrade));
    //for checking student
    HBox hbox1 = new HBox(5);
    hbox1.getChildren().addAll(new Label("FirstName"),tfFirstName,new Label("SecondName"),tfSecondName,(btnFindStud));

    VBox vBox = new VBox(12);
    vBox.getChildren().addAll(hbox,hbox1,lblStatus);
    tfssn.setPrefColumnCount(6);
    tfCourseId.setPrefColumnCount(6);
    
    tfFirstName.setPrefColumnCount(6);
    tfSecondName.setPrefColumnCount(6);
    btnShowGrade.setOnAction(e->showGrade());
    
    btnFindStud.setOnAction(e->findStud());
    //create a scene and put it in the stage
    Scene scene = new Scene(vBox,520,250);
    primaryStage.setTitle("Find Course Grade");
    primaryStage.setScene(scene);
    primaryStage.show();
}   
    
    public  void initializeDB() {
        try{
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");
        
        //connect to the databases
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaDBProgramming", "harisu", "");
        System.out.println("Database Connected");
        
       
        //
        
        /*
        *create a statement
        */
        String queryString ="select firstName,mi, "+"lastName,title,grade from Student,Enrollment,Course "+"where Student.ssn = ?  and Enrollment.courseId = ? "+
                    " and Enrollment.courseId = Course.courseId "+ " and Enrollment.ssn = Student.ssn";
        statement = connection.prepareStatement(queryString);
        //create a callable statement
        callableStatement = connection.prepareCall("{?= call studentFound(? ,?)}");
         
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch(SQLException ex){
            ex.printStackTrace();
            
        }
    }
    /*uses a callable statement to find a student in the database
    *It uses the database function below to do the work
    *
    drop function if exists studentFound;
        delimiter //
        create function studentFound(first varchar(20), last varchar(20))
        returns int
        begin
        declare result int;
        select count(*) into result
            from Student
        where Student.firstName = first and
        Student.lastName = last;
        return result;
        end;
        //
        delimiter ;
    
    */
    private void findStud(){
        String firstName = tfFirstName.getText();
        String lastName = tfSecondName.getText();
        try{
            callableStatement.setString(2, firstName);
            callableStatement.setString(3, lastName);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.execute();
            if(callableStatement.getInt(1)>=1)
                lblStatus.setText(firstName+" "+lastName+" is in the database");
            else
                lblStatus.setText(firstName +" not found in the dbase ");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private void showGrade(){
        String ssn = tfssn.getText();
        String courseId = tfCourseId.getText();
        try{
            
            statement.setString(1, ssn);
            statement.setString(2, courseId);
            ResultSet rset = statement.executeQuery();
            
            if(rset.next()){
                String lastName = rset.getString(1);
                String mi = rset.getString(2);
                String firstName = rset.getString(3);
                String title = rset.getString(4);
                String grade = rset.getString(5);
                // Display result in a label
                lblStatus.setText(firstName + " " + mi +
                " " + lastName + "'s grade on course " + title + " is " +
                grade);
                } 
            else {
                lblStatus.setText("Not found");
                }
            }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public static void main(String[] args){
        Application.launch(args);
    }
    
}
