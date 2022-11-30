package wargame.soldats;

import wargame.carte.Position;

public class Nain extends Heros{

	private int pointsDeVie;
	private int porteeVisuelle;
	private int puissance;
	private int tir;
	private Position pos;

	public Nain() {
		pointsDeVie = TypesH.NAIN.getPoints();
		porteeVisuelle = TypesH.NAIN.getPortee();
		puissance = TypesH.NAIN.getPuissance();
		tir = TypesH.NAIN.getTir();
	}
	public Nain(Position pos) {
		this();
		this.pos = pos;
	}

	@Override public int getPoints() {
		return pointsDeVie;
	}

	@Override public int getTour() {
		return 0;
	}

	@Override public int getPortee() {
		return porteeVisuelle;
	}

	@Override public void joueTour(int tour) {

	}

	@Override public void combat(Soldat soldat) {
		/* TODO */
	}

	@Override public void seDeplace(Position newPos) {
		pos = newPos;
	}
}
