package wargame.affichage;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameStarter extends MouseAdapter {
    JFrame frame;
    GameStarter(JFrame frame){
        this.frame = frame;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        new Game(frame);
    }
}
