package iscteiul.ista.battleship;

// Importações do JUnit 5
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe genérica (abstrata) Ship.
 */
@DisplayName("Testes da classe genérica Ship")
class ShipTest {

    // --- StubShip ---
    /**
     * Classe "Stub" interna (e estática) para testar a classe abstrata Ship.
     * Ela imita uma classe de barco concreta, preenchendo a lista
     * de posições (que é responsabilidade das subclasses).
     */
    static class StubShip extends Ship {

        public StubShip(String category, Compass bearing, IPosition pos) {
            super(category, bearing, pos);
        }

        // A interface IShip exige getSize() como Integer.
        @Override
        public Integer getSize() {
            return this.positions.size(); // O tamanho é o n.º de posições
        }
    }

    // --- Variáveis de Teste ---
    private StubShip ship;
    private IPosition startPos;
    private Compass bearing;

    // --- Configuração (@BeforeEach) ---
    /**
     * Configura um barco "stub" padrão antes de cada teste.
     * O barco terá tamanho 3, na vertical (NORTH),
     * ocupando (2,2), (3,2) e (4,2).
     */
    @BeforeEach
    void setUp() {
        startPos = new Position(2, 2); // Posição inicial (row=2, col=2)
        bearing = Compass.NORTH;
        ship = new StubShip("stub", bearing, startPos);

        // Preenchemos manualmente a lista de posições (BARCO VERTICAL)
        IPosition pos1 = new Position(2, 2); // row 2, col 2
        IPosition pos2 = new Position(3, 2); // row 3, col 2
        IPosition pos3 = new Position(4, 2); // row 4, col 2

        ship.positions.add(pos1);
        ship.positions.add(pos2);
        ship.positions.add(pos3);
    }

    // --- Testes ---

    @Test
    @DisplayName("Deve criar o barco e verificar getters")
    void testCreationAndGetters() {
        assertAll("Verificação de getters e estado inicial",
                () -> assertEquals("stub", ship.getCategory(), "Categoria deve ser 'stub'"),
                () -> assertEquals(startPos, ship.getPosition(), "Posição inicial deve ser (2,2)"),
                () -> assertEquals(bearing, ship.getBearing(), "Direção (bearing) deve ser NORTH"),
                () -> assertEquals(3, ship.getSize(), "Tamanho (size) deve ser 3"),
                () -> assertTrue(ship.stillFloating(), "Barco novo deve estar a flutuar"),
                () -> assertEquals(3, ship.getPositions().size(), "Lista de posições deve ter 3 elementos")
        );
    }

    @Test
    @DisplayName("Deve identificar corretamente as posições que ocupa")
    void testOccupies() {
        // Testes para o barco VERTICAL
        assertTrue(ship.occupies(new Position(2, 2)), "Deve ocupar (2,2)");
        assertTrue(ship.occupies(new Position(3, 2)), "Deve ocupar (3,2)");
        assertTrue(ship.occupies(new Position(4, 2)), "Deve ocupar (4,2)");

        assertFalse(ship.occupies(new Position(1, 1)), "Não deve ocupar (1,1)");
        assertFalse(ship.occupies(new Position(2, 3)), "Não deve ocupar (2,3)");
    }

    @Test
    @DisplayName("Deve registar um 'hit' (acerto)")
    void testShootHit() {
        // Posição para atingir (o meio do barco vertical)
        IPosition positionToHit = new Position(3, 2);

        // Verifica o estado da posição *dentro* da lista do barco
        IPosition internalPos2 = ship.getPositions().get(1); // O (3,2)
        assertFalse(internalPos2.isHit(), "Posição (3,2) não deve estar atingida inicialmente");

        // Dispara
        ship.shoot(positionToHit);

        // Verifica se a posição *interna* foi marcada como 'hit'
        assertTrue(internalPos2.isHit(), "Posição (3,2) devia estar marcada como atingida");
    }

    @Test
    @DisplayName("Não deve registar 'hit' num 'miss' (falhanço)")
    void testShootMiss() {
        IPosition positionToMiss = new Position(9, 9);
        ship.shoot(positionToMiss);

        // Verifica se nenhuma das posições internas foi atingida
        assertFalse(ship.getPositions().get(0).isHit(), "Posição 1 não devia ser atingida");
        assertFalse(ship.getPositions().get(1).isHit(), "Posição 2 não devia ser atingida");
        assertFalse(ship.getPositions().get(2).isHit(), "Posição 3 não devia ser atingida");
    }

    @Test
    @DisplayName("Deve afundar (stillFloating = false) após todos os segmentos serem atingidos")
    void testSinking() {
        // Dispara no barco VERTICAL
        assertTrue(ship.stillFloating(), "Deve flutuar no início");

        ship.shoot(new Position(2, 2)); // Hit 1
        assertTrue(ship.stillFloating(), "Deve flutuar após 1 acerto");

        ship.shoot(new Position(3, 2)); // Hit 2
        assertTrue(ship.stillFloating(), "Deve flutuar após 2 acertos");

        ship.shoot(new Position(4, 2)); // Hit 3 (afundado)
        assertFalse(ship.stillFloating(), "Não deve flutuar (afundado) após 3 acertos");
    }

    @Test
    @DisplayName("Deve calcular as posições de limite (boundary) corretamente")
    void testBoundaries() {
        // Testes para o barco VERTICAL (2,2), (3,2), (4,2)
        assertAll("Verificação dos limites",
                () -> assertEquals(2, ship.getTopMostPos(), "Posição mais a topo (min row) deve ser 2"),
                () -> assertEquals(4, ship.getBottomMostPos(), "Posição mais a fundo (max row) deve ser 4"),
                () -> assertEquals(2, ship.getLeftMostPos(), "Posição mais à esquerda (min col) deve ser 2"),
                () -> assertEquals(2, ship.getRightMostPos(), "Posição mais à direita (max col) deve ser 2")
        );
    }

    @Test
    @DisplayName("Deve detetar proximidade a uma posição (tooCloseTo IPosition)")
    void testTooCloseToPosition() {
        // Barco em (2,2), (3,2), (4,2)

        // Adjacentes diretos
        assertTrue(ship.tooCloseTo(new Position(1, 2)), "Adjacente topo (1,2)");
        assertTrue(ship.tooCloseTo(new Position(5, 2)), "Adjacente fundo (5,2)");
        assertTrue(ship.tooCloseTo(new Position(3, 1)), "Adjacente esquerda (3,1)");
        assertTrue(ship.tooCloseTo(new Position(4, 3)), "Adjacente direita (4,3)");

        // Diagonais
        assertTrue(ship.tooCloseTo(new Position(1, 1)), "Adjacente diagonal (1,1)");
        assertTrue(ship.tooCloseTo(new Position(5, 3)), "Adjacente diagonal (5,3)");

        // Longe
        assertFalse(ship.tooCloseTo(new Position(9, 9)), "Longe (9,9)");
    }

    @Test
    @DisplayName("Deve detetar proximidade a outro barco (tooCloseTo IShip)")
    void testTooCloseToShip() {
        // Barco principal em (2,2), (3,2), (4,2)

        // Outro barco (longe)
        StubShip otherShipFar = new StubShip("outro", Compass.EAST, new Position(7, 7));
        otherShipFar.positions.add(new Position(7, 7));

        assertFalse(ship.tooCloseTo(otherShipFar), "Outro barco está longe");

        // Outro barco (perto)
        StubShip otherShipClose = new StubShip("outro", Compass.EAST, new Position(3, 3));
        otherShipClose.positions.add(new Position(3, 3)); // (3,3) é adjacente a (3,2)

        assertTrue(ship.tooCloseTo(otherShipClose), "Outro barco está perto");
    }

    @Test
    @DisplayName("Deve retornar a string correta (toString)")
    void testToString() {
        // Usa a variável startPos para garantir que o formato é o correto
        String positionString = startPos.toString();
        String expected = "[stub n " + positionString + "]";

        assertEquals(expected, ship.toString());
    }

    @Test
    @DisplayName("Deve construir os tipos de barcos corretos (buildShip)")
    void testBuildShipFactory() {
        Position p = new Position(0, 0);
        Compass b = Compass.NORTH;

        assertAll("Verificação da fábrica estática buildShip",
                () -> assertSame(Barge.class, Ship.buildShip("barca", b, p).getClass()),
                () -> assertSame(Caravel.class, Ship.buildShip("caravela", b, p).getClass()),
                () -> assertSame(Carrack.class, Ship.buildShip("nau", b, p).getClass()),
                () -> assertSame(Frigate.class, Ship.buildShip("fragata", b, p).getClass()),
                () -> assertSame(Galleon.class, Ship.buildShip("galeao", b, p).getClass()),
                () -> assertNull(Ship.buildShip("submarino", b, p), "Tipo desconhecido deve retornar null")
        );
    }

    @Test
    @DisplayName("Deve calcular limites (boundaries) com posições desordenadas")
    void testBoundaries_Unordered() {
        // Criar um novo barco, diferente do setUp
        StubShip unorderedShip = new StubShip("unordered", Compass.NORTH, new Position(3, 5));

        // Adicionar posições de forma desordenada
        unorderedShip.positions.add(new Position(3, 5)); // 1º: Meio
        unorderedShip.positions.add(new Position(1, 2)); // 2º: Topo-Esquerda
        unorderedShip.positions.add(new Position(5, 8)); // 3º: Fundo-Direita

        // Agora, os 'if's dentro dos loops get...MostPos serão ativados
        // Top: começa em 3, mas encontra o 1.
        // Bottom: começa em 3, encontra o 1 (nada), depois encontra o 5.
        // Left: começa em 5, mas encontra o 2.
        // Right: começa em 5, encontra o 2 (nada), depois encontra o 8.

        assertAll("Limites Desordenados",
                () -> assertEquals(1, unorderedShip.getTopMostPos(), "Min Row (Topo) deve ser 1"),
                () -> assertEquals(5, unorderedShip.getBottomMostPos(), "Max Row (Fundo) deve ser 5"),
                () -> assertEquals(2, unorderedShip.getLeftMostPos(), "Min Col (Esquerda) deve ser 2"),
                () -> assertEquals(8, unorderedShip.getRightMostPos(), "Max Col (Direita) deve ser 8")
        );
    }

    @Test
    @DisplayName("Deve lidar com 'tooCloseTo' contra um barco vazio")
    void testTooCloseTo_EmptyShip() {
        // 'ship' é o nosso barco principal do setUp

        // Criar um barco "vazio" (tamanho 0)
        StubShip emptyShip = new StubShip("empty", Compass.NORTH, new Position(9,9));
        // Nota: Não adicionamos posições à lista 'emptyShip.positions'

        // O 'while(otherPos.hasNext())' deve ser falso imediatamente.
        // O método 'tooCloseTo' deve retornar 'false'.
        assertFalse(ship.tooCloseTo(emptyShip), "Não deve ser 'tooCloseTo' a um barco vazio");
    }
}