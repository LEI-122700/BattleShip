package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da classe Caravel (Barco de tamanho 2)")
class CaravelTest {

    @Test
    @DisplayName("getSize() deve retornar 2")
    void testGetSize() {
        Caravel caravel = new Caravel(Compass.NORTH, new Position(0, 0));
        assertEquals(2, caravel.getSize());
    }

    @Test
    @DisplayName("Caravel orientada a NORTH ocupa duas posições verticais")
    void testNorthPositions() {
        Position pos = new Position(3, 4);
        Caravel caravel = new Caravel(Compass.NORTH, pos);

        assertEquals(2, caravel.getPositions().size());

        IPosition p1 = caravel.getPositions().get(0);
        IPosition p2 = caravel.getPositions().get(1);

        assertAll(
                () -> assertEquals(3, p1.getRow()),
                () -> assertEquals(4, p1.getColumn()),
                () -> assertEquals(4, p2.getRow()),
                () -> assertEquals(4, p2.getColumn())
        );
    }

    @Test
    @DisplayName("Caravel orientada a SOUTH ocupa duas posições verticais")
    void testSouthPositions() {
        Position pos = new Position(1, 2);
        Caravel caravel = new Caravel(Compass.SOUTH, pos);

        assertEquals(2, caravel.getPositions().size());

        IPosition p1 = caravel.getPositions().get(0);
        IPosition p2 = caravel.getPositions().get(1);

        assertAll(
                () -> assertEquals(1, p1.getRow()),
                () -> assertEquals(2, p1.getColumn()),
                () -> assertEquals(2, p2.getRow()),
                () -> assertEquals(2, p2.getColumn())
        );
    }

    @Test
    @DisplayName("Caravel orientada a EAST ocupa duas posições horizontais")
    void testEastPositions() {
        Position pos = new Position(5, 6);
        Caravel caravel = new Caravel(Compass.EAST, pos);

        assertEquals(2, caravel.getPositions().size());

        IPosition p1 = caravel.getPositions().get(0);
        IPosition p2 = caravel.getPositions().get(1);

        assertAll(
                () -> assertEquals(5, p1.getRow()),
                () -> assertEquals(6, p1.getColumn()),
                () -> assertEquals(5, p2.getRow()),
                () -> assertEquals(7, p2.getColumn())
        );
    }

    @Test
    @DisplayName("Caravel orientada a WEST ocupa duas posições horizontais")
    void testWestPositions() {
        Position pos = new Position(8, 3);
        Caravel caravel = new Caravel(Compass.WEST, pos);

        assertEquals(2, caravel.getPositions().size());

        IPosition p1 = caravel.getPositions().get(0);
        IPosition p2 = caravel.getPositions().get(1);

        assertAll(
                () -> assertEquals(8, p1.getRow()),
                () -> assertEquals(3, p1.getColumn()),
                () -> assertEquals(8, p2.getRow()),
                () -> assertEquals(4, p2.getColumn())
        );
    }

    @Test
    @DisplayName("Construtor deve lançar AssertionError se bearing for null")
    void testNullBearing() {
        Position pos = new Position(0, 0);
        assertThrows(AssertionError.class,
                () -> new Caravel(null, pos)
        );
    }
}
