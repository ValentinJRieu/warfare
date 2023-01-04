package wargame.affichage;


import wargame.carte.Cellule;

import java.awt.*;
public class Case extends Cellule {
    public Color color,outlineColor;
    public boolean selected,drawOutline;
    Case(){
        color = Color.BLACK;
        outlineColor = Color.WHITE;
        selected = false;
        drawOutline = true;
    }

}
