package wargame.soldats;

import wargame.ISoldat;
import wargame.carte.Position;

import java.awt.*;

public abstract class Soldat implements ISoldat {
    protected int pointsDeVie;
    protected int porteeVisuelle;
    protected int puissance;
    protected int tir;
    protected Position position;

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


    @Override
    public int getTour() {
        return 0;
    }

    @Override
    public void joueTour(int tour) {

    }

    @Override
    public void combat(Soldat soldat) {

    }
    @Override
    public void seDeplace(Position newPos) {

    }

    @Override
    public void meurt() {

    }


}
