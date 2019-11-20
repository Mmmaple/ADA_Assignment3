
package question2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import question2.Vertex;

public class GraphADT<E> {
    
    public static final int DEFAULTSIZE = 20; //defaultSize
    private int size;//real size
    public Vertex[] vertexArr;
    private int[][] adjMatrix;
    private int vertexNum;
    
    public GraphADT(){
        size = DEFAULTSIZE;
        vertexArr = new Vertex[size];
        adjMatrix = new int[size][size];
        vertexNum = 0;
    }
    
    public boolean isEmpty(){
        return (vertexNum == 0) ;
    }
    
    
    
    public void addVertex(E value){
        if (size > 0) {
            Vertex newVertex = new Vertex<E>(value);
            for (int i = 0; i < size; i++) {
                if (vertexArr[i] == null) {
                    vertexArr[i] = newVertex;
                    vertexNum++;
                    return;
                }
            }
        }
    }
    
    public void addEdge(int i, int j){
        if (size > 0) {
            adjMatrix[i][j] = adjMatrix[j][i] = 1;
        }
    }
    
    private int findIndex(Vertex v){
        for (int i = 0; i < vertexArr.length; i++) {
            if (v.value == vertexArr[i].value) {
                return i;
            }
        }
        return -1;
    }
    
    public void calculateDepths (Vertex startVertex){
        int startIndex = findIndex(startVertex);
        depthFirstSearch(startIndex);
        findMinimumValue();
    }
    
    private void findMinimumValue(){
        System.out.println("values of m(v): ");
        for (int i = vertexNum - 1; i >= 0; i--) {
            System.out.print(vertexArr[vertexNum - i - 1].value + "  ");
            for (int j = 0; j < vertexNum; j++) {
                if ((adjMatrix[i][j] == 1) && (vertexArr[i].prevVertex != vertexArr[j])) {
                    vertexArr[i].m = Math.min(vertexArr[i].m, vertexArr[j].m);
                }
            }
        }
        System.out.println("");
        for (int i = 0; i < vertexNum; i++) {
            System.out.print(vertexArr[i].m + "  ");
        }
        
    }
    private void depthFirstSearch(int startIndex) {
        
        if (startIndex == -1) {
            System.out.println("can not find this start vertex");
            return;
        }
        System.out.println("DepthFirstSearch with d(v):");
        if (!isEmpty()) {
            Stack<Integer> stack = new Stack<Integer>();
            stack.push(startIndex);
            while(!stack.empty()){
                int cur = stack.peek();
                if (cur == 0) {
                    vertexArr[cur].prevVertex = vertexArr[cur];
                }
                if (vertexArr[cur].visited == false) {
                    System.out.print(vertexArr[cur].value + "  ");//print the value
                    
                    vertexArr[cur].d = cur;//update the order of d
                    vertexArr[cur].m = cur;//update the minimum value
                    vertexArr[cur].visited = true;
                    
                }
                int j = 0;
                for (; j < vertexNum; j++) {
                    if (adjMatrix[cur][j] == 1 && vertexArr[j].visited == false) {
                        stack.push(j);
                        vertexArr[j].prevVertex = vertexArr[cur];
                        break;
                    }
                }
                if (j == vertexNum) {
                    stack.pop();
                }
            }
            for (int i = 0; i < vertexNum; i++) {
                vertexArr[i].visited = false;
            }
        }
        System.out.println("");
        for (int i = 0;i < vertexNum; i++) {
            System.out.print(vertexArr[i].d + "  ");
        }
        System.out.println("");
    }
    
//    private void broadFirstSearch(){
//        System.out.println("BroadFirstSearch with d(v):");
//        if(!isEmpty()){
//            Queue<Integer> queue = new LinkedList<Integer>();
//            queue.offer(0);
//            while(!queue.isEmpty()){
//                int cur = queue.poll();
//                if (vertexArr[cur].visited == false) {
//                    System.out.print(vertexArr[cur].value + "  ");
//                    vertexArr[cur].visited = true;
//                }
//                for (int j = 0; j < vertexNum; j++) {
//                    if (adjMatrix[cur][j] == 1 && vertexArr[j].visited == false) {
//                        queue.offer(j);
//                    }
//                }
//            }
//            for (int i = 0; i < vertexNum; i++) {
//                vertexArr[i].visited = false;
//            }
//        }
//    }
    public void printGraph(Character[] c){
        System.out.print("*");
        for (Character vertex : c) {
            System.out.print(" " + vertex);
        }
        System.out.println("");
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i]);
            for (int j = 0; j < c.length; j++) {
                System.out.print(" " + this.adjMatrix[i][j]);
            }
            System.out.println("");
        }
    }
    
   

    
    
}
