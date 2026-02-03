package org.example;

public class QuantityMeasurement_App {
    // Create a generic method to demonstrate Length equality check
    public static boolean demonstrateLengthEquality(Length length1, Length length2){
        boolean result = length1.equals(length2);
        System.out.println("Compare " + length1 + " vs " + length2 + " -> " + result);
        return result;
    }

    //Create a static method to demonstrate feet equality check
    public static void demonstrateFeetEquality(){
        Length l1 = new Length(1.2, Length.LengthUnit.FEET);
        Length l2 = new Length(1.2, Length.LengthUnit.FEET);
        System.out.println("Feet equality:");
        demonstrateLengthEquality(l1, l2);

    }


    //Create a static method to demonstrate inches equality check
    public static void demonstrateInchesEquality(){
        Length l1 = new Length(1.2, Length.LengthUnit.INCHES);
        Length l2 = new Length(1.2, Length.LengthUnit.INCHES);
        System.out.println("Inches equality:");
        demonstrateLengthEquality(l1, l2);
    }

    //Create a static method to demonstrate feet and inches comparison
    public static void demonstrateFeetInchesComparison(){
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);
        System.out.println("Feet vs Inches comparison:");
        demonstrateLengthEquality(feet, inches);
    }

    //Main method to demonstrate feet and inches equality check
//And comparison check between feet and inches
    public static void main(String args[]){
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
    }

}
