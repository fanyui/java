/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clockanimation;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author fanyui
 */
public class ClockPane extends Pane{
    private int hour;
    private int minute;
    private int second;
    
    //clock pane's width and height
    private double w = 250, h = 250;
    
    //default clock
    public ClockPane(){
        setCurrentTime();
    }
    
    //construct a clock with specific hour minute and second
    public ClockPane(int hour, int minute,int second){
        this.hour= hour;
        this.minute = minute;
        this.second = second;
        paintClock();    
    }
    
    //retur hour
    public int getHour(){
        return hour;
    }
    //set new hour
    public void setHour(int hour){
        this.hour = hour;
        paintClock();
    }
    //get minute
    public int getMinute(){
        return minute;
    }
    //set new minute
    public void setMinute(int minute){
        this.minute = minute;
        paintClock();
    }
    //return seconds
    public int getSecond(){
        return second;
    }
    //set new second
    public void setSecond(int second){
        this.second = second;
        paintClock();
    }
    
    //return clock pane's width
    public double getW(){
        return w;
    }
    //set clock pane's width
    public void setW(double w){
        this.w = w;
        paintClock();
    }
    //returns clock panes height
    public double getH(){
        return h;
    }
    //set the clock's pane's height
    public void setH(double h){
        this.h = h;
        paintClock();
    }
    //set the current time for the clock
    public void setCurrentTime(){
        //construct a calender for the current date and time
        Calendar calendar = new GregorianCalendar();
        //set current hour ,minute,and second
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);
        paintClock();//repaint the clock
    }
    //paint the clock
    protected void paintClock(){
        //initialise the parameters
        double clockRadius = Math.min(w, h) * 0.8 * 0.5;
        double centerX = w/2;
        double centerY = h/2;
        //draw circle
       
        Circle circle = new Circle(centerX,centerY,clockRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Text t1 = new Text(centerX-5,centerY-clockRadius + 12,"12");
        Text t2 = new Text(centerX-clockRadius+3,centerY+5,"9");
        Text t3 = new Text(centerX + clockRadius -10,centerY+3,"3");
        Text t4 = new Text(centerX-3,centerY+clockRadius-3,"6");
        
        //draw second hand
        double sLength = clockRadius * 0.8;
        double secondX = centerX +sLength *Math.sin(second*(2*Math.PI/60));
        double secondY = centerY - sLength*Math.cos(second *(2*Math.PI/60));
        Line sLine = new Line(centerX,centerY,secondX,secondY);
        sLine.setStroke(Color.RED);
        
        //draw minute hand
        double mLength = clockRadius * 0.65;
        double xMinute =centerX +mLength *Math.sin(minute*(2*Math.PI/60));
        double minuteY  = centerY -mLength*Math.cos(minute*(2*Math.PI/60));
        Line mLine = new Line(centerX,centerY,xMinute,minuteY);
        mLine.setStroke(Color.BLACK);
        
        //draw hour hand
        double hLength = clockRadius * 0.5;
        double hourX = centerX+hLength * Math.sin((hour%12+minute/60)*(2*Math.PI/12));
       double hourY = centerY -hLength * Math.cos((hour%12+minute/60.0)*(2*Math.PI/2));
       Line hLine = new Line(centerX,centerY,hourX,hourY);
       hLine.setStroke(Color.GREEN);
       
       getChildren().clear();
       getChildren().addAll(circle,t1,t2,t3,t4,sLine,mLine,hLine);
       
    }
}
