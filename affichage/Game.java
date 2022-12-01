package wargame.affichage;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    Game(GameWindow parent){
        Dimension dim = new Dimension(parent.getWidth() * 8/10,parent.getHeight() * 9/10);
        this.setPreferredSize(dim);
        this.setMaximumSize(dim);
    }
    public void paint(Graphics g){
        super.paint(g);
        int height = Case.height(Case.SIZE);
        int width = Case.width(Case.SIZE);
        int x =0,y=0;
        Case c;
        for(int j=0;j<50;j++){
            for(int i=0;i<50;i++){
                c = Case.Create(new Point(x,y));
                if(i % 2 == 0){
                    c.couleur = new Color(100,100,100);
                    y+= height/2;
                }else{
                    y-= height/2;
                }
                x+=width * 3/4;
                c.draw((Graphics2D) g);
            }
            y += height;
            x = 0;
        }
    }
}
