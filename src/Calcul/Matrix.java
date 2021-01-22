package Calcul;

import java.util.ArrayList;

public class Matrix {


    public ArrayList<Double> getValues() {
        return values;
    }

    ArrayList<Double> values;

    //reset those value on matrice change
    double[] norms;
    Matrix inverse;
    Matrix triangle;
    Matrix transpose;
    public int sizeL,sizeC;
    boolean square = false;


    /**
     *
     * @param values
     *  a b c d
     *  e f g h
     *  i j k l
     *  m n o p
     */

    public Matrix(ArrayList<Double> values, int sizeL, int sizeC) {
        this.values = values;
        this.sizeC=sizeC;
        this.sizeL=sizeL;
        this.norms = new double[]{-1.0,-1.0,-1.0};
        if(sizeC==sizeL)
            square = true;
    }

    public Matrix concatLine(Matrix m){
        ArrayList<Double> newValues = new ArrayList<>(values);
        newValues.addAll(m.getValues());
        return new Matrix(values,sizeL+m.sizeL,sizeC);
    }

    public double getMaxValueCol(int col){
        double max = 0.0;
        for(int i=0;i<sizeL;i++){
            double v =getValue(i,col);
            if(v>max){
                max = v;
            }
        }
        return max;
    }
    public double getMinValueCol(int col){
        double min = Double.MAX_VALUE;
        for(int i=0;i<sizeL;i++){
            double v = getValue(i,col);
            if(v<min){
                min = v;
            }
        }
        return min;
    }
    public double getNorm1(){
        if(norms[0]!= -1.0)
            return norms[0];
        double max = 0.0;
        for(int i=0;i<sizeC;i++){
            double temp = 0.0;
            for(double v : getCol(i)){
                temp += Math.abs(v);
            }
            max = Math.max(max,temp);
        }
        norms[0] = max;
        return max;
    }
    public double getNormInf(){
        if(norms[2]!= -1.0)
            return norms[2];
        double max = 0.0;
        for(int i=0;i<sizeL;i++){
            double temp = 0.0;
            for(double v : getLine(i)){
                temp += Math.abs(v);
            }
            max = Math.max(max,temp);
        }
        norms[2] = max;
        return max;
    }

    public ArrayList<Double> getCol(int nbr){
        ArrayList<Double> result = new ArrayList<>();
        for(int i = 0;i<sizeL;i++){
            result.add(values.get(i*sizeC+nbr));
        }
        return result;
    }
    public ArrayList<Double> getLine(int nbr){
        ArrayList<Double> result = new ArrayList<>();
        for(int i = 0;i<sizeC;i++){
            result.add(values.get(nbr*sizeC+i));
        }
        return result;
    }
    public void setLine(int line,ArrayList<Double> value){
        int j = 0;
        for(double v : value){
            setValue(line,j,v);
            j++;
        }
    }


    public double getValue(int i,int j){
        return values.get(j+i*sizeC);
    }
    public void setValue(int i,int j,double v){
        values.set(j+i*sizeC,v);
    }

    public Matrix copy(){
        return new Matrix(new ArrayList<>(values),sizeL,sizeC);
    }
    public void invert(int line1,int line2){
        ArrayList<Double> temp = getLine(line2);
        setLine(line2,getLine(line1));
        setLine(line1,temp);
    }
    public int getMaxLine(int k){
        int index=0;
        double max = 0.0;
        for(int i=k;i<sizeL;i++){
            double v =getValue(i,k);
            if(v>max){
                max = v;
                index = i;
            }
        }
        return index;
    }

    public Matrix resolveGauss(Vector v){
        if(!square || v.size != sizeC)
            return null;
        Matrix triangle = copy();
        for(int k=0;k<sizeL;k++) {
            if(triangle.getValue(k, k) == 0.0){
                int l = triangle.getMaxLine(k);
                if(triangle.getValue(l,k)==0)
                    return null;
                triangle.invert(k,l);
                v.invert(k,l);
            }

            for (int i = k+1; i < sizeL; i++) {
                double factor = triangle.getValue(i, k) / triangle.getValue(k, k); // 0 a changer par k
                v.values.set(i,v.values.get(i)-factor*v.values.get(k));
                for (int j = 0; j < sizeC; j++) {
                    triangle.setValue(i, j, triangle.getValue(i, j) - factor * triangle.getValue(k, j));// 0 a changer par k
                }
            }
        }

        return triangle;
    }
    public Matrix getTranspose(){
        ArrayList<Double> newArray = new ArrayList<>();
        for(int i=0;i<sizeC;i++){
            newArray.addAll(getCol(i));
        }
        return new Matrix(newArray,sizeC,sizeL);
    }

    public Matrix multiply(Matrix b){
        ArrayList<Double> array = new ArrayList<>();
        for(int j =0;j<sizeL;j++) {
            for (int k = 0; k < b.sizeC; k++) {
                double s = 0;
                for (int i = 0; i < sizeC; i++) {
                    s += getValue(j, i) * b.getValue(i, k);
                }
                array.add(s);
            }
        }
        return new Matrix(array,sizeL,b.sizeC);
    }

    public Matrix invert(){
        if(!square)
            return null;
        ArrayList<Vector> vecs = new ArrayList<>();
        for(int i=0;i<sizeL;i++){
            ArrayList<Double> arrayList = new ArrayList<>();
            for(int j=0;j<sizeL;j++){
                arrayList.add(0.0);
            }
            arrayList.set(i, 1.0);
            vecs.add(new Vector(arrayList,false));
        }
        ArrayList<Vector> result = new ArrayList<>();
        for(int i=0;i<sizeL;i++){
            result.add(Resolve.resolveEquation(this,vecs.get(i)));
        }
        ArrayList<Double> mat = new ArrayList<>();
        for(int i=0;i<sizeL;i++){
            for(int j=0;j<sizeL;j++){
                mat.add(result.get(j).values.get(i));
            }
        }
        return new Matrix(new ArrayList<>(mat),sizeL,sizeC);
    }


    @Override
    public String toString() {
        String s = "";
        for(int i=0;i<sizeL;i++){
            s = s + " |";
            for(int j=0;j<sizeC;j++){
                if(getValue(i,j)>0.0)
                    s = s + " ";
                s = s+"  "+String.format("%.2f", getValue(i,j));
            }
            s = s + " |\n";
        }
        return s;
    }
}
