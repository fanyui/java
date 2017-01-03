/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectedccircles;

/**
 *
 * @author harisu
 */
public interface Graph<V> {
    //return the number of vertices in the graph
    public int getSize();
    
    //return the vertices in the graph
    public java.util.List<V> getVertices();
    
    //return the object for the specified vertex index
    public V getVertex(int index);
    //return the index of the specified object
    public int getIndex(V v);
    
    //returns the neighbors of the vertex with the specified index
    public java.util.List<Integer> getNeighbors(int index);
    //return the degree of a specified vertex
    public int getDegree(int v);
    
    //print the edges
    public void printEdges();
    //clear the graph
    public void clear();
    
    //add a vertex to the graph
    public boolean addVertex(V vertex);
    //add and edge to the graph
    public boolean addEdge(int u,int v);
    //obtain a depth first search starting from v
    public AbstractGraph<V>.Tree dfs(int v);
    //obtain a tree breadth first search starting from v
    public AbstractGraph<V>.Tree bfs(int v);
    
    
}
