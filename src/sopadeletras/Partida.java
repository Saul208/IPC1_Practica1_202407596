/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sopadeletras;


public class Partida {
    String nombreJugador;
    int puntos;
    int fallos;
    int palabrasEncontradas;

    public Partida(String nombreJugador, int puntos, int fallos, int palabrasEncontradas) {
        this.nombreJugador = nombreJugador;
        this.puntos = puntos;
        this.fallos = fallos;
        this.palabrasEncontradas = palabrasEncontradas;
    }

    @Override
    public String toString() {
        return "Jugador: " + nombreJugador + ", Puntos: " + puntos + ", Fallos: " + fallos + ", Palabras encontradas: " + palabrasEncontradas;
    }
    public int getPuntos() {
        return puntos;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }
}
