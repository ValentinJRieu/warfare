package wargame.affichage;

import java.awt.*;

public class CaseTable {
    private final Case cases[][];
    int columns;
    int rows;
    CaseTable(int rows,int columns){
        this.rows = rows;
        this.columns = columns;
        cases = new Case[columns][rows];
        /*for(int j = 0;j < rows;j++){
            cases[0][j] = new Case();
            cases[0][j].color = new Color(100,100,100);
        }
        for(int j = 0;j < rows;j++){
            cases[columns-1][j] = new Case();
            cases[columns-1][j].color = new Color(100,100,100);
        }*/
        for(int i = 0;i < columns;i++){
            /*cases[i][0] = new Case();
            cases[i][0].color = new Color(100,100,100);
            cases[i][rows-1] = new Case();
            cases[i][rows-1].color = new Color(100,100,100);*/
            for(int j = 0;j < rows;j++){
                cases[i][j] = new Case();
                cases[i][j].color = new Color((int)(Math.random() * 150),(int)(Math.random() * 50),(int)(Math.random() * 150));
            }
        }
    }

    public void setDrawOutline(boolean drawOutline){
        for(int i = 0;i < columns;i++){
            for(int j = 0;j < rows;j++){
                cases[i][j].drawOutline = drawOutline;
            }
        }
    }

    public Case getCase(int j,int i) {
        if(j < columns && i < rows && j >= 0 && i >= 0)
            return cases[j][i];
        else
            return null;
    }

    public static Point flat_hex_corner(Point center,double size,int i) {
        var angle_deg = 60 * i;
        var angle_rad = Math.PI / 180 * angle_deg;
        return new Point((int)(center.x + size * Math.cos(angle_rad)),(int)(center.y + size * Math.sin(angle_rad)));
    }

    public void draw(Graphics2D g2,int radius,int iStart, int jStart, int rows,int columns){
        for (int j = jStart; j < columns; j++) {
            for(int i = iStart;i < rows;i++) {
                Case c = getCase(j, i);
                if (c != null)
                    g2.setColor(c.color);
                else
                    g2.setColor(Color.BLACK);
                Polygon p = createHexagon(new RCPosition(i-iStart, j-jStart), radius);
                g2.fill(p);
                g2.setColor(Color.BLACK);
                g2.draw(p);

            }
        }
    }

    public static Polygon createHexagon(Point center,int radius){
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++) {
            Point po = flat_hex_corner(center,radius,i);
            p.addPoint(po.x, po.y);
        }
        return p;
    }

    public static Polygon createHexagon(RCPosition rcp,int radius){
        int height = (int)CaseTable.height(radius);
        int width = (int)CaseTable.width(radius);
        int x = rcp.j * (int)(width*0.75);
        int y = rcp.i * height;
        if(rcp.j %2 == 1)
            y += height/2;
        return CaseTable.createHexagon(new Point(x,y),(radius));
    }

    public static double height(double radius){return Math.sqrt(3) * radius;}

    public static double width(double radius){return radius*2;}
}
