import java.io.FileNotFoundException;

public class ClosedHash extends HashTable {

    public String[] table;
    public int commonHash = 0;
    int occupancy = 0;
    int nullLength = 0;
    int bigChunk = 0;
    String displacedWord = "";
    private int[] frequency;

    public ClosedHash(int tableSize) {
        hashSize = tableSize;
        table = new String[hashSize];
        frequency = new int[hashSize];
    }

    public boolean loadWordsFromFile(String filePath) throws FileNotFoundException {
        try {
            setFilePath(filePath);
            openFile();
        } catch (Error FileNotFoundException) {
            System.out.println("File not found");
            return false;
        }
//        if (file != null) {
//            System.out.println("found a file");
//        }
        while (reader.hasNext()) {
            add(getNextWord());
        }
        return true;
    }

    public void add(String word) {
        if (occupancy == hashSize) {
            System.out.println("Table is full");
            return;
        }
        int code = hash(word);
        if (code == -1) return;
        while (table[code] != null) {
            if (word.equalsIgnoreCase(table[code])) {
                return;
            }
            code++;
            code %= hashSize;
        }
        table[code] = word;
        occupancy++;
    }

//    public int find(String word) {
//        int code = hash(word);
//        float loadFactor = 1 + 1 / (1 - calcLoadFactor());
//        int probeNum = (int) ((1 / 2) * loadFactor) + 1;
//        while (!table[code].equalsIgnoreCase(word) && probeNum != 0) {
//            if (word.equalsIgnoreCase(table[code])) {
//                return code;
//            }
//            probeNum--;
//            code++;
//            code %= hashSize;
//        }
//        return -1;
//    }

    public void printHash() {
        System.out.printf("%-15s%-15s%-15s\n", "Hash Address", "Word", "Hash Code");
        System.out.println("________________________________________");
        for (int i = 0; i < hashSize; i++) {
            if (table[i] != null) System.out.printf("%-15d%-15s%-15d\n", i, table[i], hash(table[i]));
        }
    }


    public int unbrokenStretch() {
        int temp = 0;
        int end = 0;
        for (int i = 0; i < hashSize; i++) {
            if (table[i] != null) {
                temp++;
            } else {
                temp = 0;
            }
            if (temp > bigChunk) {
                bigChunk = temp;
                end = i;
            }
        }
        return end;
    }

    public int longestNulls() {
        int temp = 0;
        int end = 0;
        for (int i = 0; i < hashSize; i++) {
            if (table[i] == null) {
                temp++;
            } else {
                temp = 0;
            }
            if (temp > nullLength) {
                nullLength = temp;
                end = i;
            }
        }
        return end;
    }

    public int mostCommonHash() {
        for (int i = 0; i < hashSize; i++) {
            if (hash(table[i]) != -1)
                frequency[hash(table[i])]++;
        }
        int max = 0;
        for (int i = 0; i < hashSize; i++)
            if (frequency[i] > commonHash) {
                commonHash = frequency[i];
                max = i;
            }
        return max;
    }

    public int mostDisplaced() {
        int displacement = 0;
        for (int i = 0; i < hashSize; i++) {
            if (table[i] != null)
                if (i - hash(table[i]) > displacement) {
                    displacedWord = table[i];
                    displacement = i - hash(table[i]);
                }
        }
        return displacement;
    }

    @Override
    float calcLoadFactor() {
        return occupancy / (float) hashSize;
    }
}
