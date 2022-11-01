/*−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−------------------------------------------------
File name       : Program2.java
Author(s)       : Kévin Farine, Timothée Van Hove
Created         : 20 oct. 2022
Description     : Program that interprets the given command line arguments as
                  numbers, store them in 3 separate arrays, then sorts each array
                  with a bubbleSort algorithm that implements 3 different swapping
                  methods.
Remark(s)       : This program automatically closes after displaying the result.
                  This program uses a custom wrapping Int class to handle the
                  sorting algorithm
JDK             : OpenJDK Runtime Environment Temurin-17.0.5+8 (build 17.0.5+8)
−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−------------------------------------------------*/

package ch.heigvd.Program2;

import java.lang.Math;
import java.util.*;

public class Program2 {
    private static final char ZERO_ASCII = '0', MINUS_ASCII = '-', PLUS_ASCII = '+';

    public static void main(String[] args) {
        int length = args.length;
        //Display the raw command line arguments
        System.out.println("Input arguments as string : " + Arrays.toString(args));
        Int[] inputArray = new Int[length];
        try {
            //Parse the String array to retrieve the arguments as Int
            inputArray = getArgumentsToInt(args);
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.err.println("The program will exit.");
            System.exit(-1);
        }

        // Create a list of 3 slightly different arrays based on the input array
        List<Int[]> arrays = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            Int[] tmp = new Int[length];
            for (int j = 0; j < length; ++j) {
                //Add i to the value to differentiate each array from the others
                tmp[j] = new Int(inputArray[j].getValue() + (i * j));
            }

            arrays.add(tmp);
            System.out.println("Unsorted array n° " + (i + 1) + " : "
                    + Arrays.toString(tmp));
        }

        //Sort the 3 arrays
        bubbleSortV1(arrays.get(0));
        bubbleSortV2(arrays.get(1));
        bubbleSortV3(arrays.get(2));

        for (int i = 0; i < 3; ++i) {
            //Print the sorted arrays
            System.out.println("Sorted array n° " + (i + 1) + " : "
                    + Arrays.toString(arrays.get(i)));
        }
    }

    /**
     * Parses each String of an array as signed decimal integers
     * The characters in the string must be decimal digits or preceded
     * by a '+' or '-' sign.
     *
     * @param str The String array to be parsed
     * @return An Int array corresponding to the given string array
     * @throws RuntimeException if at least one char in the string is not a decimal
     *                          digits, or if the given array is null
     */
    private static Int[] getArgumentsToInt(String[] str) throws RuntimeException {
        if (str == null)
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
                numbers[i].setValue((int) (numbers[i].getValue()
                        + charToInt(currentChar) * Math.pow(10, j)));
            }
            //handle the negative sign of the number
            numbers[i].setValue(firstChar == MINUS_ASCII ?
                    -numbers[i].getValue() : numbers[i].getValue());
        }
        return numbers;
    }

    /**
     * Converts decimal digit characters to integer numbers
     *
     * @param c the character to be converted
     * @return the integer value corresponding to the given character
     * @throws RuntimeException When the given character is not a decimal digit
     */
    static private int charToInt(char c) throws RuntimeException {
        if (c >= ZERO_ASCII && c <= ZERO_ASCII + 9)
            return c - ZERO_ASCII;
        else throw new RuntimeException(
                "Cannot convert '" + c + "' to an integer value");
    }

    /**
     * BubbleSort algorithm using a value swapping method
     *
     * @param data The array of Int to be sorted
     */
    private static void bubbleSortV1(Int[] data) {
        if (data != null) {
            boolean finished = false;
            int size = data.length;
            while (!finished) {
                finished = true;
                for (int i = 0; i < size - 1; ++i) {
                    if (data[i].getValue() > data[i + 1].getValue()) {
                        swapValues(data, i, i + 1);
                        finished = false;
                    }
                }
                --size;
            }
        }
    }

    /**
     * BubbleSort algorithm using an object swapping method
     *
     * @param data The array of Int to be sorted
     */
    private static void bubbleSortV2(Int[] data) {
        if (data != null) {
            boolean finished = false;
            int size = data.length;
            while (!finished) {
                finished = true;
                for (int i = 0; i < size - 1; ++i) {
                    if (data[i].getValue() > data[i + 1].getValue()) {
                        swapObjects(data, i, i + 1);
                        finished = false;
                    }
                }
                --size;
            }
        }
    }

    /**
     * BubbleSort algorithm the internal Int.swap method
     *
     * @param data The array of Int to be sorted
     */
    private static void bubbleSortV3(Int[] data) {
        if (data != null) {
            boolean finished = false;
            int size = data.length;
            while (!finished) {
                finished = true;
                for (int i = 0; i < size - 1; ++i) {
                    if (data[i].getValue() > data[i + 1].getValue()) {
                        data[i].swap(data[i + 1]);
                        finished = false;
                    }
                }
                --size;
            }
        }
    }

    /**
     * Swaps the value of 2 objects of the given array
     *
     * @param data   the array in which to swap the values
     * @param index1 The location of the first element's value to be swapped
     * @param index2 The location of the second element's value to be swapped
     */
    private static void swapValues(Int[] data, int index1, int index2) {
        int temp = data[index1].getValue();
        data[index1].setValue(data[index2].getValue());
        data[index2].setValue(temp);
    }

    /**
     * Swaps the reference of 2 objects of the array
     *
     * @param data   the array in which to swap the objects
     * @param index1 The location of the first object to be swapped
     * @param index2 The location of the second object to be swapped
     */
    private static void swapObjects(Int[] data, int index1, int index2) {
        Int temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }
}

class Int{
    // Internal value of the wrapper class
    private int value;

    /** Constructor without parameter who set value to 0 */
    public Int(){
        this.value = 0;
    }

    /** Constructor with parameter who set the value used in parameter
     * @param value Integer value used to set at the creation */
    public Int(int value){
        this.value = value;
    }

    /** Method to get the Integer value in this object
     * @return an integer */
    public int getValue(){
        return value;
    }

    /** Method to set the new value of this object
     * @param value Integer value used to set the new value */
    public void setValue(int value){
        this.value = value;
    }

    /** Method to convert the value to string
     * @return  the value as a string */
    @Override
    public String toString(){
        return "" + value;
    }

    /** Swaps the value of the given object with the value of this object
     * @param other the other instance of this class to swap the value with */
    public void swap(Int other){
        int tmp = other.getValue();
        other.setValue(this.value);
        this.value = tmp;
    }
}