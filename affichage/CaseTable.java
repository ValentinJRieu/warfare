package wargame.affichage;

<<<<<<< Updated upstream
=======
import wargame.carte.Carte;
import wargame.carte.Cellule;
import wargame.carte.Position;
import wargame.carte.Terrain;

>>>>>>> Stashed changes
import java.awt.*;

public class CaseTable {
    private final Case cases[][];
    int columns;
    int rows;
    CaseTable(int rows,int columns){
        this.rows = rows;
        this.columns = columns;
        cases = new Case[columns][rows];
        /*for(int j = 0;j < rows;j++){
            cases[0][j] = new Case();
            cases[0][j].color = new Color(100,100,100);
        }
        for(int j = 0;j < rows;j++){
            cases[columns-1][j] = new Case();
            cases[columns-1][j].color = new Color(100,100,100);
        }*/
        for(int i = 0;i < columns;i++){
            /*cases[i][0] = new Case();
            cases[i][0].color = new Color(100,100,100);
            cases[i][rows-1] = new Case();
            cases[i][rows-1].color = new Color(100,100,100);*/
            for(int j = 0;j < rows;j++){
                cases[i][j] = new Case();
                cases[i][j].color = new Color((int)(Math.random() * 150),(int)(Math.random() * 50),(int)(Math.random() * 150));
            }
        }
    }

    /**
     * Oblige toute les cases à dessiner leurs lignes de surlignage
     * @param drawOutline La valeur booleenne à assigner
     * @deprecated Cette fonction peut causer des segfault (jsp pk)
     */
    public void setDrawOutline(boolean drawOutline){
        for(int i = 0;i < columns;i++){
            for(int j = 0;j < rows;j++){
                cases[i][j].drawOutline = drawOutline;
            }
        }
    }

    /**
     * Retourne la case en fonction des coordonnées i et j
     * @param j la colonne
     * @param i la ligne
     * @return La case associé ou null si les coordonnées sont invalides
     */
    public Case getCase(int j,int i) {
        if(j < columns && i < rows && j >= 0 && i >= 0)
            return cases[j][i];
        else
            return null;
    }

    public Cellule getNext(Cellule c) {
        Cellule next;
        if (c == null) {
            return cases.getCellule(new Position(0,0));
        } else {
            if (c.getPos().getX()%2 == 0) {
                System.out.println("Dir sud EST");
                next = c.getSudEst();
            } else {
                System.out.println("Dir Nord Est");
                next = c.getNordEst();
            }
        }
        if (next == null) {
            next = cases.getCellule(new Position(0, c.getPos().getY()+2));
        }
        return next;
    }

    /**
     * Renvoie le point d'une extremité de l'hexagone
     * @param center le point central
     * @param size la taille (diametre) du cercle circonscrit
     * @param i L'indice du Coin
     * @return Le point :)
     */
    public static Point getCornerPoint(Point center,double size,int i) {
        double angle_deg = 60 * i;
        double angle_rad = Math.PI / 180 * angle_deg;
        return new Point((int)(center.x + size * Math.cos(angle_rad)),(int)(center.y + size * Math.sin(angle_rad)));
    }

    /**
     * Dessine toute les cases
     * @param g2 l'objet <code>Graphics2D</code>
     * @param radius le rayon du cercle circonscri
     * @param iStart la ligne de départ
     * @param jStart la colonne de départ
     * @param rows le nombre de ligne à dessiner
     * @param columns le nombre de colonnes à dessiner
     */
    public void draw(Graphics2D g2,int radius,int iStart, int jStart, int rows,int columns){
<<<<<<< Updated upstream
        for (int j = jStart; j < columns; j++) {
            for(int i = iStart;i < rows;i++) {
                Case c = getCase(j, i);
                if (c != null)
                    g2.setColor(c.color);
                else
                    g2.setColor(Color.BLACK);
                Polygon p = createHexagon(new RCPosition(i-iStart, j-jStart), radius);
                g2.fill(p);
                g2.setColor(Color.BLACK);
                g2.draw(p);
=======
        Cellule c = null;
        //for (int j = jStart; j < columns; j++) {
        //    for(int i = iStart;i < rows;i++) {
        do {
            c = getNext(c);//getTerrain(j, i);
            if(c != null)
                g2.setColor(c.getTerrain().getImage());
            else
                return;//g2.setColor(Color.BLACK);
            Polygon p = createHexagon(new RCPosition(/*i-iStart*/ c.getPos().getX()+iStart/2, /*j-jStart*/ c.getPos().getY()+jStart/2), radius);
            g2.fill(p);
            g2.setColor(Color.BLACK);
            g2.draw(p);
        } while (c != null);
>>>>>>> Stashed changes

        //    }
        //}
    }

    /**
     * Crée un hexagone à partir d'un centre et d'un rayon
     * @param center le point représentant le centre
     * @param radius le rayon du cercle circonscri
     * @return L'hexagone
     */
    public static Polygon createHexagon(Point center,int radius){
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++) {
            Point po = getCornerPoint(center,radius,i);
            p.addPoint(po.x, po.y);
        }
        return p;
    }

    /**
     * Crée un hexagone à partir d'une case du tableau et d'un rayon
     * @param rcp les coordonées de l'hexagone dans le tableau
     * @param radius le rayon du cercle circonscri
     * @return L'hexagone
     */
    public static Polygon createHexagon(RCPosition rcp,int radius){
        int height = (int)CaseTable.height(radius);
        int width = (int)CaseTable.width(radius);
        int x = rcp.j * (int)(width*0.75);
        int y = rcp.i * height;
        if(rcp.j %2 == 1)
            y += height/2;
        return CaseTable.createHexagon(new Point(x,y),(radius));
    }

    /**
     * Donne la hauteur en fonction du rayon donné
     * @param radius Le rayon
     * @return la hauteur de l'hexagone
     */
    public static double height(double radius){return Math.sqrt(3) * radius;}

    /**
     * Donne la largeur en fonction du rayon donné
     * @param radius Le rayon
     * @return la largeur de l'hexagone
     */
    public static double width(double radius){return radius*2;}
}
