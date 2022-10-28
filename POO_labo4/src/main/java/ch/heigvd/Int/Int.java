package ch.heigvd.Int;

public class Int{
    //Attribut
    private int value;

    //Method
    /***
     * Constructor without parameter who set value to 0
     */
    public Int(){
        this.value = 0;
    }

    /***
     * Constructor with parameter who set the value used in parameter
     * @param value    Integer value used to set at the creation
     */
    public Int(int value){
        this.value = value;
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
     * @param value    Integer value used to set the new value
     */
    public void setValue(int value){
        this.value = value;
    }

    /***
     * Method to set the new value of this object
     * @param value    Double value used to set the new value
     */
    public void setValue(double value){
        this.value = (int)value;
    }

    /***
     * Method to convert the value to string
     * @return  the value as a string
     */
    @Override
    public String toString(){
        return "" + value;
    }

    public void swap(Int other) throws RuntimeException{
        if(other == null)
            throw new RuntimeException("The given Int is null");
        int tmp = other.getValue();
        other.setValue(this.value);
        this.value = tmp;
    }
}
