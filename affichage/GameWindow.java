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
        GroupLayout gl = new GroupLayout(this);
        GroupLayout.SequentialGroup pg1 = gl.createSequentialGroup()
                .addComponent(game)
                .addComponent(gsb);
        GroupLayout.ParallelGroup pg2 = gl.createParallelGroup(GroupLayout.Alignment.BASELINE).addGroup(pg1).addComponent(gdb);
        gl.setHorizontalGroup(pg2);
        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addGroup(pg2)
        );
        setLayout(gl);
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.insets = new Insets(0,0,0,0);
        gc.ipady = gc.anchor = GridBagConstraints.CENTER;
        gc.weightx = 4;
        gc.weighty = 4;
        //------Plateau de jeu------//
        //pos  : (0,0)
        //size : (3,3)
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 3;
        gc.gridheight = 3;
        add(game);

        //--------------------------//


        //--------Minimap--------//
        //pos  : (3,0)
        //size : (1,1)
        gc.gridx = 3;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        add(mm);
        //-----------------------//


        //-------Barre du coté------//
        //pos  : (3,1)
        //size : (1,2)
        gc.gridx = 3;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.gridheight = 3;
        add(gsb);
        //--------------------------//



        //------Barre du bas------//
        //pos  : (0,3)
        //size : (3,1)
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 3;
        gc.gridheight = 1;
        add(gdb);
        //-------------------------//
        frame.setContentPane(this);
        setBackground(Color.BLUE);
        frame.pack();
    }
}
