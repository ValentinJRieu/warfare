package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class GameSideBar extends JPanel {
    public GameSideBar(GameWindow parent){
        super();
        Dimension dim = new Dimension(parent.getWidth() * 2/10, parent.getHeight() * 9/10);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setBackground(new Color(30,60,90));

    }
}
