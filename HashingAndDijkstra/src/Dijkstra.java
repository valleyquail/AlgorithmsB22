import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


//implements an algorithm from here: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#Using_a_priority_queue
public class Dijkstra {

        int[][] matrix;
    int size;
    PriorityQueue<Node> queue;
    private int[] distance;
    private ArrayList<Character> sequence = new ArrayList<>();
    private int[] previous;

    public Dijkstra(String file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(file));
        size = scanner.nextInt();
        matrix = new int[size][size];
        while (scanner.hasNextInt()) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }
        }
        distance = new int[size];
        previous = new int[size];
        for (int i = 0; i < size; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        queue = new PriorityQueue<>(size, new Node(0, 0));
           }


    public void findShortestPath(char startPoint, char endPoint) {
        int start = encode(startPoint);
        int end = encode(endPoint);
        queue.add(new Node(start, 0));
        distance[start] = 0;
        System.out.println("The graph to be analysized is as follows:");
        for (int i = 0; i < size; i++){
            for( int j = 0 ; j < size; j++){
                System.out.printf("%-5d",matrix[i][j]);
            }
            System.out.println();
        }
             int current = start;
        while (!queue.isEmpty() && current != end) {
            current = queue.remove().node;
            for (int i = 0; i < size; i++) {
                if (matrix[current][i] == 0) continue;
                int altPath = matrix[current][i] + distance[current];
                if (altPath < distance[i]) {
                    distance[i] = altPath;
                    previous[i] = current;
                    Node insert = new Node(i, distance[i]);
                    if (!queue.contains(insert)) queue.add(insert);
                }
            }
        }
//        System.out.println(Arrays.toString(distance));
//        System.out.println(Arrays.toString(previous));
        getPathAndDistance(start, end);
    }


    private void getPathAndDistance(int start, int end) {
        char startChar = decode(start);
        char endChar = decode(end);
        if(start == -1 || end == -1){
            System.out.println("One of your points is not valid");
            return;
        }
        int dist = distance[end];
        sequence.add(endChar);
        while (previous[end] != 0) {
            sequence.add(decode(previous[end]));
            end = previous[end];
        }
        sequence.add(startChar);
        Collections.reverse(sequence);

        System.out.println("The shortest path between " + startChar + " and " + endChar + " is: " + dist);
        System.out.println("The path taken by the algorithm to get this distance is:\n" + sequence.toString());
    }


    private int encode(char vertex) {
        vertex = Character.toUpperCase(vertex);
        return switch (vertex) {
            case 'A' -> 0;
            case 'J' -> 1;
            case 'M' -> 2;
            case 'R' -> 3;
            case 'K' -> 4;
            case 'S' -> 5;
            case 'I' -> 6;
            case 'N' -> 7;
            case 'T' -> 8;
            case 'D' -> 9;
            default -> -1;
        };
    }

    private char decode(int vertex) {
        return switch (vertex) {
            case 0 -> 'A';
            case 1 -> 'J';
            case 2 -> 'M';
            case 3 -> 'R';
            case 4 -> 'K';
            case 5 -> 'S';
            case 6 -> 'I';
            case 7 -> 'N';
            case 8 -> 'T';
            case 9 -> 'D';
            default -> ' ';
        };
    }


    /*or every vertex in V
dv ←∞; pv ← null
Insert(Q,v,dv ) //initialize vertex priority in Q
ds ←0; Decrease(Q,s,ds) //update priority of s with ds
VT ←∅ //initialize set of completed vertices to empty
for i ←0 to |V |− 1 do
u∗← DeleteMin(Q) //delete the minimum priority vertex
VT ←VT ∪{u∗}//add next vertex to completed set
for every vertex u in V − VT adjacent to u∗do
if du∗+ w(u∗,u) < du
du ←du∗+ w(u∗,u) ; pu ←u∗//update distances
Decrease(Q,u,du)
*/


   static class Node implements Comparator<Node> {
        public int node;
        public int weight;

        public Node(int n, int w) {
            node = n;
            weight = w;
        }

        @Override
        public int compare(Node o1, Node o2) {
            return Integer.compare(o1.weight, o2.weight);
        }
    }

}
