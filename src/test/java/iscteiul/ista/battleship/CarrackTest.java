// src/test/java/iscteiul/ista/battleship/CarrackTest.java
package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da classe Carrack (Nau, tamanho 3)")
class CarrackTest {

    @Test
    @DisplayName("getSize() deve retornar 3")
    void testGetSize() {
        Carrack carrack = new Carrack(Compass.NORTH, new Position(0, 0));
        assertEquals(3, carrack.getSize());
    }

    @Test
    @DisplayName("Carrack orientada a NORTH ocupa 3 posições verticais")
    void testNorthPositions() {
        Position pos = new Position(2, 5);
        Carrack carrack = new Carrack(Compass.NORTH, pos);

        assertEquals(3, carrack.getPositions().size());

        IPosition p1 = carrack.getPositions().get(0);
        IPosition p2 = carrack.getPositions().get(1);
        IPosition p3 = carrack.getPositions().get(2);

        assertAll(
                () -> assertEquals(2, p1.getRow()),
                () -> assertEquals(5, p1.getColumn()),
                () -> assertEquals(3, p2.getRow()),
                () -> assertEquals(5, p2.getColumn()),
                () -> assertEquals(4, p3.getRow()),
                () -> assertEquals(5, p3.getColumn())
        );
    }

    @Test
    @DisplayName("Carrack orientada a SOUTH ocupa 3 posições verticais")
    void testSouthPositions() {
        Position pos = new Position(1, 1);
        Carrack carrack = new Carrack(Compass.SOUTH, pos);

        assertEquals(3, carrack.getPositions().size());

        IPosition p1 = carrack.getPositions().get(0);
        IPosition p2 = carrack.getPositions().get(1);
        IPosition p3 = carrack.getPositions().get(2);

        assertAll(
                () -> assertEquals(1, p1.getRow()),
                () -> assertEquals(1, p1.getColumn()),
                () -> assertEquals(2, p2.getRow()),
                () -> assertEquals(1, p2.getColumn()),
                () -> assertEquals(3, p3.getRow()),
                () -> assertEquals(1, p3.getColumn())
        );
    }

    @Test
    @DisplayName("Carrack orientada a EAST ocupa 3 posições horizontais")
    void testEastPositions() {
        Position pos = new Position(4, 3);
        Carrack carrack = new Carrack(Compass.EAST, pos);

        assertEquals(3, carrack.getPositions().size());

        IPosition p1 = carrack.getPositions().get(0);
        IPosition p2 = carrack.getPositions().get(1);
        IPosition p3 = carrack.getPositions().get(2);

        assertAll(
                () -> assertEquals(4, p1.getRow()),
                () -> assertEquals(3, p1.getColumn()),
                () -> assertEquals(4, p2.getRow()),
                () -> assertEquals(4, p2.getColumn()),
                () -> assertEquals(4, p3.getRow()),
                () -> assertEquals(5, p3.getColumn())
        );
    }

    @Test
    @DisplayName("Carrack orientada a WEST ocupa 3 posições horizontais")
    void testWestPositions() {
        Position pos = new Position(7, 9);
        Carrack carrack = new Carrack(Compass.WEST, pos);

        assertEquals(3, carrack.getPositions().size());

        IPosition p1 = carrack.getPositions().get(0);
        IPosition p2 = carrack.getPositions().get(1);
        IPosition p3 = carrack.getPositions().get(2);

        assertAll(
                () -> assertEquals(7, p1.getRow()),
                () -> assertEquals(9, p1.getColumn()),
                () -> assertEquals(7, p2.getRow()),
                () -> assertEquals(10, p2.getColumn()),
                () -> assertEquals(7, p3.getRow()),
                () -> assertEquals(11, p3.getColumn())
        );
    }

    @Test
    @DisplayName("Caravel deve lançar AssertionError se bearing for null")
    void testNullBearingThrowsException() {
        assertThrows(AssertionError.class,
                () -> new Caravel(null, new Position(1,1)));

    }

}
