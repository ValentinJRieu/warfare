package wargame.soldat;

public enum EnumClasses {
	HEROS(100, 1, 3),
	ELFE(60, 5, 1),
	ORC(150, 1, 5),
	NAINS(80, 1, 4);

	private int pointsDeVie;
	private int portee;
	private int puissance;

	EnumClasses(int pointsDeVie, int portee, int puissance) {
		this.pointsDeVie = pointsDeVie;
		this.portee = portee;
		this.puissance = puissance;
	}
	public int getPortee() {
		return this.portee;
	}
	public int getPuissance() {
		return this.puissance;
	}
	public int getPointsDeVie() {
		return this.pointsDeVie;
	}
}
