// Code originally created by Line Reinhardt.

import java.text.DecimalFormat;
import java.io.OptionalDataException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class AdjacencyGraph {
    public ArrayList<Vertex> vertices;
        int Counter = 0;
        double MSTLenght = 0;

    public AdjacencyGraph(){
        vertices=new ArrayList<Vertex>();
    }

    public void addVertex(Vertex v){
        vertices.add(v);
    }
    public void addEdge(Vertex f,Vertex t, Integer distance){          // from,to,weight
        if(!(vertices.contains(f) && vertices.contains(t)) ) {
            System.out.println("Vertex not in graph");
            return;
        }
        // It is here that I make the edges/cities bidirectional by adding Edge1.
        Edge Edge=new Edge(f,t,w);
        Edge Edge1=new Edge(t,f,w);
    }
    public void MST()  {
        PriorityQueue<Vertex> Q = new PriorityQueue<>();
            if (vertices.size() > 0) {
                vertices.get(0).Distance = 0;
                Q.offer(vertices.get(0));
            }
        
        while(!Q.isEmpty() && Counter < vertices.size()) {
            Vertex SmallestCost = Q.poll();
            ArrayList<Edge> OutEdgeOfU = SmallestCost.getOutEdges();
                
                if(!SmallestCost.TreeIndex) {
                    for(Edge edge : OutEdgeOfU) {
                        if (edge.getWeight() < edge.getToVertex().Distance && !edge.getToVertex().IsInTree){
                            edge.getToVertex().Distance       = edge.getWeight();
                            edge.getToVertex().PreviousVertex = SmallestCost;
                            Q.offer(edge.ToVertex);
                        }
                    }
                    SmallestCost.IsInTree = true;
                    MSTLength += SmallestCost.Distance;
                    Counter++;
                }
                System.out.println(" ");
             }
            public void PrintMST() {
            for (Vertex vertex : vertices) {
                System.out.print(vertex.getName());

                if (vertex.PreviousVertex != null)
                    System.out.print(" coming from  " + vertex.PreviousVertex.getName()
                                      + " The cost " + vertex.Distance);
                else{

                    System.out.println("Vertex " + vertex.getName() + " Has no parent ");
                }
                System.out.println("  ");
            }

            System.out.println("The total distance for network grid is: " + MSTLength + " KM");
            System.out.println("  ");

            System.out.println("The total cost for the network grid is:  " +
                    new DecimalFormat("### ### ###").format(MSTLength * 100000) + "kroner");
        }    
    }
    
    public  void PrintMST(){
         for (Vertex vertex : vertices) {
                System.out.print(vertex.getName());

                if (vertex.PreviousVertex != null)
                    System.out.print(" Coming from  " + vertex.PreviousVertex.getName()
                                      + " The cost " + vertex.Distance);
                else{

                    System.out.println("Vertex " + vertex.getName() + " Has no parent ");
                }
                System.out.println("  ");
            }
            System.out.println("The total distance for network grid is: " + MSTLength + " KM");

            System.out.println("  ");
            System.out.println(" The total cost for the network grid is:  " +
                    new DecimalFormat("### ### ###").format(MSTLength * 100000) + "kroner");
        }
    }
}

class Vertex implements Comparable<Vertex>{
    String name;
    
    public ArrayList<Edge> getOutEdges() {
        return OutEdges;
    }
    
    ArrayList<Edge> OutEdges;
    Integer distance = Integer.MAX_VALUE;
    Vertex PrevVertex = null;
    boolean TreeIndex = false;
    
    public String GetName() {
        return Name;
    }
    
    public Vertex(String cities){
        name=cities;
        OutEdges=new ArrayList<>();
    }
    public void addOutEdge(Edge OutEdge) {
        OutEdges.add(OutEdge);
    }

    @Override
    public int compareTo(Vertex o) {
        return this.dist.compareTo(o.distance);
    }
}

class Edge{
    Integer weight;
    Vertex from;
    Vertex to;
    public Edge(Vertex from, Vertex to, Integer cost){
        this.from=from;
        this.to=to;
        this.weight=cost;
        this.from.addOutEdge(this);
    }
    public Vertex getToVertex(){
        return to;
    }
    
    public Integer getWeight(){
        return weight;
    }
}

// This class ties the distance with index so they can be compared
class Pair implements Comparable<Pair> {
   Integer distance;
   Integer index;

   public Pair(Integer distance, Integer index) {
       this.distance=distance;
       this.index=index;
   }
    @Override
    public int compareTo(Pair p) {
        return this.distance.compareTo(p.distance);
    }
}
