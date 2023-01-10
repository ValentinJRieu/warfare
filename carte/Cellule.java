package wargame.carte;

import wargame.soldats.Heros;
import wargame.soldats.Monstres;
import wargame.soldats.Soldat;

import java.util.*;

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

	public boolean estVoisine(Cellule c) {

		return this.nord.equals(c)
				|| this.sud.equals(c)
				|| this.nordEst.equals(c)
				|| this.nordOuest.equals(c)
				|| this.sudEst.equals(c)
				|| this.sudOuest.equals(c);
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

	public boolean hasMonstre() { return this.monstre != null; }

	public boolean hasHeros() { return this.heros != null; }

	public boolean hasSoldat() { return hasMonstre() || hasHeros(); }

	public Soldat getSoldat() {
		if(hasMonstre()) return this.monstre;
		return this.heros;
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
		return terrain.getCoutDeplacement();
	}

	public int getBonusDefense() {
		return terrain.getBonusDefense();
	}

	public void seDeplace(Cellule cible) {
		cible.heros = this.heros;
		this.heros = null;
	}

	public void attaque(Cellule cible, int type) {
		if(type == 1) attaqueCaC(cible);

		else attaqueDist(cible);

	}

	public void attaqueCaC(Cellule cible) {
		int deg = this.heros.getPuissance() - cible.getBonusDefense();
		if(deg < 0) deg = 0;
		cible.getMonstre().degat(deg);
	}

	public void attaqueDist(Cellule cible) {
		int deg = this.heros.getTir() - cible.getBonusDefense();
		if(deg < 0) deg = 0;
		cible.getMonstre().degat(deg);
	}

	public void meurt() {

		if(hasMonstre()) {
			this.monstre = null;
			return;
		}
		this.heros = null;
	}

	public HashMap<String, Integer> listeDeplacementAux(int deplacementDispo) {
		if(deplacementDispo < this.terrain.getCoutDeplacement()) return null;
		deplacementDispo -= this.terrain.getCoutDeplacement();

		HashMap<String, Integer> cellules = new HashMap<>();
		cellules.put(this.posElem.toString(), deplacementDispo);

		cellules.putAll(this.nord.listeDeplacementAux(deplacementDispo));
		cellules.putAll(this.sud.listeDeplacementAux(deplacementDispo));
		cellules.putAll(this.nordOuest.listeDeplacementAux(deplacementDispo));
		cellules.putAll(this.nordEst.listeDeplacementAux(deplacementDispo));
		cellules.putAll(this.sudOuest.listeDeplacementAux(deplacementDispo));
		cellules.putAll(this.sudEst.listeDeplacementAux(deplacementDispo));

		return cellules;
	}



}
