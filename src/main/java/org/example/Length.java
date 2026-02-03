package org.example;

public class Length {
    //Instant variable
    private double value;
    private LengthUnit unit;

    //Enumn represent different length unit and their conversion factor
    //With the base unit being inches. This means all the conversion factor define in the terms of inches.
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);
        private final double conversionFactor;

        LengthUnit(double conversionFactor){
            this.conversionFactor = conversionFactor;
        }
        public double getConversionFactor(){
            return conversionFactor;
        }

    }

    //Constructor initialise length value and unit;
    public Length(double value, LengthUnit unit){
        this.value = value;
        this.unit = unit;
    }

    //Convert the length value to base unit
    private double convertToBaseUnit(){
        double baseValue= this.value * this.unit.getConversionFactor();
        return Double.parseDouble(String.format("%.2f",baseValue));
    }
    //convert two length object for equality base on their values in the base unit
    public Boolean compare(Length thatLength){
        if (thatLength == null) return false;
        return this.convertToBaseUnit() == thatLength.convertToBaseUnit();

    }
//Override equals method and first two objects are same reference or not
//If not it checks if other object is null or a different class
//Finally calls the compare methods to determine equality based on the converted value

    @Override
    public boolean equals(Object o){

        if (this == o) return true;   // same reference
        if (o == null || getClass() != o.getClass()) return false;

        Length that = (Length) o;
        return this.compare(that);
    }
    public static void main(String args[]){
        Length length1=new Length(1.2,LengthUnit.FEET);
        Length length2=new Length(1.3,LengthUnit.INCHES);
        System.out.println("Are length equals? "+ length1.equals(length2));//should print true

        Length length3=new Length(1.2,LengthUnit.YARDS);
        Length length4=new Length(36.0,LengthUnit.INCHES);
        System.out.println("Are length equals? "+ length3.equals(length4));//should print true

        Length length5=new Length(100.0,LengthUnit.CENTIMETERS);
        Length length6=new Length(39.3701,LengthUnit.INCHES);
        System.out.println("Are length equals? "+ length5.equals(length6));//should print true
    }
}

