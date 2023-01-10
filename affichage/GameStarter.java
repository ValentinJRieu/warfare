package wargame.affichage;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Démarre la partie au click sur un bouton
 */
public class GameStarter extends MouseAdapter {
    JFrame frame;
    GameStarter(JFrame frame){
        this.frame = frame;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        new GameWindow(frame);
    }
}
