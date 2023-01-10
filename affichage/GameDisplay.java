package wargame.affichage;

import wargame.carte.Carte;
import wargame.carte.Cellule;
import wargame.carte.Terrain;
import wargame.soldats.Heros;
import wargame.soldats.Monstres;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GameDisplay extends JPanel {

    CaseTable ct;
    Dimension ctDim;
    private int iStart;
    private int jStart;
    private int iEnd;
    private int jEnd;
    private int radius;

    private Carte carte;

    private List<Heros> J1;

    private List<Monstres> J2;

    private int tour = 0;

    private boolean estTerminee = false;

    public GameWindow parent;

    public RCPosition overedCase;


    /**
     * Crée un nouveau plateau de jeu
     *
     * @param parent la fenêtre actuelle
     */
    GameDisplay(GameWindow parent) {
        carte = new Carte();
        J1 = new ArrayList<>();
        J2 = new ArrayList<>();
        try {
            carte.loadCarte("map1.txt");
        } catch(FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Dimension dim = new Dimension(parent.getWidth() * 8 / 10, parent.getHeight() * 9 / 10);
        ctDim = new Dimension(25, 12);
        jStart = ctDim.width - (10 + ctDim.width%2);
        jEnd = ctDim.width;
        iStart = ctDim.height - (10 + ctDim.width%2);
        iEnd = ctDim.height;
        this.setPreferredSize(dim);
        this.setMaximumSize(dim);
        ct = new CaseTable(carte);
        GameEventHandler geh = new GameEventHandler(this);
        addMouseListener(geh);
        addMouseMotionListener(geh);
        addMouseWheelListener(geh);
        addKeyListener(geh);
        setBackground(Color.BLACK);
        this.parent = parent;
        overedCase = new RCPosition(iStart, jStart);

    }

    public void tourSuivant() {
        tour++;
        carte.inverseFinTour();
        carte.inverseTourHeros();
    }

    /**
     * Dessine les cases du plateau de jeu automatiquemet
     *
     * @param g L'objet de type <code>Graphics</code>
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(carte.isFinTour()) {
            this.tourSuivant();

        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        radius = (int) ((double) getWidth() / (double) (jEnd - jStart - 1) / 0.75);
        iEnd = iStart + (int) Math.round(getHeight() / (CaseTable.height((int)(radius / 2)))) + 1;
        int rows = iEnd;
        int cols = jEnd;
        ct.draw(g2, radius / 2, iStart, jStart, rows, cols);
        if (overedCase != null) {
            g2.setColor(getColorFromActiveDistance(overedCase));
            g2.draw(CaseTable.createHexagon(new RCPosition(overedCase.getX() - iStart, overedCase.getY() - jStart), radius / 2));
        }
    }

    private Color getColorFromActiveDistance(RCPosition overedCase) {
        Cellule active = carte.actif();
        Cellule overed = carte.getCellule(overedCase);
        if (overed == null) {
            return Color.BLACK;
        }
        if (active == null) {
            return Color.WHITE;
        }
        if (active.getSoldat() == null) {
            if (carte.getAccessible().get(overed.getPos()) == null) {
                return Color.red;
            }
            return Color.green;
        }
        return Color.WHITE;
    }


    /**
     * Reduit de nombre de cases affichées et augmente leur taille
     */
    public void zoom() {
        //System.out.println("Zooming Size : (" + (iEnd - iStart) + "," + (jEnd - jStart) + ")");
        if (iEnd - iStart > 10 && jEnd - jStart > 10) {
            iStart += 2;
            jStart += 2;
            iEnd -= 2;
            jEnd -= 2;
        }
        //System.out.println("Zooming Size : (" + (iEnd - iStart) + "," + (jEnd - jStart) + ")\n");
        parent.frame.repaint();
        ;
    }


    /**
     * Augmente le nombre de cases affichées et réduit leur taille
     */
    public void deZoom() {
        if (iStart > -ct.rows) {
            iStart -= 2;
        }
        if (iEnd < ct.rows * 2) {
            iEnd += 2;
        }
        if (jStart > -ct.columns) {
            jStart -= 2;
        }
        if (jEnd < ct.columns * 2) {
            jEnd += 2;
        }
        parent.frame.repaint();
        ;
    }


    /**
     * Déplace la fenêtre de vision de x case(s) vers le haut
     */
    public void up(int x) {
        //System.out.println("up");
        if (iStart >= -ct.rows) {
            iStart -= x;
            iEnd -= x;
            parent.frame.repaint();
            ;

        }
    }

    public void up() {
        up(2);
    }

    /**
     * Déplace la fenêtre de vision de x case(s) vers le bas
     */
    public void down(int x) {
        //System.out.println("--down--");
        //System.out.println("iStart = "+iStart+" iEnd = "+iEnd+" ct.rows = "+ct.rows);
        if (iEnd <= ct.rows * 2) {
            iStart += x;
            iEnd += x;
            parent.frame.repaint();
            ;
        }
    }

    public void down() {
        down(2);
    }

    /**
     * Déplace la fenêtre de vision de 2 cases vers la gauche
     */
    public void left() {
        //System.out.println("left");
        if (jStart >= -ct.columns) {
            jStart -= 2;
            jEnd -= 2;
            parent.frame.repaint();
            ;
        }

    }

    /**
     * Déplace la fenêtre de vision de 2 cases vers la droite
     */
    public void right() {
        //System.out.println("right");
        if (jEnd <= ct.columns * 2) {
            jStart += 2;
            jEnd += 2;
            parent.frame.repaint();
            ;
        }
    }

    /**
     * Verifie si les coordonées passé en parametre sont dans l'hexagone associtié à la position i j
     *
     * @param i l'indice de la ligne
     * @param j l'indice de la colonne
     * @param x la coordonée des abscisses
     * @param y la coordonée des ordonnées
     * @return Vrai si (x,y) appartient à l'hexagone (i,j)
     */
    private boolean contient(int i, int j, int x, int y) {
        Polygon p = CaseTable.createHexagon(new RCPosition(i, j), radius / 2);
        return p.contains(new Point(x, y));
    }


    public RCPosition getIJFromXY(int startX, int endX, int startY, int endY, int startI, int endI, int startJ, int endJ, int x, int y) {//TODO certaines positions ne sont pas détectée
        int width = endX - startX, height = endY - startY, jSize = endJ - startJ, iSize = endI - startI;

        if (endJ < 0 || endI < 0) return new RCPosition(startI, startJ);

        if (startI >= endI || startJ >= endJ) return new RCPosition(startI, startJ);

        if (endI - startI == 1 && endJ - startJ == 1) {
            for (int a = startI - iStart - 2; a <= startI - iStart + 2; a++) {
                for (int b = startJ - jStart - 2; b <= startJ - jStart + 2; b++) {
                    if (contient(a, b, x, y)) {
                        return new RCPosition(a + iStart, b + jStart);
                    }
                }
            }
            return null;
        }

        if (x < startX + width / 2) {

            endX -= width / 2;
            endJ -= jSize / 2;

        } else {
            startX += width / 2;
            startJ += jSize / 2;
        }
        if (y < startY + height / 2) {
            endY -= height / 2;
            endI -= iSize / 2;
        } else {
            startY += height / 2;
            startI += iSize / 2;
        }
        return getIJFromXY(startX, endX, startY, endY, startI, endI, startJ, endJ, x, y);
    }

    /**
     * Donne la position de l'hexagone en fonction des coordonnées passés en parametre <br>
     * Se fait via un algo de "divide and conquer"
     *
     * @param x la coordonée des abscisses
     * @param y la coordonée des ordonnées
     * @return la position en colonne et ligne de l'hexagone
     */
    public RCPosition getIJFromXY(int x, int y) {
        return getIJFromXY(0, getWidth(), 0, getHeight(), iStart - 2, iEnd + 2, jStart - 2, jEnd + 2, x, y);
    }

    /**
     * Donne la case correspondante aux coordonnées passé en parametre
     * @param x la coordonée des abscisses
     * @param y la coordonée des ordonnées
     * @return La case
     */
    public Terrain getTerrainFromXY(int x, int y) {
        RCPosition rcp = getIJFromXY(x, y);
        if (rcp == null) return null;
        return ct.getTerrain(rcp.getY(), rcp.getX());
    }

    public int getiStart() {
        return iStart;
    }

    public int getjStart() {
        return jStart;
    }

    public int getiEnd() {
        return iEnd;
    }

    public int getjEnd() {
        return jEnd;
    }

    /**
     * Téléporte la fenêtre centré sur les coordonnées I et J
     * @param i la ligne
     * @param j la colonne
     */
    public void goTo(int i, int j) {
        int iSize = iEnd - iStart, jSize = jEnd - jStart;
        iStart = i - iSize / 2;
        iEnd = i + iSize / 2;
        jStart = j - jSize / 2;
        jEnd = j + jSize / 2;
        parent.frame.repaint();
    }

    public Carte getCarte() {
        return carte;
    }
}
