package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    CaseTable ct;
    private int iStart = 0, jStart = 0, iSize = 10, jSize = 10, radius;

    Game(GameWindow parent) {
        Dimension dim = new Dimension(parent.getWidth() * 8 / 10, parent.getHeight() * 9 / 10);
        this.setPreferredSize(dim);
        this.setMaximumSize(dim);
        ct = new CaseTable(512, 512);
        GameEventHandler geh = new GameEventHandler(this);
        addMouseListener(geh);
        addMouseMotionListener(geh);
        addMouseWheelListener(geh);
        addKeyListener(geh);

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        radius = getWidth() < getHeight() ? getHeight() / (jSize) : getWidth() / (iSize);
        ct.draw(g2, radius, iStart, jStart, iStart + iSize, jStart + jSize);
    }

    public void zoom() {
        if (jSize > 9 && iSize > 9) {
            jSize -= 2;
            iSize -= 2;
        }
        if (iStart > 0 && jStart > 0) {
            jStart--;
            iStart--;
        }
        repaint();
    }

    public void deZoom() {
        if (jSize < ct.columns - jStart - 2 && iSize < ct.rows - iStart - 2) {
            jSize += 2;
            iSize += 2;
        }
        if (iStart > ct.rows - iSize && jStart > ct.columns - jSize) {
            jStart++;
            iStart++;
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
        if (iStart < ct.rows - iSize - 2) {
            iStart++;
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
        if (jStart < ct.columns - jSize - 2) {
            jStart++;
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
