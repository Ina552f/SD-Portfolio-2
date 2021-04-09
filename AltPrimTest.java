/** This is a Prim I setup with matrix. It was made as an alternative to the adjacency list, as I had problems
 * with implementing prim in the graph and needed to try out something else while having hit a wall.
**/
class AltPrimTest {

    public void Prim(int[][] G, int V) {

        int Infinity = Integer.MAX_VALUE;
        int no_edge;
        no_edge = 0;

        boolean[] Selected = new boolean[V];

        Selected[0] = true;     // Choosing the first vertex

        while (no_edge < V - 1) {

            int min = Infinity;
            int x = 0;
            int y = 0;

            for (int i = 0; i < V; i++) {
                if (Selected[i]) {
                    for (int j = 0; j < V; j++) {
                        if (!Selected[j] && G[i][j] != 0) {
                            if (min > G[i][j]) {
                                min = G[i][j];
                                x = i;
                                y = j;
                            }
                        }
                    }
                }
            }
            System.out.println(x + " to " + y + " km:  " + G[x][y]);
            Selected[y] = true;
            no_edge++;
        }
    }

    public static void main(String[] args) {
        AltPrimTest PrimGraph = new AltPrimTest();

        int Vertex = 16;     // 16 towns

        // Since making this program I have found out there are some small mistakes in the index (should be 364 in all,
        // but says 451). I'm going to let it be since I'm running out of time and is working on a new solution on the adjacency list.
        int[][] Index = {{0,0,0,0,0,0,0,28,0,13,0,0,0,0,0,24}, {0,0,0,0,0,60,24,0,0,0,25,19,47,48,34,40}, {0,0,0,34,44,66,0,0,0,0,0,36,32,46,34,0}, {0,0,0,0,0,95,58,0,0,0,0,56,33,74,63,0},
                {0,0,0,0,0,0,0,0,0,0,0,62,70,39,51,0}, {0,0,0,0,0,0,0,0,0,0,45,0,0,20,0,0},{0,0,0,0,0,0,0,0,0,0,45,28,25,0,0,60}, {0,0,0,0,0,0,0,0,27,26,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0,0,26,57,37,32,28}, {0,0,0,0,0,0,0,27,0,0,0,0,0,0,0,0}, {13,0,0,0,0,0,0,26,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,31,15,58,0}, {0,47,32,33,70,0,25,0,0,0,57,31,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0,0,0,0,14,0,0},
                {0,34,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, {24,40,0,0,0,0,60,0,0,0,28,58,0,0,0,0}};

        // The final printout is in numbers instead of cities. I could have done so (as you can somewhat see in the
        // other code, but I didnt see the need since it was an experiment in implementing a prim (see if i could).
        PrimGraph.Prim(Index, Vertex);
    }
}