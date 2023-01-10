package wargame.affichage;

import java.util.*;

public class GraphicEventManager {
    private static final Map<String,ArrayList<GraphicListener>> listeners = new TreeMap<String, ArrayList<GraphicListener>>();
    public static void addListener(GraphicListener gl,String Event)
    {
        ArrayList<GraphicListener> a = listeners.get(Event);
        if(a != null)
        {
            a.add(gl);
        }else
        {
            ArrayList<GraphicListener> b = new ArrayList<>();
            b.add(gl);
            listeners.put(Event,b);
        }
    }
    public static void removeListener(GraphicListener gl,String Event)
    {
        ArrayList<GraphicListener> a = listeners.get(Event);
        if(a != null)
        {
            a.remove(gl);
        }
    }
    public static void removeAllListener(String Event)
    {
        ArrayList<GraphicListener> a = listeners.get(Event);
        if(a != null)
        {
            a.clear();
            listeners.remove(Event,a);
        }
    }
    public static void FireEvent(String event,GraphicEvent ge)
    {
        ArrayList<GraphicListener> a = listeners.get(event);
        if(a == null)
        {
            return;
        }
        a.forEach((listener) -> listener.triggered(ge));
    }
}
