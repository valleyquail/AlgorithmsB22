import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

abstract class HashTable {

    protected int hashSize = 1;
    protected File file;
    protected Scanner reader;

    abstract float calcLoadFactor();

    protected void setFilePath(String filePath) {
        file = new File(filePath);
    }

    protected void openFile() throws FileNotFoundException {
        reader = new Scanner(file);
    }

    protected String getNextWord() {
        if (reader.hasNext()) {
            String word = reader.next();
            while (word.charAt(0) == '\n') {
                reader.nextLine();
                word = reader.next();
            }
            word = word.replaceAll("[^\\x00-\\x7F]", "").replaceAll("[\\p{Punct}&&[^'-]]+", "");
            if(word.equals("")) return null;
            return word;
        }
        return null;
    }

    public int hash(String word) {
        if (word == null) {
            return -1;
        }
        int hashcode = 0;
//        word.toLowerCase();
//        h ← 0; for i ← 0 to s − 1 do h ← (h ∗ C + ord(ci)) mod m,
        for (int i = 0; i < word.length(); i++) {
            int hashConstant = 123;
            hashcode = (hashcode * hashConstant + (int) word.charAt(i)) % hashSize;
        }
        return hashcode;
    }


}
