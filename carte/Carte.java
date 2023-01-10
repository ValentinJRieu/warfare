package wargame.carte;

import wargame.ICarte;
import wargame.IConfig;
import wargame.soldats.Heros;
import wargame.soldats.Monstres;
import wargame.soldats.Soldat;
import wargame.soldats.SoldatFactory;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Carte implements ICarte, IConfig, Serializable {

	/* Du coup, une carte serait consideree comme une Map<String, Element>, ou le String est le toString
	 *  de la position de l'element sur la map */
	private final String PATH_TO_MAPS;
	private Map<String, Cellule> carte;
	private String name;
	private int largeur;
	private int hauteur;

	private int tour = 1;
	private boolean estTerminee = false;


	private boolean tourHeros = true;

	private Cellule active = null;
	private Cellule donjon = null;
	private Cellule chateau = null;

	private HashMap<String, Integer> accessible;

	private ArrayList<Heros> listeHeros;
	private ArrayList<Monstres> listeMonstres;

	private HashMap<String, Cellule> spawnDonjons, spawnChateau;
	private static final int DISTANCE_SPAWN = 3;
	public Carte() {
		carte = new TreeMap<>();
		accessible = new HashMap<>();
		spawnDonjons = new HashMap<>();
		spawnChateau = new HashMap<>();
		listeHeros = new ArrayList<>();
		listeMonstres = new ArrayList<>();
		String os = System.getProperty("os.name");
		if(os.contains("Linux")) {
			System.out.println("sous linux");
			PATH_TO_MAPS = "data/maps/";
		}
		else if(os.contains("Mac")) {
			System.out.println("Sous mac");
			PATH_TO_MAPS = "data/maps/";
		}

		else if(os.contains("Windows")) {
			System.out.println("Sous windows");
			PATH_TO_MAPS = "data\\maps\\";
		}
		else {
			System.out.println("Sous un autre Système");
			PATH_TO_MAPS ="data\\maps\\";
		}
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
				if (i == 2 && j == 2) {
					carte.put(pos.toString(), new Cellule(pos, TerrainFactory.getTerrain("CHATEAU"))); // C'est le QG des gentils
					this.chateau = carte.get(pos.toString());
					continue;
				}
				if (i == hauteur-3 && j == largeur-3) {
					carte.put(pos.toString(), new Cellule(pos, TerrainFactory.getTerrain("DONJON"))); // C'est le QG des méchants
					this.donjon = carte.get(pos.toString());
					continue;
				}
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
		actualiseSpawnChateau(chateau, DISTANCE_SPAWN);
		actualiseSpawnDonjons(donjon, DISTANCE_SPAWN);
		for (int count = 0; count <7; count++) {
			spawnSoldats();
		}
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

	@Override
	public boolean action(Position cible) {
		Cellule celluleCible = carte.get(cible.toString());
		if(this.isTourHeros()) {
			System.out.println("action de héros");
			return this.actionHeros(celluleCible);
		}
		System.out.println("action de monstre");
		return this.actionMonstre(celluleCible);
	}

	/**
	 *	retourne l'action à réaliser entre le héros de la cellule active et la cellule cible
	 *	<pre>
	 * 	en fonction de l'état de la cellule active, l'action diffère.
	 * 	@param cible : Position
	 * @return boolean
	 * </pre>
	 * */
	 public boolean actionHeros(Cellule cible) {
		 if(!hasActif()) {
			 System.err.println("nouvel actif");
			 this.active = cible;
			 if(this.active.hasSoldat()) {
				 this.accessible.putAll(this.porteeSoldat());
			 }
			 return false;
		 }

		 if(this.active.estInfranchissable()) {
			 System.err.println("actif infranchissable, toute action est par construction impossible. nouvel actif");
			 this.active = cible;
			 this.accessible.clear();
			 if(this.active.hasSoldat()) {
				 this.accessible.putAll(this.porteeSoldat());
			 }
			 return false;
		 }

		 if(this.active.equals(cible)) {
			 System.err.println("double clic sur cellule : inactif");
			 rendInactif();
			 return false;
		 }

		 if(!this.active.hasSoldat()) {
			 System.err.println("remplacement de l'actif");
			 this.active = cible;
			 this.accessible.clear();
			 if(this.active.hasSoldat()) this.accessible.putAll(this.porteeSoldat());
			 return false;
		 }


		 if(this.accessible.containsKey(cible.getPos().toString())) {
			 System.err.println("Cible accessible, on se déplace");
			 this.active.seDeplace(cible);
			 rendInactif();
			 return false;
		 }
		 if(!cible.hasSoldat()) {
			 System.err.println("Cible trop éloignée. nouvel actif");
			 this.active = cible;
			 this.accessible.clear();
			 return false;
		 }

		 if(cible.hasHeros()) {
			 System.err.println("cible a un heros : nouvel actif");
			 this.active = cible;
			 this.accessible.clear();
			 this.accessible.putAll(this.porteeSoldat());
			 return false;
		 }
		 if (this.active.hasMonstre()) {
			 System.err.println("un monstre ne peut pas attaquer un monstre, nouvel actif");
			 this.active = cible;
			 this.accessible.clear();
			 this.accessible.putAll(this.porteeSoldat());
			 return false;
		 }

		 if(this.active.estVoisine(cible)) {
			 System.err.println("combat entre deux cellules");
			 this.active.attaqueCaC(cible);
			 rendInactif();
			 if(cible.getMonstre().estMort()) {
				 this.faireMourir(cible);
			 }
			 /* TODO : ouvre un panel de sélection d'action */
			 return true;
		 }

		 System.err.println("ennemi trop éloigné : nouvel actif");
		 this.active = cible;
		 this.accessible.clear();
		 this.accessible.putAll(this.porteeSoldat());
		 return false;
	 }


	/**
	 *	retourne l'action à réaliser entre le héros de la cellule active et la cellule cible
	 *	<pre>
	 * 	en fonction de l'état de la cellule active, l'action diffère.
	 * 	@param cible : Position
	 * @return boolean
	 * </pre>
	 * */
	public boolean actionMonstre(Cellule cible) {
		if(!hasActif()) {
			System.err.println("nouvel actif");
			this.active = cible;
			if(this.active.hasSoldat()) {
				this.accessible.putAll(this.porteeSoldat());
			}
			return false;
		}

		if(this.active.estInfranchissable()) {
			System.err.println("actif infranchissable, toute action est par construction impossible. nouvel actif");
			this.active = cible;
			this.accessible.clear();
			if(this.active.hasSoldat()) {
				this.accessible.putAll(this.porteeSoldat());
			}
			return false;
		}

		if(this.active.equals(cible)) {
			System.err.println("double clic sur cellule : inactif");
			rendInactif();
			return false;
		}

		if(!this.active.hasSoldat()) {
			System.err.println("remplacement de l'actif");
			this.active = cible;
			this.accessible.clear();
			if(this.active.hasSoldat()) this.accessible.putAll(this.porteeSoldat());
			return false;
		}


		if(this.accessible.containsKey(cible.getPos().toString())) {
			System.err.println("Cible accessible, on se déplace");
			this.active.seDeplace(cible);
			rendInactif();
			return false;
		}
		if(!cible.hasSoldat()) {
			System.err.println("Cible trop éloignée. nouvel actif");
			this.active = cible;
			this.accessible.clear();
			return false;
		}

		if(cible.hasMonstre()) {
			System.err.println("cible a un monstre : nouvel actif");
			this.active = cible;
			this.accessible.clear();
			this.accessible.putAll(this.porteeSoldat());
			return false;
		}


		if (this.active.hasHeros()) {
			System.err.println("un heros ne peut pas attaquer un autre hero, nouvel actif");
			this.active = cible;
			this.accessible.clear();
			this.accessible.putAll(this.porteeSoldat());
			return false;
		}

		if(this.active.estVoisine(cible)) {
			System.err.println("combat entre deux cellules");
			this.active.attaqueCaC(cible);
			rendInactif();
			if(cible.getHeros().estMort()) {
				this.faireMourir(cible);
			}
			/* TODO : ouvre un panel de sélection d'action */
			return true;
		}

		System.err.println("ennemi trop éloigné : nouvel actif");
		this.active = cible;
		this.accessible.clear();
		this.accessible.putAll(this.porteeSoldat());
		return false;
	}

	public void finTour() {
		tour++;

		if(isTourHeros()) {
			for(Heros h : listeHeros) {
				h.resetDeplacement();
				h.resetPeutJouer();
			}
		}
		else {
			for (Monstres m : listeMonstres) {
				m.resetDeplacement();
				m.resetPeutJouer();
			}
		}

		this.inverseTourHeros();
		if(tour % 10 == 0) {
			this.spawnSoldats();
		}
		rendInactif();
	}

	public void rendInactif() {
		this.active = null;
		this.accessible.clear();
	}

	/**
	 *	retourne des informations concernant la cellule active
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
	private HashMap<String, Integer> porteeSoldat() {
		HashMap<String, Integer> cellules = new HashMap<>();
		int deplacementDispo = active.getSoldat().getDeplacementRestant();

		cellules.putAll(this.listeDeplacementAux(this.active, deplacementDispo, this.active));
		return cellules;
	}

	public HashMap<String, Integer> listeDeplacementAux(Cellule c, int deplacementDispo) {
		if(c==null || deplacementDispo < c.getTerrain().getCoutDeplacement()){
			return new HashMap<>();
		}
		if(c.estInfranchissable()) {
			return new HashMap<>();
		}
		deplacementDispo -= c.getTerrain().getCoutDeplacement();

		if (deplacementDispo <= 0) {
			return new HashMap<>();
		}

		HashMap<String, Integer> cellules = new HashMap<>();
		if(!c.hasSoldat()) {
			cellules.put(c.getPos().toString(), deplacementDispo);
		}

		if(c.getNord() != null) {
			if (!this.accessible.containsKey(c.getNord().getPos().toString())) {
				if ( !c.getNord().hasSoldat() && deplacementDispo < c.getNord().getCoutDeplacement()) {
					cellules.put(c.getNord().getPos().toString(), deplacementDispo - c.getNord().getCoutDeplacement());
					HashMap<String, Integer> listeDepNord = listeDeplacementAux(c.getNord(), deplacementDispo);
					if (!listeDepNord.isEmpty()) {
						cellules.putAll(listeDepNord);
					}
				}
			} else {
				HashMap<String, Integer> listeDepNord = listeDeplacementAux(c.getNord(), deplacementDispo);
				if (!listeDepNord.isEmpty()) {
					cellules.putAll(listeDepNord);
				}
			}
		}
		if(c.getSud() != null) {
			if (!this.accessible.containsKey(c.getSud().getPos().toString())) {
				if ( !c.getSud().hasSoldat() && deplacementDispo < c.getSud().getCoutDeplacement()) {
					cellules.put(c.getSud().getPos().toString(), deplacementDispo - c.getSud().getCoutDeplacement());
					HashMap<String, Integer> listeDepSud = listeDeplacementAux(c.getSud(), deplacementDispo);
					if (!listeDepSud.isEmpty()) {
						cellules.putAll(listeDepSud);
					}
				}
			} else {
				HashMap<String, Integer> listeDepSud = listeDeplacementAux(c.getSud(), deplacementDispo);
				if (!listeDepSud.isEmpty()) {
					cellules.putAll(listeDepSud);
				}
			}
		}
		if(c.getNordEst() != null) {
			if (!this.accessible.containsKey(c.getNord().getPos().toString())) {
				if ( !c.getNordEst().hasSoldat() && deplacementDispo < c.getNordEst().getCoutDeplacement()) {
					cellules.put(c.getNordEst().getPos().toString(), deplacementDispo - c.getNordEst().getCoutDeplacement());
					HashMap<String, Integer> listeDepNordEst = listeDeplacementAux(c.getNordEst(), deplacementDispo);
					if (!listeDepNordEst.isEmpty()) {
						cellules.putAll(listeDepNordEst);
					}
				}
			} else {
				HashMap<String, Integer> listeDepNordEst = listeDeplacementAux(c.getNordEst(), deplacementDispo);
				if (!listeDepNordEst.isEmpty()) {
					cellules.putAll(listeDepNordEst);
				}
			}
		}
		if(c.getNordOuest() != null) {
			if (!this.accessible.containsKey(c.getNordOuest().getPos().toString())) {
				if ( !c.getNordOuest().hasSoldat() && deplacementDispo < c.getNordOuest().getCoutDeplacement()) {
					cellules.put(c.getNordOuest().getPos().toString(), deplacementDispo - c.getNordOuest().getCoutDeplacement());
					HashMap<String, Integer> listeDepNordOuest = listeDeplacementAux(c.getNordOuest(), deplacementDispo);
					if (!listeDepNordOuest.isEmpty()) {
						cellules.putAll(listeDepNordOuest);
					}
				}
			} else {
				HashMap<String, Integer> listeDepNordOuest = listeDeplacementAux(c.getNordOuest(), deplacementDispo);
				if (!listeDepNordOuest.isEmpty()) {
					cellules.putAll(listeDepNordOuest);
				}
			}
		}
		if(c.getSudEst() != null) {
			if (!this.accessible.containsKey(c.getSudEst().getPos().toString())) {
				if ( !c.getSudEst().hasSoldat() && deplacementDispo < c.getSudEst().getCoutDeplacement()) {
					cellules.put(c.getSudEst().getPos().toString(), deplacementDispo - c.getSudEst().getCoutDeplacement());
					HashMap<String, Integer> listeDepSudEst = listeDeplacementAux(c.getSudEst(), deplacementDispo);
					if (!listeDepSudEst.isEmpty()) {
						cellules.putAll(listeDepSudEst);
					}
				}
			} else {
				HashMap<String, Integer> listeDepSudEst = listeDeplacementAux(c.getSudEst(), deplacementDispo);
				if (!listeDepSudEst.isEmpty()) {
					cellules.putAll(listeDepSudEst);
				}
			}
		}
		if(c.getSudOuest() != null) {
			if (!this.accessible.containsKey(c.getSudOuest().getPos().toString())) {
				if ( !c.getSudOuest().hasSoldat() && deplacementDispo < c.getSudOuest().getCoutDeplacement()) {
					cellules.put(c.getSudOuest().getPos().toString(), deplacementDispo - c.getSudOuest().getCoutDeplacement());
					HashMap<String, Integer> listeDepSudOuest = listeDeplacementAux(c.getSudOuest(), deplacementDispo);
					if (!listeDepSudOuest.isEmpty()) {
						cellules.putAll(listeDepSudOuest);
					}
				}
			} else {
				HashMap<String, Integer> listeDepSudOuest = listeDeplacementAux(c.getSudOuest(), deplacementDispo);
				if (!listeDepSudOuest.isEmpty()) {
					cellules.putAll(listeDepSudOuest);
				}
			}
		}
		return cellules;
	}



	public boolean hasActif() { return this.active != null; }

	public Cellule actif() { return this.active; }

	public Cellule getCellule(Position position) {
		return carte.get(position.toString());
	}

	public boolean isTourHeros() {
		return tourHeros;
	}

	public void inverseTourHeros() {
		this.tourHeros = !this.tourHeros;
	}

	public Map<String, Integer> getAccessible() {
		return this.accessible;
	}

	public void actualiseSpawnChateau(Cellule c, Integer deplacementDispo) {
		if (c == null || c.estInfranchissable() || deplacementDispo == 0) {
			return;
		}
		deplacementDispo --;

		if(!c.hasSoldat()) {
			spawnChateau.put(c.getPos().toString(), c);
		}

		if(c.getNord() != null && !this.spawnChateau.containsKey(c.getNord().getPos().toString())) {
			actualiseSpawnChateau(c.getNord(), deplacementDispo);
		}
		if(c.getSud() != null && !this.spawnChateau.containsKey(c.getSud().getPos().toString())) {
			actualiseSpawnChateau(c.getSud(), deplacementDispo);
		}
		if(c.getNordEst() != null && !this.spawnChateau.containsKey(c.getNordEst().getPos().toString())) {
			actualiseSpawnChateau(c.getNordEst(), deplacementDispo);
		}
		if(c.getNordOuest() != null && !this.spawnChateau.containsKey(c.getNordOuest().getPos().toString())) {
			actualiseSpawnChateau(c.getNordOuest(), deplacementDispo);
		}
		if(c.getSudEst() != null && !this.spawnChateau.containsKey(c.getSudEst().getPos().toString())) {
			actualiseSpawnChateau(c.getSudEst(), deplacementDispo);
		}
		if(c.getSudOuest() != null && !this.spawnChateau.containsKey(c.getSudOuest().getPos().toString())) {
			actualiseSpawnChateau(c.getSudOuest(), deplacementDispo);
		}
	}

	public void actualiseSpawnDonjons(Cellule c, Integer deplacementDispo) {
		if (c == null || c.estInfranchissable() || deplacementDispo == 0) {
			return;
		}
		deplacementDispo --;

		if(!c.hasSoldat()) {
			spawnDonjons.put(c.getPos().toString(), c);
		}

		if(c.getNord() != null &&  !this.spawnDonjons.containsKey(c.getNord().getPos().toString())) {
			actualiseSpawnDonjons(c.getNord(), deplacementDispo);
		}
		if(c.getSud() != null && !this.spawnDonjons.containsKey(c.getSud().getPos().toString())) {
			actualiseSpawnDonjons(c.getSud(), deplacementDispo);
		}
		if(c.getNordEst() != null && !this.spawnDonjons.containsKey(c.getNordEst().getPos().toString())) {
			actualiseSpawnDonjons(c.getNordEst(), deplacementDispo);
		}
		if(c.getNordOuest() != null && !this.spawnDonjons.containsKey(c.getNordOuest().getPos().toString())) {
			actualiseSpawnDonjons(c.getNordOuest(), deplacementDispo);
		}
		if(c.getSudEst() != null && !this.spawnDonjons.containsKey(c.getSudEst().getPos().toString())) {
			actualiseSpawnDonjons(c.getSudEst(), deplacementDispo);
		}
		if(c.getSudOuest() != null && !this.spawnDonjons.containsKey(c.getSudOuest().getPos().toString())) {
			actualiseSpawnDonjons(c.getSudOuest(), deplacementDispo);
		}
	}

	public void spawnSoldats() {
		Random generateur = new Random();
		Object[] spawnHerosDispo = spawnChateau.values().toArray();
		Object[] spawnMonstresDispo = spawnDonjons.values().toArray();
		Object celluleSpawnHeros = spawnHerosDispo[generateur.nextInt(spawnHerosDispo.length)];
		Object celluleSpawnMonstres = spawnMonstresDispo[generateur.nextInt(spawnMonstresDispo.length)];
		String[] typesHeros = {"ELFE", "HOBBIT", "HUMAIN", "NAIN"}; // why not le mettre en global
		String[] typesMonstres = {"GOBELIN", "ORC", "TROLL"}; // idem
		Cellule herosSpawn = (Cellule) celluleSpawnHeros;
		Cellule monstreSpawn = (Cellule) celluleSpawnMonstres;
		if(!herosSpawn.hasSoldat()) {
			herosSpawn.setHeros((Heros) SoldatFactory.getSoldat(typesHeros[generateur.nextInt(typesHeros.length)]));
			actualiseSpawnDonjons(donjon, DISTANCE_SPAWN);
			listeHeros.add(herosSpawn.getHeros());
		}
		if(!monstreSpawn.hasSoldat()) {
			monstreSpawn.setMonstre((Monstres) SoldatFactory.getSoldat(typesMonstres[generateur.nextInt(typesMonstres.length)]));
			actualiseSpawnChateau(chateau, DISTANCE_SPAWN);
			listeMonstres.add(monstreSpawn.getMonstre());
		}
	}

	public void faireMourir(Cellule c) {
		if (c.hasMonstre()) {
			listeMonstres.remove(c.getMonstre());
		} else {
			listeHeros.remove(c.getHeros());
		}
		c.meurt();
	}

	public int getTour() { return tour; }

	public ArrayList<Heros> getListeHeros() {
		return listeHeros;
	}

	public ArrayList<Monstres> getListeMonstres() {
		return listeMonstres;
	}

	public void save(String name)
	{
		ObjectOutputStream outputStream = null;
		try {
			//Construct the LineNumberReader object
			outputStream = new ObjectOutputStream(new FileOutputStream("saves/"+name+".save"));
			outputStream.writeObject(this);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			//Close the ObjectOutputStream
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public static Carte load(String name)
	{
		Carte c = null;
		ObjectInputStream inputStream = null;
		try {
			//Construct the LineNumberReader object
			inputStream = new ObjectInputStream(new FileInputStream("saves/"+name+".save"));
			c = (Carte) inputStream.readObject();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
			//Close the ObjectOutputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		if(c == null)
		{
			throw new Error("Impossible de charger la sauvegarde "+name);
		}
		return c;
	}
}
