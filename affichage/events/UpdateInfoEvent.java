package wargame.affichage.events;

import wargame.affichage.GraphicEvent;
import wargame.carte.Carte;

public class UpdateInfoEvent implements GraphicEvent {
    public Carte carte;
    public UpdateInfoEvent(Carte c)
    {
        carte = c;
    }
}
