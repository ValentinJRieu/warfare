package wargame.soldats;

public class SoldatFactory {

	public static Soldat getSoldat(String soldatTypeStr) {
		if (soldatTypeStr == null) {
			return null;
		} else if (soldatTypeStr.equalsIgnoreCase("ELFE")) {
			return new Elfe();
		} else if (soldatTypeStr.equalsIgnoreCase("GOBELIN")) {
			return new Gobelin();
		} else if (soldatTypeStr.equalsIgnoreCase("HOBBIT")) {
			return new Hobbit();
		} else if (soldatTypeStr.equalsIgnoreCase("HUMAIN")) {
			return new Humain();
		} else if (soldatTypeStr.equalsIgnoreCase("NAIN")) {
			return new Nain();
		} else if (soldatTypeStr.equalsIgnoreCase("ORC")) {
			return new Orc();
		} else if (soldatTypeStr.equalsIgnoreCase("TROLL")) {
			return new Troll();
		}
		return null;
	}
}
