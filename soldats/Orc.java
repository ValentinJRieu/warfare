package wargame.soldats;

import wargame.carte.Position;

import java.awt.*;

public class Orc extends Monstres{
	private static final Color image = Color.darkGray;
	public Orc() {
		pointsDeVie = TypesM.ORC.getPoints();
		porteeVisuelle = TypesM.ORC.getPortee();
		puissance = TypesM.ORC.getPuissance();
		tir = TypesM.ORC.getTir();
	}
	public Orc(Position pos) {
		this();
		position = pos;
	}

	@Override
	public Color getImage() {
		return image;
	}
}
