/* 
a program to crawl the web 
 */
package webcrawler;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author fanyui
 */
public class WebCrawler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
        System.out.println("Enter root URL: ");
        String url = input.nextLine();
        crawler(url);
    }
    public static void crawler(String startingURL){
        ArrayList<String> PendingURLs = new ArrayList<>();
        ArrayList<String> TraversalURLs = new ArrayList<>();
        
        PendingURLs.add(startingURL);
        while(!PendingURLs.isEmpty() && TraversalURLs.size()<=100){
            String urlString = PendingURLs.remove(0);
            if(!TraversalURLs.contains(urlString)){
                TraversalURLs.add(urlString);
            System.out.println("Craw"+urlString);
            for(String s: getSubURLs(urlString)){
                if(!TraversalURLs.contains(s))
                    PendingURLs.add(s);
                
            }
        }
    }
}
public static ArrayList<String> getSubURLs(String urlString){
ArrayList<String> list = new ArrayList<>();
try{
    java.net.URL url = new java.net.URL(urlString);
    Scanner input = new Scanner(url.openStream());
    int current = 0;
    while (input.hasNext()){
        String line = input.nextLine();
        current = line.indexOf("http:",current);
        while(current >0){
            int endIndex = line.indexOf("\"",current);
            if(endIndex>0){
                list.add(line.substring(current,endIndex));
                current = line.indexOf("http:",endIndex);
            }
            else current =-1;
            
        }
    }
}
catch (Exception ex){
    System.out.println("Error: "+ ex.getMessage());
}
return list;
}
}
