/*−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−------------------------------------------------
File name       : Program2.java
Author(s)       : Kévin Farine, Timothée Van Hove
Created         : 20 oct. 2022
Description     : Program that sorts the given command line arguments as numbers
Remark(s)       : This program automatically closes after displaying the result.
                  This program uses a custom wrapping Int class to handle the
                  sorting algorithm
JDK             : OpenJDK Runtime Environment Temurin-17.0.5+8 (build 17.0.5+8)
−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−------------------------------------------------*/

package ch.heigvd.Program2;

import ch.heigvd.Int.Int;
import org.jetbrains.annotations.NotNull;
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

    /** Parses each String of an array as signed decimal integers
     * The characters in the string must be decimal digits or preceded
     * by a '+' or '-' sign.
     * @param str The String array to be parsed
     * @return An Int array corresponding to the given string array
     * @throws RuntimeException if at least one char in the string is not a decimal
     * digits, or if the given array is null */
    private static Int[] getArgumentsToInt(String[] str) throws RuntimeException {
        if(str == null)
            throw new RuntimeException("The given array is null");

        //Initialization of the Int array
        Int[] numbers = new Int[str.length];
        for (int i = 0; i < str.length; i++)
            numbers[i] = new Int();

        //Parse each string of the array
        for (int i = 0; i < str.length; i++) {
            String currentString = str[i];
            char firstChar = currentString.charAt(0);

            //Remove the first char if '-' or '+'
            if (firstChar == MINUS_ASCII || firstChar == PLUS_ASCII)
                currentString = currentString.substring(1);

            //Parse each char of the string
            for (int j = currentString.length() - 1, k = 0; j >= 0; j--, k++) {
                char currentChar = currentString.charAt(k);

                //Set the Int object int the array with the corresponding char value
                numbers[i].setValue((int)(numbers[i].getValue()
                        + charToInt(currentChar) * Math.pow(10, j)));
            }
            //handle the negative sign of the number
            numbers[i].setValue(firstChar == MINUS_ASCII ?
                    -numbers[i].getValue() : numbers[i].getValue());
        }
        return numbers;
    }

    /** Converts decimal digit characters to integer numbers
     * @param c the character to be converted
     * @return the integer value corresponding to the given character
     * @throws RuntimeException When the given character is not a decimal digit */
    static private int charToInt(char c)throws RuntimeException{
        if(c >= ZERO_ASCII && c <= ZERO_ASCII + 9)
            return c - ZERO_ASCII;
        else throw new RuntimeException(
                "Cannot convert '" + c + "' to an integer value");
    }

    /** BubbleSort algorithm using 3 different swapping methods
     * @param data The array of Int to be sorted*/
    private static void bubbleSort(Int[] data){
        if (data != null) {
            boolean finished = false;
            int size = data.length;
            while (!finished) {
                finished = true;
                for (int i = 0; i < size - 1; ++i) {
                    if (data[i].getValue() > data[i + 1].getValue()) {
                        //Do 3 swaps to prove that the 3 methods are working
                        swapValues(data, i, i + 1);
                        swapObjects(data, i, i + 1);
                        data[i].swap(data[i + 1]);
                        finished = false;
                    }
                }
                --size;
            }
        }
    }

    /** Swaps the value of 2 objects of the given array
     * @param data the array in which to swap the values
     * @param index1 The location of the first element's value to be swapped
     * @param index2 The location of the second element's value to be swapped */
    private static void swapValues(Int @NotNull [] data, int index1, int index2) {
        int temp = data[index1].getValue();
        data[index1].setValue(data[index2].getValue());
        data[index2].setValue(temp);
    }

    /** Swaps the reference of 2 objects of the array
     * @param data the array in which to swap the objects
     * @param index1 The location of the first object to be swapped
     * @param index2 The location of the second object to be swapped */
    private static void swapObjects(Int @NotNull [] data, int index1, int index2) {
        Int temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }
}
