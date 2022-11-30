package wargame.carte;

import java.awt.*;
import java.util.Map;
import wargame.ICarte;
import wargame.IConfig;
import wargame.affichage.PanneauJeu;
import wargame.soldats.Heros;
import wargame.soldats.Soldat;

public class Carte implements ICarte, IConfig {

	private Map<String, Element> carte;
	/* Du coup, une carte serait consid�r�e comme une Map<String, Element>, o� le String est le toString
	*  de la position de l'�l�ment sur la map, si d'autres impl�mentations vous semblent meilleures,
	* Ne pas h�siter � les proposer */

	@Override public Element getElement(Position pos) {
		return null;
	}

	@Override public Position positionSetVide() {
		return null;
	}

	@Override public Position positionSetVide(Position pos) {
		return null;
	}

	@Override public Heros trouveHeros() {
		return null;
	}

	@Override public Heros trouveHeros(Position pos) { // renvoi un h�ros ssi on clique sur un heros
		return null;
	}

	@Override public boolean deplaceSoldat(Position pos, Soldat soldat) {
		return false;
	}

	@Override public void mort(Soldat perso) {

	}

	@Override public boolean actionHeros(Position pos, Position pos2) {
		return false;
	}

	@Override public void jouerSoldats(PanneauJeu pj) {

	}

	@Override public void toutDessiner(Graphics g) {

	}
}
