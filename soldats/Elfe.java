package wargame.soldats;

import wargame.carte.Position;

import java.awt.*;

public class Elfe extends Heros{

	private static final Color image = Color.GREEN;
	public Elfe() {
		pointsDeVie = TypesH.ELFE.getPoints();
		porteeVisuelle = TypesH.ELFE.getPortee();
		puissance = TypesH.ELFE.getPuissance();
		tir = TypesH.ELFE.getTir();
	}
	public Elfe(Position pos) {
		this();
		this.position = pos;
	}

	@Override public int getTour() {
		return 0;
	}

	@Override public void joueTour(int tour) {

	}

	@Override public void combat(Soldat soldat) {
		/* TODO */
	}

	@Override public void seDeplace(Position newPos) {
		position = newPos;
	}

	@Override public void meurt() {
		this.pointsDeVie = 0;
	}

	@Override
	public Color getImage() {
		return image;
	}
}
