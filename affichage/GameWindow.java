package wargame.affichage;

import javax.swing.*;
import java.awt.*;

/**
 * L'affichage du jeu
 */
public class GameWindow extends JPanel {
    public JFrame frame;
    public GameDisplay gameDisplay;

    public GameSideBar gsb;

    public GameDownBar gdb;

    public MiniMap mm;


    /**
     * Met en place les éléments nécessaire pour voir le jeu
     * @param frame la fenêtre
     */
    public GameWindow(JFrame frame){
        this.frame = frame;
        frame.setContentPane(this);
        frame.pack();
        setBackground(Color.black);
        gameDisplay = new GameDisplay(this);
        gsb = new GameSideBar(this);
        gdb = new GameDownBar(this);
        setMaximumSize(new Dimension(frame.getWidth(),frame.getHeight()));
        setLayout(new BorderLayout());
        //------Plateau de jeu------//
        add(gameDisplay,BorderLayout.CENTER);
        //--------------------------//

        //-------Barre du coté------//
        add(gsb,BorderLayout.EAST);
        //--------------------------//

        //------Barre du bas------//
        add(gdb,BorderLayout.SOUTH);
        //-------------------------//
        setBackground(Color.BLUE);
        frame.pack();
        mm = new MiniMap(gsb);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        gsb.add(mm,c);
        frame.pack();
    }

    /**
     * Affiche les different objets nécessaire.
     * @param g Les <code>Graphics</code>
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gsb.paintComponent(g);
        gameDisplay.paintComponents(g);
        gdb.paintComponents(g);
        mm.paintComponent(g);

    }
}
