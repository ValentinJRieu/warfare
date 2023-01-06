package wargame.carte;

import wargame.soldats.Heros;
import wargame.soldats.Monstres;
import wargame.soldats.Soldat;

public class Cellule {

	private Cellule nord;
	private Cellule nordEst;
	private Cellule sudEst;
	private Cellule sud;
	private Cellule sudOuest;
	private Cellule nordOuest;
	private Terrain terrain;
	private Heros heros;
	private Monstres monstre;
	private Position posElem;
	private boolean estVisible;

	public Cellule() {
		nord = nordEst = sudEst = sud = sudOuest = nordOuest = null;
		terrain = null;
		heros = null;
		monstre = null;
		posElem = null;
		estVisible = false;
	}

	public Cellule(Terrain t) {
		this();
		terrain = t;
	}

	public Cellule(Terrain t, Soldat s) {
		this(t);
		if (s instanceof Heros) {
			heros = (Heros) s;
		}
		else if(s instanceof Monstres) {
			monstre = (Monstres) s;
		}
	}

	public Cellule(Terrain t, Soldat s, Cellule nord, Cellule nordEst, Cellule sudEst, Cellule sud, Cellule sudOuest, Cellule nordOuest) {
		this(t, s);
		this.nord = nord;
		this.nordEst = nordEst;
		this.sudEst = sudEst;
		this.sud = sud;
		this.sudOuest = sudOuest;
		this.nordOuest = nordOuest;
	}

	public Cellule(Terrain t, Cellule nord, Cellule nordEst, Cellule sudEst, Cellule sud, Cellule sudOuest, Cellule nordOuest ) {
		this(t);
		this.nord = nord;
		this.nordEst = nordEst;
		this.sudEst = sudEst;
		this.sud = sud;
		this.sudOuest = sudOuest;
		this.nordOuest = nordOuest;
	}

	public Cellule(Position pos) {
		this();
		posElem = pos;
	}

	public Cellule(Position pos, Terrain t) {
		this(pos);
		terrain = t;
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

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public boolean estInfranchissable() {
		return this.terrain instanceof Infranchissable;
	}

	public void setVide() {
		terrain = TerrainFactory.getTerrain("vide");
	}

	public Heros getHeros() {
		return heros;
	}

	public Monstres getMonstre() {
		return monstre;
	}

	public boolean getEstVisible() {
		return estVisible;
	}

	public void setEstVisible() {
		if (heros != null) {
			estVisible = true;
			//setEstVisibleParVosinage(heros.getPortee());
		}
	}

	public void forceVisible() {
		estVisible = true;
	}

	public void setEstVisibleParVosinage(int level) {
		if (level == 0) {
			return;
		}
		if (nordEst != null && !nordEst.estVisible) {
			nordEst.forceVisible();
			nordEst.setEstVisibleParVosinage(level-1);
		}
		if (sudEst != null && !sudEst.estVisible) {
			sudEst.forceVisible();
			sudEst.setEstVisibleParVosinage(level-1);
		}
		if (sud != null && !sud.estVisible) {
			sud.forceVisible();
			sud.setEstVisibleParVosinage(level-1);
		}
		if (sudOuest != null && !sudOuest.estVisible) {
			sudOuest.forceVisible();
			sudOuest.setEstVisibleParVosinage(level-1);
		}
		if (nordOuest != null && !nordOuest.estVisible) {
			nordOuest.forceVisible();
			nordOuest.setEstVisibleParVosinage(level-1);
		}
		if (nord != null && !nord.estVisible) {
			nord.forceVisible();
			nord.setEstVisibleParVosinage(level-1);
		}
	}

	public int getCoutDeplacement() {
		return 0;
	}

	public int getBonusDefense() {
		return 0;
	}
}
