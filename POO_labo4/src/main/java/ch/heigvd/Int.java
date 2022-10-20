package ch.heigvd;

public class Int {
    //Attribut
    private int value;


    //Method
    /***
     * Constructor without parameter who set value to 0
     */
    public Int(){
        value = 0;
    }

    /***
     * Constructor with parameter who set the value used in parameter
     * @param aValue    Integer value used to set at the creation
     */
    public Int(int aValue){
        value = aValue;
    }

    /***
     * Method to get the Integer value in this object
     * @return an integer
     */
    public int getValue(){
        return value;
    }

    /***
     * Method to set the new value of this object
     * @param aValue    Integer value used to set the new value
     */
    public void setValue(int aValue){
        value = aValue;
    }

    /***
     * Method to convert the value to string
     * @return  the value as a string
     */
    public String toString(){
        return "" + value;
    }
}
