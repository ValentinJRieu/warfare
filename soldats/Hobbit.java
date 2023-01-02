package wargame.soldats;

import wargame.carte.Position;

import java.awt.*;

public class Hobbit extends Heros{

	private static final Color image = Color.BLACK ;

	public Hobbit() {
		pointsDeVie = TypesH.HOBBIT.getPoints();
		porteeVisuelle = TypesH.HOBBIT.getPortee();
		puissance = TypesH.HOBBIT.getPuissance();
		tir = TypesH.HOBBIT.getTir();
	}
	public Hobbit(Position pos) {
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

	}

	@Override
	public Color getImage() {
		return image;
	}
}
