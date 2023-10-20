package Ahorcado;
import java.util.*;
public class Jugador {
    private boolean primerAdivinador;
    private int fallas;
    private String nombre;

    public Jugador(boolean primerAdivinador, int fallas, String nombre) {
        this.primerAdivinador=primerAdivinador;
        this.fallas = fallas;
        this.nombre = nombre;
    }
    public boolean isPrimerAdivinador() { return primerAdivinador; }
    public void setPrimerAdivinador(boolean primerAdivinador) { this.primerAdivinador = primerAdivinador; }
    public int getFallas() { return fallas; }
    public void setFallas(int fallas) { this.fallas = fallas; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void adivinar(boolean dosJugadores,Palabra palabra, Scanner entrada) {
        boolean continuar;
        Character letra=null;
        HashSet<Character> historialIntentos=new HashSet<>(),historialIntentosFallidos=new HashSet<>();
        int vidas=6;
        do {
            if(historialIntentosFallidos.size()>0) {
                System.out.print("\nIntentos fallidos: ");
                for(Character caracter:historialIntentosFallidos) System.out.print(caracter+" - ");
            }
            System.out.print("\n");
            palabra.mostrarEstadoActual();
            System.out.print("Vidas: "+vidas+"\nIngresá cualquier letra: ");
            do {
                try {
                    String entradaString=entrada.nextLine();
                    while(entradaString.length()>1) {
                        System.out.print("Por favor ingresá una sola letra: ");
                        entradaString=entrada.nextLine();
                    }
                    letra=entradaString.toUpperCase().charAt(0);
                    while(historialIntentos.contains(letra)) {
                        System.out.print("Ya ingresaste esa letra, intente nuevamente: ");
                        letra=entrada.nextLine().toUpperCase().charAt(0);
                    }
                    continuar=true;
                } catch(StringIndexOutOfBoundsException e) {
                    continuar=false;
                    System.out.print("Error: input vacío. Intente nuevamente: ");
                }
            } while(!continuar);
            historialIntentos.add(letra);
            if(palabra.getCaracteresPorDevelar().contains(letra) || palabra.getCaracteresPorDevelar().contains(Palabra.vocalConTilde(letra))) {
                palabra.getCaracteresPorDevelar().remove(letra);
                palabra.getCaracteresPorDevelar().remove(Palabra.vocalConTilde(letra));
                System.out.println("\nLa letra está en la palabra.");
            } else {
                fallas++;
                historialIntentosFallidos.add(letra);
                vidas--;
                System.out.println("\nLa letra no está en la palabra.");
            }
        } while(palabra.getCaracteresPorDevelar().size()>0 && vidas>0);
        if(!dosJugadores && palabra.getCaracteresPorDevelar().size()==0) System.out.println("\nAdivinaste la palabra \""+palabra.getPalabra().toUpperCase()+"\" en "+historialIntentosFallidos.size()+" intentos.");
        else if(!dosJugadores && vidas==0) System.out.println("\nNo pudiste adivinar la palabra \""+palabra.getPalabra().toUpperCase()+"\".");
        else if(dosJugadores && palabra.getCaracteresPorDevelar().size()==0) System.out.println("\n"+nombre+" adivinó la palabra \""+palabra.getPalabra().toUpperCase()+"\" y suma "+ fallas +" puntos.");
        else System.out.println("\n"+nombre+" no pudo adivinar la palabra \""+palabra.getPalabra().toUpperCase()+"\" y suma "+ fallas +" puntos.");
    }
}