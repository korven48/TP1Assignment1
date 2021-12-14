package es.ucm.tp1.model.Elements;

public interface IPosElement {
	public boolean isInPos(int x, int y);
	public boolean receiveWave(); 
	public int getX();
	public int getY();
}
