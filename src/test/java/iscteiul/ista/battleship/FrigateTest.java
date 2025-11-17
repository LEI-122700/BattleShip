package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a classe Frigate (Fragata - Tamanho 4)")
class FrigateTest {

    private final Position POS_INICIAL = new Position(5, 5);

    @Test
    @DisplayName("1. Verificar o tamanho constante do navio (SIZE=4)")
    void testGetSize() {
        Frigate frigate = new Frigate(Compass.NORTH, POS_INICIAL);
        assertEquals(4, frigate.getSize(), "O tamanho da Fragata deve ser 4");
    }

    @Nested
    @DisplayName("2. Testar o preenchimento das 4 posições nas 4 direções")
    class PositionTests {

        @Test
        @DisplayName("2.1. Direção NORTE (Linhas consecutivas, Coluna 5)")
        void testPositionsNorth() {
            Frigate frigate = new Frigate(Compass.NORTH, POS_INICIAL);
            // Posições esperadas: (5,5), (6,5), (7,5), (8,5)
            assertTrue(frigate.getPositions().contains(new Position(5, 5)));
            assertTrue(frigate.getPositions().contains(new Position(6, 5)));
            assertTrue(frigate.getPositions().contains(new Position(7, 5)));
            assertTrue(frigate.getPositions().contains(new Position(8, 5)));
            assertEquals(4, frigate.getPositions().size());
        }

        @Test
        @DisplayName("2.2. Direção SUL (Linhas consecutivas, Coluna 5)")
        void testPositionsSouth() {
            Frigate frigate = new Frigate(Compass.SOUTH, POS_INICIAL);
            // Posições esperadas: (5,5), (6,5), (7,5), (8,5)
            assertTrue(frigate.getPositions().contains(new Position(5, 5)));
            assertTrue(frigate.getPositions().contains(new Position(6, 5)));
            assertTrue(frigate.getPositions().contains(new Position(7, 5)));
            assertTrue(frigate.getPositions().contains(new Position(8, 5)));
            assertEquals(4, frigate.getPositions().size());
        }

        @Test
        @DisplayName("2.3. Direção ESTE (Colunas consecutivas, Linha 5)")
        void testPositionsEast() {
            Frigate frigate = new Frigate(Compass.EAST, POS_INICIAL);
            // Posições esperadas: (5,5), (5,6), (5,7), (5,8)
            assertTrue(frigate.getPositions().contains(new Position(5, 5)));
            assertTrue(frigate.getPositions().contains(new Position(5, 6)));
            assertTrue(frigate.getPositions().contains(new Position(5, 7)));
            assertTrue(frigate.getPositions().contains(new Position(5, 8)));
            assertEquals(4, frigate.getPositions().size());
        }

        @Test
        @DisplayName("2.4. Direção OESTE (Colunas consecutivas, Linha 5)")
        void testPositionsWest() {
            Frigate frigate = new Frigate(Compass.WEST, POS_INICIAL);
            // Posições esperadas: (5,5), (5,6), (5,7), (5,8)
            assertTrue(frigate.getPositions().contains(new Position(5, 5)));
            assertTrue(frigate.getPositions().contains(new Position(5, 6)));
            assertTrue(frigate.getPositions().contains(new Position(5, 7)));
            assertTrue(frigate.getPositions().contains(new Position(5, 8)));
            assertEquals(4, frigate.getPositions().size());
        }
    }
}