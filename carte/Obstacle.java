package wargame.carte;
import java.awt.Color;

public class Obstacle extends Element {

	public enum TypeObstacle {
		EAU(COULEUR_EAU), FORET(COULEUR_FORET), ROCHER(COULEUR_ROCHER), VIDE(COULEUR_VIDE);
		private final Color COULEUR;

		TypeObstacle(Color couleur) {
			COULEUR = couleur;
		}

		public static TypeObstacle getObstacleAlea() {
			return values()[(int) (Math.random() * values().length)];
		}
	}

	private TypeObstacle TYPE;
	private Position pos;
	Obstacle(TypeObstacle type, Position pos) { TYPE = type; this.pos = pos; }
	public String toString() { return ""+TYPE; }

	public TypeObstacle getTypeObstacle() {
		return TYPE;
	}
}

