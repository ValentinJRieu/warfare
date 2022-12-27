package wargame.carte;

import java.awt.*;

public class Element {
	protected static final Color COULEUR_ROCHER = Color.GRAY;
	protected static final Color COULEUR_FORET = Color.GREEN;
	protected static final Color COULEUR_EAU = Color.BLUE;
	protected static final Color COULEUR_VIDE = Color.MAGENTA; // pour le moment, voir si à changer
	private int coutDeplacement;
	private int bonusDefense;
}
