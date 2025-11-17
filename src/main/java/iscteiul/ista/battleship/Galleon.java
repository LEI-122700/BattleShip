/**
 *
 */
package iscteiul.ista.battleship;

public class Galleon extends Ship {
    private static final Integer SIZE = 5;
    private static final String NAME = "Galeao";

    /**
     * Construtor do Galleon.
     *
     * @param bearing - direção (orientação) do galeão (NORTE, SUL, ESTE, OESTE)
     * @param pos     - posição inicial (geralmente o canto superior esquerdo da forma)
     * @throws IllegalArgumentException se a direção for inválida
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Define a forma do galeão quando está virado a NORTE.

     * O formato parece um “T” invertido:

     *   ■ ■ ■
     *     ■
     *     ■

     * As três primeiras posições estão na linha base,
     * e as duas seguintes descem na coluna do meio.
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Define a forma do galeão quando está virado a SUL.

     * O formato é semelhante ao NORTE, mas invertido verticalmente:

     *     ■
     *     ■
     *   ■ ■ ■
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Define a forma do galeão quando está virado a ESTE.

     * O formato é um “T” deitado para a direita:

     *   ■
     *   ■ ■ ■
     *   ■
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Define a forma do galeão quando está virado a OESTE.

     * O formato é um “T” deitado para a esquerda:

     *       ■
     *   ■ ■ ■
     *       ■
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow() - 1, pos.getColumn() + 2));
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 2));
    }

}
