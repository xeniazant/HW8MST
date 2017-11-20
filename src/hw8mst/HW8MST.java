/*
 * @author Xenia Zantello 
    For Adam Whitley's Algorithms and Data structures class.
    The purpose of this code is to implement graph functionality and 
    Prim's algorithm for finding minimum spanning trees.
    11/20/2017


 */
package hw8mst;

public class HW8MST {

    public static void main(String[] args) {
       
    Edge[][] arr = new Edge[5][5];
    for(int i = 0; i < arr.length; i ++){
        for(int j = 0 ; j < arr.length ; j ++){
            arr[i][j] = new Edge();
        }
    }
    arr[0][1].setExists(true);
    arr[0][1].setWeight(6);
    
    arr[0][2].setExists(true);
    arr[0][2].setWeight(4);
    
    arr[1][0].setExists(true);
    arr[1][0].setWeight(6);
    
    arr[1][2].setExists(true);
    arr[1][2].setWeight(2);
    
    arr[1][3].setExists(true);
    arr[1][3].setWeight(1);
    
    arr[2][0].setExists(true);
    arr[2][0].setWeight(4);
    
    arr[2][1].setExists(true);
    arr[2][1].setWeight(2);
    
    arr[2][3].setExists(true);
    arr[2][3].setWeight(4);
    
    arr[2][4].setExists(true);
    arr[2][4].setWeight(5);
    
    arr[3][1].setExists(true);
    arr[3][1].setWeight(1);
    
    arr[3][2].setExists(true);
    arr[3][2].setWeight(4);
    
    arr[3][4].setExists(true);
    arr[3][4].setWeight(4);
    
    arr[4][2].setExists(true);
    arr[4][2].setWeight(5);
    
    arr[4][3].setExists(true);
    arr[4][3].setWeight(4);
    
    
    
    Graph test = new Graph(arr);
    test.printGraph();
    
    System.out.println("////----After Prim----------////");
    
    test.primMst();
    test.printGraph();
    test.printV();
        
        
    }
}
