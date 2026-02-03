import org.example.QuantityMeasurement_App;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuantityMeasurement_AppTest {

    @Test
    public void testFeetEquality_SameValue(){
        QuantityMeasurement_App.Feet feet1=new QuantityMeasurement_App.Feet(1.2);
        QuantityMeasurement_App.Feet feet2=new QuantityMeasurement_App.Feet(1.2);
        assertEquals(feet1,feet2);
    }
    @Test
    public void testFeetEquality_DifferntValue(){
        QuantityMeasurement_App.Feet feet1=new QuantityMeasurement_App.Feet(1.4);
        QuantityMeasurement_App.Feet feet2=new QuantityMeasurement_App.Feet(1.2);
        assertNotEquals(feet1,feet2);
    }
    @Test
    public void testFeetEquality_NullComparision(){
        QuantityMeasurement_App.Feet feet1=new QuantityMeasurement_App.Feet(1.2);
        assertNotEquals(feet1, null);
    }

    @Test
    public void testFeetEquality_DifferentClass(){
        QuantityMeasurement_App.Feet feet1=new QuantityMeasurement_App.Feet(1.2);
        String objectDiff="Different Class";
        assertNotEquals(feet1,objectDiff);
    }
    @Test
    public void testFeetEquality_SameReference(){
        QuantityMeasurement_App.Feet feet1=new QuantityMeasurement_App.Feet(1.2);
        assertEquals(feet1,feet1);
    }

    @Test
    public void testMainMethodException(){
        QuantityMeasurement_App.main(new String[]{});
    }
    @Test
    public void testInchesEquality_SameValue(){
        QuantityMeasurement_App.Inches inches1=new QuantityMeasurement_App.Inches(1.2);
        QuantityMeasurement_App.Inches inches2=new QuantityMeasurement_App.Inches(1.2);
        assertEquals(inches1,inches2);

    }
    @Test
    public void testInchesEquality_DifferentValue(){
        QuantityMeasurement_App.Inches inches1=new QuantityMeasurement_App.Inches(1.4);
        QuantityMeasurement_App.Inches inches2=new QuantityMeasurement_App.Inches(1.2);
        assertNotEquals(inches1,inches2);
    }
    @Test
    public void testInchesEquality_NullComparison(){
        QuantityMeasurement_App.Inches inches1=new QuantityMeasurement_App.Inches(1.2);
        assertNotEquals(inches1, null);
    }
    @Test
    public void testInchesEquality_DifferentClass(){
        QuantityMeasurement_App.Inches inches1=new QuantityMeasurement_App.Inches(1.2);
        String objectDiff="Different Class";
        assertNotEquals(inches1,objectDiff);
    }
    @Test
    public void testInchesEquality_SameReference(){
        QuantityMeasurement_App.Inches inches1=new QuantityMeasurement_App.Inches(1.2);
        assertEquals(inches1,inches1);
    }

}
