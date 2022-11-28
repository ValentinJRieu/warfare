package wargame.affichage;

import java.awt.*;

public class Case extends Polygon {
    Case(Point center,int radius){
        super();
        for (int i = 0; i < 6; i++) {
            int xVal = (int) (center.x + radius
                    * Math.cos(i * 2 * Math.PI / 6D));
            int yVal = (int) (center.y + radius
                    * Math.sin(i * 2 * Math.PI / 6D));
            this.addPoint(xVal, yVal);
        }
    }
    public void draw(Graphics2D g){
        g.fill(this);
    }
    public static Case Create(){
        return new Case(new Point(50,45),50);
    }
}
