package wargame.carte.types;

import wargame.carte.Terrain;

public class TypePlaine implements Terrain {

	private int coutDeDeplacement = 1;

	private int bonusAttaque = 0;

	@Override public int getCoutDeDeplacement() {
		return this.coutDeDeplacement;
	}

	@Override public int getBonusDefense() {
		return this.bonusAttaque;
	}
}
