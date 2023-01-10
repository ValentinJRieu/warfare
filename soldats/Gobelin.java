package wargame.soldats;

import wargame.carte.Position;

import java.awt.*;


public class Gobelin extends Monstres {

	private static final Color image = new Color(0, 77, 0);

	public Gobelin() {
		pointsDeVie = TypesM.GOBELIN.getPoints();
		porteeVisuelle = TypesM.GOBELIN.getPortee();
		puissance = TypesM.GOBELIN.getPuissance();
		tir = TypesM.GOBELIN.getTir();
		deplacement = TypesM.GOBELIN.getDeplacement();
		deplacementRestant = deplacement;
	}

	public Gobelin(Position pos) {
		this();
		position = pos;
	}

	/**
	 * Renvoie l'image associé à l'unité
	 * @return
	 */
	@Override
	public Color getImage() {
		return image;
	}


}
