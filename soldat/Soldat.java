package wargame.soldat;

import wargame.ISoldat;
import wargame.Position;

public class Soldat implements ISoldat {

	private Position pos;
	private int pointsDeVie;
	private int portee;
	private int puissance;
	private int tour;

	@Override public int getPoints() {
		return pointsDeVie;
	}

	@Override public int getTour() {
		return tour;
	}

	@Override public int getPortee() {
		return portee;
	}

	@Override public void joueTour(int tour) {
		this.tour = tour;
	}

	@Override public void combat(Soldat soldat) {
		/* TODO */
	}

	@Override public void seDeplace(Position newPos) {
		if (!newPos.equals(pos)) {
			pos = newPos;
		}
	}
}
