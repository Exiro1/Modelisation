package Display;

import Calcul.Matrix;

import java.awt.*;
import java.util.ArrayList;

public class Plot {

    ArrayList<Layer> layers;
    int startX,startY,w,h;
    int marginX=50,marginY=50;
    boolean grid;
    double min,max,start,end;

    public Plot(int startX, int startY, int w, int h, int marginX, int marginY,int start,int end) {
        this.startX = startX;
        this.startY = startY;
        this.w = w;
        this.h = h;
        this.marginX = marginX;
        this.marginY = marginY;
        this.layers = new ArrayList<>();
        this.start = start;
        this.end = end;
    }

    public void addLayer(Layer l){
        layers.add(l);
        min = getMin();
        max = getMax();

        for(Layer layer : layers){
            layer.update(min,max,start,end,h-2*marginX,w-2*marginY);
        }
    }

    public void drawAxes(Graphics g){
        //g.drawRect(marginX,marginY,IMG_WIDTH-2*marginX,IMG_HEIGHT-2*marginY);
        Color c = g.getColor();
        g.setColor(Color.BLACK);
        int x = (int) (( (w-2*marginY)/(end-start))*(0-start));
        int y = (int) (( (h-2*marginX)/(max-min))*(0-min));
        g.drawLine(startX+marginX,startY-marginY-y,startX+w-marginX,startY-marginY-y);//H
        g.drawLine(startX+marginX+x,startY-marginY,startX+marginX+x,startY-h+marginY);//V
        g.setColor(c);
    }

    public double getMax(){
        double max = Double.MIN_VALUE;
        for(Layer l : layers){
            max = Math.max(l.getMax(start,end),max);
        }
        return max;
    }
    public double getMin(){
        double min = Double.MAX_VALUE;
        for(Layer l : layers){
            min = Math.min(l.getMin(start,end),min);
        }
        return min;
    }

    public void drawLayers(Graphics g){
        g.setColor(Color.BLACK);
        g.drawRect(startX,startY-h,w,h);
        drawAxes(g);
        if(grid){

        }
        for(Layer l : layers){
            l.drawLayer(g,startX+marginX,startY-marginY);
        }
    }

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    public void setLayers(ArrayList<Layer> layers) {
        this.layers = layers;
    }
}
