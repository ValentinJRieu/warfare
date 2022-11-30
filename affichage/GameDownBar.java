package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class GameDownBar extends JPanel {
    public GameDownBar(){
        super();
        this.setPreferredSize(new Dimension(Option.WIDTH * 3/4,Option.HEIGHT * 1/4));
    }
}
