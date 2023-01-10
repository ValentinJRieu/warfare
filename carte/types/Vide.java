package wargame.carte.types;

import wargame.carte.Infranchissable;
import wargame.carte.Terrain;

import java.awt.*;
import java.io.Serializable;

public class Vide extends Terrain implements Infranchissable, Serializable {
	private static final Color color =  new Color(12, 12, 12);

	public Vide() {
		this.coutDeplacement = 0;
		this.coutDeplacement = 0;
	}

	@Override
	public Color getImage() {
		return color;
	}
}
