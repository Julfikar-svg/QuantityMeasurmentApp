package org.example;

import java.math.RoundingMode;

public class QuantityMeasurement_App {
    // Create a generic method to demonstrate Length equality check
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        boolean result = length1.equals(length2);
        System.out.println("Compare " + length1 + " vs " + length2 + " -> " + result);
        return result;
    }

    // Create a static method to demonstrate feet equality check
    public static void demonstrateFeetEquality() {
        Length l1 = new Length(1.2, Length.LengthUnit.FEET);
        Length l2 = new Length(1.2, Length.LengthUnit.FEET);
        System.out.println("Feet equality:");
        demonstrateLengthEquality(l1, l2);
    }

    // Create a static method to demonstrate inches equality check
    public static void demonstrateInchesEquality() {
        Length l1 = new Length(1.2, Length.LengthUnit.INCHES);
        Length l2 = new Length(1.2, Length.LengthUnit.INCHES);
        System.out.println("Inches equality:");
        demonstrateLengthEquality(l1, l2);
    }

    // Create a static method to demonstrate feet and inches comparison
    public static void demonstrateFeetInchesComparison() {
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);
        System.out.println("Feet vs Inches comparison:");
        demonstrateLengthEquality(feet, inches);
    }

    public static void demonstrateLengthComparison(double value1, Length.LengthUnit unit1,
                                                   double value2, Length.LengthUnit unit2) {
        Length a = new Length(value1, unit1);
        Length b = new Length(value2, unit2);
        System.out.println("Length comparison:");
        demonstrateLengthEquality(a, b);
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
        /*// Equality / comparison demonstrations (same as before)
        demonstrateLengthComparison(1.0, Length.LengthUnit.FEET, 12.0, Length.LengthUnit.INCHES);
        demonstrateLengthComparison(1.0, Length.LengthUnit.YARDS, 36.0, Length.LengthUnit.INCHES);
        demonstrateLengthComparison(100.0, Length.LengthUnit.CENTIMETERS, 39.3701, Length.LengthUnit.INCHES);
        demonstrateLengthComparison(3.0, Length.LengthUnit.FEET, 1.0, Length.LengthUnit.YARDS);
        demonstrateLengthComparison(30.48, Length.LengthUnit.CENTIMETERS, 1.0, Length.LengthUnit.FEET);

        // Optional: show the new conversion API
        demonstrateLengthConversions(1.0,Length.LengthUnit.FEET,Length.LengthUnit.INCHES);
        demonstrateLengthConversions(3.0,Length.LengthUnit.YARDS,Length.LengthUnit.FEET);
        demonstrateLengthConversions(36.0,Length.LengthUnit.INCHES,Length.LengthUnit.YARDS);
        demonstrateLengthConversions(1.0,Length.LengthUnit.CENTIMETERS,Length.LengthUnit.INCHES);
        demonstrateLengthConversions(0.0,Length.LengthUnit.FEET,Length.LengthUnit.INCHES);

        Length length=new Length(1.0,Length.LengthUnit.FEET);
        demonstrateLengthConversions(length,Length.LengthUnit.INCHES);*/


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
