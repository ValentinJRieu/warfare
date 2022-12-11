package wargame.affichage;

import javax.print.attribute.standard.JobState;
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
    public GameWindow parent;

    public RCPosition overedCase;
    Game(GameWindow parent) {
        Dimension dim = new Dimension(parent.getWidth() * 8 / 10, parent.getHeight() * 9 / 10);
        ctDim = new Dimension(128, 128);
        jStart = ctDim.width-10;
        jEnd = ctDim.width;
        iStart = ctDim.height - 10;
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
        overedCase = new RCPosition(iStart,jStart);

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        radius = (int)((double)getWidth()/(double)(jEnd-jStart-1)/0.75);
        iEnd = iStart + (int)Math.round(getHeight()/(CaseTable.height(radius/2))) + 1;
        int rows = iEnd;
        int cols = jEnd;
        ct.draw(g2,radius/2, iStart, jStart, rows, cols);
        if(overedCase != null){
            g2.setColor(Color.WHITE);
            g2.draw(CaseTable.createHexagon(new RCPosition(overedCase.i-iStart,overedCase.j - jStart),radius/2));
        }
    }

    public void zoom() {
        //System.out.println("Zooming Size : (" + (iEnd - iStart) + "," + (jEnd - jStart) + ")");
        if (iEnd - iStart > 10 && jEnd - jStart > 10) {
            iStart+=2;
            jStart+=2;
            iEnd-=2;
            jEnd-=2;
        }
        //System.out.println("Zooming Size : (" + (iEnd - iStart) + "," + (jEnd - jStart) + ")\n");
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
        //System.out.println("up");
        if (iStart >= -ct.rows) {
            iStart-=1;
            iEnd-=1;
            parent.frame.repaint();;

        }
    }

    public void down() {
        //System.out.println("--down--");
        //System.out.println("iStart = "+iStart+" iEnd = "+iEnd+" ct.rows = "+ct.rows);
        if (iEnd <= ct.rows*2) {
            iStart+=1;
            iEnd+=1;
            parent.frame.repaint();;
        }
    }

    public void left() {
        //System.out.println("left");
        if (jStart >= -ct.columns) {
            jStart -= 2;
            jEnd -= 2;
            parent.frame.repaint();;
        }

    }

    public void right() {
        //System.out.println("right");
        if (jEnd <= ct.columns*2) {
            jStart += 2;
            jEnd += 2;
            parent.frame.repaint();;
        }
    }

    private boolean contient (int i,int j,int x,int y){
        Polygon p = CaseTable.createHexagon(new RCPosition(i,j),radius/2);
       return p.contains(new Point(x,y));
    }

    public RCPosition getIJFromXY(int startX,int endX,int startY,int endY,int startI,int endI,int startJ,int endJ,int x, int y) {//TODO certaines positions ne sont pas détectée
        int width = endX - startX;
        int height = endY - startY;
        int jSize = endJ - startJ;
        int iSize = endI - startI;
        /*System.out.println(
            "startX: "+
            startX+
            " endX: "+
            endX+
            " startY: "+
            startY+
            " endY: "+
            endY+
            " startI: "+
            startI+
            " endI: "+
            endI+
            " startJ: "+
            startJ+
            " endJ: "+
            endJ+
            " x: "+
            x+
            " y: " +
            y
        );*/
        if(endJ < 0 || endI < 0)return new RCPosition(startI,startJ);
        if(startI >= endI || startJ >= endJ)return new RCPosition(startI,startJ);
        if(endI-startI == 1 && endJ - startJ == 1){
            for(int a = startI-iStart-2;a <= startI-iStart+2;a++){
                for(int b = startJ-jStart-2;b <= startJ-jStart+2;b++){
                    if(contient(a,b,x,y)){
                        return  new RCPosition(a+iStart,b+jStart);
                    }
                }
            }
            return null;
        }
        if(x < startX + width/2){
            endX -= width/2;
            endJ -= jSize/2;
        }else{
            startX += width/2;
            startJ += jSize/2;
        }
        if(y < startY + height/2){
            endY -= height/2;
            endI -= iSize/2;
        }else {
            startY += height/2;
            startI += iSize/2;
        }
        return getIJFromXY(startX,endX,startY,endY,startI,endI,startJ,endJ,x,y);
    }

    public  RCPosition getIJFromXY(int x,int y){
        return getIJFromXY(0,getWidth(),0,getHeight(),iStart-2,iEnd+2,jStart-2,jEnd+2,x,y);
    }

    public Case getCaseFromXY(int x,int y){
        RCPosition rcp = getIJFromXY( x, y);
        if(rcp == null)return null;
        return ct.getCase(rcp.j,rcp.i);
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
