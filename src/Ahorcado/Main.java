package Ahorcado;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Main {
    public static void unJugador(Scanner entrada) throws FileNotFoundException {
        boolean victoria;
        File archivo=new File("src/Ahorcado/diccionario.txt");
        int racha=0;
        Jugador jugador=new Jugador(false,0,null);
        Random generadorAleatorio=new Random();
        Scanner lector=new Scanner(archivo);
        String palabraString=null;
        do {
            int linea=generadorAleatorio.nextInt(79688)+1,vidas=6;
            for(int i=0;i<linea;i++) palabraString=lector.nextLine();
            Palabra palabra=new Palabra(palabraString);
            jugador.adivinar(false,palabra,entrada);
            if(jugador.getPuntos()<6) {
                jugador.setPuntos(0);
                racha++;
                victoria=true;
                System.out.println("Tu racha actual es de "+racha+".");
            } else victoria=false;
        } while(victoria);
        System.out.println("Tu racha final fue de "+racha+".");
    }
    public static void dosJugadores(Scanner entrada) {
        boolean puntosReseteados=false,terminar=false,unoAUno=false;
        Random generadorAleatorio=new Random();
        System.out.print("\nJugador 1, ingresá tu nombre: ");
        String nombreJ1=entrada.nextLine();
        System.out.print("Jugador 2, ingresá tu nombre: ");
        String nombreJ2=entrada.nextLine();
        Jugador j1=new Jugador(false,0,nombreJ1),j2=new Jugador(false,0,nombreJ2);
        int adivinador=generadorAleatorio.nextInt(2)+1,rondasJugadas=2;
        if(adivinador==1) {
            j1.setPrimerAdivinador(true);
            System.out.println("\nEl primero en adivinar es... "+j1.getNombre()+".");
        }
        else {
            j2.setPrimerAdivinador(true);
            System.out.println("\nEl primero en adivinar es... "+j2.getNombre()+".");
        }
        do {
            if(adivinador==1) System.out.print("\nTurno de adivinar de "+j1.getNombre()+".\n"+j2.getNombre()+", ingresá una palabra: ");
            else System.out.print("\nTurno de adivinar de "+j2.getNombre()+".\n"+j1.getNombre()+", ingresá una palabra: ");
            String palabraString=entrada.nextLine();
            Palabra palabra=new Palabra(palabraString);
            System.out.println(".\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n");
            if(adivinador==1) j1.adivinar(true,palabra,entrada);
            else j2.adivinar(true,palabra,entrada);
            if(adivinador==1) adivinador++;
            else adivinador--;
            if(puntosReseteados) rondasJugadas++;
            if(rondasJugadas==2) {
                if(!unoAUno) {
                    if(j1.getPuntos()>=30 && j1.isPrimerAdivinador() && 30-j2.getPuntos()<=6) {
                        System.out.println("\n"+j1.getNombre()+" ha alcanzado los 30 puntos límite.\nSin embargo, como "+j1.getNombre()+" fue el primero en adivinar, "+j2.getNombre()+" deberá realizar una ronda extra y evitar llegar a 30 también.");
                        unoAUno=true;
                    } else if(j2.getPuntos()>=30 && j2.isPrimerAdivinador() && 30-j1.getPuntos()<=6) {
                        System.out.println("\n"+j2.getNombre()+" ha alcanzado los 30 puntos límite.\nSin embargo, como "+j2.getNombre()+" fue el primero en adivinar, "+j1.getNombre()+" deberá realizar una ronda extra y evitar llegar a 30 también.");
                        unoAUno=true;
                    } else if(j1.getPuntos()>=30) {
                        System.out.println("\n"+j1.getNombre()+" alcanzó los 30 puntos límite. "+j2.getNombre()+" es el ganador.");
                        terminar=true;
                    } else if(j2.getPuntos()>=30) {
                        System.out.println("\n"+j2.getNombre()+" alcanzó los 30 puntos límite. "+j1.getNombre()+" es el ganador.");
                        terminar=true;
                    }
                } else if(!puntosReseteados && j1.getPuntos()<30) {
                    System.out.println("\n"+j1.getNombre()+" evitó llegar a los 30 puntos y es el ganador.");
                    terminar=true;
                } else if(!puntosReseteados && j2.getPuntos()<30) {
                    System.out.println("\n"+j2.getNombre()+" evitó llegar a los 30 puntos y es el ganador.");
                    terminar=true;
                } else if(!puntosReseteados) {
                    System.out.println("\nAmbos jugadores han llegado a los 30 puntos.\nAhora deberán realizar enfrentamientos directos hasta que uno adivine la palabra del otro en menos intentos.");
                    j1.setPuntos(0);
                    j2.setPuntos(0);
                    puntosReseteados=true;
                    rondasJugadas=0;
                } else if(j1.getPuntos()-j2.getPuntos()==0) {
                    System.out.println("\nAmbos jugadores emplearon la misma cantidad de intentos que el otro.\nSe realizará otro enfrentaminto directo.");
                    j1.setPuntos(0);
                    j2.setPuntos(0);
                    rondasJugadas=0;
                } else terminar=true;
            }
        } while(!terminar);
        if(puntosReseteados && j1.getPuntos()<j2.getPuntos()) System.out.println("\n"+j1.getNombre()+" adivinó la palabra en menos intentos que "+j2.getNombre()+" y es el ganador.");
        else if(puntosReseteados) System.out.println("\n"+j2.getNombre()+" adivinó la palabra en menos intentos que "+j1.getNombre()+" y es el ganador.");
    }
    public static void main(String[] args) {
        Scanner entrada=new Scanner(System.in);
        System.out.print("- AHORCADO -\n\n1. Un jugador\n2. Dos jugadores\n\nIngresá el dígito correspondiente: ");
        int respuesta=entrada.nextInt();
        entrada.nextLine();
        while(respuesta<1 || respuesta>2) {
            System.out.print("Dígito no válido, intentá nuevamente: ");
            respuesta=entrada.nextInt();
            entrada.nextLine();
        }
        if(respuesta==1) {
            try { unJugador(entrada); }
            catch(FileNotFoundException e) { System.out.println("Error: no se encontró el archivo. Debería haber un archivo llamado \"diccionario.txt\" con un listado de una palabra por línea dentro de Juegos/src/Ahorcado."); }
        }
        else dosJugadores(entrada);
    }
}