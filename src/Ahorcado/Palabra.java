package Ahorcado;
import java.util.HashSet;
public class Palabra {
    private HashSet<Character> caracteresPorDevelar;
    private String palabra;
    public Palabra(String palabra) {
        this.palabra = palabra;
        this.caracteresPorDevelar = establecerCaracteresPorDevelar();
    }
    public HashSet<Character> getCaracteresPorDevelar() { return caracteresPorDevelar; }
    public void setCaracteresPorDevelar(HashSet<Character> caracteresPorDevelar) { this.caracteresPorDevelar = caracteresPorDevelar; }
    public String getPalabra() { return palabra; }
    public void setPalabra(String palabra) { this.palabra = palabra; }
    public HashSet<Character> establecerCaracteresPorDevelar() {
        HashSet<Character> caracteresPorDevelar=new HashSet<>();
        for(int i=0;i<palabra.length();i++) {
            if(!caracteresPorDevelar.contains(palabra.charAt(i))) caracteresPorDevelar.add(palabra.toUpperCase().charAt(i));
        }
        return caracteresPorDevelar;
    }
    public void mostrarEstadoActual() {
        for(int i=0;i<palabra.length();i++) {
            if(!caracteresPorDevelar.contains(palabra.toUpperCase().charAt(i))) System.out.print(palabra.toUpperCase().charAt(i));
            else System.out.print("_");
            System.out.print(" ");
        }
        System.out.print("\n");
    }
    public static Character vocalConTilde(Character letra) {
        switch(letra) {
            case 'A':
                return 'Á';
            case 'E':
                return 'É';
            case 'I':
                return 'Í';
            case 'O':
                return 'Ó';
            case 'U':
                return 'Ú';
        }
        return null;
    }
}