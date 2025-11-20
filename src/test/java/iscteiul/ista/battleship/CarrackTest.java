package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a classe Carrack (Nau - Tamanho 3)")
class CarrackTest {

    private final Position POS_INICIAL = new Position(4, 4); // row=4, col=4

    @Test
    @DisplayName("1. Verificar o tamanho constante do navio (SIZE=3)")
    void testGetSize() {
        Carrack carrack = new Carrack(Compass.NORTH, POS_INICIAL);
        assertEquals(3, carrack.getSize(), "O tamanho da Carrack deve ser 3");
    }

    @Test
    @DisplayName("2. Testar exceções no construtor")
    void testConstructorExceptions() {
        // Cobre a linha do 'if (bearing == null)'
        assertThrows(AssertionError.class, () -> {
            new Carrack(null, POS_INICIAL);
        }, "Deve lançar AssertionError se bearing for null");
    }

    @Nested
    @DisplayName("3. Testar o preenchimento das 3 posições conforme a direção")
    class CarrackShapeTests {

        @Test
        @DisplayName("3.1. Direção NORTH")
        void testPositionsNorth() {
            Carrack carrack = new Carrack(Compass.NORTH, POS_INICIAL);
            assertAll("Verificar posições NORTH",
                    () -> assertTrue(carrack.getPositions().contains(new Position(4, 4))),
                    () -> assertTrue(carrack.getPositions().contains(new Position(5, 4))),
                    () -> assertTrue(carrack.getPositions().contains(new Position(6, 4))),
                    () -> assertEquals(3, carrack.getPositions().size())
            );
        }

        @Test
        @DisplayName("3.2. Direção SOUTH")
        void testPositionsSouth() {
            Carrack carrack = new Carrack(Compass.SOUTH, POS_INICIAL);
            assertAll("Verificar posições SOUTH",
                    () -> assertTrue(carrack.getPositions().contains(new Position(4, 4))),
                    () -> assertTrue(carrack.getPositions().contains(new Position(5, 4))),
                    () -> assertTrue(carrack.getPositions().contains(new Position(6, 4))),
                    () -> assertEquals(3, carrack.getPositions().size())
            );
        }

        @Test
        @DisplayName("3.3. Direção EAST")
        void testPositionsEast() {
            Carrack carrack = new Carrack(Compass.EAST, POS_INICIAL);
            assertAll("Verificar posições EAST",
                    () -> assertTrue(carrack.getPositions().contains(new Position(4, 4))),
                    () -> assertTrue(carrack.getPositions().contains(new Position(4, 5))),
                    () -> assertTrue(carrack.getPositions().contains(new Position(4, 6))),
                    () -> assertEquals(3, carrack.getPositions().size())
            );
        }

        @Test
        @DisplayName("3.4. Direção WEST")
        void testPositionsWest() {
            Carrack carrack = new Carrack(Compass.WEST, POS_INICIAL);
            assertAll("Verificar posições WEST",
                    () -> assertTrue(carrack.getPositions().contains(new Position(4, 4))),
                    () -> assertTrue(carrack.getPositions().contains(new Position(4, 5))),
                    () -> assertTrue(carrack.getPositions().contains(new Position(4, 6))),
                    () -> assertEquals(3, carrack.getPositions().size())
            );
        }
    }
}
