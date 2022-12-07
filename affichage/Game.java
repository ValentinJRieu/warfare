package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    CaseTable ct;
    Dimension ctDim;
    private int iStart = 0, jStart = 0,iEnd = 52, jEnd = 52, radius, minZoomI = 20,minZoomJ = 20;

    Game(GameWindow parent) {
        Dimension dim = new Dimension(parent.getWidth() * 8 / 10, parent.getHeight() * 9 / 10);
        ctDim = new Dimension(52,52);
        this.setPreferredSize(dim);
        this.setMaximumSize(dim);
        ct = new CaseTable(ctDim.width, ctDim.height);
        GameEventHandler geh = new GameEventHandler(this);
        addMouseListener(geh);
        addMouseMotionListener(geh);
        addMouseWheelListener(geh);
        addKeyListener(geh);
        setBackground(Color.darkGray);

    }

    public void paint(Graphics g) {//TODO
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        int halfX = 0,halfY = 0;
        double dbRadius = 0;
        if(getWidth() > getHeight()){
            System.out.println("w = "+getWidth()+" & end-start = "+(jEnd-jStart));
            radius = (getWidth() / ((jEnd-jStart)) );
            dbRadius = (double)radius * 3/8;
            radius = radius * 3/8;
            System.out.println("radius = "+radius+" dbRadius = "+dbRadius);
        }

        if(getWidth() < getHeight()){
            radius = getHeight() / (iEnd - iStart);
            int height = (int)((Math.sqrt(3)*radius));
        }
        //radius = getWidth() < getHeight() ? getHeight() / (jEnd-jStart) : getWidth() / (iEnd-iStart);
        if(getWidth() > getHeight() && jEnd-jStart == ctDim.width){
            halfX  = (getWidth() - (int)(dbRadius / 0.75) * ctDim.width) /2;
        }
        if(getWidth() <= getHeight() && iEnd-iStart == ctDim.height){
            halfY  = (getWidth() - (int)(dbRadius / 0.75) * ctDim.width) /2;
        }
        ct.draw(g2,halfX+0,halfY+0, radius, iStart, jStart, iStart + (iEnd-iStart), jStart + (jEnd-jStart));
    }

    public void zoom() {
        System.out.println("Zooming Size : ("+(iEnd - iStart)+","+(jEnd - jStart)+")");
        if(iEnd - iStart > 20 && jEnd - jStart > 20){
            iStart++;
            jStart++;
            iEnd--;
            jEnd--;
        }
        repaint();
    }

    public void deZoom() {
        if(iStart > 0){
            iStart--;
        }
        if(iEnd < ct.rows){
            iEnd++;
        }
        if(jStart > 0){
            jStart--;
        }
        if(jEnd < ct.columns){
            jEnd++;
        }
        repaint();
    }

    public void up() {
        if (iStart > 0) {
            iStart--;
            repaint();
        }
    }

    public void down() {
        if (iEnd < ct.rows) {
            iStart++;
            iEnd++;
            repaint();
        }
    }

    public void left() {
        if (jStart > 0) {
            jStart--;
            repaint();
        }

    }

    public void right() {
        if (jEnd < ct.columns - 2) {
            jStart++;
            jEnd++;
            repaint();
        }
    }

    public RCPosition getIJFromXY(int x, int y) {
        int i = x / (CaseTable.width(radius) * 2 / 4), j = y / CaseTable.height(radius);
        if (i % 2 == 0) {
            j = (y + CaseTable.height(radius) / 2) / CaseTable.height(radius);
        }

        return new RCPosition(i, j);

    }
}
