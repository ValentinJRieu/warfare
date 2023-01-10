package wargame.affichage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameDownBar extends JPanel {
    private JLabel gameInfo;
    public GameDownBar(GameWindow parent){
        super();
        Dimension dim = new Dimension(parent.getWidth() * 9/10, parent.getHeight() * 1/10);
        this.setPreferredSize(dim);
        this.setMaximumSize(dim);
        JButton b1 = new JButton("Fin tour (t)");
        gameInfo = new JLabel("<html>Nombre de tours : 0<br>Nombre de soldat restant : 0<br>Nombre de Hero restant : 0<br>Nombre d'ennemis restants : 0</html>");
        add(b1);
        add(gameInfo);
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
        GraphicEventManager.addListener(new GraphicListener() {
            @Override
            public void triggered(GraphicEvent e) {
                update();
            }
        },"UpdateGameInfo");
    }
    public void update()
    {
        gameInfo.setText("<html>Nombre de tours : "+0+"<br>Nombre de soldat restant : "+0+"<br>Nombre de Hero restant : "+0+"<br>Nombre d'ennemis restants : "+0+"</html>");
        repaint();
    }
}
