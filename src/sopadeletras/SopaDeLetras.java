package sopadeLetras;

import java.util.Scanner;

public class SopaDeLetras {

    private static final int MAX_PALABRAS = 50; 
    private static String[] palabras = new String[MAX_PALABRAS]; 
    private static int cantidadPalabras = 0; 
    private static Scanner scanner = new Scanner(System.in);
    private static String nombreUsuario;
    private static char[][] tablero;
    private static int tamañoTablero = 15; 

    public static void main(String[] args) {
        mostrarMenuPrincipal();
    }

    private static void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("=== MENÚ PRINCIPAL ===");
            System.out.println("1. Nueva Partida");
            System.out.println("2. Historial de Partidas");
            System.out.println("3. Mostrar Puntuaciones más Altas");
            System.out.println("4. Mostrar Información del Estudiante");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    nuevaPartida();
                    break;
                case 2:
                    mostrarHistorial();
                    break;
                case 3:
                    mostrarPuntuacionesAltas();
                    break;
                case 4:
                    mostrarInformacionEstudiante();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void nuevaPartida() {
        System.out.print("Ingrese su nombre: ");
        nombreUsuario = scanner.nextLine();
        System.out.println("Bienvenido, " + nombreUsuario + "!");

        while (true) {
            System.out.println("=== NUEVA PARTIDA ===");
            System.out.println("1. Menú Palabras");
            System.out.println("2. Jugar");
            System.out.println("3. Terminar Partida");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcion) {
                case 1:
                    menuPalabras();
                    break;
                case 2:
                    jugar();
                    break;
                case 3:
                    System.out.println("Terminando partida...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void menuPalabras() {
        while (true) {
            System.out.println("=== MENÚ PALABRAS ===");
            System.out.println("1. Insertar Palabras");
            System.out.println("2. Modificar Palabras");
            System.out.println("3. Eliminar Palabras");
            System.out.println("4. Mostrar Palabras");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    insertarPalabras();
                    break;
                case 2:
                    modificarPalabras();
                    break;
                case 3:
                    eliminarPalabras();
                    break;
                case 4:
                    mostrarPalabras();
                    break;
                case 5:
                    System.out.println("Saliendo del menú de palabras...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void insertarPalabras() {
        System.out.print("Ingrese el número de palabras que desea insertar: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        if (cantidadPalabras + cantidad > MAX_PALABRAS) {
            System.out.println("No se pueden agregar más palabras. Límite alcanzado.");
            return;
        }

        for (int i = 0; i < cantidad; i++) {
            System.out.print("Ingrese la palabra " + (i + 1) + ": ");
            String palabra = scanner.nextLine();
            if (validarLongitudPalabra(palabra)) {
                palabras[cantidadPalabras] = palabra.toUpperCase();
                cantidadPalabras++;
                System.out.println("Palabra agregada: " + palabra);
            } else { 
                System.out.println("La palabra no cumple con la longitud permitida. Intente de nuevo.");
                i--; 
                
            }
        }
    }

    private static void modificarPalabras() {
        
        System.out.print("Ingrese la palabra que desea modificar: ");
        String palabraVieja = scanner.nextLine().toUpperCase();
        int indice = buscarPalabra(palabraVieja);
         for (int i = 0; i <= indice; i++) {
            if (indice != -1) {
            System.out.print("Ingrese la nueva palabra: ");
            String palabraNueva = scanner.nextLine();
                    
                if (validarLongitudPalabra(palabraNueva)) {
                palabras[indice] = palabraNueva.toUpperCase();
                System.out.println("Palabra modificada.");
                } else {
                System.out.println("La nueva palabra no cumple con la longitud permitida.");
                i--;
                }
            } else {
            System.out.println("La palabra no se encuentra en el banco de palabras.");
            i--;
                    
            
        }
        } 
        
    }

    private static void eliminarPalabras() {
        System.out.print("Ingrese la palabra que desea eliminar: ");
        String palabra = scanner.nextLine().toUpperCase();
        int indice = buscarPalabra(palabra);

        if (indice != -1) {
            
            for (int i = indice; i < cantidadPalabras - 1; i++) {
                palabras[i] = palabras[i + 1];
            }
            cantidadPalabras--;
            System.out.println("Palabra eliminada.");
        } else {
            System.out.println("La palabra no se encuentra en el banco de palabras.");
        }
    }

    private static void mostrarPalabras() {
        if (cantidadPalabras == 0) {
            System.out.println("No hay palabras en el banco.");
        } else {
            System.out.println("=== PALABRAS ===");
            for (int i = 0; i < cantidadPalabras; i++) {
                System.out.println(palabras[i]);
            }
        }
    }

    private static int buscarPalabra(String palabra) {
        for (int i = 0; i < cantidadPalabras; i++) {
            if (palabras[i].equals(palabra)) {
                return i;
            }
        }
        return -1; 
    }

    private static boolean validarLongitudPalabra(String palabra) {
        int longitud = palabra.length();
        return (longitud >= 3 && longitud <= 8); 
    }

    private static void jugar() {
        if (cantidadPalabras == 0) {
            System.out.println("No hay palabras en el banco. Por favor, inserte palabras primero.");
            return;
        }

        generarTablero();
        mostrarTablero();

      
    }

    private static void generarTablero() {
        tablero = new char[tamañoTablero][tamañoTablero];
        
        for (int i = 0; i < tamañoTablero; i++) {
            for (int j = 0; j < tamañoTablero; j++) {
                tablero[i][j] = (char) ('A' + Math.random() * 26); 
            }
        }

        
    }

    private static void mostrarTablero() {
        for (int i = 0; i < tamañoTablero; i++) {
            for (int j = 0; j < tamañoTablero; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void mostrarHistorial() {
        System.out.println("=== HISTORIAL DE PARTIDAS ===");
       
    }

    private static void mostrarPuntuacionesAltas() {
        System.out.println("=== PUNTUACIONES MÁS ALTAS ===");
        
    }

    private static void mostrarInformacionEstudiante() {
        System.out.println("=== INFORMACIÓN DEL ESTUDIANTE ===");
        System.out.println("Nombre: Jefferson Saúl Ajcúc Sambrano");
        System.out.println("Carnet: 202407596");
        System.out.println("Sección: B");
    }
}
