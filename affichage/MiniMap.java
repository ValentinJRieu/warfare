package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class MiniMap extends JPanel {
    MiniMap(GameSideBar parent){
        super();
        Dimension dim = new Dimension(parent.getWidth(), parent.getHeight()*1/4);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setBackground(Color.BLACK);
    }
}
