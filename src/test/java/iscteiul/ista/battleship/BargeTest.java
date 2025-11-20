package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da classe Barge (Barco de tamanho 1)")
class BargeTest {

    @Test
    @DisplayName("O método getSize() deve retornar 1")
    void testGetSize() {
        Barge barge = new Barge(Compass.NORTH, new Position(2, 3));
        assertEquals(1, barge.getSize());
    }

    @Test
    @DisplayName("A barca deve criar exatamente uma posição igual à posição inicial")
    void testSinglePositionCreation() {
        Position pos = new Position(5, 7);
        Barge barge = new Barge(Compass.EAST, pos);

        assertEquals(1, barge.getPositions().size(), "A barca só ocupa 1 célula");

        IPosition p = barge.getPositions().get(0);

        assertAll(
                () -> assertEquals(5, p.getRow(), "Linha incorreta"),
                () -> assertEquals(7, p.getColumn(), "Coluna incorreta")
        );
    }

    @Test
    @DisplayName("O bearing deve ser corretamente guardado")
    void testBearing() {
        Barge barge = new Barge(Compass.SOUTH, new Position(0, 0));
        assertEquals(Compass.SOUTH, barge.getBearing());
    }
}
