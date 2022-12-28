package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class Tests {
    public static void main(String[] args){
        JFrame frame = new JFrame("WAR GAME");
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(Option.WIDTH,Option.HEIGHT));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new MainMenu(frame);

    }
}
