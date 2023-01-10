package wargame.affichage;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Démarre la partie au click sur un bouton
 */
public class GameLoaderOpener extends MouseAdapter {
    JFrame frame;
    GameLoaderOpener(JFrame frame){
        this.frame = frame;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Option.oldScreen = 0;
        new LoadMenu(frame);
    }
}
