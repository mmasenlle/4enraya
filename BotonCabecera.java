import javax.swing.*;

public class BotonCabecera extends JButton {

	private int suPos;

	public BotonCabecera(int p){
		super(Integer.toString(p+1));
		suPos=p;
	}

	public int getSuPos(){
		return suPos;
	}
}
