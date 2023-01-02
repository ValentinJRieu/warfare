package wargame;

import java.io.FileNotFoundException;
import wargame.carte.Carte;

public class Main {
	public static void main(String[] args) {
		Carte carte = new Carte();
		try {
			carte.loadCarte("map1.txt");
		} catch(FileNotFoundException e) {
			throw new RuntimeException(e);
		}

	}
}
