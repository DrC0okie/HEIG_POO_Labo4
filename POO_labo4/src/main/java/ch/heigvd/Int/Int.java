/*−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−------------------------------------------------
File name       : Int.java
Author(s)       : Kévin Farine, Timothée Van Hove
Date création   : 20.10.2022
Description     : Wrapper class who provide a way to use primitive integer as object
JDK             : OpenJDK Runtime Environment Temurin-17.0.5+8 (build 17.0.5+8)
−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−------------------------------------------------*/
package ch.heigvd.Int;

public class Int{
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
