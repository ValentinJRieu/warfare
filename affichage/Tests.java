package wargame.affichage;

import javax.swing.*;

public class Tests {
    public static void main(String[] args){
        JFrame frame = new JFrame("WAR GAME");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new MainMenu(frame);

    }
}
