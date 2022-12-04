package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel {
    public JFrame frame;
    public Game game;



    public GameWindow(JFrame frame){
        this.frame = frame;
        frame.setContentPane(this);
        frame.pack();
        game = new Game(this);
        GameSideBar gsb = new GameSideBar(this);

        GameDownBar gdb = new GameDownBar(this);
        setMaximumSize(new Dimension(frame.getWidth(),frame.getHeight()));
        setLayout(new BorderLayout());
        //------Plateau de jeu------//
        add(game,BorderLayout.CENTER);
        //--------------------------//

        //-------Barre du coté------//
        add(gsb,BorderLayout.EAST);
        //--------------------------//

        //------Barre du bas------//
        add(gdb,BorderLayout.SOUTH);
        //-------------------------//
        setBackground(Color.BLUE);
        frame.pack();
        MiniMap mm = new MiniMap(gsb);
        gsb.add(mm,BorderLayout.NORTH);
        frame.pack();
    }
}
