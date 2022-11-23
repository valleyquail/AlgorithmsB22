import java.util.Locale;

public class Palindrome {


    public void checkPalindrome(String sentence) {
        String phraseToCheck = sentence.toLowerCase(Locale.ROOT);
        boolean result = compare(phraseToCheck);
        printResult(sentence, result);
    }

    private boolean compare(String phrase) {
        int rightIndex = phrase.length() - 1;
        int leftIndex = 0;
        while (!Character.isAlphabetic((phrase.charAt(rightIndex)))) {
            rightIndex -= 1;
        }
        while (!Character.isAlphabetic((phrase.charAt(leftIndex)))) {
            leftIndex += 1;
        }
        if (phrase.charAt(rightIndex) == phrase.charAt(leftIndex)) {
            if (leftIndex + 1 >= rightIndex) {
                return true;
            }
            return compare(phrase.substring(leftIndex + 1, rightIndex));
        }
        return false;
    }

    private void printResult(String sentence, boolean result) {
        if (result) {
            System.out.println("The statement: \"" + sentence + "\" is a palindrome!");
        } else {
            System.out.println("The statement: \"" + sentence + "\" is a not palindrome.");
        }
    }
}

