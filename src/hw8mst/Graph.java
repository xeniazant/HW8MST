/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw8mst;

import java.util.PriorityQueue;

/**
 *
 * @author awhitley
 *  @author Xenia Zantello 
    For Adam Whitley's Algorithms and Data structures class.
    The purpose of this code is to implement graph functionality and 
    Prim's algorithm for finding minimum spanning trees.
    11/20/2017
 */
public class Graph {

    // ----- Data Fields -----
    private Vertex[] vertices; // DO NOT rename me without Refactor --> Rename, or you'll break decreaseKey.
    private int n; //int representing number of vertices in graph
    private Edge[][] edges; // 2d array of Edge objects size n by n representing the adjacency matrix
   
    
    // You need to declare the other data fields, too!
    // You need to define all the constructor(s) and other class methods, too!
    
    
    //----------CONSTRUCTOR--------------//
    
    Graph(Edge[][] arr){
        n = arr.length;
        edges = arr;
        vertices = new Vertex[n];
        for(int i = 0; i < n; i++){
            vertices[i] = new Vertex(i , Edge.INFINITE_WEIGHT);
        }
    }
    
    // ----- Private Helper Methods -----
    /**
     * Tells you whether a Vertex with the provided label is still in the queue
     * q. This is needed because, since the PriorityQueue is keyed on key not
     * label, it will tell you whether a given key is still in it, but not
     * whether a given label is still in it.
     *
     * @param q a PriorityQueue<Vertex>
     * @param label The Vertex label to check for.
     * @return Returns true if a Vertex with matching label is in q. Returns
     * false if no Vertex with matching label is in q.
     */
    private boolean isStillInQ(PriorityQueue<Vertex> q, int label) {

        Vertex[] array = q.toArray(new Vertex[0]); // dump out an array of the elements

        // traverse the array of elements, searching for a matching label
        for(int i = 0; i < array.length; i++) {
            if ((array[i]).getLabel() == label) {
                return true;
            }
        }

        return false; // no matching label found

    }

    /**
     * Takes the Vertex with matching label in queue q, and reduces its key to
     * newKey. Will return false if Vertex is not in the queue, or newKey is
     * larger than old key. Will return true if it successfully reduced the key.
     *
     * @param q The priority queue of Vertex
     * @param label The label of the Vertex whose key you want to decrease
     * @param newKey
     * @return Returns false if the Vertex with the given label is not in the
     * queue. Returns false if the newKey is larger than the old key of Vertex
     * with given label. Returns true otherwise; the vertex with the given label
     * had its key changed to newKey.
     */
    private boolean decreaseKey(PriorityQueue<Vertex> q, int label, int newKey) {
        // PAY NO ATTENTION TO THE CODE BEHIND THAT CURTAIN! ;)
        // Don't worry about the code in this method body. Read the Javadoc above.

        int indexOfVertex = -1;
        Vertex[] array = q.toArray(new Vertex[0]);

        // check to see if Vertex with the given label is in the Priority queue.
        for (int i = 0; i < array.length; i++) {
            if ((array[i]).getLabel() == label) {
                indexOfVertex = i;
            }
        }

        // if Vertex with the given label is not in the queue, do nothing and return false
        // also returns false if the new key is larger than the old key.
        if (indexOfVertex == -1 || newKey > array[indexOfVertex].getKey()) {
            return false;
        }

        // Without decreaseKey already in the PriorityQueue class,
        // I must remove the vertex and add it again with a different key. 
        // Actually, I'm emptying the queue, then I am inserting all the other
        // vertices back in, except the one being decreased. Then I am reinserting
        // the decreased vertex, with the newKey key value.
        // I had to resort to this because technically you can't remove 
        // an element from a PriorityQueue by its label, since it is keyed on something else.
        Vertex vertexToDecrease = array[indexOfVertex];
        vertexToDecrease.setKey(newKey);

        // clear and rebuild the priority queue
        q.clear();
        for (int i = 0; i < array.length; i++) { // add everything else
            if (i != indexOfVertex) { // not including the old vertex to be reduced
                q.add(array[i]);
            }
        }
        q.add(vertexToDecrease); // insert the decreased vertex back in

        return true; // queue is now effectively identical to before, but with one Vertex's key reduced to newKey
   
    
    
    }
    //------ Getters and Setters---//
        /**
         * 
         * @return Returns global n value describing size of both dimensions of 2d array.
         */
        public int getN(){
            return this.n;
        }
        
        
        /**
         * 
         * @param index describes the index of the vertex to be returned
         * @return 
         */
        public Vertex getV(int index){
            for(Vertex v : this.vertices){
                if(v.getLabel() == index){
                    return v;
                }
            }
            return null;
        }
        
        
        /**
         * 
         * @param v0 describes the first vertex enclosing the edge to be returned
         * @param v1 describes the second vertex enclosing the edge to be returned
         * @return returns the edge object connecting the two vertices or null if one does not exist.
         */
        public Edge getEdge(Vertex v0 , Vertex v1){
            int v0Lab = v0.getLabel();
            int v1Lab = v1.getLabel();
            
            for(int i = 0 ; i < edges.length; i++){
                for(int j = 0; j < edges.length; j++){
                    if(i == v0Lab && j == v1Lab){
                        return edges[i][j];
                    }
                }
            }
            return null;
        }
        
        
        //---------Print Graph----///
        
        
        /**
         *  public method to print "n" value of the graph object, as well as a formatted version of the adjacency matrix.
         */
        public void printGraph(){
            System.out.println("N is : " + this.n);
            for(int i = 0; i < this.edges.length ; i++){
                for(int j = 0; j < this.edges.length ; j++){
                    System.out.print(this.edges[i][j] + "   ");
                }
                System.out.println();
            }
        }
        
        /**
         * public method to print all vertex objects in out array of those objects
         */
        public void printV(){
            for(Vertex v : vertices){
                System.out.println(v);
            }
        
        }
        
        //---------Prim's MST--------//
        
        
        /**
         * 
         */
        public void primMst(){
            vertices[0].setKey(0); // For this example we are starting at index 0 
            PriorityQueue<Vertex> q = new PriorityQueue();
            for(Vertex v : vertices){
                q.add(v); //for loop to add all vertex objects to min-priority-queue
            }
            while(!q.isEmpty()){
                Vertex u = q.poll();
                for(int i = 0 ; i < n; i++){
                    if(edges[u.getLabel()][i].getExists()){
                        Vertex v = getV(i);
                        if(isStillInQ(q , i) && getEdge(u , v).getWeight() < v.getKey()){
                            v.setParent(u);
                            decreaseKey(q , v.getLabel() , getEdge(u , v ).getWeight());
                        }
                    }
                    
                }
                
            }
        }
        
}
