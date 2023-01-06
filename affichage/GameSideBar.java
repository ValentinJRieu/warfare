package wargame.affichage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameSideBar extends JPanel {
    public GameWindow parent;

    public BufferedImage background;
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
        try {
            background = ImageIO.read(new File("textures/gui/side_bar.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g2.drawImage(background.getScaledInstance(getWidth(),getHeight(),Image.SCALE_FAST),0,0,null);
    }
}
