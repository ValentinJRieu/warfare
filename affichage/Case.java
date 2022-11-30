package wargame.affichage;

import java.awt.*;
import java.awt.geom.Point2D;

public class Case extends Polygon {
    public static final int SIZE = 50;
    private Point center;
    public Color couleur;
    Case(Point center,int radius){
        super();
        this.center = center;
        for (int i = 0; i < 6; i++) {
            int xVal = (int) (center.x + radius
                    * Math.cos(i * 2 * Math.PI / 6D));
            int yVal = (int) (center.y + radius
                    * Math.sin(i * 2 * Math.PI / 6D));
            this.addPoint(xVal, yVal);
        }
        this.couleur = new Color(0,0,0);
    }
    public void draw(Graphics2D g){
        g.setColor(couleur);
        g.fill(this);
        g.setColor(new Color(0,0,couleur.getBlue()));
        g.draw(this);
    }

    public static int height(int radius){
        Case c = new Case(new Point(0,0),radius);
        return Math.abs(c.ypoints[1]-c.ypoints[5]);
    }

    public static int width(int radius){
        Case c = new Case(new Point(0,0),radius);
        return Math.abs(c.xpoints[0]-c.xpoints[3]);
    }

    public static Case Create(Point centre){
        return new Case(centre,SIZE);
    }
}
