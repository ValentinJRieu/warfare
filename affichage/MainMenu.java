package wargame.affichage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

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
    /*public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.setClip(CaseTable.createHexagon(new Point(200,200),50));
            BufferedImage ig = ImageIO.read(new File("C:\\Users\\NicoAngel\\IdeaProjects\\warfare\\textures\\terrain\\grass_1.png"));
            TexturePaint tp = new TexturePaint(ig,new Rectangle(0,0,500, 500));
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(tp);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fill(CaseTable.createHexagon(new Point(300,300),500));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }*/
}
