package wargame.carte;

import wargame.carte.types.TypeCime;
import wargame.carte.types.TypeForet;
import wargame.carte.types.TypeMontagne;
import wargame.carte.types.TypePlaine;
import wargame.carte.types.TypeVide;
import wargame.carte.types.TypeVille;

public class TerrainFactory {

	public static Terrain getTerrain(String terrainTypeStr) {
		if (terrainTypeStr == null) {
			return null;
		}
		if (terrainTypeStr.equalsIgnoreCase("CIME")) {
			return new TypeCime();
		} else if (terrainTypeStr.equalsIgnoreCase("FORET")) {
			return new TypeForet();
		} else if (terrainTypeStr.equalsIgnoreCase("MONTAGNE")) {
			return new TypeMontagne();
		} else if (terrainTypeStr.equalsIgnoreCase("PLAINE")) {
			return new TypePlaine();
		} else if (terrainTypeStr.equalsIgnoreCase("VILLE")) {
			return new TypeVille();
		} else {
			return new TypeVide();
		}
	}

}
