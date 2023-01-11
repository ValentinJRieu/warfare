package wargame.affichage;

public class Circle {
    private int centerX;
    private int centerY;
    private int radius;
    public Circle()
    {}

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setRadius(double radius) {
        this.radius = (int)radius;
    }
}
