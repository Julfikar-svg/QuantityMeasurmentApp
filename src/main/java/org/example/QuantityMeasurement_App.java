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

    public static class Inches {
        private final double value;
        public Inches(double value){
            this.value=value;
        }
        public double getValue(){
            return value;
        }
        //Overrride equals method and compare two value
        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;

            if(obj==null || getClass() !=obj.getClass()) return false;
            Inches other= (Inches) obj;
            return Double.compare(this.value,other.value)==0;
        }
    }

    //


    public static void demonStrateFeetEquality(){
        Feet firstLengthValue=new Feet(1.2);
        Feet secondLengthValue=new Feet(1.2);
        boolean status= firstLengthValue.equals(secondLengthValue);
        System.out.println("Feet value is:: "+status);
    }

    public static void demonStrateInchesEquality(){
        Inches firstLengthValue=new Inches(1.2);
        Inches secondLengthValue=new Inches(1.2);
        boolean status= firstLengthValue.equals(secondLengthValue);
        System.out.println("Inches value is:: "+status);
    }


    public static void main(String args[]){

        demonStrateFeetEquality();
        demonStrateInchesEquality();
    }

}
