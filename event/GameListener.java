package wargame.event;

/**
 * L'écouteur d'un évènement du jeu
 */
public interface GameListener {
    /**
     * Est appelé si l'évènement auquel il est accroché et appelé
     * @param e l'évènement attacher à l'appel
     */
    void triggered(GameEvent e);
}
