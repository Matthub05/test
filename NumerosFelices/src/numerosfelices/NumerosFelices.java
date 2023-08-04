package numerosfelices;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Mateo
 */
public class NumerosFelices {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean nuevaIteracion = false;
        do {
            identificarNumero();
            nuevaIteracion = preguntarRepeticion();
            System.out.println();
        } while (nuevaIteracion);
        System.out.println("\n"+"Fin.");
    }
    
    private static void identificarNumero() {
        long numero = leerNumero();
        boolean aux = escribirIteraciones(numero);
        String resultado = (aux) ? "Es un número feliz :D" : "No es un número feliz :(";
        System.out.println(resultado+"\n");
    }
    
    private static boolean escribirIteraciones(long entrada) {
        ArrayList<String> resultados = new ArrayList<>();
        long numero = entrada;
        while(numero != 1) {
            long resultado = sumarCuadrados(numero);
            String aux = resultado+"";
            for (int i = 0; i < resultados.size(); i++) {
                if (resultados.get(i).matches(aux)) {
                    System.out.println("\nCiclo a partir de: "+aux+"\n");
                    return false;
                }
            }
            resultados.add(aux);
            numero = resultado;
        }
        return true;
    }
    
    private static long sumarCuadrados(long entrada){
        String aux = entrada+"", feedbk = "";
        long numero = entrada, resultado = 0;
        for (int i = 0; i < aux.length(); i++) {
            feedbk += aux.charAt(i)+"²";
            feedbk += (i == aux.length()-1) ? " = " : " + ";
            resultado += Math.pow((numero%10), 2);
            numero /= 10;
        }
        feedbk += resultado+"";
        System.out.println(feedbk);
        return resultado;
    }
    
    public static long leerNumero() {
        String entrada = "";
        do {
            entrada = leerLine("Ingrese un número:");
        } while (!esNumero(entrada));
        long retorno = Long.parseLong(entrada);
        if (retorno < 0) retorno *= -1;
        return retorno;
    }
    
    public static String leerLine(String mensaje){
        Scanner sc = new Scanner(System.in);
        System.out.println(mensaje);
        return sc.nextLine(); 
    }
    
    public static boolean esNumero(String entrada) {
        Long aux;
        try {
            aux = Long.parseLong(entrada);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    
    public static boolean preguntarRepeticion() {
        String entrada = "";
        do {
            entrada = leerLine("¿Otra vez?: [S/N]");
            switch (entrada.toLowerCase()) {
                case "s":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.println("Entrada no válida\n");
            }
        } while (true);
    }
}
