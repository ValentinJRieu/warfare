package wargame.carte.types;

import wargame.carte.Terrain;

public class TypeVide implements Terrain {

	@Override public int getCoutDeDeplacement() {
		return 0;
	}

	@Override public int getBonusDefense() {
		return 0;
	}
}
