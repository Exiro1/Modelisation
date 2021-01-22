package Display;



public class Point {

    double x,y;
    int posX,posY;
    Parameters params;

    public Point(double x, double y, int posX, int posY, Parameters params) {
        this.x = x;
        this.y = y;
        this.posX = posX;
        this.posY = posY;
        this.params = params;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Parameters getParams() {
        return params;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", posX=" + posX +
                ", posY=" + posY +
                '}';
    }

    public void setParams(Parameters params) {
        this.params = params;
    }
}
