
package question2;

//the original vertex in graph
public class Vertex<E> {
    
    public E value;
    public int d;//the first time visited
    public int m;//the depth
    
    public Vertex prevVertex;//the previous vertex ordered by visited 
    
    public boolean visited;
    
    public Vertex(E value){
        this.value = value;
        this.visited = false;
    }
}
