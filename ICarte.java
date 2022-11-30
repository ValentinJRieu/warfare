package wargame;
import java.awt.Graphics;
import wargame.affichage.PanneauJeu;
import wargame.carte.Element;
import wargame.carte.Position;
import wargame.soldats.Heros;
import wargame.soldats.Soldat;

public interface ICarte {
	Element getElement(Position pos);
	Position positionSetVide(); // Trouve aléatoirement une position vide sur la carte
	Position positionSetVide(Position pos); // Trouve une position vide choisie
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
