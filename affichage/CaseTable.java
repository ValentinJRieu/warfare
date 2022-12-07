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

    public void draw(Graphics2D g2,int startX,int startY,int radius,int iStart, int jStart, int rows,int columns){
        int x = startX,y = startY;
        int width = width(radius), height = height(radius);
        for (int j = jStart; j < columns; j++) {
            if(j%2 == 0){
                y=startY;
            }else{
                y =startY + height/2;
            }
            for(int i = iStart;i < rows;i++) {
                g2.setColor(getCase(j,i).color);
                g2.fill(createHexagon(new Point(x,y),radius));
                y += height;
            }
            x+=width*3/4;
        }
    }



    public static Polygon createHexagon(Point center,int radius){
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++) {
            int xVal = (int) (center.x + radius * Math.cos(i * 2 * Math.PI / 6D));
            int yVal = (int) (center.y + radius * Math.sin(i * 2 * Math.PI / 6D));
            p.addPoint(xVal, yVal);
        }
        return p;
    }

    public static int height(int radius){return Math.abs(((int) (radius * Math.sin(2 * Math.PI / 6D))) - ((int) (radius * Math.sin(10 * Math.PI / 6D))));}

    public static int width(int radius){return radius*2;}
}
