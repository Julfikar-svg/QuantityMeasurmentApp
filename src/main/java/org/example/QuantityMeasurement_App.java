package org.example;

import java.math.RoundingMode;

public class QuantityMeasurement_App {
    // Create a generic method to demonstrate Length equality check
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        boolean result = length1.equals(length2);
        System.out.println("Compare " + length1 + " vs " + length2 + " -> " + result);
        return result;
    }


    // Demonstrate the new conversion API
    public static Length demonstrateLengthConversions(double value,Length.LengthUnit sourceUnit,Length.LengthUnit targetUnit) {
        System.out.println("\n--- Conversion ---");

        // Static conversion:
        double inStatic = Length.convert(value, sourceUnit, targetUnit);
        System.out.println("Static: "+value+" "+sourceUnit+" -> "+targetUnit+" = " + inStatic);

        // Instance conversion: 12 inches -> feet (should be 1.0)
        Length length = new Length(inStatic, targetUnit);
        return length;
    }
    //UC5
    public static Length demonstrateLengthConversions(Length sourceLength,Length.LengthUnit targetUnit) {

        // conversion
        double inStatic = sourceLength.convert(targetUnit);
        System.out.println(sourceLength+"  inStatic= " + inStatic);

        Length targetLength = new Length(inStatic, targetUnit);
        return targetLength;
    }
    //UC6
    public  static Length demonstrateLengthConversions(Length length1,Length length2){
      return length1.add(length2);
    }
    // Main method to demonstrate equality checks and conversions
    public static void main(String[] args) {


        //UC6
        System.out.println(demonstrateLengthConversions(new Length(1.0,Length.LengthUnit.FEET),new Length(2.0,Length.LengthUnit.FEET)));

        System.out.println(demonstrateLengthConversions(new Length(12.0,Length.LengthUnit.INCHES),new Length(1.0,Length.LengthUnit.FEET)));

        System.out.println(demonstrateLengthConversions(new Length(1.0,Length.LengthUnit.FEET),new Length(12.0,Length.LengthUnit.INCHES)));

        System.out.println(demonstrateLengthConversions(new Length(1.0,Length.LengthUnit.YARDS),new Length(3.0,Length.LengthUnit.FEET)));

        System.out.println(demonstrateLengthConversions(new Length(36.0,Length.LengthUnit.INCHES),new Length(1.0,Length.LengthUnit.YARDS)));

        System.out.println(demonstrateLengthConversions(new Length(2.54,Length.LengthUnit.CENTIMETERS),new Length(1.0,Length.LengthUnit.INCHES)));

        System.out.println(demonstrateLengthConversions(new Length(5.0,Length.LengthUnit.FEET),new Length(0.0,Length.LengthUnit.INCHES)));

        System.out.println(demonstrateLengthConversions(new Length(5.0,Length.LengthUnit.FEET),new Length(-2.0,Length.LengthUnit.FEET)));
    }


}
