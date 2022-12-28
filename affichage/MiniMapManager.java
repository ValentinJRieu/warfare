package wargame.affichage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MiniMapManager extends MouseAdapter {

    MiniMap mm;

    public MiniMapManager(MiniMap mm){
        this.mm = mm;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int a = (e.getX()-(int)((double)(mm.getWidth()/2)-(double)(mm.w*mm.game.ct.columns/2)))/2;
        int b = (e.getY()-(int)((double)(mm.getHeight()/2)-(double)(mm.h*mm.game.ct.rows/2)))/2;
        if(a < 0 || b < 0){
            return;
        }
        mm.game.goTo(b,a);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int a = (e.getX()-(int)((double)(mm.getWidth()/2)-(double)(mm.w*mm.game.ct.columns/2)))/2;
        int b = (e.getY()-(int)((double)(mm.getHeight()/2)-(double)(mm.h*mm.game.ct.rows/2)))/2;
        if(a < 0 || b < 0){
            return;
        }
        mm.game.goTo(b,a);
    }
}
