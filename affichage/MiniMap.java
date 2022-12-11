package wargame.affichage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MiniMap extends JPanel {
    public GameSideBar parent;
    public Game game;

    public int w,h;
    MiniMap(GameSideBar parent){
        super();
        Dimension dim = new Dimension(parent.getWidth(), parent.getHeight()*3/10);
        setSize(dim);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setBackground(Color.DARK_GRAY);
        this.parent = parent;
        game = parent.parent.game;
        MiniMapManager mmm = new MiniMapManager(this);
        addMouseListener(mmm);
        addMouseMotionListener(mmm);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        CaseTable ct = game.ct;
        //System.out.println(getWidth()+"x"+getHeight());
        w = getWidth()/(ct.columns) > 0 ? getWidth()/(ct.columns) : 1;
        h = getHeight()/(ct.rows)>0 ? getHeight()/(ct.rows) : 1;
        for(int i = 0;i < ct.rows;i++) {
            for (int j = 0; j < ct.columns; j++) {
                Rectangle r = new Rectangle();
                r.setRect((double)j*w+(double)(getWidth()/2)-(double)(w*ct.columns/2),(double)i*h+(double)(getHeight()/2)-(double)(h* ct.rows/2),w,h);
                if(((i == game.getiStart() || i == game.getiEnd()) && j >= game.getjStart() && j <= game.getjEnd()) || ((i >= game.getiStart() && i <= game.getiEnd() && (j == game.getjStart() || j == game.getjEnd())))){
                    g2.setColor(Color.WHITE);
                }else{
                    g2.setColor(ct.getCase(j,i).color);
                }
                g2.fill(r);
            }
        }

    }
}
