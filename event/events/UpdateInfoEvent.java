package wargame.event.events;

import wargame.event.GameEvent;
import wargame.event.GraphicEvent;
import wargame.carte.Carte;

public class UpdateInfoEvent implements GameEvent {
    public Carte carte;
    public UpdateInfoEvent(Carte c)
    {
        carte = c;
    }
}
