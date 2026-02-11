package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Length {
    //Instant variable
    private double value;
    private LengthUnit unit;

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

    private double toBaseInchesRaw() {
        return this.value * this.unit.getConversionFactor();
    }

    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
        return convert(value, sourceUnit, targetUnit, -1, null);
    }

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

    public double convert(LengthUnit targetUnit) {
        return convert(this.value, this.unit, targetUnit);
    }

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
    public Length add(Length thatLength) {
        if(thatLength==null){
            throw new IllegalArgumentException("Length can not be null");
        }
        double sumInches=this.convertToBaseUnit()+thatLength.convertToBaseUnit();
        double resultValue=convertBaseFromBaseUnitTargetUnit(sumInches,this.unit);
        return new Length(round(resultValue,2, RoundingMode.HALF_UP),this.unit);
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

