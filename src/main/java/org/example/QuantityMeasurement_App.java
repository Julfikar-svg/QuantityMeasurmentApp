package org.example;

public class QuantityMeasurement_App {
    public static class Feet {
        private final double value;
        public Feet(double value) {
            this.value = value;
        }
        public double getValue() {
            return value;
        }
        //Override equal method and compare two value
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value)==0;
        }

        @Override
        public int hashCode() {

            return Double.hashCode(value);
        }

        @Override
        public String toString() {
            return "Feet(" + value + ")";
        }
    }

    //
    public static boolean compare(double a, double b) {
        Feet firstLengthValue=new Feet(a);
        Feet secondLengthValue=new Feet(b);
        return firstLengthValue.equals(secondLengthValue);

    }

    public static void main(String args[]){

        double firstFeetValue=1.2;
        double secondFeetValue=1.2;
        boolean status= QuantityMeasurement_App.compare(firstFeetValue,secondFeetValue);
        System.out.println("Feet value is:: "+status);
    }

}
