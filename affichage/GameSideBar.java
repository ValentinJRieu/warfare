package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class GameSideBar extends JPanel {
    public GameWindow parent;
    public GameSideBar(GameWindow parent){
        super();
        Dimension dim = new Dimension(parent.getWidth() * 2/10, parent.getHeight() * 9/10);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setBackground(new Color(30,60,90));
        BorderLayout bl = new BorderLayout();
        bl.setHgap(0);
        bl.setVgap(0);
        setLayout(bl);
        this.parent = parent;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
