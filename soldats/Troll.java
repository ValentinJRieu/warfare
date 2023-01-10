package wargame.soldats;

import wargame.carte.Position;

import java.awt.*;

public class Troll extends Monstres{

	private static final Color image = Color.red;
	public Troll() {
		pointsDeVie = TypesM.TROLL.getPoints();
		porteeVisuelle = TypesM.TROLL.getPortee();
		puissance = TypesM.TROLL.getPuissance();
		tir = TypesM.TROLL.getTir();
		deplacement = TypesM.TROLL.getDeplacement();
	}
	public Troll(Position pos) {
		this();
		position = pos;
	}

	@Override
	public Color getImage() {
		return null;
	}
}
