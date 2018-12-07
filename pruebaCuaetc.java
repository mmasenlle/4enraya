import java.io.*;

class pruebaCuaetc {
	public static void main(String[] args) {
		CuatroEnRayaInterno ceri = new CuatroEnRayaInterno();
		BufferedReader entrada = new BufferedReader(new InputStreamReader (System.in));
		int m=0;
		while(!ceri.estaLleno()) {
			System.out.print("\n\n Jugador 1 - indique columna : ");
			try{
				m = Integer.parseInt(entrada.readLine());
			}catch(Exception e){};
			ceri.poner1(m);
			ceri.dibuja();
			if(ceri.esGanador(1))break;
			System.out.print("\n\n Jugador 2 - indique columna : ");
			try{
				m = Integer.parseInt(entrada.readLine());
			}catch(Exception e){};
			ceri.poner2(m);
			ceri.dibuja();
			if(ceri.esGanador(2))break;
		}
	}
}