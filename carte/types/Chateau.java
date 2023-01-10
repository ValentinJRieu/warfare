package wargame.carte.types;

import java.awt.*;
import wargame.carte.Terrain;

public class Chateau extends Terrain {

	private static final Color color = new Color(50,50,250);

	public Chateau() {
		this.bonusDefense = 2;
		this.coutDeplacement = 1;
	}

	@Override public Color getImage() {
		return color;
	}

}
