package ch.heigvd.Program2;
import ch.heigvd.Int.Int;

import java.lang.Math;
import java.util.Arrays;

public class Program2 {
    private static final char ZERO_ASCII = '0', MINUS_ASCII = '-', PLUS_ASCII = '+';
    public static void main(String[] args){
        System.out.println("Input arguments : " + Arrays.toString(args));
        Int[] inputArray = new Int[args.length];
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

    private static Int[] getArgumentsToInt(String[] str) throws RuntimeException {
        if(str == null)
            throw new RuntimeException("The given array is null");
        Int[] numbers = new Int[str.length];

        //Initialization of the Int array
        for (int i = 0; i < str.length; i++){
            numbers[i] = new Int();
        }
        for (int i = 0; i < str.length; i++) {
            String currentString = str[i];
            char firstChar = currentString.charAt(0);

            // Remove the first char if '-' or '+'
            if (firstChar == MINUS_ASCII || firstChar == PLUS_ASCII)
                currentString = currentString.substring(1);

            for (int j = currentString.length() - 1, k = 0; j >= 0; j--, k++) {
                char currentChar = currentString.charAt(k);
                //Convert the char to int value
                numbers[i].setValue(numbers[i].getValue()
                        + charToInt(currentChar) * Math.pow(10, j));
            }
            //handles the negative sign of the number
            numbers[i].setValue(firstChar == MINUS_ASCII ?
                    -numbers[i].getValue() : numbers[i].getValue());
        }
        return numbers;
    }

    static private int charToInt(char c)throws RuntimeException{
        if(c >= ZERO_ASCII && c <= ZERO_ASCII + 9)
            return c - ZERO_ASCII;
        else throw new RuntimeException(
                "Cannot convert '" + c + "' to an integer value");
    }

    private static void bubbleSort(Int[] data){
        if (data != null) {
            boolean finished = false;
            int size = data.length;
            while (!finished) {
                finished = true;
                for (int i = 0; i < size - 1; ++i) {
                    if (data[i].getValue() > data[i + 1].getValue()) {
                        swap(data, i, i + 1);
                        finished = false;
                    }
                }
                --size;
            }
        }
    }

    private static void swap(Int[] data, int a, int b) {
        int temp = data[a].getValue();
        data[a].setValue(data[b].getValue());
        data[b].setValue(temp);
    }
}

