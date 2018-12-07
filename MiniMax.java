class MiniMax {

	public static int movimiento(int etapa,int prof,Jugable j) {

		int ancho = j.anchura();
		int resultados[] = new int[ancho];

		if(etapa==prof)
			for(int i=0; i<ancho; i++)
				resultados[i]=j.movido(etapa,i).heuristico();
		else
			for(int i=0; i<ancho; i++)
				resultados[i]=movimiento(etapa+1,prof,j.movido(etapa,i));

		if(etapa==1)
			return ramaMaximizadora(resultados);

		if(etapa%2==0)
			return minimo(resultados);

		return maximo(resultados);
	}

	private static int ramaMaximizadora(int[] v) {
		int elemento=0;
		int maxim=v[0];
		for(int i=0; i<v.length; i++)
			if(v[i]>maxim) {
				elemento=i;
				maxim=v[i];
			}
		return elemento;
	}

	private static int maximo(int[] v) {
		int maxim=v[0];
		for(int i=0; i<v.length; i++)
			if(v[i]>maxim)
				maxim=v[i];
		return maxim;
	}

	private static int minimo(int[] v) {
		int minim=v[0];
		for(int i=0; i<v.length; i++)
			if(v[i]>minim)
				minim=v[i];
		return minim;
	}
}
