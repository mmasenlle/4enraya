class CuatroEnRayaInterno implements Jugable {

	final static private int VACIA  = 0;
	final static private int FICHA1 = 1;
	final static private int FICHA2 = 2;

	private int nFilas;
	private int nColus;
	private int[][] chisme;


	public CuatroEnRayaInterno(int nf,int nc) {
		nFilas=nf;
		nColus=nc;
		chisme=new int[nf][nc];
	}

	public CuatroEnRayaInterno() {
		this(6,7);
	}

	public void poner1(int c) {
		if(chisme[nFilas-1][c]==VACIA)
			chisme[nFilas-1][c]=FICHA1;
		else
			for(int i=0; i<nFilas-1; i++)
				if(chisme[i][c]==VACIA && chisme[i+1][c]!=VACIA) {
					chisme[i][c]=FICHA1;
					return;
				}
	}

	public void poner2(int c) {
		if(chisme[nFilas-1][c]==VACIA)
			chisme[nFilas-1][c]=FICHA2;
		else
			for(int i=0; i<nFilas-1; i++)
				if(chisme[i][c]==VACIA && chisme[i+1][c]!=VACIA) {
					chisme[i][c]=FICHA2;
					return;
				}
	}

	public boolean estaLleno() {
		for(int i=0; i<nColus; i++)
			if(chisme[0][i]==VACIA)
				return false;
		return true;
	}

	public boolean esGanador(int j) {
		return colu4(j) || fila4(j) || diago4(j);
	}

	private boolean colu4(int jugador) {
		for(int i=0; i<nColus; i++)
			for(int j=3; j<nFilas; j++)
				if(chisme[j][i]==jugador && chisme[j-1][i]==jugador &&
					chisme[j-2][i]==jugador && chisme[j-3][i]==jugador)
					return true;
		return false;
	}

	private boolean fila4(int jugador) {
		for(int i=0; i<nFilas; i++)
			for(int j=3; j<nColus; j++)
				if(chisme[i][j]==jugador && chisme[i][j-1]==jugador &&
					chisme[i][j-2]==jugador && chisme[i][j-3]==jugador)
					return true;
		return false;
	}

	private boolean diago4(int g) {
		for(int i=0; i<nFilas; i++)
			for(int j=0; j<nColus; j++)
				if(chisme[i][j]==g &&
				  ((puesto(i-3,j-3,g) && puesto(i-2,j-2,g) && puesto(i-1,j-1,g))
				 ||(puesto(i+3,j+3,g) && puesto(i+2,j+2,g) && puesto(i+1,j+1,g))
				 ||(puesto(i-3,j+3,g) && puesto(i-2,j+2,g) && puesto(i-1,j+1,g))
				 ||(puesto(i+3,j-3,g) && puesto(i+2,j-2,g) && puesto(i+1,j-1,g))))
					return true;
		return false;
	}

	private boolean puesto(int i,int j,int jugador) {
		return i>=0 && j>=0 && i<nFilas && j<nColus && chisme[i][j]==jugador;
	}
/* para jugar contra el ordenador */

	public void poner_automatico(int nivel) {
		poner2(MiniMax.movimiento(1,nivel,this));
	}

	public Jugable movido(int etapa,int c) {
		CuatroEnRayaInterno ceri = new CuatroEnRayaInterno(nFilas,nColus);
		copiar(ceri,this);
		if(etapa%2==0)
			ceri.poner1(c);
		else
			ceri.poner2(c);
		return (Jugable)ceri;
	}

	private void copiar(CuatroEnRayaInterno c1,CuatroEnRayaInterno c2) {
		for(int i=0; i<nFilas; i++)
			for(int j=0; j<nColus; j++)
				c1.chisme[i][j]=c2.chisme[i][j];
	}

	public int anchura() {
			return nColus;
	}

	public int heuristico() {
		int acum=0;

		int pesoGanar = 100000;
		int pesoFastidiar = 1000;
		int pesoFastiDos = 100;
		int peso3raya = 50;
		int pesoOpcion4real = 10;
		int pesoOpcion4hipo = 1;

		if(esGanador(2))
			acum+=pesoGanar;
		if(esGanador(1))
			acum-=pesoGanar;

		for(int i=0; i<nFilas; i++)
			for(int j=0; j<nColus; j++) {
				if(chisme[i][j]==FICHA2) {
					if(puesto1(i+2,j) && puesto1(i+1,j))
						acum+=pesoFastiDos;
					if(puesto1(i,j-2) && puesto1(i,j-1))
						acum+=pesoFastiDos;
					if(puesto1(i,j+2) && puesto1(i,j+1))
						acum+=pesoFastiDos;
					if(puesto1(i-2,j-2) && puesto1(i-1,j-1))
						acum+=pesoFastiDos;
					if(puesto1(i+2,j+2) && puesto1(i+1,j+1))
						acum+=pesoFastiDos;
					if(puesto1(i-2,j+2) && puesto1(i-1,j+1))
						acum+=pesoFastiDos;
					if(puesto1(i+2,j-2) && puesto1(i+1,j-1))
						acum+=pesoFastiDos;

					if((puesto1(i+3,j) && puesto1(i+2,j) && puesto1(i+1,j))
					 ||(puesto1(i,j-3) && puesto1(i,j-2) && puesto1(i,j-1))
					 ||(puesto1(i,j+3) && puesto1(i,j+2) && puesto1(i,j+1))
					 ||(puesto1(i-3,j-3) && puesto1(i-2,j-2) && puesto1(i-1,j-1))
					 ||(puesto1(i+3,j+3) && puesto1(i+2,j+2) && puesto1(i+1,j+1))
					 ||(puesto1(i-3,j+3) && puesto1(i-2,j+2) && puesto1(i-1,j+1))
					 ||(puesto1(i+3,j-3) && puesto1(i+2,j-2) && puesto1(i+1,j-1)))
						acum+=pesoFastidiar;
				}
				if(chisme[i][j]==FICHA1) {
					if(puesto2(i+2,j) && puesto2(i+1,j))
						acum+=pesoFastiDos;
					if(puesto2(i,j-2) && puesto2(i,j-1))
						acum+=pesoFastiDos;
					if(puesto2(i,j+2) && puesto2(i,j+1))
						acum+=pesoFastiDos;
					if(puesto2(i-2,j-2) && puesto2(i-1,j-1))
						acum+=pesoFastiDos;
					if(puesto2(i+2,j+2) && puesto2(i+1,j+1))
						acum+=pesoFastiDos;
					if(puesto2(i-2,j+2) && puesto2(i-1,j+1))
						acum+=pesoFastiDos;
					if(puesto2(i+2,j-2) && puesto2(i+1,j-1))
						acum+=pesoFastiDos;

					if((puesto2(i+3,j) && puesto2(i+2,j) && puesto2(i+1,j))
					 ||(puesto2(i,j-3) && puesto2(i,j-2) && puesto2(i,j-1))
					 ||(puesto2(i,j+3) && puesto2(i,j+2) && puesto2(i,j+1))
					 ||(puesto2(i-3,j-3) && puesto2(i-2,j-2) && puesto2(i-1,j-1))
					 ||(puesto2(i+3,j+3) && puesto2(i+2,j+2) && puesto2(i+1,j+1))
					 ||(puesto2(i-3,j+3) && puesto2(i-2,j+2) && puesto2(i-1,j+1))
					 ||(puesto2(i+3,j-3) && puesto2(i+2,j-2) && puesto2(i+1,j-1)))
						acum-=pesoFastidiar;
				}
				if(chisme[i][j]==VACIA) {
					if(puesto2(i+3,j) && puesto2(i+2,j) && puesto2(i+1,j))
						acum+=peso3raya;
					if(puesto2(i,j-3) && puesto2(i,j-2) && puesto2(i,j-1))
						acum+=peso3raya;
					if(puesto2(i,j+3) && puesto2(i,j+2) && puesto2(i,j+1))
						acum+=peso3raya;
					if(puesto2(i-3,j-3) && puesto2(i-2,j-2) && puesto2(i-1,j-1))
						acum+=peso3raya;
					if(puesto2(i+3,j+3) && puesto2(i+2,j+2) && puesto2(i+1,j+1))
						acum+=peso3raya;
					if(puesto2(i-3,j+3) && puesto2(i-2,j+2) && puesto2(i-1,j+1))
						acum+=peso3raya;
					if(puesto2(i+3,j-3) && puesto2(i+2,j-2) && puesto2(i+1,j-1))
						acum+=peso3raya;

					if(puesto1(i+3,j) && puesto1(i+2,j) && puesto1(i+1,j))
						acum-=peso3raya;
					if(puesto1(i,j-3) && puesto1(i,j-2) && puesto1(i,j-1))
						acum-=peso3raya;
					if(puesto1(i,j+3) && puesto1(i,j+2) && puesto1(i,j+1))
						acum-=peso3raya;
					if(puesto1(i-3,j-3) && puesto1(i-2,j-2) && puesto1(i-1,j-1))
						acum-=peso3raya;
					if(puesto1(i+3,j+3) && puesto1(i+2,j+2) && puesto1(i+1,j+1))
						acum-=peso3raya;
					if(puesto1(i-3,j+3) && puesto1(i-2,j+2) && puesto1(i-1,j+1))
						acum-=peso3raya;
					if(puesto1(i+3,j-3) && puesto1(i+2,j-2) && puesto1(i+1,j-1))
						acum-=peso3raya;
				}
				if(chisme[i][j]==FICHA2) {
					if(ponible2(i-3,j) && ponible2(i-2,j) && ponible2(i-1,j))
						acum+=pesoOpcion4real;
					if(ponible2(i,j-3) && ponible2(i,j-2) && ponible2(i,j-1))
						acum+=pesoOpcion4real;
					if(ponible2(i,j+3) && ponible2(i,j+2) && ponible2(i,j+1))
						acum+=pesoOpcion4real;
					if(ponible2(i-3,j-3) && ponible2(i-2,j-2) && ponible2(i-1,j-1))
						acum+=pesoOpcion4hipo;
					if(ponible2(i+3,j+3) && ponible2(i+2,j+2) && ponible2(i+1,j+1))
						acum+=pesoOpcion4hipo;
					if(ponible2(i-3,j+3) && ponible2(i-2,j+2) && ponible2(i-1,j+1))
						acum+=pesoOpcion4hipo;
					if(ponible2(i+3,j-3) && ponible2(i+2,j-2) && ponible2(i+1,j-1))
						acum+=pesoOpcion4hipo;
				}
				if(chisme[i][j]==FICHA1) {
					if(ponible1(i-3,j) && ponible1(i-2,j) && ponible1(i-1,j))
						acum-=pesoOpcion4real;
					if(ponible1(i,j-3) && ponible1(i,j-2) && ponible1(i,j-1))
						acum-=pesoOpcion4real;
					if(ponible1(i,j+3) && ponible1(i,j+2) && ponible1(i,j+1))
						acum-=pesoOpcion4real;
					if(ponible1(i-3,j-3) && ponible1(i-2,j-2) && ponible1(i-1,j-1))
						acum-=pesoOpcion4hipo;
					if(ponible1(i+3,j+3) && ponible1(i+2,j+2) && ponible1(i+1,j+1))
						acum-=pesoOpcion4hipo;
					if(ponible1(i-3,j+3) && ponible1(i-2,j+2) && ponible1(i-1,j+1))
						acum-=pesoOpcion4hipo;
					if(ponible1(i+3,j-3) && ponible1(i+2,j-2) && ponible1(i+1,j-1))
						acum-=pesoOpcion4hipo;
				}
			}
		return acum;
	}

	private boolean ponible2(int i,int j) {
		return i>=0 && j>=0 && i<nFilas && j<nColus && chisme[i][j]!=FICHA1;
	}

	private boolean ponible1(int i,int j) {
		return i>=0 && j>=0 && i<nFilas && j<nColus && chisme[i][j]!=FICHA2;
	}

	private boolean puesto2(int i,int j) {
		return i>=0 && j>=0 && i<nFilas && j<nColus && chisme[i][j]==FICHA2;
	}

	private boolean puesto1(int i,int j) {
		return i>=0 && j>=0 && i<nFilas && j<nColus && chisme[i][j]==FICHA1;
	}


	public void dibuja() {
		for(int i=0; i<nFilas; i++) {
			System.out.print("     ");
			for(int j=0; j<nColus; j++)
				System.out.print(" "+chisme[i][j]+" ");
			System.out.println();
		}
		System.out.println("\n");
	}


	public int getAncho() {
		return nColus;
	}

	public int getAlto() {
		return nFilas;
	}

	public int getValor(int i,int j) {
		return chisme[i][j];
	}
}
