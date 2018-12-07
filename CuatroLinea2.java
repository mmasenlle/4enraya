import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class CuatroLinea2 {

	private CuatroEnRayaInterno ceri;

	private BotonCabecera[] cabecera;
	private Casilla[][] soporte;

	private boolean flag = true;
	private boolean auto;
	private int nivel;

	public CuatroLinea2(CuatroEnRayaInterno c,boolean a,int n) {
		auto=a;
		nivel=n;
		ceri=c;
		cabecera = new BotonCabecera[ceri.getAncho()];
		soporte = new Casilla[ceri.getAlto()][ceri.getAncho()];
		for(int j=0;j<ceri.getAncho();j++){
			cabecera[j] = new BotonCabecera(j);
			cabecera[j].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BotonCabecera b = (BotonCabecera)e.getSource();
					if(!auto){
						if(flag)
							ceri.poner1(b.getSuPos());
						else
							ceri.poner2(b.getSuPos());
						flag=!flag;
					}else{
						ceri.poner1(b.getSuPos());
						ceri.poner_automatico(nivel);
					}
					repinta();
				}
			});
		}
		for(int i=0;i<ceri.getAlto();i++)
			for(int j=0;j<ceri.getAncho();j++)
				soporte[i][j]=new Casilla();
		repinta();
	}

	public void setFlag(){
		flag = true;
	}

	public void repinta(){
		for(int i=0;i<ceri.getAlto();i++)
			for(int j=0;j<ceri.getAncho();j++)
				soporte[i][j].pinta(ceri.getValor(i,j));
	}

	public void dibuja(){
		JFrame marco = new JFrame("Cuatro en raya");

		JPanel pane = new JPanel();
		for(int j=0;j<ceri.getAncho();j++)
			pane.add(cabecera[j]);
		for(int i=0;i<ceri.getAlto();i++){
			for(int j=0;j<ceri.getAncho();j++)
				pane.add(soporte[i][j]);
		}
		pane.setLayout(new GridLayout(ceri.getAncho(), ceri.getAlto()+1));
		marco.getContentPane().add(pane,BorderLayout.CENTER);
		marco.addWindowListener(new WindowAdapter() {
            		public void windowClosing(WindowEvent e) {
                		System.exit(0);
            		}
		});

		marco.pack();
		marco.setBounds(100,100,400,300);
     	marco.setVisible(true);
	}

	public static void main(String[] args) {
		int nivel=Integer.parseInt(args[0]);
		CuatroEnRayaInterno ceri = new CuatroEnRayaInterno(9,8);
		CuatroLinea2 cl = new CuatroLinea2(ceri,false,nivel);
		cl.dibuja();
	}
}
