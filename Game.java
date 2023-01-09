package wargame;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import wargame.affichage.GameDisplay;
import wargame.carte.Carte;
import wargame.soldats.Heros;
import wargame.soldats.Monstres;

public class Game {

	private Carte map;

	private List<Heros> J1;

	private List<Monstres> J2;

	private int tour = 0;

	private boolean tourHumain = true;

	private boolean estTerminee = false;

	public Game() {
		Carte carte = new Carte();
		J1 = new ArrayList<>();
		J2 = new ArrayList<>();
		try {
			carte.loadCarte("map1.txt");
		} catch(FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void run() {
		while (!estTerminee && !J1.isEmpty() && !J2.isEmpty()) {

		}

	}

	public Carte getCarte() {
		return map;
	}
}
