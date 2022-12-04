package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class MiniMap extends JPanel {
    public GameSideBar parent;
    MiniMap(GameSideBar parent){
        super();
        Dimension dim = new Dimension(parent.getWidth(), parent.getHeight()*3/10);
        setSize(dim);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setBackground(Color.BLACK);

        this.parent = parent;

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        CaseTable ct = parent.parent.game.ct;
        int radius = getHeight()/(ct.rows+2);
        if(radius < 2){
            for(int i = 0;i < ct.rows;i++) {
                for (int j = 0; j < ct.columns; j++) {
                    Rectangle r = new Rectangle();
                    r.setRect((double)j+(getWidth()/2.0)-(ct.columns/2.0),(double)i+(getHeight()/2.0)-(ct.rows/2.0),1,1);
                    g2.setColor(ct.getCase(j,i).color);
                    g2.fill(r);
                }
            }

        }else{
            parent.parent.game.ct.draw(
                    g2,
                   radius,
                    0,
                    0,
                    ct.rows,
                    ct.columns
        );
        }

    }
}
