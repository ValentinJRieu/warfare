package wargame.carte;

import wargame.soldats.Soldat;

public class Cellule extends Element {

	private Cellule nord;
	private Cellule nordEst;
	private Cellule sudEst;
	private Cellule sud;
	private Cellule sudOuest;
	private Cellule nordOuest;
	private Type type;
	private Obstacle obstacle;
	private Soldat soldat;

	public Cellule() {
		nord = nordEst = sudEst = sud = sudOuest = nordOuest = null;
		type = null;
		obstacle = null;
		soldat = null;
	}

	public Cellule(Type t) {
		this();
		type = t;
	}

	public Cellule(Type t, Obstacle o) {
		this(t);
		obstacle = o;
	}

	public Cellule(Type t, Soldat s) {
		this(t);
		soldat = s;
	}

	public Cellule(Type t, Obstacle o, Cellule nord, Cellule nordEst, Cellule sudEst, Cellule sud, Cellule sudOuest, Cellule nordOuest ) {
		this(t, o);
		nord = nord;
		nordEst = nordEst;
		sudEst = sudEst;
		sud = sud;
		sudOuest = sudOuest;
		nordOuest = nordOuest;
	}

	public Cellule(Type t, Soldat s, Cellule nord, Cellule nordEst, Cellule sudEst, Cellule sud, Cellule sudOuest, Cellule nordOuest ) {
		this(t, s);
		nord = nord;
		nordEst = nordEst;
		sudEst = sudEst;
		sud = sud;
		sudOuest = sudOuest;
		nordOuest = nordOuest;
	}

	public Cellule(Type t, Cellule nord, Cellule nordEst, Cellule sudEst, Cellule sud, Cellule sudOuest, Cellule nordOuest ) {
		this(t);
		nord = nord;
		nordEst = nordEst;
		sudEst = sudEst;
		sud = sud;
		sudOuest = sudOuest;
		nordOuest = nordOuest;
	}

	public Cellule getNord() {
		return nord;
	}

	public void setNord(Cellule nord) {
		this.nord = nord;
	}

	public Cellule getNordEst() {
		return nordEst;
	}

	public void setNordEst(Cellule nordEst) {
		this.nordEst = nordEst;
	}

	public Cellule getSudEst() {
		return sudEst;
	}

	public void setSudEst(Cellule sudEst) {
		this.sudEst = sudEst;
	}

	public Cellule getSud() {
		return sud;
	}

	public void setSud(Cellule sud) {
		this.sud = sud;
	}

	public Cellule getSudOuest() {
		return sudOuest;
	}

	public void setSudOuest(Cellule sudOuest) {
		this.sudOuest = sudOuest;
	}

	public Cellule getNordOuest() {
		return nordOuest;
	}

	public void setNordOuest(Cellule nordOuest) {
		this.nordOuest = nordOuest;
	}
}
