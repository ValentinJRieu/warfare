package wargame.carte.types;

import wargame.carte.Infranchissable;
import wargame.carte.Terrain;

import java.awt.*;

public class Vide extends Terrain implements Infranchissable {
	private static final Color color =  Color.BLACK;

	public Vide() {
		this.coutDeplacement = 0;
		this.coutDeplacement = 0;
	}

	@Override
	public Color getImage() {
		return color;
	}
}
