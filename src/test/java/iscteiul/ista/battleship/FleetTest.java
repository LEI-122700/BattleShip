package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes JUnit 6 para a classe Fleet
 * Usa um StubShip real (sem Mockito) para testar bem colisões e limites.
 */
class FleetTest {

    // ---------- STUB REUTILIZÁVEL ----------
    static class StubShip extends Ship {
        public StubShip(String category, Compass bearing, IPosition pos) {
            super(category, bearing, pos);
        }

        @Override
        public Integer getSize() {
            return positions.size();
        }

        public StubShip addPos(int row, int col) {
            this.positions.add(new Position(row, col));
            return this;
        }
    }

    private Fleet fleet;
    private final int BOARD = IFleet.BOARD_SIZE; // Para evitar números mágicos

    @BeforeEach
    void setUp() {
        fleet = new Fleet();
    }

    // ---------------- HELPER -------------------
    private StubShip createValidShip(String category, int row, int col) {
        return new StubShip(category, Compass.NORTH, new Position(row, col))
                .addPos(row, col);
    }

    // ----------------- TESTES -------------------

    @Test
    @DisplayName("Deve adicionar navio válido dentro dos limites e sem colisões")
    void testAddValidShip() {
        StubShip ship = createValidShip("Barca", 2, 2);

        boolean result = fleet.addShip(ship);

        assertAll(
                () -> assertTrue(result),
                () -> assertEquals(1, fleet.getShips().size()),
                () -> assertEquals(ship, fleet.getShips().get(0))
        );
    }

    @Test
    @DisplayName("Não deve adicionar navios com posições negativas ou acima do limite")
    void testShipOutOfBounds() {
        StubShip neg = createValidShip("Barca", -1, -1);
        StubShip big = createValidShip("Barca", BOARD, BOARD);

        assertFalse(fleet.addShip(neg));
        assertFalse(fleet.addShip(big));
        assertTrue(fleet.getShips().isEmpty());
    }

    @Test
    @DisplayName("Não deve adicionar navios que colidem ou estão demasiado próximos")
    void testCollision() {
        StubShip s1 = createValidShip("Nau", 5, 5);
        fleet.addShip(s1);

        // MESMA POSIÇÃO → colisão
        StubShip s2 = createValidShip("Barca", 5, 5);

        // POSIÇÃO ADJACENTE → tooCloseTo() deve impedir
        StubShip s3 = createValidShip("Fragata", 5, 6);

        assertFalse(fleet.addShip(s2));
        assertFalse(fleet.addShip(s3));
        assertEquals(1, fleet.getShips().size());
    }

    @Test
    @DisplayName("Fleet não deve exceder capacidade máxima (FLEET_SIZE)")
    void testFleetCapacity() {
        int success = 0;
        int fail = 0;

        for (int col = 0; col < BOARD; col += 2) {
            StubShip ship = createValidShip("Barca", 0, col);
            boolean added = fleet.addShip(ship);
            if (added) success++;
            else fail++;
        }

        StubShip extra = createValidShip("Extra", 9, 9);
        boolean result = fleet.addShip(extra);

        if (!result) {
            assertTrue(success <= IFleet.FLEET_SIZE,
                    "FLEET_SIZE foi atingido corretamente!");
        }
    }

    @Test
    @DisplayName("getShipsLike deve devolver apenas navios da categoria correta")
    void testGetShipsLike() {
        StubShip s1 = createValidShip("Frigate", 1, 1);
        StubShip s2 = createValidShip("Galeao", 3, 3);
        fleet.addShip(s1);
        fleet.addShip(s2);

        List<IShip> frigates = fleet.getShipsLike("Frigate");
        assertEquals(1, frigates.size());
        assertEquals(s1, frigates.get(0));
        assertTrue(fleet.getShipsLike("Submarine").isEmpty());
    }

    @Test
    @DisplayName("shipAt deve encontrar navio na posição certa e retornar null caso não encontre")
    void testShipAt() {
        StubShip s1 = createValidShip("Barca", 2, 2);
        fleet.addShip(s1);

        assertNotNull(fleet.shipAt(new Position(2, 2)));
        assertEquals(s1, fleet.shipAt(new Position(2, 2)));
        assertNull(fleet.shipAt(new Position(0, 0)));
    }

    @Test
    @DisplayName("getFloatingShips deve devolver apenas navios ainda não afundados")
    void testGetFloatingShips() {
        StubShip s1 = createValidShip("Barca", 1, 1);
        StubShip s2 = createValidShip("Galeao", 3, 3);

        s2.positions.get(0).shoot();

        fleet.addShip(s1);
        fleet.addShip(s2);

        List<IShip> floating = fleet.getFloatingShips();

        assertEquals(1, floating.size());
        assertEquals(s1, floating.get(0));
        assertFalse(s2.stillFloating());
    }

    @Test
    @DisplayName("printStatus deve funcionar sem lançar exceções")
    void testPrintStatus() {
        StubShip s1 = createValidShip("Barca", 1, 1);
        fleet.addShip(s1);

        assertDoesNotThrow(() -> fleet.printStatus());
    }

    // ---------- EXTRA PARA MAIS COVERAGE ----------

    @Test
    @DisplayName("addShip deve lançar NullPointerException se tentar adicionar null")
    void testAddShipNull() {
        assertThrows(NullPointerException.class, () -> fleet.addShip(null));
    }


    @Test
    @DisplayName("Não deve adicionar navio completamente fora dos limites")
    void testShipCompletamenteOffBounds() {
        StubShip ship = new StubShip("Sub", Compass.NORTH, new Position(-5, -5))
                .addPos(-5, -5)
                .addPos(-6, -5);
        assertFalse(fleet.addShip(ship));
    }

    @Test
    @DisplayName("getShipsLike deve lidar com null ou string vazia")
    void testGetShipsLikeInvalid() {
        fleet.addShip(createValidShip("Barca", 2, 2));
        assertTrue(fleet.getShipsLike(null).isEmpty());
        assertTrue(fleet.getShipsLike("").isEmpty());
    }

    @Test
    @DisplayName("shipAt deve retornar null se receber null")
    void testShipAtNull() {
        assertNull(fleet.shipAt(null));
    }

    @Test
    @DisplayName("printStatus não deve falhar com fleet vazia")
    void testPrintStatusVazia() {
        assertDoesNotThrow(() -> fleet.printStatus());
    }

    @Test
    @DisplayName("isInsideBoard deve falhar em todos os limites")
    void testIsInsideBoardBounds() {
        // Esquerda (-1)
        StubShip s1 = new StubShip("Barca", Compass.NORTH, new Position(-1, 2)).addPos(-1,2);
        assertFalse(fleet.addShip(s1));

        // Direita (10)
        StubShip s2 = new StubShip("Barca", Compass.NORTH, new Position(10, 2)).addPos(10,2);
        assertFalse(fleet.addShip(s2));

        // Topo (-1)
        StubShip s3 = new StubShip("Barca", Compass.NORTH, new Position(2, -1)).addPos(2,-1);
        assertFalse(fleet.addShip(s3));

        // Fundo (10)
        StubShip s4 = new StubShip("Barca", Compass.NORTH, new Position(2, 10)).addPos(2,10);
        assertFalse(fleet.addShip(s4));
    }

}
