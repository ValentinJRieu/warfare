package wargame.soldats;

import wargame.Position;

public class Gobelin extends Monstres{

	private int pointsDeVie;
	private int porteeVisuelle;
	private int puissance;
	private int tir;
	private Position pos;

	public Gobelin() {
		pointsDeVie = TypesM.GOBELIN.getPoints();
		porteeVisuelle = TypesM.GOBELIN.getPortee();
		puissance = TypesM.GOBELIN.getPuissance();
		tir = TypesM.GOBELIN.getTir();
	}
	public Gobelin(Position pos) {
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
