package wargame.carte.types;

import wargame.carte.Infranchissable;
import wargame.carte.Terrain;

import java.awt.*;

public class Montagne extends Terrain implements Infranchissable {

	private static final Color color =  new Color(179, 134, 0);

	public Montagne() {
		this.coutDeplacement = 4;
		this.bonusDefense = 4;
	}

	@Override
	public Color getImage() {
		return color;
	}
}
