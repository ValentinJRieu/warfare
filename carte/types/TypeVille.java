package wargame.carte.types;

import wargame.carte.Terrain;

public class TypeVille implements Terrain {

	private int coutDeDeplacement = 3;

	private int bonusAttaque = 2;

	@Override public int getCoutDeDeplacement() {
		return this.coutDeDeplacement;
	}

	@Override public int getBonusDefense() {
		return this.bonusAttaque;
	}

}
