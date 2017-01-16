/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcodetest;
import java.util.*;
/**
 *
 * @author harisu
 */
public class HashCodeTest {
    public static int minNum =1;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        int[][]array = {{0,0,0,0,0},{0,1,1,1,0},{0,0,0,0,0}};
int[][]array = {{0,0,0,0,0},{0,0,1,0,0},{1,0,1,0,0},{1,0,0,0,0},{0,0,0,1,1}};
        System.out.println("The columns of the matrix is:"+array[0].length);
        
        splitVertical(array,0,5,10);
        
    }
    public static void splitVertical(int[][]a,int startCol, int stopCol,int size){
        int rows = a.length;
        int cols = (stopCol-startCol)/2;
        // int rrows = a.length-rows;
        int rcols = (stopCol-startCol) -cols;
//        int [][]b = new int[rows][cols];
//        for(int i = 0;i<rows;i++){
//            for(int j = 0; j< cols;j++){
//                b[i][j] = a[i][j];
//            }
//        }
//        
//        int [][]c = new int[rrows][rcols];
//         for(int i = rows;i<a.length;i++){
//            for(int j = cols; j< a[0].length;j++){
//                c[i][j] = a[i][j];
//            }
//        }
        if(cols * rows <= size){
                if(check_correct(a,startCol,cols+startCol,size))
                      System.out.println(0+" "+startCol+" "+(rows-1)+" "+(cols+startCol-1));
                else 
                    System.out.println("error");
            
        }
        else 
            splitVertical(a,startCol,cols+startCol,size);
        
        if(rcols*rows <=size){
            
          if( check_correct(a,stopCol-rcols,stopCol,size))
                System.out.println(0+" "+(stopCol-rcols)+" "+(rows-1)+" "+(stopCol-1);
          else  System.out.println("error");
        }
        else splitVertical(a,cols+startCol,cols+startCol+rcols,size);
        
       
        
    }
    public static boolean check_correct(int [][]a,int startCol,int stopCol, int size){
        int mStatus = 0;
        int tStatus = 0;
        for(int i = 0; i< a.length;i++){
            for(int j = startCol; j< stopCol; j++){
                if(a[i][j]==0)
                    mStatus++;
                else tStatus++;
            }
        }
        if(mStatus <minNum || tStatus<minNum)
            return false;
        else 
            return true;
    }
    
}
