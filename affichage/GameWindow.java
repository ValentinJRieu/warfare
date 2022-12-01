package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel {
    JFrame frame;
    public GameWindow(JFrame frame){
        this.frame = frame;
        frame.setContentPane(this);
        frame.pack();
        Game game = new Game(this);
        GameSideBar gsb = new GameSideBar(this);

        GameDownBar gdb = new GameDownBar(this);
        setMaximumSize(new Dimension(frame.getWidth(),frame.getHeight()));
        setLayout(new BorderLayout());
        GridBagConstraints gc = new GridBagConstraints();
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
