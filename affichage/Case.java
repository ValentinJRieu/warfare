package wargame.affichage;


import java.awt.*;
public class Case {
    public Color color,outlineColor;
    public boolean selected,drawOutline;
    Case(){
        color = Color.BLACK;
        outlineColor = Color.WHITE;
        selected = false;
        drawOutline = true;
    }

}
