package wargame;

import java.awt.*;
import java.io.FileNotFoundException;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;
import wargame.affichage.GameDisplay;
import wargame.affichage.MainMenu;
import wargame.affichage.Option;
import wargame.carte.Carte;
import wargame.soldats.Heros;
import wargame.soldats.Monstres;

import javax.swing.*;

public class Game {

	private Carte map;

	private List<Heros> J1;

	private List<Monstres> J2;

	private int tour = 0;

	private boolean estTerminee = false;

	public Game() {
	/*	map = new Carte();
		J1 = new ArrayList<>();
		J2 = new ArrayList<>();
		try {
			map.loadCarte("map1.txt");
		} catch(FileNotFoundException e) {
			throw new RuntimeException(e);
		}

	 */
	}



	public void run() {
		JFrame frame = new JFrame("La Bagarre");
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(Option.WIDTH,Option.HEIGHT));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		new MainMenu(frame);
		/*
		long now;
		long update;
		long toWait;

		final int FPS = 24;
		final long OPTIMAL = 1000000000 / FPS;



		while (!estTerminee) {
			now = System.nanoTime();
			System.out.println("testing of while");

			if(map.isTourHeros()) {
				while (map.isTourHeros()) {

				}
			}

			update = System.nanoTime() - now;
			toWait = (OPTIMAL - update) / 1000000;

			try {
				Thread.sleep(toWait);
			} catch( Exception e) {
				e.printStackTrace();
			}
		}*/
	}

	public Carte getCarte() {
		return map;
	}
}
