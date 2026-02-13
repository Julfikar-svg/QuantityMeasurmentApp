import org.example.Length;
import org.example.QuantityMeasurement_App;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

public class QuantityMeasurement_AppTest {

    @Test
    public void testFeetEquality() {
        Length feet1 = new Length(1.2, Length.LengthUnit.FEET);
        Length feet2 = new Length(1.2, Length.LengthUnit.FEET);

        assertTrue("1.2 ft should equal 1.2 ft", feet1.equals(feet2));
        assertTrue("Symmetry: equals should be symmetric", feet2.equals(feet1));
    }

    @Test
    public void testInchesEquality() {
        Length inches1 = new Length(1.2, Length.LengthUnit.INCHES);
        Length inches2 = new Length(1.2, Length.LengthUnit.INCHES);

        assertTrue("1.2 in should equal 1.2 in", inches1.equals(inches2));
    }

    @Test
    public void testFeetInchesComparison() {
        Length threeFeet = new Length(3.0, Length.LengthUnit.FEET);
        Length thirtySixInches = new Length(36.0, Length.LengthUnit.INCHES);

        assertTrue("3 ft should equal 36 in", threeFeet.equals(thirtySixInches));
        assertTrue("Symmetry: 36 in should equal 3 ft", thirtySixInches.equals(threeFeet));
    }

    @Test
    public void testFeetInequalty() {
        Length twoFeet = new Length(2.0, Length.LengthUnit.FEET);     // 24 inches
        Length twentyFiveInches = new Length(25.0, Length.LengthUnit.INCHES);

        assertFalse("2 ft (24 in) should NOT equal 25 in", twoFeet.equals(twentyFiveInches));
    }

    @Test
    public void testInchsInequality() {
        Length tenInches = new Length(10.0, Length.LengthUnit.INCHES);
        Length oneFoot = new Length(1.0, Length.LengthUnit.FEET);     // 12 inches

        assertFalse("10 in should NOT equal 12 in (1 ft)", tenInches.equals(oneFoot));
    }

    @Test
    public void testCrossnquality() {//testCrossUnitInequality
        // Interpreting this as cross-unit equality positive case
        Length oneFoot = new Length(1.0, Length.LengthUnit.FEET);
        Length twelveInches = new Length(12.0, Length.LengthUnit.INCHES);

        assertTrue("1 ft should equal 12 in", oneFoot.equals(twelveInches));
    }

    @Test
    public void testCrossUnitInequality() {//testCrossUnitInequality
        // Interpreting this as cross-unit equality positive case
        Length oneFoot = new Length(1.0, Length.LengthUnit.FEET);
        Length twelveInches = new Length(12.0, Length.LengthUnit.INCHES);

        assertTrue("1 ft should equal 12 in", oneFoot.equals(twelveInches));
    }

    @Test
    public void testMultipleFeetComrisn() {
        Length ft1 = new Length(1.0, Length.LengthUnit.FEET);
        Length in12 = new Length(12.0, Length.LengthUnit.INCHES);
        Length ft1Again = new Length(1.0, Length.LengthUnit.FEET);
        Length in13 = new Length(13.0, Length.LengthUnit.INCHES);

        // Equality chain
        assertTrue("1 ft should equal 12 in", ft1.equals(in12));
        assertTrue("12 in should equal 1 ft", in12.equals(ft1Again));
        assertTrue("Transitivity: 1 ft == 12 in == 1 ft", ft1.equals(ft1Again));

        // Negative case
        assertFalse("1 ft (12 in) should NOT equal 13 in", ft1.equals(in13));
    }
 @Test
 public void yardEquals36Inches(){
     Length oneYard = new Length(1.0, Length.LengthUnit.YARDS);
     Length thirtySixInches = new Length(36.0, Length.LengthUnit.INCHES);
     assertEquals(oneYard,thirtySixInches);
 }
 @Test
 public void centimetersEquals39Point370Inches(){
     Length oneCentemiters = new Length(100.0, Length.LengthUnit.CENTIMETERS);
     Length inches = new Length(39.3701, Length.LengthUnit.INCHES);
     assertEquals(oneCentemiters,inches);
   }
   @Test
    public void threeFeetEqualsOneYard(){
       Length feet = new Length(3.0, Length.LengthUnit.FEET);
       Length yard = new Length(1.0, Length.LengthUnit.YARDS);
       assertEquals(feet,yard);
   }

    @Test
    public void thirtyPoints48cmEqualsOneFoot(){
        Length cm = new Length(30.48, Length.LengthUnit.CENTIMETERS);
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        assertEquals(cm,feet);
    }
    @Test
    public void yardNotEqualsToInches(){
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length inches = new Length(1.0, Length.LengthUnit.INCHES);
        assertNotEquals(yard,inches);
    }

    @Test
    public void referenceEqualitySameObject(){
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(1.0, Length.LengthUnit.INCHES);
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length centimeter = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        assertEquals(feet,feet);
        assertEquals(inches,inches);
        assertEquals(yard,yard);
        assertEquals(centimeter,centimeter);
    }

    @Test
    public void equalsReturnsFalseForNull(){
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(1.0, Length.LengthUnit.INCHES);
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length centimeter = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        assertNotEquals(null,feet);
        assertNotEquals(null,inches);
        assertNotEquals(null,yard);
        assertNotEquals(null,centimeter);
    }

    @Test
    public void reflexiveSymmetricAndTransitiveProperty(){
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);


        // Reflexive
        assertEquals(feet,feet);

        //Symmetric
        assertEquals(feet,inches);
        assertEquals(inches,feet);

        //Transitive:
        assertEquals(feet,yard);
    }

    @Test
    public void differentValuesSameUnitNotEqual(){
        Length feet1 = new Length(3.0, Length.LengthUnit.FEET);
        Length feet2 = new Length(5.0, Length.LengthUnit.FEET);
        assertNotEquals(feet1,feet2);

        Length inches1 = new Length(36.0, Length.LengthUnit.INCHES);
        Length inches2 = new Length(50.0, Length.LengthUnit.INCHES);
        assertNotEquals(inches1,inches2);
    }

    @Test
    public void crossUnitEqualityDemonstrateMethod(){
        Length oneFoot = new Length(1.0, Length.LengthUnit.FEET);               // 12.00 in
        Length twelveInches = new Length(12.0, Length.LengthUnit.INCHES);       // 12.00 in
        assertEquals("1 ft == 12 in", oneFoot, twelveInches);

        // 3 ft == 1 yd
        Length threeFeet = new Length(3.0, Length.LengthUnit.FEET);             // 36.00 in
        Length oneYard = new Length(1.0, Length.LengthUnit.YARDS);              // 36.00 in
        assertEquals("3 ft == 1 yd", threeFeet, oneYard);

        // 30.48 cm == 1 ft  (30.48 cm = 12.00 in)
        Length thirtyPoint48Cm = new Length(30.48, Length.LengthUnit.CENTIMETERS);
        assertEquals("30.48 cm == 1 ft", thirtyPoint48Cm, oneFoot);

        // 100 cm == 39.3701 in (rounds to 39.37 in on both sides)
        Length cm100 = new Length(100.0, Length.LengthUnit.CENTIMETERS);
        Length in393701 = new Length(39.3701, Length.LengthUnit.INCHES);
        assertEquals("100 cm == 39.3701 in after 2-dec rounding", cm100, in393701);

        // Negative demonstration: 1 yd != 1 in
        Length oneInch = new Length(1.0, Length.LengthUnit.INCHES);
        assertNotEquals("1 yd != 1 in", oneYard, oneInch);
    }

    @Test
    public void convertFeetToInches(){
        Length lenthInInches= QuantityMeasurement_App.demonstrateLengthConversions(3.0,Length.LengthUnit.FEET,Length.LengthUnit.INCHES);
        Length expectedLength=new Length(36.0,Length.LengthUnit.INCHES);

        assertTrue(QuantityMeasurement_App.demonstrateLengthEquality(lenthInInches,expectedLength));
    }
    @Test
    public void convertYardToInchesUsingOverloadMethod(){
        Length lengthInYards=new Length(2.0,Length.LengthUnit.YARDS);
        Length lenthInInches= QuantityMeasurement_App.demonstrateLengthConversions(lengthInYards,Length.LengthUnit.INCHES);
        Length expectedLength=new Length(72.0,Length.LengthUnit.INCHES);

        assertTrue(QuantityMeasurement_App.demonstrateLengthEquality(lenthInInches,expectedLength));
    }
   //UC6

    @Test
    public void addSameUnitFeetAndFeet(){
        Length length1=new Length(1.0,Length.LengthUnit.FEET);
        Length length2=new Length(1.0,Length.LengthUnit.FEET);
        Length sumLength=QuantityMeasurement_App.demonstrateLengthConversions(length1,length2);

        Length expectedLength=new Length(2.0,Length.LengthUnit.FEET);
        assertTrue(QuantityMeasurement_App.demonstrateLengthEquality(sumLength,expectedLength));
    }

    @Test
    public void addSameUnitInchesAndInches(){
        Length length1=new Length(12.0,Length.LengthUnit.INCHES);
        Length length2=new Length(12.0,Length.LengthUnit.INCHES);
        Length sumLength=QuantityMeasurement_App.demonstrateLengthConversions(length1,length2);

        Length expectedLength=new Length(24.0,Length.LengthUnit.INCHES);
        assertTrue(QuantityMeasurement_App.demonstrateLengthEquality(sumLength,expectedLength));
    }

    @Test
    public void addCrossUnitFeetAndInches(){
        Length length1=new Length(1.0,Length.LengthUnit.FEET);
        Length length2=new Length(12.0,Length.LengthUnit.INCHES);
        Length sumLength=QuantityMeasurement_App.demonstrateLengthConversions(length1,length2);

        Length expectedLength=new Length(2.0,Length.LengthUnit.FEET);
        assertTrue(QuantityMeasurement_App.demonstrateLengthEquality(sumLength,expectedLength));
    }
    @Test
    public void addCrossUnitYardsAndInches(){
        Length length1=new Length(1.0,Length.LengthUnit.YARDS);
        Length length2=new Length(3.0,Length.LengthUnit.FEET);
        Length sumLength=QuantityMeasurement_App.demonstrateLengthConversions(length1,length2);

        Length expectedLength=new Length(2.0,Length.LengthUnit.YARDS);
        assertTrue(QuantityMeasurement_App.demonstrateLengthEquality(sumLength,expectedLength));
    }


    @Test
    public void additionCommutative(){
        Length length1=new Length(1.0,Length.LengthUnit.FEET);
        Length length2=new Length(12.0,Length.LengthUnit.INCHES);

        Length length3=new Length(12.0,Length.LengthUnit.INCHES);
        Length length4=new Length(1.0,Length.LengthUnit.FEET);

        Length sumLength1=QuantityMeasurement_App.demonstrateLengthConversions(length1,length2);
        Length sumLength2=QuantityMeasurement_App.demonstrateLengthConversions(length3,length4);

        assertTrue(QuantityMeasurement_App.demonstrateLengthEquality(sumLength1,sumLength2));
    }

    @Test
    public void addCrossUnitWithZero(){
        Length length1=new Length(5.0,Length.LengthUnit.FEET);
        Length length2=new Length(0.0,Length.LengthUnit.INCHES);
        Length sumLength=QuantityMeasurement_App.demonstrateLengthConversions(length1,length2);

        Length expectedLength=new Length(5.0,Length.LengthUnit.FEET);
        assertTrue(QuantityMeasurement_App.demonstrateLengthEquality(sumLength,expectedLength));
    }

    @Test
    public void additonWithNegativeValue(){
        Length length1=new Length(5.0,Length.LengthUnit.FEET);
        Length length2=new Length(-2.0,Length.LengthUnit.FEET);
        Length sumLength=QuantityMeasurement_App.demonstrateLengthConversions(length1,length2);

        Length expectedLength=new Length(3.0,Length.LengthUnit.FEET);
        assertTrue(QuantityMeasurement_App.demonstrateLengthEquality(sumLength,expectedLength));
    }

    @Test
    public void additonWithNullSecondOperand(){
        Length length1=new Length(5.0,Length.LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class,()->length1.add(null));
    }

    @Test
    public void addLargeValueFeetAndFeet(){
        Length length1=new Length(1e6,Length.LengthUnit.FEET);
        Length length2=new Length(1e6,Length.LengthUnit.FEET);
        Length sumLength=QuantityMeasurement_App.demonstrateLengthConversions(length1,length2);

        Length expectedLength=new Length(2e6,Length.LengthUnit.FEET);
        assertTrue(QuantityMeasurement_App.demonstrateLengthEquality(sumLength,expectedLength));
    }

    @Test
    public void testAdditionExplicitTargetUnitFeet(){
        Length length1=new Length(1.0,Length.LengthUnit.FEET);
        Length length2=new Length(12.0,Length.LengthUnit.INCHES);

        Length sumOfLength=QuantityMeasurement_App.demonstrateLengthAddition(length1,length2, Length.LengthUnit.FEET);
        Length expecteValue=new Length(2.0, Length.LengthUnit.FEET);
        assertTrue(QuantityMeasurement_App.demonstrateLengthEquality(sumOfLength,expecteValue));
    }
    @Test
    public void testAdditionExplicitTargetUnitInches(){
        Length length1=new Length(1.0,Length.LengthUnit.FEET);
        Length length2=new Length(12.0,Length.LengthUnit.INCHES);

        Length sumOfLength=QuantityMeasurement_App.demonstrateLengthAddition(length1,length2, Length.LengthUnit.INCHES);
        Length expecteValue=new Length(24.0, Length.LengthUnit.INCHES);
        assertTrue(QuantityMeasurement_App.demonstrateLengthEquality(sumOfLength,expecteValue));
    }
    @Test
    public void testAdditionExplicitTargetUnitYards(){
        Length length1=new Length(1.0,Length.LengthUnit.FEET);
        Length length2=new Length(12.0,Length.LengthUnit.INCHES);

        Length sumOfLength=QuantityMeasurement_App.demonstrateLengthAddition(length1,length2, Length.LengthUnit.YARDS);
        Length expecteValue=new Length(0.67, Length.LengthUnit.YARDS);
        assertTrue(QuantityMeasurement_App.demonstrateLengthEquality(sumOfLength,expecteValue));
    }
    @Test
    public void testAdditionExplicitTargetUnitCentimeters(){
        Length length1=new Length(1.0,Length.LengthUnit.INCHES);
        Length length2=new Length(1.0,Length.LengthUnit.INCHES);

        Length sumOfLength=QuantityMeasurement_App.demonstrateLengthAddition(length1,length2, Length.LengthUnit.CENTIMETERS);
        Length expecteValue=new Length(5.08, Length.LengthUnit.CENTIMETERS);
        assertTrue(QuantityMeasurement_App.demonstrateLengthEquality(sumOfLength,expecteValue));
    }
    @Test
    public void testAdditionExplicitTargetUnitSameAsFirstOperand(){
        Length length1=new Length(2.0,Length.LengthUnit.YARDS);
        Length length2=new Length(3.0,Length.LengthUnit.FEET);

        Length sumOfLength=QuantityMeasurement_App.demonstrateLengthAddition(length1,length2, Length.LengthUnit.YARDS);
        Length expecteValue=new Length(3, Length.LengthUnit.YARDS);
        assertTrue(QuantityMeasurement_App.demonstrateLengthEquality(sumOfLength,expecteValue));
    }

    @Test
    public void testAdditionExplicitTargetUnitSameAsSecondOperand(){
        Length length1=new Length(2.0,Length.LengthUnit.YARDS);
        Length length2=new Length(3.0,Length.LengthUnit.FEET);

        Length sumOfLength=QuantityMeasurement_App.demonstrateLengthAddition(length1,length2, Length.LengthUnit.FEET);
        Length expecteValue=new Length(9.0, Length.LengthUnit.FEET);
        assertTrue(QuantityMeasurement_App.demonstrateLengthEquality(sumOfLength,expecteValue));
    }

    @Test
    public void testAdditionExplicitTargetUnitCommutative(){
        Length length1=new Length(1.0,Length.LengthUnit.FEET);
        Length length2=new Length(12.0,Length.LengthUnit.INCHES);

        Length sumOfLength1=QuantityMeasurement_App.demonstrateLengthAddition(length1,length2, Length.LengthUnit.YARDS);

        Length length3=new Length(12.0,Length.LengthUnit.INCHES);
        Length length4=new Length(1.0,Length.LengthUnit.FEET);

        Length sumOfLength2=QuantityMeasurement_App.demonstrateLengthAddition(length3,length4, Length.LengthUnit.YARDS);

        assertTrue(QuantityMeasurement_App.demonstrateLengthEquality(sumOfLength1,sumOfLength2));
    }

}
