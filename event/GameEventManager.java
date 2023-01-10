package wargame.event;

import java.util.*;

public class GameEventManager {
    private static final Map<String,ArrayList<GameListener>> listeners = new TreeMap<String, ArrayList<GameListener>>();
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
    public static void removeListener(GameListener gl,String Event)
    {
        ArrayList<GameListener> a = listeners.get(Event);
        if(a != null)
        {
            a.remove(gl);
        }
    }
    public static void removeAllListener(String Event)
    {
        ArrayList<GameListener> a = listeners.get(Event);
        if(a != null)
        {
            a.clear();
            listeners.remove(Event,a);
        }
    }
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
