package ch.heigvd.Program1;
import java.lang.Math;
import java.util.Arrays;

public class Program1 {
    private static final char ZERO_ASCII = '0', MINUS_ASCII = '-', PLUS_ASCII = '+';
    public static void main(String[] args){
        System.out.println("Input arguments : " + Arrays.toString(args));
        int[] inputArray = new int[args.length];
        try{
            inputArray = getArgumentsToInt(args);
        }
        catch(RuntimeException e){
            e.printStackTrace();
            System.err.println("The program will exit.");
            System.exit(-1);
        }
        bubbleSort(inputArray);
        System.out.println("Sorted arguments : " + Arrays.toString(inputArray));
    }

    private static int[] getArgumentsToInt(String[] str) throws RuntimeException {
        if(str == null)
            throw new RuntimeException("The given array is null");
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
            //handles the negative sign of the number
            numbers[i] = firstChar == MINUS_ASCII ? -numbers[i] : numbers[i];
        }
        return numbers;
    }

    static private int charToInt(char c)throws RuntimeException{
        if(c >= ZERO_ASCII && c <= ZERO_ASCII + 9)
            return c - ZERO_ASCII;
        else throw new RuntimeException(
                "Cannot convert '" + c + "' to an integer value");
    }

    private static void bubbleSort(int[] data){
        if (data != null) {
            boolean finished = false;
            int size = data.length;
            while (!finished) {
                finished = true;
                for (int i = 0; i < size - 1; ++i) {
                    if (data[i] > data[i + 1]) {
                        swap(data, i, i + 1);
                        finished = false;
                    }
                }
                --size;
            }
        }
    }

    private static void swap(int[] data, int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }
}

