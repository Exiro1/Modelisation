package Calcul;

import java.util.ArrayList;

public class Vector {

    public ArrayList<Double> getValues() {
        return values;
    }

    ArrayList<Double> values;
    int size;
    boolean line;

    public Vector(ArrayList<Double> values, boolean line) {
        this.values = values;
        size = values.size();
        this.line = line;
    }
    public void invert(int a,int b){
        double temp = values.get(b);
        values.set(b, values.get(a));
        values.set(a,temp);
    }


    @Override
    public String toString() {
        String s = "";
        for(int i=0;i<size;i++){
            s = s + "| "+values.get(i)+" |\n";
        }
        return s;
    }



}
