package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    CaseTable ct;
    Dimension ctDim;
    private int iStart;
    private int jStart;
    private int iEnd;
    private int jEnd;
    private int radius;
    private final int minZoomI = 20;
    private final int minZoomJ = 20;
    public GameWindow parent;
    Game(GameWindow parent) {
        Dimension dim = new Dimension(parent.getWidth() * 8 / 10, parent.getHeight() * 9 / 10);
        ctDim = new Dimension(128, 128);
        jStart = 0;
        jEnd = ctDim.width;
        iStart = 0;
        iEnd = ctDim.height;
        this.setPreferredSize(dim);
        this.setMaximumSize(dim);
        ct = new CaseTable(ctDim.width, ctDim.height);
        GameEventHandler geh = new GameEventHandler(this);
        addMouseListener(geh);
        addMouseMotionListener(geh);
        addMouseWheelListener(geh);
        addKeyListener(geh);
        setBackground(Color.BLACK);
        this.parent = parent;

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int halfX = 0, halfY = 0;
        double dbRadius = 0;
        int cols = jEnd;
        dbRadius = ((double)getWidth()/0.75) /((double)jEnd-(double)jStart);
        System.out.println(getWidth() + "/"+getHeight() + " Radius = "+dbRadius);
        iEnd = iStart + (int)(getHeight()/dbRadius);
        int rows = iEnd;
        halfX = (int)(getWidth()/0.75) - (int)dbRadius * (jEnd-jStart);
        ct.draw(g2, 0, 0, dbRadius / 2.0, iStart, jStart, rows*2, cols*2);
    }

    public void zoom() {
        System.out.println("Zooming Size : (" + (iEnd - iStart) + "," + (jEnd - jStart) + ")");
        if (iEnd - iStart > 10 && jEnd - jStart > 10) {
            iStart+=2;
            jStart+=2;
            iEnd-=2;
            jEnd-=2;
        }
        System.out.println("Zooming Size : (" + (iEnd - iStart) + "," + (jEnd - jStart) + ")\n");
        parent.frame.repaint();;
    }

    public void deZoom() {
        if (iStart > -ct.rows) {
            iStart-=2;
        }
        if (iEnd < ct.rows*2) {
            iEnd+=2;
        }
        if (jStart > -ct.columns) {
            jStart-=2;
        }
        if (jEnd < ct.columns*2) {
            jEnd+=2;
        }
        parent.frame.repaint();;
    }

    public void up() {
        System.out.println("up");
        if (iStart >= -ct.rows) {
            iStart-=1;
            iEnd-=1;
            parent.frame.repaint();;
            
        }
    }

    public void down() {
        System.out.println("--down--");
        System.out.println("iStart = "+iStart+" iEnd = "+iEnd+" ct.rows = "+ct.rows);
        if (iEnd <= ct.rows*2) {
            iStart+=1;
            iEnd+=1;
            parent.frame.repaint();;
        }
    }

    public void left() {
        System.out.println("left");
        if (jStart >= -ct.columns) {
            jStart -= 2;
            jEnd -= 2;
            parent.frame.repaint();;
        }

    }

    public void right() {
        System.out.println("right");
        if (jEnd <= ct.columns*2) {
            jStart += 2;
            jEnd += 2;
            parent.frame.repaint();;
        }
    }

    public RCPosition getIJFromXY(int x, int y) {
        int i = 0, j = 0;
        return new RCPosition(i, j);

    }

    public int getiStart() {
        return iStart;
    }

    public int getjStart() {
        return jStart;
    }

    public int getiEnd() {
        return iEnd;
    }

    public int getjEnd() {
        return jEnd;
    }

    public void goTo(int i,int j){
        int iSize = iEnd - iStart,jSize = jEnd - jStart;
        iStart = i - iSize/2;
        iEnd = i + iSize/2;
        jStart = j - jSize/2;
        jEnd = j + jSize/2;
        parent.frame.repaint();
    }
}
