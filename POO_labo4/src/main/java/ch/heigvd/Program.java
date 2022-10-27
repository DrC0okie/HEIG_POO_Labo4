package ch.heigvd;
import java.lang.Math;
import java.util.Arrays;
import java.util.Random;

public class Program {
    private static final char ZERO_ASCII = '0', MINUS_ASCII = '-', PLUS_ASCII = '+';
    public static void main(String[] args) {
        String[] test = new String[]{"42", "-55", "+14567"};
        int[] shuffledArray = new int[test.length];
        shuffledArray = getArgumentsToInt(test);
        quickSort(shuffledArray, 0, shuffledArray.length);
        System.out.println(Arrays.toString(shuffledArray));

    }

    private static int[] getArgumentsToInt(String[] str) throws RuntimeException {
        int[] numbers = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            String currentString = str[i];
            char firstChar = currentString.charAt(0);

            // Remove the first char if '-' or '+'
            if (firstChar == MINUS_ASCII || firstChar == PLUS_ASCII)
                currentString = currentString.substring(1);

            for (int j = currentString.length() - 1, k = 0; j >= 0; j--, k++) {
                char currentChar = currentString.charAt(k);
                //Convert the char to int value
                numbers[i] += (charToInt(currentChar) * Math.pow(10, j));
            }
            numbers[i] = firstChar == MINUS_ASCII ? -numbers[i] : numbers[i];
        }
        return numbers;
    }

    static private int charToInt(char c)throws RuntimeException{
        if(c > ZERO_ASCII && c < ZERO_ASCII + 9)
            return c - ZERO_ASCII;
        else throw new RuntimeException(
                "Cannot convert " + c + " to an integer value");
    }

    private static final Random generator = new Random();

    private static void swap(int[] data, int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    private static int partition(int[] data, int first,
                                 int last) {
        int i = first - 1, j = last;
        do {
            do {
                ++i;
            } while (data[i] < data[last - 1]);

            do {
                --j;
            } while (j > first && data[last - 1] < data[j]);

            if (i >= j)
                break;
            swap(data, i, j);
        } while (true);
        swap(data, i, last - 1);
        return i;
    }

    public static void quickSort(int[] data, int first, int last) {
        while (first < last) {
            //Random pivot selection
            int pivot = first + getRandom(0, distance(first, last));
            swap(data, last - 1, pivot);

            //Pivot index of the partitioned array
            int i = partition(data, first, last);

            //Tail recursion optimization:
            // If left part is smaller, recursion for left, iteration for right
            if (distance(i, first) < distance(last, i)) {
                quickSort(data, first, i);
                first = i + 1;
            } else {
                quickSort(data, i + 1, last);
                last = i;
            }
        }
    }

    private static int distance(int a, int b) {
        return Math.abs(a - b);
    }

    private static int getRandom(int lower, int upper) {
        return generator.nextInt(lower, upper);
    }
}