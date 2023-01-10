package wargame.carte.types;

import wargame.carte.Infranchissable;
import wargame.carte.Terrain;

import java.awt.*;
import java.io.Serializable;

public class EauProfonde extends Terrain implements Infranchissable, Serializable {

    private static final Color color = new Color(0, 0, 102);

    public EauProfonde() {
        this.coutDeplacement = 0;
        this.bonusDefense = 0;
    }

    @Override
    public Color getImage() {
        return color;
    }
}
