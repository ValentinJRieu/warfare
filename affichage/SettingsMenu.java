package wargame.affichage;

import javax.swing.*;
import java.awt.*;

/**
 * Menu des option
 * Abandonné faute d'options à mettre dedans
 */
public class SettingsMenu extends JPanel{
    JFrame frame;
    JComboBox<String> jb;
    SettingsMenu(JFrame frame){
        this.frame = frame;
        frame.setContentPane(this);
        setPreferredSize(new Dimension(Option.WIDTH,Option.HEIGHT));
        setBackground(new Color(100,100,100));
        this.jb = new JComboBox<String>(Option.RESOLUTIONS);
        frame.add(jb);

        frame.pack();
    }

}
