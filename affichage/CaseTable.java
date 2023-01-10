package wargame.affichage;

import javafx.scene.shape.Circle;
import wargame.ISoldat;
import wargame.carte.Carte;
import wargame.carte.Cellule;
import wargame.carte.Position;
import wargame.carte.Terrain;
import wargame.soldats.Heros;
import wargame.soldats.Soldat;

import java.awt.*;

public class CaseTable {
    private final Carte cases;
    int columns;
    int rows;
    CaseTable(Carte map){
        cases = map;
        int i = 0,j=0;
        while(true) {
            try{
                map.getElement(new Position(i,j));
            }catch (NullPointerException e){
                break;
            }
            j++;
        }
        columns = j;
        j--;
        while(true) {
            try{
                map.getElement(new Position(i,j));
            }catch (NullPointerException e){
                break;
            }
            i++;
        }
        rows = i;
        System.out.println(columns + " -> " + rows);
    }

    /**
     * Retourne la case en fonction des coordonnées i et j
     * @param j la colonne
     * @param i la ligne
     * @return La case associé ou null si les coordonnées sont invalides
     */
    public Terrain getTerrain(int j, int i) {
        if(j < columns && i < rows && j >= 0 && i >= 0)
            return cases.getElement(new Position(i,j));
        else
            return null;
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
        Cellule c;
        Color colorTerrain;
        Color colorSoldat;
        for (int j = jStart; j < columns; j++) {
            for(int i = iStart;i < rows;i++) {
                c = cases.getCellule(new Position(i, j));
                if (c != null) {
                    colorTerrain = c.getTerrain().getImage();
                    if (c.getHeros() != null) {
                        colorSoldat = c.getHeros().getImage();
                    } else if (c.getMonstre() != null) {
                        colorSoldat =  c.getMonstre().getImage();
                    } else {
                        colorSoldat = colorTerrain;
                    }
                } else {
                    colorTerrain = Color.BLACK;
                    colorSoldat = colorTerrain;
                }
                Polygon p = createHexagon(new RCPosition(i-iStart, j-jStart), radius);
                g2.setColor(colorTerrain);
                g2.fill(p);
                g2.setColor(Color.BLACK);
                if (c != null && cases.hasActif()) {
                    if (cases.actif().equals(c)) {
                        g2.setColor(Color.MAGENTA);
                    }
                }
                g2.draw(p);
                g2.setColor(colorSoldat);
                Circle unit = createCircle(new RCPosition(i-iStart, j-jStart), radius);
                g2.fillOval((int)unit.getCenterX()-(int)unit.getRadius(),(int)unit.getCenterY()-(int)unit.getRadius(),(int)unit.getRadius()*2,(int)unit.getRadius()*2);

            }
        }
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

    public static Circle createCircle(Point center, int radius){
        Circle p = new Circle();
        p.setCenterX(center.x);
        p.setCenterY(center.y);
        p.setRadius(radius/2.);
        return p;
    }

    public static Circle createCircle(RCPosition rcp,int radius){
        int height = (int)CaseTable.height(radius);
        int width = (int)CaseTable.width(radius);
        int x = rcp.getY() * (int)(width*0.75);
        int y = rcp.getX() * height;
        if(rcp.getY() %2 == 1)
            y += height/2;
        return CaseTable.createCircle(new Point(x,y),(radius));
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
        int x = rcp.getY() * (int)(width*0.75);
        int y = rcp.getX() * height;
        if(rcp.getY() %2 == 1)
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
