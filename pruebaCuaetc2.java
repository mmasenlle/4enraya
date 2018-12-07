import java.io.*;

class pruebaCuaetc2 {
	public static void main(String[] args) {
		int nivel=Integer.parseInt(args[0]);
		CuatroEnRayaInterno ceri = new CuatroEnRayaInterno();
		BufferedReader entrada = new BufferedReader(new InputStreamReader (System.in));
		int m=0;
		while(!ceri.estaLleno()) {
			System.out.print("\n\n Jugador 1 - indique columna : ");
			try{
				m = Integer.parseInt(entrada.readLine())-1;
			}catch(Exception e){};
			ceri.poner1(m);
			ceri.dibuja();
			if(ceri.esGanador(1))break;
			/*stem.out.print("\n\n Jugador 2 - indique columna : ");
			try{
				m = Integer.parseInt(entrada.readLine());
			}catch(Exception e){};*/
			ceri.poner_automatico(nivel);
			ceri.dibuja();
			if(ceri.esGanador(2))break;
		}
	}
}
