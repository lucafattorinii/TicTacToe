import java.util.Scanner;

public class TresEnRaya {
    private static final char VACIO = ' ';
    private static final char JUGADOR_X = 'X';
    private static final char JUGADOR_O = 'O';
    private static char turnoActual = JUGADOR_X;
    private static char[][] tablero = {
        { VACIO, VACIO, VACIO },
        { VACIO, VACIO, VACIO },
        { VACIO, VACIO, VACIO }
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean juegoEnCurso = true;

        while (juegoEnCurso) {
            imprimirTablero();
            System.out.println("Turno del jugador " + turnoActual + ". Ingresa la fila y la columna (0, 1, 2): ");
            int fila = scanner.nextInt();
            int columna = scanner.nextInt();

            if (fila < 0 || fila > 2 || columna < 0 || columna > 2 || tablero[fila][columna] != VACIO) {
                System.out.println("Movimiento inválido. Inténtalo de nuevo.");
            } else {
                tablero[fila][columna] = turnoActual;
                if (hayGanador()) {
                    imprimirTablero();
                    System.out.println("¡El jugador " + turnoActual + " ha ganado!");
                    juegoEnCurso = false;
                } else if (tableroLleno()) {
                    imprimirTablero();
                    System.out.println("¡El juego es un empate!");
                    juegoEnCurso = false;
                } else {
                    turnoActual = (turnoActual == JUGADOR_X) ? JUGADOR_O : JUGADOR_X;
                }
            }
        }
        scanner.close();
    }

    private static void imprimirTablero() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  -----");
        }
    }

    private static boolean hayGanador() {
        // Comprobar filas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] != VACIO && tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {
                return true;
            }
        }
        // Comprobar columnas
        for (int i = 0; i < 3; i++) {
            if (tablero[0][i] != VACIO && tablero[0][i] == tablero[1][i] && tablero[1][i] == tablero[2][i]) {
                return true;
            }
        }
        // Comprobar diagonales
        if (tablero[0][0] != VACIO && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            return true;
        }
        if (tablero[0][2] != VACIO && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            return true;
        }
        return false;
    }

    private static boolean tableroLleno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == VACIO) {
                    return false;
                }
            }
        }
        return true;
    }
}
