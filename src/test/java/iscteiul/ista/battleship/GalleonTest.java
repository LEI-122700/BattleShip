package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a classe Galleon (Galeão - Tamanho 5)")
class GalleonTest {

    private final Position POS_INICIAL = new Position(5, 5); // r=5, c=5

    @Test
    @DisplayName("1. Verificar o tamanho constante do navio (SIZE=5)")
    void testGetSize() {
        Galleon galleon = new Galleon(Compass.NORTH, POS_INICIAL);
        assertEquals(5, galleon.getSize(), "O tamanho do Galeão deve ser 5");
    }

    @Test
    @DisplayName("2. Testar exceções no construtor")
    void testConstructorExceptions() {
        // Cobre a linha do 'if (bearing == null)'
        assertThrows(AssertionError.class, () -> {
            new Galleon(null, POS_INICIAL);
        }, "Deve lançar AssertionError se bearing for null");
    }

    @Nested
    @DisplayName("3. Testar o preenchimento das 5 posições (Forma de T)")
    class GalleonShapeTests {

        @Test
        @DisplayName("3.1. Direção NORTE (Forma: T invertido)")
        void testPositionsNorth() {
            Galleon galleon = new Galleon(Compass.NORTH, POS_INICIAL);
            // Esperadas: (5, 5), (5, 6), (5, 7) [Base] e (6, 6), (7, 6) [Haste]
            assertAll("Verificar 5 posições North",
                    () -> assertTrue(galleon.getPositions().contains(new Position(5, 5))),
                    () -> assertTrue(galleon.getPositions().contains(new Position(5, 7))),
                    () -> assertTrue(galleon.getPositions().contains(new Position(6, 6))),
                    () -> assertTrue(galleon.getPositions().contains(new Position(7, 6))),
                    () -> assertEquals(5, galleon.getPositions().size())
            );
        }

        @Test
        @DisplayName("3.2. Direção SUL (Forma: Semelhante ao norte mas invertido verticalmente)")
        void testPositionsSouth() {
            Galleon galleon = new Galleon(Compass.SOUTH, POS_INICIAL);
            // Esperadas (pelo código): (5, 5), (6, 5) [Haste] e (7, 4), (7, 5), (7, 6) [Base]
            assertAll("Verificar 5 posições South",
                    () -> assertTrue(galleon.getPositions().contains(new Position(5, 5))),
                    () -> assertTrue(galleon.getPositions().contains(new Position(6, 5))),
                    () -> assertTrue(galleon.getPositions().contains(new Position(7, 4))),
                    () -> assertTrue(galleon.getPositions().contains(new Position(7, 6))),
                    () -> assertEquals(5, galleon.getPositions().size())
            );
        }

        @Test
        @DisplayName("3.3. Direção ESTE (Forma: T deitado para a direita)")
        void testPositionsEast() {
            Galleon galleon = new Galleon(Compass.EAST, POS_INICIAL);
            // Esperadas (pelo código): (5, 5), (6, 5), (7,5), (6, 6), (6, 7)
            assertAll("Verificar 5 posições East",
                    () -> assertTrue(galleon.getPositions().contains(new Position(5, 5))),
                    () -> assertTrue(galleon.getPositions().contains(new Position(6, 5))),
                    () -> assertTrue(galleon.getPositions().contains(new Position(7, 5))),
                    () -> assertTrue(galleon.getPositions().contains(new Position(6, 6))),
                    () -> assertTrue(galleon.getPositions().contains(new Position(6, 7))),
                    () -> assertEquals(5, galleon.getPositions().size())
            );
        }

        @Test
        @DisplayName("3.4. Direção OESTE (Forma: T deitado para a esquerda)")
        void testPositionsWest() {
            Galleon galleon = new Galleon(Compass.WEST, POS_INICIAL);
            // Esperadas (pelo código): (5, 5), (5, 6), (5, 7), (4, 7), (6,7)
            assertAll("Verificar 5 posições West",
                    () -> assertTrue(galleon.getPositions().contains(new Position(5, 5))),
                    () -> assertTrue(galleon.getPositions().contains(new Position(5, 6))),
                    () -> assertTrue(galleon.getPositions().contains(new Position(5, 7))),
                    () -> assertTrue(galleon.getPositions().contains(new Position(4, 7))),
                    () -> assertTrue(galleon.getPositions().contains(new Position(6, 7))),
                    () -> assertEquals(5, galleon.getPositions().size())
            );
        }
    }
}