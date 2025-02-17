package sopadeLetras;

import java.util.Scanner;
import sopadeletras.Partida;
public class SopaDeLetras {

    private static final int MAX_PALABRAS = 50; // Máximo de palabras que se pueden almacenar
    private static String[] palabras = new String[MAX_PALABRAS]; // Arreglo para almacenar las palabras
    private static int cantidadPalabras = 0; // Contador de palabras ingresadas
    private static Scanner scanner = new Scanner(System.in);
    private static String nombreUsuario;
    private static char[][] tablero;
    private static int tamañoTablero = 15; // Por defecto, sección B (15x15)
    private static Partida[] historial = new Partida[10]; // Tamaño inicial
    private static int cantidadPartidas = 0; // Contador de partidas jugadas

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
            try{
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

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
            }} catch (java.util.InputMismatchException e) {
            System.out.println("Error: Debes ingresar un número entero. Intente de nuevo.");
            scanner.nextLine(); // Limpiar el buffer para evitar un bucle infinito
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
        try{    
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

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
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Debes ingresar un número entero. Intente de nuevo.");
            scanner.nextLine();
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
        try{
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

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
            }} catch (java.util.InputMismatchException e) {
            System.out.println("Error: Debes ingresar un número entero. Intente de nuevo.");
            scanner.nextLine(); // Limpiar el buffer para evitar un bucle infinito
        }
        }
    }

    private static void insertarPalabras() {
        System.out.print("Ingrese el número de palabras que desea insertar: ");
        try{
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

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
                i--; // Reintentar la misma posición1
                
             }
        }} catch (java.util.InputMismatchException e) {
        System.out.println("Error: Debes ingresar un número entero. Intente de nuevo.");
        scanner.nextLine(); // Limpiar el buffer para evitar un bucle infinito
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
                i++;
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
            // Eliminar la palabra moviendo las palabras restantes
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
        return -1; // Palabra no encontrada
    }

    private static boolean validarLongitudPalabra(String palabra) {
        int longitud = palabra.length();
        return (longitud >= 3 && longitud <= 8); // Ajustar según la sección
    }

    private static void jugar() {
    if (cantidadPalabras == 0) {
        System.out.println("No hay palabras en el banco. Por favor, inserte palabras primero.");
        return;
    }

    // Inicializar variables del juego
    int puntos = 25; // Puntos iniciales
    int oportunidades = 4; // Número de oportunidades
    int palabrasEncontradas = 0; // Contador de palabras encontradas
    boolean[] palabrasEncontradasArray = new boolean[cantidadPalabras]; // Para marcar palabras encontradas

    // Generar el tablero con las palabras colocadas
    generarTableroConPalabras();

    // Mostrar el tablero inicial
    System.out.println("\n=== TABLERO ===");
    mostrarTablero();

    // Bucle principal del juego
    while (oportunidades > 0 && palabrasEncontradas < cantidadPalabras) {
        System.out.println("\n=== JUEGO ===");
        System.out.println("Puntos: " + puntos);
        System.out.println("Oportunidades restantes: " + oportunidades);
        System.out.println("Palabras encontradas: " + palabrasEncontradas + " / " + cantidadPalabras);
        System.out.print("Ingrese una palabra: ");
        String palabra = scanner.nextLine().toUpperCase();

        // Verificar si la palabra está en el banco de palabras
        int indice = buscarPalabra(palabra);
        if (indice != -1 && !palabrasEncontradasArray[indice]) {
            // Verificar si la palabra está en el tablero
            if (buscarPalabraEnTablero(palabra)) {
                palabrasEncontradasArray[indice] = true; // Marcar la palabra como encontrada
                palabrasEncontradas++;
                puntos += palabra.length(); // Sumar puntos según la longitud de la palabra
                System.out.println("¡Palabra encontrada! +" + palabra.length() + " puntos.");
            } else {
                oportunidades--; // Restar una oportunidad
                puntos -= 5; // Restar 5 puntos por error
                System.out.println("La palabra no está en el tablero. -5 puntos.");
            }
        } else if (indice != -1 && palabrasEncontradasArray[indice]) {
            System.out.println("La palabra ya fue encontrada. -5 puntos.");
            puntos -= 5;
        } else {
            oportunidades--; // Restar una oportunidad
            puntos -= 5; // Restar 5 puntos por error
            System.out.println("La palabra no está en el banco. -5 puntos.");
        }

        // Mostrar el tablero actualizado
        System.out.println("\n=== TABLERO ===");
        mostrarTablero();
    }

    // Mostrar resultado final
    if (palabrasEncontradas == cantidadPalabras) {
        System.out.println("\n¡Felicidades! Has encontrado todas las palabras.");
    } else {
        System.out.println("\n¡Se acabaron las oportunidades! Fin del juego.");
    }
    System.out.println("Puntos finales: " + puntos);

    // Guardar la partida en el historial (puedes implementar esto más adelante)
    guardarPartidaEnHistorial(puntos, palabrasEncontradas, oportunidades, nombreUsuario);
}

    private static void generarTableroConPalabras() {
        tablero = new char[tamañoTablero][tamañoTablero];

        // Inicializar tablero con espacios vacíos
        for (int i = 0; i < tamañoTablero; i++) {
            for (int j = 0; j < tamañoTablero; j++) {
                tablero[i][j] = ' ';
            }
        }

        // Colocar palabras
        for (String palabra : palabras) {
            if (palabra != null) {
                colocarPalabraEnTablero(palabra);
            }
        }

        // Llenar espacios vacíos con letras aleatorias
        for (int i = 0; i < tamañoTablero; i++) {
            for (int j = 0; j < tamañoTablero; j++) {
                if (tablero[i][j] == ' ') {
                    tablero[i][j] = (char) ('A' + Math.random() * 26);
                }
            }
        }
    }

    private static void colocarPalabraEnTablero(String palabra) {
        int longitud = palabra.length();
        boolean colocada = false;

        while (!colocada) {
            boolean horizontal = Math.random() < 0.5;
            int fila = (int) (Math.random() * tamañoTablero);
            int columna = (int) (Math.random() * tamañoTablero);

            if (horizontal && columna + longitud <= tamañoTablero && !hayColisionHorizontal(fila, columna, palabra)) {
                for (int i = 0; i < longitud; i++) {
                    tablero[fila][columna + i] = palabra.charAt(i);
                }
                colocada = true;
            } else if (!horizontal && fila + longitud <= tamañoTablero && !hayColisionVertical(fila, columna, palabra)) {
                for (int i = 0; i < longitud; i++) {
                    tablero[fila + i][columna] = palabra.charAt(i);
                }
                colocada = true;
            }
        }
    }

    private static boolean hayColisionHorizontal(int fila, int columna, String palabra) {
        for (int i = 0; i < palabra.length(); i++) {
            if (tablero[fila][columna + i] != ' ') {
                return true;
            }
        }
        return false;
    }

    private static boolean hayColisionVertical(int fila, int columna, String palabra) {
        for (int i = 0; i < palabra.length(); i++) {
            if (tablero[fila + i][columna] != ' ') {
                return true;
            }
        }
        return false;
    }

    private static boolean buscarPalabraEnTablero(String palabra) {
    int longitud = palabra.length();

    // Buscar la palabra en el tablero (horizontal y vertical)
    for (int i = 0; i < tamañoTablero; i++) {
        for (int j = 0; j < tamañoTablero; j++) {
            // Buscar horizontalmente
            if (j + longitud <= tamañoTablero) {
                boolean encontrada = true;
                for (int k = 0; k < longitud; k++) {
                    if (tablero[i][j + k] != palabra.charAt(k)) {
                        encontrada = false;
                        break;
                    }
                }
                if (encontrada) {
                    // Marcar la palabra como encontrada
                    for (int k = 0; k < longitud; k++) {
                        tablero[i][j + k] = '#';
                    }
                    return true;
                }
            }

            // Buscar verticalmente
            if (i + longitud <= tamañoTablero) {
                boolean encontrada = true;
                for (int k = 0; k < longitud; k++) {
                    if (tablero[i + k][j] != palabra.charAt(k)) {
                        encontrada = false;
                        break;
                    }
                }
                if (encontrada) {
                    // Marcar la palabra como encontrada
                    for (int k = 0; k < longitud; k++) {
                        tablero[i + k][j] = '#';
                    }
                    return true;
                }
            }
        }
    }
    return false;
}
    
    private static void mostrarTablero() {
        for (int i = 0; i < tamañoTablero; i++) {
            for (int j = 0; j < tamañoTablero; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }
   
    private static void guardarPartidaEnHistorial(int puntos, int palabrasEncontradas, int oportunidades, String nombreUsuario) {
        if (cantidadPartidas >= historial.length) {
            // Redimensionar el arreglo duplicando su tamaño
            Partida[] nuevoHistorial = new Partida[historial.length * 2];
            System.arraycopy(historial, 0, nuevoHistorial, 0, historial.length);
            historial = nuevoHistorial;
        }

        // Crear y almacenar la nueva partida
        int fallos = 4 - oportunidades; // Los fallos son las oportunidades restantes
        historial[cantidadPartidas] = new Partida(nombreUsuario, puntos, fallos, palabrasEncontradas);
        cantidadPartidas++;
    }
    
    private static void mostrarHistorial() {
    System.out.println("=== HISTORIAL DE PARTIDAS ===");
    
    // Verificar si hay partidas guardadas
    if (cantidadPartidas == 0) {
        System.out.println("No hay partidas registradas en el historial.");
        return;
    }

    // Recorrer el arreglo de historial y mostrar cada partida
    for (int i = 0; i < cantidadPartidas; i++) {
        Partida partida = historial[i];
        if (partida != null) { // Asegurarse de que la partida no sea nula
            System.out.println(partida.toString()); // Usar el método toString() de Partida
        }
    }
}

    private static void mostrarPuntuacionesAltas() {
    System.out.println("=== PUNTUACIONES MÁS ALTAS ===");

    // Verificar si hay partidas registradas
    if (cantidadPartidas == 0) {
        System.out.println("No hay partidas registradas.");
        return;
    }

    // Crear una copia del historial para no modificar el original
    Partida[] copiaHistorial = new Partida[cantidadPartidas];
    System.arraycopy(historial, 0, copiaHistorial, 0, cantidadPartidas);

    // Ordenar el arreglo de partidas por puntos (de mayor a menor)
    for (int i = 0; i < copiaHistorial.length - 1; i++) {
        for (int j = i + 1; j < copiaHistorial.length; j++) {
            if (copiaHistorial[i].getPuntos() < copiaHistorial[j].getPuntos()) {
                // Intercambiar partidas si están en el orden incorrecto
                Partida temp = copiaHistorial[i];
                copiaHistorial[i] = copiaHistorial[j];
                copiaHistorial[j] = temp;
            }
        }
    }

    // Mostrar las 3 puntuaciones más altas
    int limite = Math.min(3, copiaHistorial.length); // Mostrar máximo 3 partidas
    for (int i = 0; i < limite; i++) {
        Partida partida = copiaHistorial[i];
        System.out.println((i + 1) + ". " + partida.getNombreJugador() + " - Puntos: " + partida.getPuntos());
    }
}

    private static void mostrarInformacionEstudiante() {
        System.out.println("=== INFORMACIÓN DEL ESTUDIANTE ===");
        System.out.println("Nombre: Jefferson Saúl Ajcúc Sambrano");
        System.out.println("Carnet: 202407596");
        System.out.println("Sección: B");
    }
}

