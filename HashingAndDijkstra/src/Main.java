import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ClosedHash hash = new ClosedHash(293);
        hash.loadWordsFromFile("C:\\Users\\green\\IdeaProjects\\HashingAndDijkstra\\src\\EdgarAllanPoeBellsB2022groomed.txt");
        hash.printHash();
        System.out.println("A)How many non-empty addresses are there in the table? What does that make\n" + "the load factor, α, for our table?");
        System.out.println("There are currently " + hash.occupancy + " spots occupied, meaning there are " + (hash.hashSize - hash.occupancy) + "spots empty.\n");

        System.out.println("B)What is the longest empty area in the table, and where is it?");
        int nullStarts = hash.longestNulls();
        System.out.println("The longest stretch of empty cells is " + hash.nullLength + " cells long, starting at " + (nullStarts - hash.nullLength +1) + " and ending at " + nullStarts + ".\n");

        System.out.println("What is the longest (largest) cluster in the table, and where is it?\n" +
                "Note: It might wrap from the end of the table back to the beginning.");
        int unbroken = hash.unbrokenStretch();
        System.out.println("The longest stretch of full cells is " + hash.bigChunk + " cells long, starting at " + (unbroken - hash.bigChunk +1) + " and ending at " + unbroken + ".\n");

        System.out.println("What hash value results from the greatest number of distinct words, and how\n" +
                "many words have that hash value?");
        System.out.println("The most common hashvalue is " + hash.mostCommonHash() + " with " + hash.commonHash + " words having that value.\n");

        System.out.println("What word is placed in the table farthest from its actual hash value, and\n" +
                "how far away is it from its actual hash value?");
        int displacement =hash.mostDisplaced();
        System.out.println("The word placed furthest from its hash value is \"" + hash.displacedWord + "\" which is " + displacement + " away from its predicted spot.\n");

//        System.out.println(Arrays.toString(hash.table));

        Dijkstra search = new Dijkstra("C:\\Users\\green\\IdeaProjects\\HashingAndDijkstra\\src\\DijkstraMatrix.txt");
        Scanner scanner= new Scanner(System.in);
        System.out.println("Starting point for Dijkstra search:\n");
        char start = scanner.next().charAt(0);
        System.out.println("Ending point for Dijkstra search:\n");
        char end = scanner.next().charAt(0);
        search.findShortestPath(start,end);

        System.out.println("A fun easter egg is that the optimal path from D to A spells DIJKSTRA with the order of the nodes visited");

    }
}
