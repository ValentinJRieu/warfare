package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    MainMenu(JFrame frame){
        frame.setContentPane(this);
        setPreferredSize(new Dimension(Option.WIDTH,Option.HEIGHT));
        setBackground(new Color(255,100,50));
        JButton newButton = new JButton("NEW");
        JButton loadButton = new JButton("LOAD");
        JButton settingsButton = new JButton("SETTINGS");
        SettingsOpener sBo = new SettingsOpener(frame);
        GameStarter gs = new GameStarter(frame);
        settingsButton.addMouseListener(sBo);
        newButton.addMouseListener(gs);
        this.add(newButton);
        this.add(loadButton);
        this.add(settingsButton);
        frame.pack();
    }
}
