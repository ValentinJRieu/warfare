package wargame.soldats;

import wargame.carte.Position;

import java.awt.*;

/**
 * La classe de l'elfe
 */
public class Elfe extends Heros{

	private static final Color image = new Color(102, 255, 102);

	/**
	 * Initialise la classe Elfe
	 */
	public Elfe() {
		pointsDeVie = TypesH.ELFE.getPoints();
		porteeVisuelle = TypesH.ELFE.getPortee();
		puissance = TypesH.ELFE.getPuissance();
		tir = TypesH.ELFE.getTir();
		deplacement = TypesH.ELFE.getDeplacement();
		deplacementRestant = deplacement;
	}

	/**
	 * Initialise à la position donné
	 * @param pos la position
	 */
	public Elfe(Position pos) {
		this();
		this.position = pos;
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
