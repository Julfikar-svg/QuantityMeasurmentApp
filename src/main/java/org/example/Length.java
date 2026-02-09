package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

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
     //===== Construction & validation =====

    // Constructor initializes length value and unit.

    public Length(double value, LengthUnit unit){

        validateValue(value);
        this.unit = Objects.requireNonNull(unit, "unit must not be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }



    private static void validateValue(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("value must be a finite number (not NaN or infinite).");
        }
    }
    // ===== Core base conversion (no rounding) =====

    /**
     * Converts this value to the base unit (inches), without rounding.
     */

    private double toBaseInchesRaw() {
        return this.value * this.unit.getConversionFactor();
    }


    // ===== Static conversion API =====

    /**
     * Converts a numeric value from sourceUnit to targetUnit.
     * Main flow:
     *   1) Validate inputs (finite value, non-null units)
     *   2) Convert to base (inches)
     *   3) Convert from base to target unit
     *   4) Return numeric result (no rounding by default)
     *
     * @throws IllegalArgumentException if inputs are invalid
     */
    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
        return convert(value, sourceUnit, targetUnit, -1, null);
    }

    /**
     * Converts a numeric value from sourceUnit to targetUnit with optional rounding.
     * @param scale non-negative scale for rounding; use -1 to skip rounding
     * @param roundingMode required if scale >= 0
     * @throws IllegalArgumentException if inputs are invalid
     */
    public static double convert(double value,
                                LengthUnit sourceUnit,
                                LengthUnit targetUnit,
                                int scale,
                                RoundingMode roundingMode) {

        validateValue(value);
        if (sourceUnit == null) {
            throw new IllegalArgumentException("sourceUnit must not be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("targetUnit must not be null");
        }

        // Convert to base (inches)
        double inInches = value * sourceUnit.getConversionFactor();

        // Convert from base (inches) to target
        double result = inInches / targetUnit.getConversionFactor();

        // Optional rounding
        if (scale >= 0) {
            if (roundingMode == null) {
                throw new IllegalArgumentException("roundingMode is required when scale >= 0");
            }
            result = round(result, scale, roundingMode);
        }

        return result;
    }

    // ===== Instance conversion API =====

    /**
     * Converts THIS quantity to the target unit (no rounding).
     */
    public double convert(LengthUnit targetUnit) {
        return convert(this.value, this.unit, targetUnit);
    }

    /**
     * Converts THIS quantity to the target unit with rounding.
     */
    public double convert(LengthUnit targetUnit, int scale, RoundingMode roundingMode) {
        return convert(this.value, this.unit, targetUnit, scale, roundingMode);
    }



    //Convert the length value to base unit
    private double convertToBaseUnit(){
        double baseValue= this.value * this.unit.getConversionFactor();
        return Double.parseDouble(String.format("%.2f",baseValue));
    }
    private double convertBaseFromBaseUnitTargetUnit(double lengthInches, LengthUnit targetUnit){

        // Convert to target;
        double targetValue = lengthInches / targetUnit.getConversionFactor();
        return Double.parseDouble(String.format("%.2f",targetValue));
     }
    //convert two length object for equality base on their values in the base unit
    public Boolean compare(Length thatLength){
        if (thatLength == null) return false;

        double thisRoundedInches = round(toBaseInchesRaw(), 2, RoundingMode.HALF_UP);
        double thatRoundedInches = round(thatLength.toBaseInchesRaw(), 2, RoundingMode.HALF_UP);

        // Use Double.compare for safe equality of rounded values.
        return Double.compare(thisRoundedInches, thatRoundedInches) == 0;

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
    // ===== Utilities =====

    private static double round(double value, int scale, RoundingMode mode) {
        return new BigDecimal(Double.toString(value))
                .setScale(scale, mode)
                .doubleValue();
    }
//UC6
    /** Adds two lengths and returns the sum in the specified targetUnit. Base unit: FEET. */
    public static Length add(Length length1, Length length2, LengthUnit targetUnit) {
        Objects.requireNonNull(targetUnit, "targetUnit must not be null");
        validateLength(length1, "length1");
        validateLength(length2, "length2");

        double sumFeet = toFeet(length1.value, length1.unit)
                + toFeet(length2.value, length2.unit);

        double sumInTargetUnit = fromFeet(sumFeet, targetUnit);
        return new Length(sumInTargetUnit, targetUnit);
    }
//Validate the length
    private static void validateLength(Length length, String argName) {
        if (length == null) {
            throw new IllegalArgumentException(argName + " must not be null");
        }
        if (length.unit == null) {
            throw new IllegalArgumentException(argName + ".unit must not be null");
        }
        validateValue(length.value);
    }

    // --- FEET-based conversion helpers (using enum's inches-per-unit factors) ---

    private static double toFeet(double value, LengthUnit unit) {
        // value[in unit] * (inches per unit) / (inches per foot)
        return (value * unit.getConversionFactor()) / 12.0;
    }

    private static double fromFeet(double feet, LengthUnit targetUnit) {
        // feet * (inches per foot) / (inches per targetUnit)
        return (feet * 12.0) / targetUnit.getConversionFactor();
    }


    //private static double
    @Override
    public String toString() {
        return value + " " + unit.name();
    }
    public static void main(String args[]){
        Length length1 = new Length(1.2, LengthUnit.FEET);
        Length length2 = new Length(1.3, LengthUnit.INCHES);
        System.out.println("Are length equals? " + length1.equals(length2)); // should print true after 2-dec rounding

        Length length3 = new Length(1.2, LengthUnit.YARDS);
        Length length4 = new Length(36.0, LengthUnit.INCHES);
        System.out.println("Are length equals? " + length3.equals(length4)); // should print true

        Length length5 = new Length(100.0, LengthUnit.CENTIMETERS);
        Length length6 = new Length(39.3701, LengthUnit.INCHES);
        System.out.println("Are length equals? " + length5.equals(length6)); // should print true

        // Examples of conversion API:
        double inches = Length.convert(2.0, LengthUnit.YARDS, LengthUnit.INCHES);          // 72.0
        double feetRounded = Length.convert(100.0, LengthUnit.CENTIMETERS, LengthUnit.FEET, 3, RoundingMode.HALF_UP); // 3.281
        System.out.println("2 yd in inches = " + inches);
        System.out.println("100 cm in feet (3-dec) = " + feetRounded);

    }
}

