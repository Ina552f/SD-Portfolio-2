// Code originally created by Line Reinhardt.
// I am trying to get the code converted so it can be used with the adjacency list and made my own or used as an example.

import java.io.OptionalDataException;
import java.util.ArrayList;
import java.util.Arrays;

public class AdjacencyGraph {
    private static int length;
    private static OptionalDataException AdjacencyGraph;
    ArrayList<Vertex> vertices;

    public AdjacencyGraph(){
        vertices=new ArrayList<Vertex>();
    }

    public static void setLength(int length) {
        AdjacencyGraph.length = length;
    }

    public void addVertex(Vertex v){
        vertices.add(v);
    }
    public void addEdge(Vertex f,Vertex t, Integer w){          // from,to,weight
        if(!(vertices.contains(f) && vertices.contains(t)) ) {
            System.out.println("Vertex not in graph");
            return;
        }
        // It is here that I make the edges/cities bidirectional by adding Edge1.
        Edge Edge=new Edge(f,t,w);
        Edge Edge1=new Edge(t,f,w);
    }
    public void MSTPrims()  {
        //Setup for the algorithm
        int[] Distance =new int[AdjacencyGraph.length];
        int[] Predecessor = new int[AdjacencyGraph.length];
        MinHeap<Pair> Q = new MinHeap<>();
        ArrayList<Pair> VertexPairs=new ArrayList<>();
        Arrays.fill(Distance,Integer.MAX_VALUE);
        Arrays.fill(Predecessor, -1);        // Sets it up so the program won't think under -1 is null (important detail)
        if(AdjacencyGraph.length>0)              //0 is the root chosen
            Distance[0]=0;
        for(int i=0;i<AdjacencyGraph.length;i++)
            VertexPairs.add(new Pair(Distance[i],i));
        int i = 0;
        Q.Insert(VertexPairs.get(i));

        // The algorithm
        int MST=0;
        while(!Q.isEmpty()) {
            Pair minVertexPair=Q.extractMin();
            for(int i=0;i<AdjacencyGraph.length;i++){
                if(AdjacencyGraph[minVertexPair.index][i]==1 && AdjacencyGraph[minVertexPair.index][i]<Distance[i])
                {
                        Distance[i] = AdjacencyGraph[minVertexPair.index][i];
                        Predecessor[i]=minVertexPair.index;
                        int pos = Q.getPosition(VertexPairs.get(i));
                        VertexPairs.get(i).distance=AdjacencyGraph[minVertexPair.index][i];
                        Q.decreasekey(pos);

                }
            }
            MST+=Distance[minVertexPair.index];
        }
        System.out.println("Minimum spanning tree distance: " +MST);
            for(i;i<AdjacencyGraph.length;i++)
            {
                System.out.println(" parent "+ Predecessor[i] + " to " + i +" EdgeWeight: "+ Distance[i]);
            }
    }
    public  void PrintGraph(){
        for (Vertex vertex : vertices) {
            System.out.println(" From: " + vertex.name);
            for (int j = 0; j < vertex.OutEdges.size(); j++) {
                Edge currentEdge = vertex.OutEdges.get(j);
                System.out.println(" To: " + currentEdge.to.name + " km: " + currentEdge.weight);
            }
            System.out.println(" ");
        }
    }
}

class Vertex implements Comparable<Vertex>{
    String name;
    ArrayList<Edge> OutEdges;
    Integer dist= Integer.MAX_VALUE;
    public Vertex(String id){
        name=id;
        OutEdges=new ArrayList<Edge>();
    }
    public void addOutEdge(Edge e) {
        OutEdges.add(e);
    }

    @Override
    public int compareTo(Vertex o) {
        return this.dist.compareTo(o.dist);
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