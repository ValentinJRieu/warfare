package wargame.soldats;

import wargame.carte.Position;

import java.awt.*;

public class Nain extends Heros{

	private static final Color image = new Color(255, 102, 0);


	public Nain() {
		pointsDeVie = TypesH.NAIN.getPoints();
		porteeVisuelle = TypesH.NAIN.getPortee();
		puissance = TypesH.NAIN.getPuissance();
		tir = TypesH.NAIN.getTir();
		deplacement = TypesH.NAIN.getDeplacement();
		deplacementRestant = deplacement;
	}
	public Nain(Position pos) {
		this();
		this.position = pos;
	}

	@Override
	public Color getImage() {
		return image;
	}
}
