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

    // ===== Instance conversion API =====

    public double convert(LengthUnit targetUnit) {
        return convert(this.value, this.unit, targetUnit, -1, null);
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

        // Convert to base
        double baseUnitValue = value * sourceUnit.getConversionFactor();
        double result = baseUnitValue / targetUnit.getConversionFactor();
        // Optional rounding
        if (scale >= 0) {
            if (roundingMode == null) {
                throw new IllegalArgumentException("roundingMode is required when scale >= 0");
            }
            result = round(result, scale, roundingMode);
        }
        return result;
    }

    //Convert the length value to base unit
    private double convertToBaseUnit(){
        double baseValue= this.value * this.unit.getConversionFactor();
        return Double.parseDouble(String.format("%.2f",baseValue));
    }
    private double convertBaseFromBaseUnitTargetUnit(double lengthInches, LengthUnit targetUnit){
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

    private static double round(double value, int scale, RoundingMode mode) {
        return new BigDecimal(Double.toString(value))
                .setScale(scale, mode)
                .doubleValue();
    }
    private double round(double value,LengthUnit unit){
        int precision = (unit == LengthUnit.YARDS)?3:2;
        double factor = Math.pow(10,precision);
        return Math.round(value*factor)/factor;
    }

    //UC6
    public Length add(Length thatLength) {
        if(thatLength==null){
            throw new IllegalArgumentException("Length can not be null");
        }
        double sumValue=this.convertToBaseUnit()+thatLength.convertToBaseUnit();
        double resultValue=convertBaseFromBaseUnitTargetUnit(sumValue,this.unit);
        return new Length(round(resultValue,2, RoundingMode.HALF_UP),this.unit);
    }
    //UC7
    public Length add(Length length,LengthUnit targetUnit){
        return addAndConvert(length,targetUnit);
    }
    private Length addAndConvert(Length length,LengthUnit targetUnit){
        double sumOfValue=this.convertToBaseUnit() + length.convertToBaseUnit();
        double convertedUnit=convertBaseFromBaseUnitTargetUnit(sumOfValue,targetUnit);

        return new Length(round(convertedUnit,targetUnit),targetUnit);
    }


    @Override
    public String toString() {
        return value + " " + unit.name();
    }
    public static void main(String args[]){

        Length length1=new Length(12.0,Length.LengthUnit.INCHES);
        Length length2=new Length(12.0,Length.LengthUnit.INCHES);

        Length resultLength=length1.add(length2);
        System.out.println("Length:: "+resultLength);

    }
}

