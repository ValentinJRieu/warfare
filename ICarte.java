package wargame;

import wargame.carte.Position;
import wargame.carte.Terrain;
import wargame.soldats.Heros;

public interface ICarte {
    Terrain getElement(Position pos);

    Position trouvePositionVide(); // Trouve aléatoirement une position vide sur la carte

    Position trouvePositionVide(Position pos); // Trouve une position vide choisie

    // aléatoirement parmi les 8 positions adjacentes de pos
    Heros trouveHeros(); // Trouve aléatoirement un héros sur la carte

    Heros trouveHeros(Position pos); // Trouve un héros choisi aléatoirement

    boolean action(Position cible);
}
