import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        Palindrome palindrome = new Palindrome();
        Inversion inversions = new Inversion();
        GrayCode grays = new GrayCode();

        int[] array1 = {3, 2, 1};
        int[] array2 = {8, 7, 6, 5, 4, 3, 2, 1};

        palindrome.checkPalindrome("abacaba");
        palindrome.checkPalindrome("never odd or even");
        palindrome.checkPalindrome("Able was I ere I saw Elba.");
        palindrome.checkPalindrome("A man, a plan, a canal: Panama!");
        System.out.println();

        System.out.println("The number of inversions in the array " + Arrays.toString(array1) +
                " using the easyinversioncount() method is: " + inversions.easyinversioncount(array1));
        System.out.println("The number of inversions in the array " + Arrays.toString(array1) +
                " using the fastinversioncount() method is: " + inversions.fastinversioncount(array1));
        System.out.println();
        System.out.println("The number of inversions in the array " + Arrays.toString(array2) +
                " using the easyinversioncount() method is: " + inversions.easyinversioncount(array2));
        System.out.println("The number of inversions in the array " + Arrays.toString(array2) +
                " using the fastinversioncount() method is: " + inversions.fastinversioncount(array2));


        System.out.println();
        System.out.println("The Gray Codes of order 4 are: ");
        grays.printGrayCodes(4);
        System.out.println();
        grays.printTable();


        System.out.println("The Gray Codes of order 6 are (formatting gets funky): ");
        grays.printGrayCodes(6);
        System.out.println();
        grays.printTable();
    }
}
