package wargame.soldats;

import wargame.Position;

public class Hobbit extends Heros{

	private int pointsDeVie;
	private int porteeVisuelle;
	private int puissance;
	private int tir;
	private Position pos;

	public Hobbit() {
		pointsDeVie = TypesH.HOBBIT.getPoints();
		porteeVisuelle = TypesH.HOBBIT.getPortee();
		puissance = TypesH.HOBBIT.getPuissance();
		tir = TypesH.HOBBIT.getTir();
	}
	public Hobbit(Position pos) {
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
