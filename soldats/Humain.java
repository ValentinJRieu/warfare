package wargame.soldats;

import wargame.carte.Position;

import java.awt.*;

public class Humain extends Heros{

	private static final Color image =  new Color(255, 153, 153);

	public Humain() {
		pointsDeVie = TypesH.HUMAIN.getPoints();
		porteeVisuelle = TypesH.HUMAIN.getPortee();
		puissance = TypesH.HUMAIN.getPuissance();
		tir = TypesH.HUMAIN.getTir();
		deplacement = TypesH.HUMAIN.getDeplacement();
		deplacementRestant = deplacement;
	}

	public Humain(Position pos) {
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
