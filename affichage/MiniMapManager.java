package wargame.affichage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * La gestion des evenements de la minimap
 */
public class MiniMapManager extends MouseAdapter {

    MiniMap mm;

    public MiniMapManager(MiniMap mm){
        this.mm = mm;
    }

    /**
     * En cas de click sur la minimap
     * @param e L'event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int a = (e.getX()-(int)((double)(mm.getWidth()/2)-(double)(mm.w*mm.gameDisplay.ct.columns/2)))/mm.w;
        int b = (e.getY()-(int)((double)(mm.getHeight()/2)-(double)(mm.h*mm.gameDisplay.ct.rows/2)))/mm.h;
        System.out.println(mm.w + ":" + mm.h);
        if(mm.gameDisplay.ct.columns%2 == 0)
        {
            a += a%2;
        }
        else{
            if(a%2 == 0)
            {
                a++;
            }
        }
        if(a < 0 || b < 0){
            return;
        }
        mm.gameDisplay.goTo(b,a);
    }

    /**
     * Lorsqu'on maintien le click et qu'on bouge la souris
     * @param e L'event
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        int a = (e.getX()-(int)((double)(mm.getWidth()/2)-(double)(mm.w*mm.gameDisplay.ct.columns/2)))/mm.w;
        int b = (e.getY()-(int)((double)(mm.getHeight()/2)-(double)(mm.h*mm.gameDisplay.ct.rows/2)))/mm.h;
        if(mm.gameDisplay.ct.columns%2 == 0)
        {
            a += a%2;
        }
        else{
            if(a%2 == 0)
            {
                a++;
            }
        }
        if(a < 0 || b < 0){
            return;
        }
        mm.gameDisplay.goTo(b,a);
    }
}
