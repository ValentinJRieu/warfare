package wargame.affichage;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Gestion de l'ouverture de la page des Options
 */
public class SettingsOpener extends MouseAdapter {
    JFrame frame;
    SettingsOpener(JFrame frame){
        this.frame = frame;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        new SettingsMenu(frame);
    }
}
