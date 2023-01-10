package wargame.carte;

import wargame.ICarte;
import wargame.IConfig;
import wargame.soldats.Heros;
import wargame.soldats.Monstres;
import wargame.soldats.SoldatFactory;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Carte implements ICarte, IConfig {

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
			return this.actionHeros(celluleCible);
		}
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

		if(!this.accessible.containsKey(cible.getPos().toString())) {
			System.err.println("Cible trop éloignée (ou soldat dessus) pour déplacement : nouvel actif");
			this.active = cible;
			this.accessible.clear();
			if(this.active.hasSoldat()) this.accessible.putAll(this.porteeSoldat());
			return false;
		}

		if(!cible.hasSoldat()) {
			System.err.println("cible sans soldat");
			if(cible.estInfranchissable()) {
				System.err.println("cible infranchissable");
				this.active = cible;
				this.accessible.clear();
				return false;
			}

			System.err.println("cible accessible, déplacement et actif null");
			this.active.seDeplace(cible);
			rendInactif();
			return false;
		}

		/* cible a un soldat */

		if(cible.hasHeros()) {
			System.err.println("cible a un héros : nouvel actif");
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

		if(!this.accessible.containsKey(cible.getPos().toString())) {
			System.err.println("Cible trop éloignée (ou soldat dessus) pour déplacement : nouvel actif");
			this.active = cible;
			this.accessible.clear();
			if(this.active.hasSoldat()) this.accessible.putAll(this.porteeSoldat());
			return false;
		}

		if(!cible.hasSoldat()) {
			System.err.println("cible sans soldat");
			if(cible.estInfranchissable()) {
				System.err.println("cible infranchissable");
				this.active = cible;
				this.accessible.clear();
				return false;
			}

			System.err.println("cible accessible, déplacement et actif null");
			this.active.seDeplace(cible);
			rendInactif();
			return false;
		}

		/* cible a un soldat */

		if(cible.hasMonstre()) {
			System.err.println("cible a un monstre : nouvel actif");
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

	private boolean aPorteeDeSoldat(Cellule celluleCible) {
		return false;
	}

	public void finTour() {
		tour++;
		this.inverseTourHeros();
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

		cellules.putAll(this.listeDeplacementAux(this.active, deplacementDispo));
		return cellules;
	}

	public HashMap<String, Integer> listeDeplacementAux(Cellule c, int deplacementDispo) {
		if(c==null || deplacementDispo < c.getTerrain().getCoutDeplacement()) return new HashMap<>();
		if(c.estInfranchissable()) return new HashMap<>();
		deplacementDispo -= c.getTerrain().getCoutDeplacement();

		HashMap<String, Integer> cellules = new HashMap<>();
		if(!c.hasSoldat()) {
			cellules.put(c.getPos().toString(), deplacementDispo);
		}

		if(c.getNord() != null && this.accessible.containsKey(c.getNord().getPos().toString())) {
			cellules.putAll(listeDeplacementAux(c.getNord(), deplacementDispo));
		}
		if(c.getSud() != null && this.accessible.containsKey(c.getSud().getPos().toString())) {
			cellules.putAll(listeDeplacementAux(c.getSud(), deplacementDispo));
		}
		if(c.getNordEst() != null && this.accessible.containsKey(c.getNordEst().getPos().toString())) {
			cellules.putAll(listeDeplacementAux(c.getNordEst(), deplacementDispo));
		}
		if(c.getNordOuest() != null && this.accessible.containsKey(c.getNordOuest().getPos().toString())) {
			cellules.putAll(listeDeplacementAux(c.getNordOuest(), deplacementDispo));
		}
		if(c.getSudEst() != null && this.accessible.containsKey(c.getSudEst().getPos().toString())) {
			cellules.putAll(listeDeplacementAux(c.getSudEst(), deplacementDispo));
		}
		if(c.getSudOuest() != null && this.accessible.containsKey(c.getSudOuest().getPos().toString())) {
			cellules.putAll(listeDeplacementAux(c.getSudOuest(), deplacementDispo));
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
		herosSpawn.setHeros((Heros) SoldatFactory.getSoldat(typesHeros[generateur.nextInt(typesHeros.length)]));
		monstreSpawn.setMonstre((Monstres) SoldatFactory.getSoldat(typesMonstres[generateur.nextInt(typesMonstres.length)]));
		actualiseSpawnDonjons(donjon, DISTANCE_SPAWN);
		actualiseSpawnChateau(chateau, DISTANCE_SPAWN);
		listeHeros.add(herosSpawn.getHeros());
		listeMonstres.add(monstreSpawn.getMonstre());
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
}
