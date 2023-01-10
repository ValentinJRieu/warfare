package wargame.soldats;

import wargame.carte.Position;

import java.awt.*;

public class Hobbit extends Heros{

	private static final Color image = new Color(51, 51, 0) ;


	public Hobbit() {
		pointsDeVie = TypesH.HOBBIT.getPoints();
		porteeVisuelle = TypesH.HOBBIT.getPortee();
		puissance = TypesH.HOBBIT.getPuissance();
		tir = TypesH.HOBBIT.getTir();
		deplacement = TypesH.HOBBIT.getDeplacement();
		deplacementRestant = deplacement;
	}

	public Hobbit(Position pos) {
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
