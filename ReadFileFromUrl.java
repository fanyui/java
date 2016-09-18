/*
 *this program reads input from a url and print it to a file name us in the same directory
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readfilefromurl;
import java.util.*;
import java.io.*;
/**
 *
 * @author fanyui
 */
public class ReadFileFromUrl {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Enter a URL: ");
        String URLString = new Scanner(System.in).next();
        
        File destination = new File("us.txt");
        if (destination.exists())
            System.out.println("target file already exist");
        PrintWriter  output = new PrintWriter(destination);
        try {
            java.net.URL url = new java.net.URL(URLString);
            int count = 0;
            Scanner input = new Scanner(url.openStream());
            while (input.hasNext()){
                String line = input.nextLine();
                count +=line.length();
                output.println(line);
            }
            System.out.println("The file size is "+count+" characters");
            
        }
        catch (java.net.MalformedURLException ex){
            System.out.println("Invalid URL");
            
        }
        catch(java.io.IOException ex){
            System.out.println("I/O errors: no such file");
        }
    }
    
}
