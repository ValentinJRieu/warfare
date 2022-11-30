package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel {
    public GameWindow(JFrame frame){
        Game game = new Game();
        GameSideBar gsb = new GameSideBar();
        MiniMap mm = new MiniMap();
        GameDownBar gdb = new GameDownBar();
        setPreferredSize(new Dimension(Option.WIDTH,Option.HEIGHT));
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.insets = new Insets(0,0,0,0);
        gc.ipady = gc.anchor = GridBagConstraints.CENTER;
        gc.weightx = 4;
        gc.weighty = 4;
        //-----------//
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.gridheight = 2;
        add(game,gc);
        gc.gridx = 2;
        gc.gridy = 0;
        gc.gridwidth = 1;
        add(gsb,gc);
        gc.gridx = 2;
        gc.gridy = 2;
        gc.gridwidth = 1;
        add(mm,gc);
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 2;
        add(gdb,gc);
        frame.setContentPane(this);
        setBackground(Color.BLUE);
        frame.pack();

    }
}
