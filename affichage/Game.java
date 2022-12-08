package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    CaseTable ct;
    Dimension ctDim;
    private int iStart = 0;
    private int jStart = 0;
    private int iEnd = 64;
    private int jEnd = 64;
    private int radius;
    private final int minZoomI = 20;
    private final int minZoomJ = 20;

    Game(GameWindow parent) {
        Dimension dim = new Dimension(parent.getWidth() * 8 / 10, parent.getHeight() * 9 / 10);
        ctDim = new Dimension(64, 64);
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


    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        int halfX = 0, halfY = 0, rows = 0, cols = 0;
        double dbRadius = 0;
        rows = iStart + (iEnd - iStart);
        cols = jStart + (jEnd - jStart);
        if (getWidth() > getHeight()) {
            dbRadius = ((getWidth() / 0.75) / ((double) (jEnd - jStart)));
            radius = (int) dbRadius;
            System.out.println(CaseTable.height(dbRadius/2) + "height = "+getHeight());
            if(iEnd < ctDim.height){
                iEnd = iStart + 3 + (int)(getHeight()/CaseTable.height(dbRadius/2));
                System.out.println(iEnd - iStart);
            }
        }

        if (getWidth() < getHeight()) {
            radius = getHeight() / (iEnd - iStart);
            int height = (int) ((Math.sqrt(3) * radius));
        }
        if (getWidth() > getHeight() && jEnd - jStart == ctDim.width) {
            halfX = (int) ((getWidth() - ((radius * ctDim.width) * 0.75)) / 2) +radius;
        }
        if (getWidth() > getHeight() && jEnd - jStart < ctDim.width - 2) {
            cols += 2;
            if (jStart > 0)
                jStart -= 1;
            jEnd += 1;
        }
        if (getWidth() <= getHeight() && iEnd - iStart == ctDim.height) {
            halfY = (getWidth() - (int) (dbRadius * 0.75) * ctDim.width) / 2;
        }
        //System.out.println("hX = "+halfX);
        ct.draw(g2, halfX, halfY, dbRadius / 2.0, iStart, jStart, rows, cols);
        //ct.drawLine(g2,0,0,dbRadius,0,0,52);
    }

    public void zoom() {
        System.out.println("Zooming Size : (" + (iEnd - iStart) + "," + (jEnd - jStart) + ")");
        if (iEnd - iStart > 20 && jEnd - jStart > 20) {
            iStart++;
            jStart++;
            iEnd--;
            jEnd--;
        }
        System.out.println("Zooming Size : (" + (iEnd - iStart) + "," + (jEnd - jStart) + ")\n");
        repaint();
    }

    public void deZoom() {
        if (iStart > 0) {
            iStart--;
        }
        if (iEnd < ct.rows) {
            iEnd++;
        }
        if (jStart > 0) {
            jStart--;
        }
        if (jEnd < ct.columns) {
            jEnd++;
        }
        repaint();
    }

    public void up() {
        System.out.println("up");
        if (iStart > 0) {
            iStart--;
            repaint();
        }
    }

    public void down() {
        System.out.println("down");
        if (iEnd < ct.rows) {
            iStart++;
            iEnd++;
            repaint();
        }
    }

    public void left() {
        System.out.println("left");
        if (jStart > 0) {
            jStart -= 2;
            jEnd -= 2;
            System.out.println(jStart + "->" + jEnd);
            repaint();
        }

    }

    public void right() {
        System.out.println("right");
        if (jEnd < ct.columns - 2) {
            jStart += 2;
            jEnd += 2;
            System.out.println(jStart + "->" + jEnd);
            repaint();
        }
    }

    public RCPosition getIJFromXY(int x, int y) {
        int i = 0, j = 0;
        return new RCPosition(i, j);

    }
}
