package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class GameDownBar extends JPanel {
    public GameDownBar(GameWindow parent){
        super();
        Dimension dim = new Dimension(parent.getWidth() * 9/10, parent.getHeight() * 1/10);
        this.setPreferredSize(dim);
        this.setMaximumSize(dim);
    }
}
