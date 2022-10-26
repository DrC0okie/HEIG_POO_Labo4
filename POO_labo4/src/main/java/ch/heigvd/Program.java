package ch.heigvd;
import java.lang.Math;
import java.util.Arrays;
import java.util.Random;

public class Program {
    public static void main(String[] args) {
        String[] test = new String[]{"42", "-55", "+14567"};
        int[] shuffledArray = new int[test.length];
        shuffledArray = getArgumentsToInt(test);

        int[] array = new int[]{2, 4, 6, 8, 7, 5, 3, 1};
        quickSort(array, 0, array.length);
        System.out.println(Arrays.toString(array));

    }

    private static int[] getArgumentsToInt(String[] args) throws RuntimeException {
        boolean isNegative = false;
        int[] numbers = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            int number = 0;
            for (int j = args[i].length() - 1, k = 0; j >= 0; j--, k++) {
                if (args[i].charAt(k) == '-' || args[i].charAt(k) == '+') {
                    k++;
                    j--;
                    isNegative = args[i].charAt(0) == '-';
                }
                number += ((args[i].charAt(k) - 48) * Math.pow(10, j));
            }
            numbers[i] = isNegative ? -number : number;
        }
        return numbers;
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