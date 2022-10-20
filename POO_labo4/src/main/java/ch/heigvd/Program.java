package ch.heigvd;
import java.lang.Math;

public class Program {
    public static void main(String[] args) {
        String[] test = new String[]{"42", "-55", "+14567"};
        int[] shuffledArray = new int[test.length];
        shuffledArray = getArgumentsToInt(test);
    }

    private static int[] getArgumentsToInt(String[] args) throws RuntimeException {
        boolean isNegative = false;
        int[] numbers = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            int number = 0;
            for(int j = args[i].length() - 1 , k = 0; j >= 0; j--, k++){
                if(args[i].charAt(k) == '-' || args[i].charAt(k) == '+'){
                    k++;
                    j--;
                    isNegative = args[i].charAt(0) == '-';
                }
                number += ((args[i].charAt(k) - 48) * Math.pow(10, j));
            }
            numbers[i] = isNegative? -number : number;
        }
        return numbers;
    }
}