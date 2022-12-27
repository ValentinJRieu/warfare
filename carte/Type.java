package wargame.carte;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Type {
	PLAINE,
	MONTAGNE, // infranchissable
	FORET,
	RIVIERE,
	VILLE,
	VIDE;

	private static final List<Type> VALEURS =
		Collections.unmodifiableList(Arrays.asList(values()));
	private static final int TAILLES = VALEURS.size();
	private static final Random RANDOM = new Random();

	public static Type randomType()  {
		return VALEURS.get(RANDOM.nextInt(TAILLES));
	}

	@Override public String toString() {
		switch (this) {
			case PLAINE:
				return "PLAINE";
			case MONTAGNE:
				return "MONTAGNE";
			case FORET:
				return "FORET";
			case RIVIERE:
				return "RIVIERE";
			case VILLE:
				return "VILLE";
			default:
				return "VIDE";
		}
	}
}
