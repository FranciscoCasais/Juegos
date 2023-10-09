package Ahorcado;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        boolean fallasReseteadas=false,terminar=false,unoAUno=false;
        Random generadorAleatorio=new Random();
        Scanner entrada=new Scanner(System.in);
        System.out.print("- AHORCADO -\n\nJugador 1, ingresá tu nombre: ");
        String nombreJ1=entrada.nextLine();
        System.out.print("Jugador 2, ingresá tu nombre: ");
        String nombreJ2=entrada.nextLine();
        Jugador j1=new Jugador(false,0,nombreJ1),j2=new Jugador(false,0,nombreJ2);
        System.out.print("\nSeleccionen la cantidad de intentos fallidos límite:\n\n1. Partida a 12 fallas\n2. Partida a 18 fallas\n3. Partida a 24 fallas\n4. Partida a 30 fallas\n\nIngresar el dígito correspondiente: ");
        int adivinador=generadorAleatorio.nextInt(2)+1,fallasLimite=0,respuesta=entrada.nextInt(),rondasJugadas=2;
        entrada.nextLine();
        while(respuesta<1 || respuesta>4) {
            System.out.print("Dígito no válido, intentá nuevamente: ");
            respuesta=entrada.nextInt();
            entrada.nextLine();
        }
        switch(respuesta) {
            case 1:
                fallasLimite=12;
                break;
            case 2:
                fallasLimite=18;
                break;
            case 3:
                fallasLimite=24;
                break;
            case 4:
                fallasLimite=30;
                break;
        }
        if(adivinador==1) {
            j1.setPrimerAdivinador(true);
            System.out.println("\nEl primero en adivinar es... "+j1.getNombre()+".");
        } else {
            j2.setPrimerAdivinador(true);
            System.out.println("\nEl primero en adivinar es... "+j2.getNombre()+".");
        }
        do {
            if(adivinador==1) System.out.print("\nTurno de adivinar de "+j1.getNombre()+".\n"+j2.getNombre()+", ingresá una palabra: ");
            else System.out.print("\nTurno de adivinar de "+j2.getNombre()+".\n"+j1.getNombre()+", ingresá una palabra: ");
            String palabraString=entrada.nextLine();
            while(palabraString.contains(" ")) {
                System.out.print("Por favor ingresá una sola palabra: ");
                palabraString=entrada.nextLine();
            }
            Palabra palabra=new Palabra(palabraString);
            System.out.println(".\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n");
            if(adivinador==1) j1.adivinar(true,palabra,entrada);
            else j2.adivinar(true,palabra,entrada);
            if(adivinador==1) adivinador++;
            else adivinador--;
            if(fallasReseteadas) rondasJugadas++;
            if(rondasJugadas==2) {
                if(!unoAUno) {
                    if(j1.getFallas()>=fallasLimite && j1.isPrimerAdivinador() && fallasLimite-j2.getFallas()<=6) {
                        System.out.println("\n"+j1.getNombre()+" ha alcanzado las "+fallasLimite+" fallas límite.\nSin embargo, como "+j1.getNombre()+" fue el primero en adivinar, "+j2.getNombre()+" deberá realizar una ronda extra y evitar llegar a "+fallasLimite+" también.");
                        unoAUno=true;
                    } else if(j2.getFallas()>=fallasLimite && j2.isPrimerAdivinador() && fallasLimite-j1.getFallas()<=6) {
                        System.out.println("\n"+j2.getNombre()+" ha alcanzado las "+fallasLimite+" fallas límite.\nSin embargo, como "+j2.getNombre()+" fue el primero en adivinar, "+j1.getNombre()+" deberá realizar una ronda extra y evitar llegar a "+fallasLimite+" también.");
                        unoAUno=true;
                    } else if(j1.getFallas()>=fallasLimite) {
                        System.out.println("\n"+j1.getNombre()+" alcanzó las "+fallasLimite+" fallas límite. "+j2.getNombre()+" es el ganador.");
                        terminar=true;
                    } else if(j2.getFallas()>=fallasLimite) {
                        System.out.println("\n"+j2.getNombre()+" alcanzó las "+fallasLimite+" fallas límite. "+j1.getNombre()+" es el ganador.");
                        terminar=true;
                    }
                } else if(!fallasReseteadas && j1.getFallas()<fallasLimite) {
                    System.out.println("\n"+j1.getNombre()+" evitó llegar a las "+fallasLimite+" fallas y es el ganador.");
                    terminar=true;
                } else if(!fallasReseteadas && j2.getFallas()<fallasLimite) {
                    System.out.println("\n"+j2.getNombre()+" evitó llegar a las "+fallasLimite+" fallas y es el ganador.");
                    terminar=true;
                } else if(!fallasReseteadas) {
                    System.out.println("\nAmbos jugadores han llegado a las "+fallasLimite+" fallas.\nAhora deberán realizar enfrentamientos directos hasta que uno adivine la palabra del otro en menos intentos.");
                    j1.setFallas(0);
                    j2.setFallas(0);
                    fallasReseteadas=true;
                    rondasJugadas=0;
                } else if(j1.getFallas()-j2.getFallas()==0) {
                    System.out.println("\nAmbos jugadores emplearon la misma cantidad de intentos que el otro.\nSe realizará otro enfrentaminto directo.");
                    j1.setFallas(0);
                    j2.setFallas(0);
                    rondasJugadas=0;
                } else terminar=true;
            }
        } while(!terminar);
        if(fallasReseteadas && j1.getFallas()<j2.getFallas()) System.out.println("\n"+j1.getNombre()+" adivinó la palabra en menos intentos que "+j2.getNombre()+" y es el ganador.");
        else if(fallasReseteadas) System.out.println("\n"+j2.getNombre()+" adivinó la palabra en menos intentos que "+j1.getNombre()+" y es el ganador.");
    }
}