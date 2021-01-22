package Calcul;

import java.awt.*;
import java.util.ArrayList;

public class Resolve {


    public static Vector resolveEquation(Matrix m, Vector v){
        Matrix triangle = m.resolveGauss(v);
        Vector result = new Vector(new ArrayList<>(v.values),false);
        result.values.set(v.size-1,v.getValues().get(v.size-1)/triangle.getValue(v.size-1,v.size-1));
        for(int i=triangle.sizeL-2;i>=0;i--){
            double temp = 0.0;
            for(int j=i+1;j<v.size;j++){
                temp += triangle.getValue(i,j)*result.values.get(j);
            }
            temp = (v.getValues().get(i)-temp)/triangle.getValue(i,i);
            result.values.set(i,temp);
        }
        return result;
    }

    public static Matrix resolveApprox(Matrix points, int deg){

        ArrayList<Double> m = new ArrayList<>();
        for(int k=0;k< points.sizeL;k++){
            m.add(1.0);
        for(int i=0;i<deg;i++){
            m.add(m.get(m.size()-1)*points.getValue(k,0));
        }
        }
        Matrix M = new Matrix(m,points.sizeL,deg+1);
        ArrayList<Double> y = new ArrayList<>();
        for(int i=0;i<points.sizeL;i++){
            y.add(points.getValue(i,1));
        }
        Matrix ym = new Matrix(y,points.sizeL,1);
        //Matrice S = M.getTranspose().multiply(ym);
        //Matrice Q = M.getTranspose().multiply(M);

        Matrix P = M.getTranspose().multiply(M).invert().multiply(M.getTranspose().multiply(ym));

        return P;
    }
    public static Matrix resolveInterpolation(Matrix points){
        System.out.println(points.toString());
        int deg = points.sizeL-1;
        ArrayList<Double> m = new ArrayList<>();
        for(int k=0;k< points.sizeL;k++){
            m.add(1.0);
            for(int i=0;i<deg;i++){
                m.add(m.get(m.size()-1)*points.getValue(k,0));
            }
        }
        Matrix M = new Matrix(m,points.sizeL,deg+1);
        System.out.println(M.toString());
        ArrayList<Double> y = new ArrayList<>();
        for(int i=0;i<points.sizeL;i++){
            y.add(points.getValue(i,1));
        }
        Matrix ym = new Matrix(y,points.sizeL,1);
        //Matrice S = M.getTranspose().multiply(ym);
        //Matrice Q = M.getTranspose().multiply(M);

        Matrix P = M.getTranspose().multiply(M).invert().multiply(M.getTranspose().multiply(ym));

        return P;
    }

    public static Matrix getIntegrationWeight(Matrix points){
        return null;
    }

    public static Matrix getFunction(Matrix m,int points,double start,double end){
        double factor = (end-start)/(points-1);
        ArrayList<Double> values = new ArrayList<>();

        for(int i=0;i<points;i++) {
            double sum = 0.0;
            double p = 1;
            for (int k = 0; k < m.sizeL; k++) {
                sum += m.getValue(k,0)*p;
                p*=factor*i+start;
            }
            values.add(factor*i+start);
            values.add(sum);
        }
        return new Matrix(values,points,2);
    }


}
