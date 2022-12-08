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
        for(int j = 0;j < rows;j++){
            cases[0][j] = new Case();
            cases[0][j].color = new Color(100,100,100);
        }
        for(int j = 0;j < rows;j++){
            cases[columns-1][j] = new Case();
            cases[columns-1][j].color = new Color(100,100,100);
        }
        for(int i = 1;i < columns-1;i++){
            cases[i][0] = new Case();
            cases[i][0].color = new Color(100,100,100);
            cases[i][rows-1] = new Case();
            cases[i][rows-1].color = new Color(100,100,100);
            for(int j = 1;j < rows-1;j++){
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
        return cases[j][i];
    }

    public static Point flat_hex_corner(Point center,double size,int i) {
        var angle_deg = 60 * i;
        var angle_rad = Math.PI / 180 * angle_deg;
        return new Point((int)(center.x + size * Math.cos(angle_rad)),(int)(center.y + size * Math.sin(angle_rad)));
    }

    public void draw(Graphics2D g2,int startX,int startY,double radius,int iStart, int jStart, int rows,int columns){
        int x = startX,y = startY;
        double width = width(radius), height = height(radius);
        for (int j = jStart; j < columns; j++) {
            if(j%2 == 0){
                y=startY;
            }else{
                y =startY + (int)(height/2);
            }
            for(int i = iStart;i < rows;i++) {
                g2.setColor(getCase(j,i).color);
                g2.fill(createHexagon(new Point(x,y),radius));
                y += height;
            }
            x+=width*3/4;
        }
    }

    public void drawLine(Graphics2D g2,int startX,int startY,double radius,int i,int jStart,int columns){
        int x = startX,y = startY;
        System.out.println("Start : x = "+x+", y="+y+" radius = "+(int)radius+" radius * columns = "+((int)radius*3/4 * columns));
        for(int j = jStart;j < columns;j++){
            g2.setColor(getCase(j,i).color);
            g2.fill(createHexagon(new Point(x,y),radius));
            x+=radius*3/4;
            System.out.println(x);
        }
    }


    public static Polygon createHexagon(Point center,double radius){
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++) {
            Point po = flat_hex_corner(center,radius,i);
            p.addPoint(po.x, po.y);
        }
        return p;
    }

    public static double height(double radius){return Math.sqrt(3) * radius;}

    public static double width(double radius){return radius*2;}
}
