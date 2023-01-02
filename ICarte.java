package wargame;

import wargame.affichage.PanneauJeu;
import wargame.carte.Position;
import wargame.carte.Terrain;
import wargame.soldats.Heros;
import wargame.soldats.Soldat;

import javax.swing.text.Element;
import java.awt.*;

public interface ICarte {
    Terrain getElement(Position pos);

    Position trouvePositionVide(); // Trouve aléatoirement une position vide sur la carte

    Position trouvePositionVide(Position pos); // Trouve une position vide choisie

    // aléatoirement parmi les 8 positions adjacentes de pos
    Heros trouveHeros(); // Trouve aléatoirement un héros sur la carte

    Heros trouveHeros(Position pos); // Trouve un héros choisi aléatoirement

    // parmi les 8 positions adjacentes de pos
    boolean deplaceSoldat(Position pos, Soldat soldat);

    void mort(Soldat perso);

    boolean actionHeros(Position pos, Position pos2);

    void jouerSoldats(PanneauJeu pj);

    void toutDessiner(Graphics g);
}
