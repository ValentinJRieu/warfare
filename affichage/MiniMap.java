package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class MiniMap extends JPanel {
    public GameSideBar parent;
    public Game game;
    MiniMap(GameSideBar parent){
        super();
        Dimension dim = new Dimension(parent.getWidth(), parent.getHeight()*3/10);
        setSize(dim);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setBackground(Color.DARK_GRAY);

        this.parent = parent;
        game = parent.parent.game;

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        CaseTable ct = game.ct;
        int w = getWidth()/(ct.columns),h = getHeight()/(ct.rows);
        for(int i = 0;i < ct.rows;i++) {
            for (int j = 0; j < ct.columns; j++) {
                Rectangle r = new Rectangle();
                r.setRect((double)j*w+(double)(getWidth()/2)-(double)(w*ct.columns/2),(double)i*h+(double)(getHeight()/2)-(double)(h* ct.rows/2),w,h);
                if(i > game.getiStart() && i < game.getiEnd() && j < game.getjEnd() && j > game.getjStart()){
                    g2.setColor(Color.WHITE);
                }else{
                    g2.setColor(ct.getCase(j,i).color);
                }
                g2.fill(r);
            }
        }

    }
}
