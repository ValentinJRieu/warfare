package wargame.soldats;

import wargame.ISoldat;
import wargame.carte.Position;

import java.io.Serializable;

public abstract class Soldat implements ISoldat, Serializable {
    protected int pointsDeVie;
    protected int porteeVisuelle;
    protected int puissance;
    protected int tir;
    protected int deplacement;
    protected int deplacementRestant;
    protected Position position;
    protected boolean peutJouer = true;


    /**
     *
     * @return Les Point de vie de l'unité
     */
    public int getPointsDeVie() {
        return pointsDeVie;
    }

    /**
     * Set les PV
     * @param pointsDeVie les PV
     */
    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    /**
     *
     * @return La portée de l'unitée
     */
    public int getPorteeVisuelle() {
        return porteeVisuelle;
    }

    /**
     * Set la portée de l'unité
     * @param porteeVisuelle la portée de l'unité
     */
    public void setPorteeVisuelle(int porteeVisuelle) {
        this.porteeVisuelle = porteeVisuelle;
    }

    /**
     *
     * @return La puissance
     */
    public int getPuissance() {
        return puissance;
    }

    /**
     * Set la puissance
     * @param puissance la puissance
     */
    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    /**
     *
     * @return le tir de l'unité
     */
    public int getTir() {
        return tir;
    }

    /**
     * Set le tir de l'unité
     * @param tir
     */
    public void setTir(int tir) {
        this.tir = tir;
    }

    /**
     *
     * @return La position de l'unité
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Set la position de l'unité
     * @param position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     *
     * @return le nombre de déplacement restant
     */
    public int getDeplacementRestant() { return deplacementRestant; }

    /**
     * remet par défaut les déplacement
     */
    public void resetDeplacement() { deplacementRestant = deplacement; }

    /**
     * Set le nombre de déplacement restant
     * @param deplacement
     */
    public void setDeplacementRestant(int deplacement) {deplacementRestant = deplacement; }

    /**
     * le joueur peut jouer
     */
    public void resetPeutJouer() { peutJouer = true; }

    /**
     * Joue le tour
     */
    @Override
    public void joueTour() {
        this.peutJouer = false;
    }


    /**
     *
     * @param degats Les dégats qu'inflige l'unité
     */
    @Override
    public void degat(int degats) {

        this.pointsDeVie -= degats % this.pointsDeVie;
    }


    /**
     *
     * @return si l'unité est morte ou pas
     */
    @Override
    public boolean estMort() {
        return this.pointsDeVie == 0;
    }

    /**
     *
     * @return si le joueur peut jouer
     */
    @Override
    public boolean peutJouer() {
        return this.peutJouer;
    }

    /**
     *
     * @return La conversion en string des infos de l'unité
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName().toLowerCase() + " :\n" + this.pointsDeVie + " pv\n" + this.deplacementRestant + "/" + this.deplacement + " dep\n" + this.puissance + " deg\n" + this.tir + " tir";
    }
}
