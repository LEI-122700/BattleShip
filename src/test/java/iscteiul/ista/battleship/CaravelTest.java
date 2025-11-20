package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a classe Caravel (Caravela - Tamanho 2)")
class CaravelTest {

    private final Position POS_INICIAL = new Position(3, 3);

    @Test
    @DisplayName("1. Verificar o tamanho constante do navio (SIZE=2)")
    void testGetSize() {
        Caravel caravel = new Caravel(Compass.NORTH, POS_INICIAL);
        assertEquals(2, caravel.getSize(), "O tamanho da Caravela deve ser 2");
    }

    @Test
    @DisplayName("2. Testar exceções no construtor")
    void testConstructorExceptions() {
        // Cobre a linha do 'if (bearing == null)'
        assertThrows(AssertionError.class, () -> {
            new Caravel(null, POS_INICIAL);
        }, "Deve lançar AssertionError se bearing for null");
    }

    @Nested
    @DisplayName("3. Testar posições da Caravela (2 posições)")
    class CaravelShapeTests {

        @Test
        @DisplayName("3.1. Direção NORTH")
        void testPositionsNorth() {
            Caravel caravel = new Caravel(Compass.NORTH, POS_INICIAL);
            assertAll("Verificar 2 posições NORTH",
                    () -> assertTrue(caravel.getPositions().contains(new Position(3, 3))),
                    () -> assertTrue(caravel.getPositions().contains(new Position(4, 3))),
                    () -> assertEquals(2, caravel.getPositions().size())
            );
        }

        @Test
        @DisplayName("3.2. Direção SOUTH")
        void testPositionsSouth() {
            Caravel caravel = new Caravel(Compass.SOUTH, POS_INICIAL);
            assertAll("Verificar 2 posições SOUTH",
                    () -> assertTrue(caravel.getPositions().contains(new Position(3, 3))),
                    () -> assertTrue(caravel.getPositions().contains(new Position(4, 3))),
                    () -> assertEquals(2, caravel.getPositions().size())
            );
        }

        @Test
        @DisplayName("3.3. Direção EAST")
        void testPositionsEast() {
            Caravel caravel = new Caravel(Compass.EAST, POS_INICIAL);
            assertAll("Verificar 2 posições EAST",
                    () -> assertTrue(caravel.getPositions().contains(new Position(3, 3))),
                    () -> assertTrue(caravel.getPositions().contains(new Position(3, 4))),
                    () -> assertEquals(2, caravel.getPositions().size())
            );
        }

        @Test
        @DisplayName("3.4. Direção WEST")
        void testPositionsWest() {
            Caravel caravel = new Caravel(Compass.WEST, POS_INICIAL);
            assertAll("Verificar 2 posições WEST",
                    () -> assertTrue(caravel.getPositions().contains(new Position(3, 3))),
                    () -> assertTrue(caravel.getPositions().contains(new Position(3, 4))),
                    () -> assertEquals(2, caravel.getPositions().size())
            );
        }
    }
}
