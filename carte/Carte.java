package wargame.carte;

import wargame.ICarte;
import wargame.IConfig;
import wargame.soldats.Heros;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Carte implements ICarte, IConfig {

	/* Du coup, une carte serait consideree comme une Map<String, Element>, ou le String est le toString
	 *  de la position de l'element sur la map */
	private final String PATH_TO_MAPS = "data\\maps\\";
	private Map<String, Cellule> carte;
	private String name;
	private int largeur;
	private int hauteur;

	private Cellule active = null;
	private Cellule donjon = null;
	private Cellule chateau = null;

	private HashMap<String, Integer> accessible;



	private HashMap<String, Cellule> spawnDonjons, spawnChateau;
	private static final int DISTANCE_SPAWN = 3;
	public Carte() {
		carte = new TreeMap<>();
		accessible = new HashMap<>();
	}


	public void loadCarte(String path) throws FileNotFoundException {
		System.err.println(PATH_TO_MAPS + path);
		File f = new File(PATH_TO_MAPS + path);
		Scanner myReader = new Scanner(f);
		/* On va lire le nom de la map a la premiere ligne */
		if(myReader.hasNextLine()) {
			name = myReader.nextLine().trim();
		}
		/* On va lire la largeur et la hauteur */
		if(myReader.hasNextLine()) {
			largeur = Integer.valueOf(myReader.nextLine().trim());
		}
		if(myReader.hasNextLine()) {
			hauteur = Integer.valueOf(myReader.nextLine().trim());
		}
		/* On saute l'espace reglementaire */
		if(myReader.hasNextLine()) {
			System.out.println(myReader.nextLine().trim());
		}
		String map = "";
		/* On va maintenant lire la carte */
		while(myReader.hasNextLine()) {
			String line = myReader.nextLine();
			line += "\n";
			map += line;
		}
		boolean iWantToBreakFree = false;
		for(int i = 0, k = 0; i <= hauteur && !iWantToBreakFree; i++) {
			for(int j = 0; j <= largeur && !iWantToBreakFree; j++, k++) {
				char c = map.charAt(k);
				if(k >= map.length() - 1) {
					iWantToBreakFree = true;
				}
				Position pos = new Position(i, j);
				switch(c) {
					case 'P':
						carte.put(pos.toString(), new Cellule(pos, TerrainFactory.getTerrain("PLAINE")));
						break;
					case 'F':
						carte.put(pos.toString(), new Cellule(pos, TerrainFactory.getTerrain("FORET")));
						break;
					case 'M':
						carte.put(pos.toString(), new Cellule(pos, TerrainFactory.getTerrain("MONTAGNE")));
						break;
					case 'R':
						carte.put(pos.toString(), new Cellule(pos, TerrainFactory.getTerrain("RIVIERE")));
						break;
					case 'V':
						carte.put(pos.toString(), new Cellule(pos, TerrainFactory.getTerrain("VILLE")));
						break;
					case 'C':
						carte.put(pos.toString(), new Cellule(pos, TerrainFactory.getTerrain("CIME")));
				}
			}
		}
		System.err.println("---------------------------------------------------------");
		carte.forEach((position, cellule) -> {
			Position voisin = new Position(0, 0);
			// N-O
			if (cellule.getPos().getX() % 2 == 0) {
				voisin.setY(cellule.getPos().getY()-1);
				voisin.setX(cellule.getPos().getX()-1);
				cellule.setNordOuest(carte.get(voisin.toString()));
			} else {
				voisin.setY(cellule.getPos().getY());
				voisin.setX(cellule.getPos().getX()-1);
				cellule.setNordOuest(carte.get(voisin.toString()));
			}
			// N-E
			if (cellule.getPos().getX() % 2 == 0) {
				voisin.setY(cellule.getPos().getY()-1);
				voisin.setX(cellule.getPos().getX()+1);
				cellule.setNordEst(carte.get(voisin.toString()));
			} else {
				voisin.setY(cellule.getPos().getY());
				voisin.setX(cellule.getPos().getX()+1);
				cellule.setNordEst(carte.get(voisin.toString()));
			}
			// N
			voisin.setY(cellule.getPos().getY()-1);
			voisin.setX(cellule.getPos().getX());
			cellule.setNord(carte.get(voisin.toString()));
			// S
			voisin.setY(cellule.getPos().getY()+1);
			voisin.setX(cellule.getPos().getX());
			cellule.setSud(carte.get(voisin.toString()));
			// S-O
			if (cellule.getPos().getX() % 2 == 0) {
				voisin.setY(cellule.getPos().getY()+1);
				voisin.setX(cellule.getPos().getX()-1);
				cellule.setSudOuest(carte.get(voisin.toString()));
			} else {
				voisin.setY(cellule.getPos().getY());
				voisin.setX(cellule.getPos().getX()-1);
				cellule.setSudOuest(carte.get(voisin.toString()));
			}
			// S-E
			if (cellule.getPos().getX() % 2 == 0) {
				voisin.setY(cellule.getPos().getY()+1);
				voisin.setX(cellule.getPos().getX()+1);
				cellule.setSudEst(carte.get(voisin.toString()));
			} else {
				voisin.setY(cellule.getPos().getY());
				voisin.setX(cellule.getPos().getX()+1);
				cellule.setSudEst(carte.get(voisin.toString()));
			}
		});
		System.err.println(carte);
	}

	/**
	 * Retourne l'element a la position donnée.
	 *
	 * */
	@Override public Terrain getElement(Position pos) {
		return carte.get(pos.toString()).getTerrain();
	}

	@Override
	public Position trouvePositionVide() {
		return null;
	}

	@Override
	public Position trouvePositionVide(Position pos) {
		return null;
	}

	/*
	 * On passe un element de la collection a vide et on retourne sa position
	 * */
	public Position positionSetVide() {
		Position pos = new Position(new Random().nextInt(LARGEUR_CARTE), new Random().nextInt(HAUTEUR_CARTE));
		this.carte.get(pos.toString()).setVide();
		return pos;
	}

	/*
	 * On set la position donnee a vide et on la retourne
	 * */
	public Position positionSetVide(Position pos) {
		this.carte.get(pos.toString()).setVide();
		return pos;
	}

	/**
	 * parcoure la carte pour touver un heros
	 * */
	@Override public Heros trouveHeros() {
		for(Map.Entry<String, Cellule> entry : carte.entrySet()) {
			if(entry.getValue().getHeros() != null) {
				return entry.getValue().getHeros();
			}
		}
		return null;
	}

	/**
	 * Renvoie le heros a la position pos (S'il n'y a pas de heros, null est renvoye)
	 * */
	@Override public Heros trouveHeros(Position pos) {
		return carte.get(pos.toString()).getHeros();
	}

	/**
	 *	retourne l'action à réaliser entre la cellule active et la cellule cible
	 *	<pre>
	 * 	en fonction de l'état de la cellule active, l'action diffère.
	 * 	@param cible : Position
	 * @return boolean
	 * </pre>
	 * */
	@Override public boolean action(Position cible) {
		Cellule celluleCible = carte.get(cible.toString());
		if(!hasActif()) {
			System.err.println("nouvel actif");
			this.active = celluleCible;
			if(this.active.hasSoldat()) {
				this.accessible.putAll(this.porteeSoldat());
			}

			return false;
		}

		if(this.active.estInfranchissable()) {
			System.err.println("actif infranchissable, toute action est par construction impossible. nouvel actif");
			this.active = celluleCible;
			this.accessible.clear();
			if(this.active.hasSoldat()) {
				this.accessible.putAll(this.porteeSoldat());
			}
			return false;
		}

		if(this.active.equals(celluleCible)) {
			System.err.println("double clic sur cellule : inactif");
			rendInactif();
			return false;
		}

		if(!this.active.hasSoldat()) {
			System.err.println("remplacement de l'actif");
			this.active = celluleCible;
			this.accessible.clear();
			if(this.active.hasSoldat()) this.accessible.putAll(this.porteeSoldat());
			return false;
		}

		if(!this.accessible.containsKey(celluleCible.getPos().toString())) {
			System.err.println("Cible trop éloignée (ou soldat dessus) pour déplacement : nouvel actif");
			this.active = celluleCible;
			this.accessible.clear();
			if(this.active.hasSoldat()) this.accessible.putAll(this.porteeSoldat());
			return false;
		}

		if(!celluleCible.hasSoldat()) {
			System.err.println("cible sans soldat");
			if(celluleCible.estInfranchissable()) {
				System.err.println("cible infranchissable");
				this.active = celluleCible;
				this.accessible.clear();
				return false;
			}

			System.err.println("cible accessible, déplacement et actif null");
			this.active.seDeplace(celluleCible);
			rendInactif();
			return false;
		}

		/* cible a un soldat */

		if(celluleCible.hasHeros()) {
			System.err.println("cible a un héros : nouvel actif");
			this.active = celluleCible;
			this.accessible.clear();
			this.accessible.putAll(this.porteeSoldat());
			return false;
		}

		if(this.active.estVoisine(celluleCible)) {
			System.err.println("combat entre deux cellules");
			this.active.attaqueCaC(celluleCible);
			rendInactif();
			if(celluleCible.getMonstre().estMort()) {
				celluleCible.meurt();
			}
			/* TODO : ouvre un panel de sélection d'action */
			return true;
		}

		System.err.println("ennemi trop éloigné : nouvel actif");
		this.active = celluleCible;
		this.accessible.clear();
		this.accessible.putAll(this.porteeSoldat());
		return false;
	}

	private boolean aPorteeDeSoldat(Cellule celluleCible) {
		return false;
	}

	public void rendInactif() {
		this.active = null;
		this.accessible.clear();
	}

	/**
	 *	retourne des informations concernant la cellule active
	 *
	 * @param void
	 *
	 * @return {@link String}[] informations de la cellule active
	 * @return null si pas de cellule active
	 */
	public String[] returnActif() {
		if(!this.hasActif()) return null;

		String soldat;
		String terrain;

		if(!this.active.hasSoldat()) {
			soldat = null;
		} else {
			soldat = this.active.getSoldat().toString();
		}

		terrain = this.active.getTerrain().toString();

		return new String[] {soldat, terrain};
	}

	/**
	* retourne une hashmap des cases accessibles depuis la case actif
	*
	* @return {@link HashMap} liste des Deplacements possibles depuis case actif
	* </pre>
	* */
	public HashMap<String, Integer> porteeSoldat() {

		return listeDeplacement();
	}

	private HashMap<String, Integer> listeDeplacement() {
		HashMap<String, Integer> cellules = new HashMap<>();
		int deplacementDispo = active.getSoldat().getDeplacementRestant();

		cellules.putAll(this.active.listeDeplacementAux(deplacementDispo));
		return cellules;
	}



	public boolean hasActif() { return this.active != null; }

	public Cellule actif() { return this.active; }

	public Cellule getCellule(Position position) {
		return carte.get(position.toString());
	}
}
