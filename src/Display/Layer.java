package Display;

import Calcul.Matrix;

import java.awt.*;
import java.util.ArrayList;

public class Layer {

    ArrayList<Point> points;
    boolean linked;
    Color cLine;
    boolean showPoint = true;



    public Layer(ArrayList<Point> points, boolean linked, Color cLine) {
        this.points = points;
        this.linked = linked;
        this.cLine = cLine;
    }

    public Layer(Matrix m, boolean linked, Color cLine) {
        this.points = new ArrayList<>();
        for(int i=0;i<m.sizeL;i++){
            this.points.add(new Point(m.getValue(i,0),m.getValue(i,1),-1,-1,new Parameters(cLine,Shape.CIRCLE,showPoint)));
        }
        this.linked = linked;
        this.cLine = cLine;
    }

    public Layer(ArrayList<Point> points) {
        this.points = points;
        this.linked = false;
    }

    public void update(double min, double max, double start, double end, double H, double W){
        for(Point p : points){
            if(p.getX()<=end && p.getX()>=start){
                p.setPosX((int) (( W/(end-start))*(p.getX()-start)));
                p.setPosY((int) (( H/(max-min))  *(p.getY()-min)));
            }
        }
    }

    public double getMax(double start, double end){
        double max = Double.MIN_VALUE;
        for(Point p : points){
            if(p.getX()<=end && p.getX()>=start)
                max = Math.max(p.getY(),max);
        }
        return max;
    }
    public double getMin(double start, double end){
        double min = Double.MAX_VALUE;
        for(Point p : points){
            if(p.getX()<=end && p.getX()>=start)
                min = Math.min(p.getY(),min);
        }
        return min;
    }

    public void drawLayer(Graphics g, int xoffset,int yoffset){

        int lastx=-1,lasty=-1;

        for(Point p : points){
            if(p.getPosX()==-1 || p.getPosY()==-1 )
                continue;
            int x = p.posX+xoffset;
            int y = yoffset-p.posY;

            g.setColor(p.getParams().color);
            if(showPoint)
                g.drawOval(x, y,3,3);
            if(linked && lastx != -1 && lasty != -1)
                g.drawLine(x,y,lastx,lasty);
            System.out.println(p.posX+xoffset+" "+(-p.posY+yoffset));
            System.out.println(p.toString());


            lastx = x;
            lasty = y;
        }


    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public boolean isLinked() {
        return linked;
    }

    public void setLinked(boolean linked) {
        this.linked = linked;
    }

    public Color getcLine() {
        return cLine;
    }

    public void setcLine(Color cLine) {
        this.cLine = cLine;
    }

    public boolean isShowPoint() {
        return showPoint;
    }

    public void setShowPoint(boolean showPoint) {
        this.showPoint = showPoint;
    }
}
