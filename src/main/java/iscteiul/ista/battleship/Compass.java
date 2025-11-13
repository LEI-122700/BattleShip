/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Enumeração Compass representa as possíveis direções (orientações)
 * que um navio pode ter no jogo Battleship.

 * As direções disponíveis são:
 *  - NORTH ('n')
 *  - SOUTH ('s')
 *  - EAST  ('e')
 *  - WEST  ('o')  ← (a letra “o” representa “oeste” em português)
 *  - UNKNOWN ('u') caso a direção não seja reconhecida.

 * Esta enum facilita o manuseamento das orientações, evitando o uso
 * de strings ou caracteres soltos no código.
 *
 * @author fba
 */
public enum Compass {
    NORTH('n'), SOUTH('s'), EAST('e'), WEST('o'), UNKNOWN('u');

    private final char c;

    /**
     * Construtor da enumeração Compass.
     * É chamado automaticamente para cada uma das constantes definidas acima.
     *
     * @param c - carácter que representa a direção (por exemplo, 'n' para norte)
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Retorna o carácter associado a esta direção.
     *
     * @return o carácter ('n', 's', 'e', 'o' ou 'u')
     */
    public char getDirection() {
        return c;
    }

    /**
     * Sobrescreve o método toString() para devolver o carácter da direção.
     * Assim, se fizeres System.out.println(Compass.NORTH), imprime "n".
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Metodo auxiliar que converte um carácter ('n', 's', 'e', 'o')
     * para o respetivo valor da enumeração Compass.
     * Se o carácter não corresponder a nenhuma direção válida,
     * devolve Compass.UNKNOWN.
     * @param ch - carácter da direção
     * @return a direção correspondente na enum Compass
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }

        return bearing;
    }
}
