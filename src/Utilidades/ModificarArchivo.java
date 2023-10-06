package Utilidades;
import java.io.*;
public class ModificarArchivo {
    public static void main(String[] args) {
        String rutaArchivo = "src/Ahorcado/diccionario.txt";

        try {
            // Paso 1: Leer el contenido del archivo en memoria
            FileReader fileReader = new FileReader(rutaArchivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder contenido = new StringBuilder();
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                if(linea.contains(" ")) {
                    String nuevaLinea="";
                    for(int i=0;i<linea.length();i++) {
                        if(linea.charAt(i)!=' ') nuevaLinea+=linea.charAt(i);
                    }
                    linea=nuevaLinea;
                }
                if(linea.length()>=5 && linea.length()<=10) contenido.append(linea).append("\n");
            }

            bufferedReader.close();

            // Paso 2: Escribir el contenido modificado de vuelta al archivo
            FileWriter fileWriter = new FileWriter(rutaArchivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(contenido.toString());
            bufferedWriter.close();

            System.out.println("El archivo ha sido modificado exitosamente.");
        } catch (IOException e) {
            System.err.println("Ocurrió un error al modificar el archivo.");
            e.printStackTrace();
        }
    }
}