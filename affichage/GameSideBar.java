package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class GameSideBar extends JPanel {
    public GameSideBar(){
        super();
        setPreferredSize(new Dimension(Option.WIDTH/4,Option.HEIGHT * 3/4));
        setBackground(new Color(30,60,90));
    }
}
