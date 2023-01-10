package wargame.affichage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameDownBar extends JPanel {
    public GameDownBar(GameWindow parent){
        super();
        Dimension dim = new Dimension(parent.getWidth() * 9/10, parent.getHeight() * 1/10);
        this.setPreferredSize(dim);
        this.setMaximumSize(dim);
        JButton b1 = new JButton("Fin tour (t)");
        add(b1);
        b1.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                parent.gameDisplay.getCarte().inverseFinTour();
            }
        });
    }
}
