package Display;

import Calcul.Matrix;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DisplayManager {


    MFrame frame;

    boolean grid = true;

    static int IMG_WIDTH = 800,IMG_HEIGHT = 600;
    int marginX=50,marginY=50;
    BufferedImage currentImage;

    ArrayList<Plot> plots;

    public DisplayManager(){

        frame = new MFrame();
        plots = new ArrayList<>();
        createNewFrame();
    }
    public void createNewFrame(){
       currentImage = new BufferedImage(DisplayManager.IMG_WIDTH, DisplayManager.IMG_HEIGHT,BufferedImage.TYPE_INT_ARGB);
    }

    public void show(){
        update();
        frame.getPanel().setImage(currentImage);
        frame.getPanel().repaint();
    }

    public void update(){
        createNewFrame();
        Graphics g = currentImage.getGraphics();
        for(Plot p : plots){
            p.drawLayers(g);
        }
        g.dispose();
    }

    public Plot getPlot(int index){
        return plots.get(index);
    }


    public void drawAxes(Graphics g,int x,int y){
        //g.drawRect(marginX,marginY,IMG_WIDTH-2*marginX,IMG_HEIGHT-2*marginY);
        Color c = g.getColor();
        g.setColor(Color.BLACK);
        g.drawLine(marginX,IMG_HEIGHT-marginY-y,IMG_WIDTH-marginX+x,IMG_HEIGHT-marginY-y);//H
        g.drawLine(marginX+x,IMG_HEIGHT-marginY,marginX+x,marginY);//V
        g.setColor(c);
    }

    public void addPlot(Plot p){
        plots.add(p);
    }









}
