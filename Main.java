package wargame;

import wargame.affichage.MainMenu;
import wargame.affichage.Option;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("La Bagarre");
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(Option.WIDTH,Option.HEIGHT));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new MainMenu(frame);
    }
}
