package wargame.carte;

import wargame.carte.types.*;

public class TerrainFactory {

	public static Terrain getTerrain(String terrainTypeStr) {
		if (terrainTypeStr == null) {
			return null;
		} else if (terrainTypeStr.equalsIgnoreCase("FORET")) {
			return new Foret();
		} else if (terrainTypeStr.equalsIgnoreCase("MONTAGNE")) {
			return new Montagne();
		} else if (terrainTypeStr.equalsIgnoreCase("PLAINE")) {
			return new Plaine();
		} else if (terrainTypeStr.equalsIgnoreCase("VILLE")) {
			return new Ville();
		} else if (terrainTypeStr.equalsIgnoreCase("EAUPROFONDE")) {
			return new EauProfonde();
		} else if (terrainTypeStr.equalsIgnoreCase("RIVIERE")) {
			return new Riviere();
		}

		return new Vide();
	}
}
