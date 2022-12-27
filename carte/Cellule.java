package wargame.carte;

import javafx.geometry.Pos;
import wargame.soldats.Heros;
import wargame.soldats.Monstres;
import wargame.soldats.Soldat;

public class Cellule extends Element {

	private Cellule nord;
	private Cellule nordEst;
	private Cellule sudEst;
	private Cellule sud;
	private Cellule sudOuest;
	private Cellule nordOuest;
	private Type typeElem;
	private Obstacle obstacle;
	private Heros heros;
	private Monstres monstre;
	private Position posElem;

	public Cellule() {
		nord = nordEst = sudEst = sud = sudOuest = nordOuest = null;
		typeElem = null;
		obstacle = null;
		heros = null;
		monstre = null;
		posElem = null;
	}

	public Cellule(Type t) {
		this();
		typeElem = t;
	}

	public Cellule(Type t, Obstacle o) {
		this(t);
		obstacle = o;
	}

	public Cellule(Type t, Soldat s) {
		this(t);
		if (s instanceof Heros) {
			heros = (Heros) s;
		}
		if (s instanceof Monstres) {
			monstre = (Monstres) s;
		}
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

	public Cellule(Position pos) {
		this();
		posElem = pos;
	}

	public Cellule(Position pos, Type t) {
		this(pos);
		typeElem = t;
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

	public Position getPos() {
		return posElem;
	}

	public void setPos(Position pos) {
		this.posElem = pos;
	}

	public Type getTypeElem() {
		return typeElem;
	}

	public void setTypeElem(Type typeElem) {
		this.typeElem = typeElem;
	}

	public boolean estInfranchissable() {
		return this.typeElem==Type.MONTAGNE;
	}

	public void setVide() {
		typeElem = Type.VIDE;
	}

	public Heros getHeros() {
		return heros;
	}

	public Monstres getMonstre() {
		return monstre;
	}

	public Obstacle.TypeObstacle getTypeObstacle() {
		return obstacle.getTypeObstacle();
	}
}
