package wargame.soldats;

import wargame.carte.Position;

public class Orc extends Monstres{

	private int pointsDeVie;
	private int porteeVisuelle;
	private int puissance;
	private int tir;
	private Position pos;

	public Orc() {
		pointsDeVie = TypesM.ORC.getPoints();
		porteeVisuelle = TypesM.ORC.getPortee();
		puissance = TypesM.ORC.getPuissance();
		tir = TypesM.ORC.getTir();
	}
	public Orc(Position pos) {
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
