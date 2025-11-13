/**
 *
 */
package iscteiul.ista.battleship;

public class Barge extends Ship {
    private static final Integer SIZE = 1;
    private static final String NAME = "Barca";

    /**
     * @param bearing - barge bearing
     * @param pos     - upper left position of the barge
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Retorna o tamanho da barca (número de células que ocupa).
     *
     * @return tamanho do navio (1)
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
