package wargame.affichage;

public class RCPosition {
    public int i;
    public int j;
    RCPosition(int i,int j){
        this.i = i;
        this.j = j;
    }
    public String toString(){
        return "["+i+","+j+"]";
    }
}
