package wargame.carte;

import wargame.ICarte;
import wargame.IConfig;
import wargame.affichage.PanneauJeu;
import wargame.soldats.Heros;
import wargame.soldats.Soldat;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class Carte implements ICarte, IConfig {

	/* Du coup, une carte serait consid�r�e comme une Map<String, Element>, o� le String est le toString
	 *  de la position de l'�l�ment sur la map, si d'autres impl�mentations vous semblent meilleures,
	 * Ne pas h�siter � les proposer */
	private final String PATH_TO_MAPS = "data\\maps\\";

	private Map<String, Cellule> carte;
	private String name;
	private int largeur;
	private int hauteur;

	public Carte() {
		carte = new TreeMap<>();
	}

	public void loadCarte(String path) throws FileNotFoundException {
		System.out.println(PATH_TO_MAPS + path);
		File f = new File(PATH_TO_MAPS + path);
		Scanner myReader = new Scanner(f);
		/* On va lire le nom de la map � la premi�re ligne */
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
		/* On saute l'espace r�glementaire */
		if(myReader.hasNextLine()) {
			System.out.println(myReader.nextLine().trim());
		}
		String map = "";
		/* On va maintenant lire la carte */
		while(myReader.hasNextLine()) {
			String line = myReader.nextLine();
			line += line.length() == largeur ? "\n" : " \n";
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
					case ' ':
					case 'P':
						carte.put(pos.toString(), new Cellule(pos, Type.PLAINE));
						break;
					case 'F':
						carte.put(pos.toString(), new Cellule(pos, Type.FORET));
						break;
					case 'M':
						carte.put(pos.toString(), new Cellule(pos, Type.MONTAGNE));
						break;
					case 'R':
						carte.put(pos.toString(), new Cellule(pos,Type.RIVIERE));
						break;
					case 'V':
						carte.put(pos.toString(), new Cellule(pos,Type.VILLE));
						break;
					default:
						continue;
				}
			}
		}
		System.out.println("---------------------------------------------------------");
		carte.forEach((position, cellule) -> {
			Position voisin = new Position(0, 0);
			// N-O
			voisin.setY(cellule.getPos().getY()-1);
			voisin.setX(cellule.getPos().getX()-1);
			cellule.setNordOuest(carte.get(voisin.toString()));
			// N-E
			voisin.setY(cellule.getPos().getY()-1);
			voisin.setX(cellule.getPos().getX()+1);
			cellule.setNordEst(carte.get(voisin.toString()));
			// N
			voisin.setY(cellule.getPos().getY()-2);
			voisin.setX(cellule.getPos().getX());
			cellule.setNord(carte.get(voisin.toString()));
			// S
			voisin.setY(cellule.getPos().getY()+2);
			voisin.setX(cellule.getPos().getX());
			cellule.setSud(carte.get(voisin.toString()));
			// S-O
			voisin.setY(cellule.getPos().getY()+1);
			voisin.setX(cellule.getPos().getX()-1);
			cellule.setSudOuest(carte.get(voisin.toString()));
			// S-E
			voisin.setY(cellule.getPos().getY()+1);
			voisin.setX(cellule.getPos().getX()+1);
			cellule.setSudEst(carte.get(voisin.toString()));
		});
		System.out.println(carte);
	}

	/*
	 * On retourne l'�l�ment � la position pos.
	 * */
	@Override public Element getElement(Position pos) {
		return carte.get(pos.toString());
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
	 * On passe un �l�ment de la collection � vide et on retourne sa position
	 * */
	public Position positionSetVide() {
		Position pos = new Position(new Random().nextInt(LARGEUR_CARTE), new Random().nextInt(HAUTEUR_CARTE));
		this.carte.get(pos.toString()).setVide();
		return pos;
	}

	/*
	 * On set la position donn�e � vide et on la retourne
	 * */
	public Position positionSetVide(Position pos) {
		this.carte.get(pos.toString()).setVide();
		return pos;
	}

	/*
	 * On parcours la carte pour touver un h�ros
	 * */
	@Override public Heros trouveHeros() {
		for(Map.Entry<String, Cellule> entry : carte.entrySet()) {
			if(entry.getValue().getHeros() != null) {
				return entry.getValue().getHeros();
			}
		}
		return null;
	}

	/*
	 * On renvoie le heros � la position pos (S'il n'y a pas de heros, null est renvoy�)
	 * */
	@Override public Heros trouveHeros(Position pos) {
		return carte.get(pos.toString()).getHeros();
	}

	/*
	 * On d�place le soldat si possible (e.g, il n'y a pas d'obstacle, de monstre ou de h�ros d�j� pr�sent, et position diff�rentes)
	 * */

	@Override public boolean deplaceSoldat(Position pos, Soldat soldat) {
		if(carte.get(pos.toString()).getHeros() != null || carte.get(pos.toString()).getMonstre() != null
			|| carte.get(pos.toString()).getTypeObstacle() != Obstacle.TypeObstacle.VIDE || carte.get(
			pos.toString()).estInfranchissable()) {
			return false;
		}
		soldat.seDeplace(pos);
		return true;
	}

	/*
	 * On fait mourir un soldat
	 * */
	@Override public void mort(Soldat perso) {
		perso.meurt();
	}

	/*
	 * Je ne sais plus ce que c'est
	 * */
	@Override public boolean actionHeros(Position pos, Position pos2) {
		return false;
	}

	@Override public void jouerSoldats(PanneauJeu pj) {

	}

	@Override public void toutDessiner(Graphics g) {

	}
}
