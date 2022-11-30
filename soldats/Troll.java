package wargame.soldats;

import wargame.carte.Position;

public class Troll extends Monstres{

	private int pointsDeVie;
	private int porteeVisuelle;
	private int puissance;
	private int tir;
	private Position pos;

	public Troll() {
		pointsDeVie = TypesM.TROLL.getPoints();
		porteeVisuelle = TypesM.TROLL.getPortee();
		puissance = TypesM.TROLL.getPuissance();
		tir = TypesM.TROLL.getTir();
	}
	public Troll(Position pos) {
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
