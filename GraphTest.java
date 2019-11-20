package question2;

public class GraphTest {
    
     public static void main(String[] args) {
        GraphADT g = new GraphADT();
        Character[] vertexValue = new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm'};
        
        for (int i = 0; i < vertexValue.length; i++) {
            g.addVertex(vertexValue[i]);
        }
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 3);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(5, 6);
        g.addEdge(4, 7);
        g.addEdge(7, 8);
        g.addEdge(4, 9);
        g.addEdge(9, 10);
        g.addEdge(10, 11);
        g.addEdge(11, 12);
        g.addEdge(9, 11);
        g.addEdge(9, 12);
        
        g.printGraph(vertexValue);
        g.calculateDepths(g.vertexArr[0]);
    }
}
