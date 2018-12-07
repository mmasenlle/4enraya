import javax.swing.*;
import java.awt.*;

public class Casilla extends JPanel {

	public Casilla(){
		super();
		setBorder(BorderFactory.createLoweredBevelBorder());
		setBackground(Color.white);
	}

	public void pinta(int c){
		switch(c){
			case 0 :
				setBackground(Color.white);
				break;
			case 1 :
				setBackground(Color.red);
				break;
			case 2 :
				setBackground(Color.blue);
		}
	}
}
