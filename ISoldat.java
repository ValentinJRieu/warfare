package wargame;

import wargame.carte.Position;

import java.awt.*;

public interface ISoldat {
   enum TypesH {
      HUMAIN (40,3,10,2, 5), NAIN (80,1,20,0, 4), ELFE (70,5,10,6, 7), HOBBIT (20,3,5,2, 7);
      private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR, DEPLACEMENT;
      TypesH(int points, int porteeVisuelle, int puissance, int tir, int deplacement) {
        POINTS_DE_VIE = points; PORTEE_VISUELLE = porteeVisuelle;
        PUISSANCE = puissance; TIR = tir;
        DEPLACEMENT = deplacement;
      }
      public int getPoints() { return POINTS_DE_VIE; }
      public int getPortee() { return PORTEE_VISUELLE; }
      public int getPuissance() { return PUISSANCE; }
      public int getTir() { return TIR; }
      public int getDeplacement() { return DEPLACEMENT; }
      public static TypesH getTypeHAlea() {
         return values()[(int)(Math.random()*values().length)];
      }
   }
   enum TypesM {
      TROLL (100,1,30,0, 5), ORC (40,2,10,3, 3), GOBELIN (20,2,5,2, 4);
      private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR, DEPLACEMENT;
      TypesM(int points, int porteeVisuelle, int puissance, int tir, int deplacement) {
        POINTS_DE_VIE = points; PORTEE_VISUELLE = porteeVisuelle;
        PUISSANCE = puissance; TIR = tir;
        DEPLACEMENT = deplacement;
      }
      public int getPoints() { return POINTS_DE_VIE; }
      public int getPortee() { return PORTEE_VISUELLE; }
      public int getPuissance() { return PUISSANCE; }
      public int getTir() { return TIR; }
      public int getDeplacement() { return DEPLACEMENT; }
      public static TypesM getTypeMAlea() {
         return values()[(int)(Math.random()*values().length)];
      }
   }

   void joueTour();
   void degat(int degats);
   boolean estMort();
   boolean peutJouer();
   Color getImage();
}
