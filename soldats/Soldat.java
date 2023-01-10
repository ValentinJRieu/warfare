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


    public int getPointsDeVie() {
        return pointsDeVie;
    }

    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    public int getPorteeVisuelle() {
        return porteeVisuelle;
    }

    public void setPorteeVisuelle(int porteeVisuelle) {
        this.porteeVisuelle = porteeVisuelle;
    }

    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public int getTir() {
        return tir;
    }

    public void setTir(int tir) {
        this.tir = tir;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getDeplacementRestant() { return deplacementRestant; }

    public void resetDeplacement() { deplacementRestant = deplacement; }

    public void setDeplacementRestant(int deplacement) {deplacementRestant = deplacement; }
    public void resetPeutJouer() { peutJouer = true; }
    @Override
    public void joueTour() {
        this.peutJouer = false;
    }



    @Override
    public void degat(int degats) {

        this.pointsDeVie -= degats % this.pointsDeVie;
    }


    @Override
    public boolean estMort() {
        return this.pointsDeVie == 0;
    }

    @Override
    public boolean peutJouer() {
        return this.peutJouer;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName().toLowerCase() + " :\n" + this.pointsDeVie + " pv\n" + this.deplacementRestant + "/" + this.deplacement + " dep\n" + this.puissance + " deg\n" + this.tir + " tir";
    }
}
