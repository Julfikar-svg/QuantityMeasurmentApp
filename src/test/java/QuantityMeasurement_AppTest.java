import org.example.Length;
import org.example.QuantityMeasurement_App;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

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
    public void testCrossnquality() {
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


}
