import Calcul.Matrix;
import Calcul.Resolve;
import Display.DisplayManager;
import Display.Layer;
import Display.MFrame;
import Display.Plot;

import java.awt.*;
import java.util.ArrayList;

public class Main {




    public static void main(String[] args){



        /*Matrix m = new Matrix(new ArrayList<>(){{ add(1.0);add(-3.0);add(9.0);add(1.0);add(-1.0);add(1.0);add(1.0);add(0.0);add(0.0);add(1.0);add(2.0);add(4.0);}},4,3);
        Matrix m2 = new Matrix(new ArrayList<>(){{ add(17.0);add(1.0);add(-1.0);add(7.0);}},4,1);
        Matrix m3 = new Matrix(new ArrayList<>(){{ add(-3.0);add(17.0);add(-1.0);add(1.0);add(0.0);add(-1.0);add(2.0);add(7.0);}},4,2);

        System.out.println(m.toString());
        Matrix Q = m.getTranspose().multiply(m);
        System.out.println(Q.toString());
        Matrix S = m.getTranspose().multiply(m2);
        System.out.println(S.toString());
        //System.out.println(Resolve.invert(Q).multiply(S));

        System.out.println(Resolve.resolveApprox(m3,2).toString());
        System.out.println(Resolve.resolveInterpolation(m3).toString());
        */

        //Matrix m3 = new Matrix(new ArrayList<>(){{ add(-3.0);add(17.0);add(-1.0);add(1.0);add(0.0);add(-1.0);add(2.0);add(7.0);}},4,2);
        Matrix m3 = new Matrix(new ArrayList<>(){{ add(0.0);add(1.0);add(1.0);add(-1.0);add(2.0);add(2.0);add(3.0);add(-2.0);}},4,2);
        Matrix m4 = Resolve.resolveInterpolation(m3);

        Matrix m5 = Resolve.resolveApprox(m3,1);
        Matrix m6 = Resolve.resolveApprox(m3,2);
        Matrix m7 = Resolve.resolveApprox(m3,3);

        DisplayManager d = new DisplayManager();
        d.addPlot(new Plot(400,300,400,300,50,50,0,3));
        d.getPlot(0).addLayer(new Layer(m3,true,Color.BLACK));
        d.getPlot(0).addLayer(new Layer(Resolve.getFunction(m6,50, 0, 5),true,Color.BLUE));
        d.getPlot(0).getLayers().get(1).setShowPoint(false);

        d.addPlot(new Plot(0,300,400,300,50,50,0,3));
        d.getPlot(1).addLayer(new Layer(m3,true,Color.BLACK));
        d.getPlot(1).addLayer(new Layer(Resolve.getFunction(m5,50, 0, 5),true,Color.BLUE));
        d.getPlot(1).getLayers().get(1).setShowPoint(false);

        d.addPlot(new Plot(400,600,400,300,50,50,0,3));
        d.getPlot(2).addLayer(new Layer(m3,true,Color.BLACK));
        d.getPlot(2).addLayer(new Layer(Resolve.getFunction(m4,50, 0, 5),true,Color.BLUE));
        d.getPlot(2).getLayers().get(1).setShowPoint(false);

        d.addPlot(new Plot(0,600,400,300,50,50,0,3));
        d.getPlot(3).addLayer(new Layer(m3,true,Color.BLACK));
        d.getPlot(3).addLayer(new Layer(Resolve.getFunction(m7,50, 0, 5),true,Color.BLUE));
        d.getPlot(3).getLayers().get(1).setShowPoint(false);
        //d.drawPoints(m3,0.0,3.0, Color.BLACK);
        //d.drawFunction(m4,50,0.0,3.0,Color.BLUE);
        d.show();


    }


}
