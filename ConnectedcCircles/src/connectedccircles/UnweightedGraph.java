/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectedccircles;
import java.util.*;
/**
 *
 * @author harisu
 */
public class UnweightedGraph<V> extends AbstractGraph<V> {
    //construct and empty graph
    public UnweightedGraph(){
        
    }
    //construct a graph from vertices and edges stored in arrays
    public UnweightedGraph(V[] vertices, int [][] edges){
        super(vertices,edges);
    }
    //construct a graph from vertices and edges stored in list
    public UnweightedGraph(List<V> vertices,List<Edge>edges){
        super(vertices,edges);
    }
    //construct a graph for integeter vertices 0,1,2,and edge list
    public UnweightedGraph(List<Edge> edges, int numberOfVertices){
        super(edges,numberOfVertices);
    }
    //construct a graph from integer vertices 0,1,and edte array
    public UnweightedGraph(int[][]edges, int numberOfVertices){
        super(edges, numberOfVertices);
    }
}
