package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    Game(JFrame frame){
        frame.setContentPane(this);
        setPreferredSize(new Dimension(Option.WIDTH,Option.HEIGHT));
        frame.pack();

    }
    public void paint(Graphics g){
        super.paint(g);
        Case c = Case.Create();
        c.draw((Graphics2D) g);
    }
}
