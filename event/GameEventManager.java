package wargame.event;

import java.util.*;

/**
 * Gérant des évènement du jeu ainsi que de ses écouteurs
 */
public class GameEventManager {
    private static final Map<String,ArrayList<GameListener>> listeners = new TreeMap<String, ArrayList<GameListener>>();

    /**
     * Ajout un écouteur gl sur l'évènement Event
     * @param gl l'écouteur
     * @param Event l'évènement
     */
    public static void addListener(GameListener gl,String Event)
    {
        ArrayList<GameListener> a = listeners.get(Event);
        if(a != null)
        {
            a.add(gl);
        }else
        {
            ArrayList<GameListener> b = new ArrayList<>();
            b.add(gl);
            listeners.put(Event,b);
        }
    }
    /**
     * Supprime un écouteur gl de l'évènement Event
     * @param gl l'écouteur
     * @param Event l'évènement
     */
    public static void removeListener(GameListener gl,String Event)
    {
        ArrayList<GameListener> a = listeners.get(Event);
        if(a != null)
        {
            a.remove(gl);
        }
    }

    /**
     * Supprime TOUT les écouteurs d'un évènement
     * @param Event
     */
    public static void removeAllListener(String Event)
    {
        ArrayList<GameListener> a = listeners.get(Event);
        if(a != null)
        {
            a.clear();
            listeners.remove(Event,a);
        }
    }

    /**
     * Déclanche l'appel de l'évènement event avec ge pour données
     * @param event l'évènement
     * @param ge les données de l'évènement
     */
    public static void FireEvent(String event,GameEvent ge)
    {
        ArrayList<GameListener> a = listeners.get(event);
        if(a == null)
        {
            return;
        }
        a.forEach((listener) -> listener.triggered(ge));
    }
}
