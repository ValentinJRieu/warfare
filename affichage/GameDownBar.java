package wargame.affichage;

import wargame.event.GameEvent;
import wargame.event.GameEventManager;
import wargame.event.GameListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameDownBar extends JPanel {
    private JLabel gameInfo;
    public  GameWindow parent;
    public GameDownBar(GameWindow parent){
        super();
        this.parent = parent;
        Dimension dim = new Dimension(parent.getWidth() * 9/10, parent.getHeight() * 1/10);
        this.setPreferredSize(dim);
        this.setMaximumSize(dim);
        JButton b1 = new JButton("Fin tour (t)");
        gameInfo = new JLabel("<html>Nombre de tours : 0<br>Nombre de Hero restant : 0<br>Nombre d'ennemis restants : 0</html>");
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

                parent.gameDisplay.getCarte().finTour();

            }
        });
        GameEventManager.addListener(new GameListener() {
            @Override
            public void triggered(GameEvent e) {
                update();
            }
        },"UpdateGameInfo");
    }
    public void update()
    {
        gameInfo.setText("<html>Nombre de tours : "+ parent.gameDisplay.getCarte().getTour() +"<br>Nombre de Hero restant : "+ parent.gameDisplay.getCarte().getListeHeros().size() +"<br>Nombre d'ennemis restants : "+ parent.gameDisplay.getCarte().getListeMonstres().size() +"</html>");
        repaint();
    }
}
