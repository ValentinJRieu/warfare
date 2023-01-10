package wargame.soldats;

import wargame.carte.Position;

import java.awt.*;

public class Elfe extends Heros{

	private static final Color image = Color.GREEN;
	public Elfe() {
		pointsDeVie = TypesH.ELFE.getPoints();
		porteeVisuelle = TypesH.ELFE.getPortee();
		puissance = TypesH.ELFE.getPuissance();
		tir = TypesH.ELFE.getTir();
		deplacement = TypesH.ELFE.getDeplacement()*5;
		deplacementRestant = deplacement;
	}
	public Elfe(Position pos) {
		this();
		this.position = pos;
	}



	@Override
	public Color getImage() {
		return image;
	}
}
