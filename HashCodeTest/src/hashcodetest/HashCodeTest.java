/*op
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
        //int[][]array = {{0,0,0,0,0},{0,1,1,1,0},{0,0,0,0,0}};
int[][]array = {{1,1,1,1,1},{0,0,0,0,0},{0,0,1,0,0},{1,0,1,0,0},{1,0,0,0,0},{0,1,0,1,1}};
        System.out.println("The columns of the matrix is:"+array[0].length);
        System.out.println("Result from split vertical method");
       // splitVertical(array,0,array[0].length,0,6,6);
        System.out.println("result from topButtom");
        topButtom(array,5,1);
        System.out.println("Result from bottomUp: ");
        buttomUp(array,5,1);
 }
    public static void buttomUp(int[][]A, int size, int min){
        int i=A.length;
        int startRow=0;
        int stopRow = 0;
        while(i>=0){
            if(i-size <0){
                startRow =0;
                stopRow = i;
            }
            else{
                startRow =i-size;
                stopRow = i;
            } 
            if(stopRow-startRow < min*2)
                ;
            else
                 splitVertical(A,0,A[0].length,startRow, stopRow,size);
        i-=size;
        }
    }
    public static void topButtom(int[][]A, int size, int min){
        int i = 0; 
         int  startRow = 0;
            int stopRow = 0;
        while(i<A.length) {
            if(A.length- i<size){
                startRow= i; 
                stopRow = A.length;
            }
            else{
             startRow = i;
            stopRow = i+ size;
            }
            if(stopRow-startRow < min*2)
                System.out.println("mistake");
                //horizontalSplite(A, startCol, stopCol, size);
              
            else 
                splitVertical(A,0,A[0].length,startRow, stopRow,size);
        i+=size;
        }
    }
    
    public static void horizontalSplite(int[][]a,int startrow,int stoprow,int size){}
    public static void splitVertical(int[][]a,int startCol, int stopCol,int startRow, int stopRow,int size){
        int rows = stopRow-startRow;
        int cols = (stopCol-startCol)/2;
        // int rrows = a.length-rows;
        int rcols = (stopCol-startCol) -cols;

        if(cols * rows <= size){
                if(check_correct(a,startCol,cols+startCol,startRow, stopRow,size))
                      System.out.println(startRow+" "+startCol+" "+(stopRow-1)+" "+(cols+startCol-1));
                else 
                    System.out.println("error");
            
        }
        else 
            splitVertical(a,startCol,cols+startCol,startRow, stopRow,size);
        
        if(rcols*rows <=size){
            
          if( check_correct(a,stopCol-rcols,stopCol,startRow, stopRow,size))
                System.out.println(startRow+" "+(stopCol-rcols)+" "+(stopRow-1)+" "+(stopCol-1));
          else  System.out.println("error");
        }
        else splitVertical(a,cols+startCol,cols+startCol+rcols,startRow, stopRow,size);
        
       
        
    }
    
    
    
    //preserve the original method to split the matrix
//    public static void splitVertical(int[][]a,int startCol, int stopCol,int size){
//        int rows = a.length;
//        int cols = (stopCol-startCol)/2;
//        // int rrows = a.length-rows;
//        int rcols = (stopCol-startCol) -cols;
//
//        if(cols * rows <= size){
//                if(check_correct(a,startCol,cols+startCol,size))
//                      System.out.println(0+" "+startCol+" "+(rows-1)+" "+(cols+startCol-1));
//                else 
//                    System.out.println("error");
//            
//        }
//        else 
//            splitVertical(a,startCol,cols+startCol,size);
//        
//        if(rcols*rows <=size){
//            
//          if( check_correct(a,stopCol-rcols,stopCol,size))
//                System.out.println(0+" "+(stopCol-rcols)+" "+(rows-1)+" "+(stopCol-1));
//          else  System.out.println("error");
//        }
//        else splitVertical(a,cols+startCol,cols+startCol+rcols,size);
//        
//       
//        
//    }
    public static boolean check_correct(int [][]a,int startCol,int stopCol,int startRow, int stopRow, int size){
        int mStatus = 0;
        int tStatus = 0;
        for(int i = startRow; i< stopRow;i++){
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
