package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Permite usar as anotações @Mock e @InjectMocks
@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para a lógica principal do Jogo (Game)")
class GameTest {

    // Simula a frota. Não usamos a implementação real, apenas o contrato (IFleet).
    @Mock
    private IFleet mockFleet;

    // A classe Game real, com o mockFleet injetado no construtor.
    @InjectMocks
    private Game game;

    // Posições de teste (Assumindo BOARD_SIZE é um valor como 10)
    private final IPosition VALID_POS = new Position(5, 5);
    private final IPosition MISS_POS = new Position(1, 1);
    private final IPosition INVALID_POS = new Position(15, 15);
    // Adicionar posições limite para cobrir validShot
    private final IPosition VALID_POS_BOUNDARY = new Position(Fleet.BOARD_SIZE, Fleet.BOARD_SIZE);
    private final IPosition INVALID_POS_NEGATIVE = new Position(-1, 5);


    // O construtor corrigido garante que countHits e countSinks são 0.
    @BeforeEach
    void setUp() {
        // Limpar a lista de tiros para garantir isolamento em testes que manipulam 'shots'
        game.getShots().clear();
    }

    // --- TESTES DE GETTERS E CONSTRUTOR ---

    @Test
    @DisplayName("1. Verificar inicialização do construtor e lista de tiros vazia")
    void testConstructorAndGetters() {
        assertNotNull(game.getShots(), "A lista de tiros não deve ser null.");
        assertEquals(0, game.getShots().size(), "A lista de tiros deve ser vazia.");
        assertEquals(0, game.getInvalidShots());
        assertEquals(0, game.getRepeatedShots());
        assertEquals(0, game.getHits()); // Agora inicializado
        assertEquals(0, game.getSunkShips()); // Agora inicializado
    }

    // --- COBERTURA DOS AUXILIARES E GETTERS ---

    @Nested
    @DisplayName("2. Cobertura de validShot e repeatedShot")
    class AuxiliaryMethodsTests {

        // Cobre a lógica de limites (0 e BOARD_SIZE)
        @Test
        @DisplayName("2.1. validShot: Posição válida (centro e limites)")
        void testValidShot_True() {
            assertTrue(game.validShot(VALID_POS), "Posição central deve ser válida.");
            assertTrue(game.validShot(VALID_POS_BOUNDARY), "Posição no limite (BOARD_SIZE) deve ser válida.");
        }

        // Cobre as condições 'negativas' e 'fora do tabuleiro'
        @Test
        @DisplayName("2.2. validShot: Posição inválida (negativa e superior)")
        void testValidShot_False() {
            assertFalse(game.validShot(INVALID_POS), "Posição superior deve ser inválida.");
            assertFalse(game.validShot(INVALID_POS_NEGATIVE), "Posição com linha negativa deve ser inválida.");
        }

        // Cobre o loop for e o 'return false' final (não repetido)
        @Test
        @DisplayName("2.3. repeatedShot: Falso (percorre a lista e não encontra)")
        void testRepeatedShot_False() {
            game.getShots().add(VALID_POS);
            game.getShots().add(MISS_POS);
            // Verifica se a nova posição (Boundary) não é repetida
            assertFalse(game.repeatedShot(VALID_POS_BOUNDARY), "Nova posição não deve ser repetida.");
        }

        // Cobre o 'return true' no meio do loop (repetido)
        @Test
        @DisplayName("2.4. repeatedShot: Verdadeiro (encontrado na 2ª iteração)")
        void testRepeatedShot_True() {
            game.getShots().add(VALID_POS);
            game.getShots().add(MISS_POS);
            // Verifica se a posição MISS_POS é repetida
            assertTrue(game.repeatedShot(MISS_POS), "MISS_POS deve ser encontrada como repetida.");
        }

        @Test
        @DisplayName("2.5. getRemainingShips (cobre a chamada a fleet.getFloatingShips)")
        void testGetRemainingShips() {
            // Mock para simular uma frota com 3 navios flutuantes
            List<IShip> floatingShips = List.of(mock(IShip.class), mock(IShip.class), mock(IShip.class));
            when(mockFleet.getFloatingShips()).thenReturn(floatingShips);

            assertEquals(3, game.getRemainingShips());
            verify(mockFleet, times(1)).getFloatingShips();
        }
    }


    // --- TESTES DA LÓGICA DE TIRO (FIRE) ---

    @Nested
    @DisplayName("3. Cobertura dos 5 Caminhos de fire(pos)")
    class FireTests {

        // Cobre o 1º ramo: if (!validShot(pos))
        @Test
        @DisplayName("3.1. Tiro Inválido: Aumenta countInvalidShots")
        void testFire_InvalidShot() {
            int initialInvalidShots = game.getInvalidShots();
            IShip result = game.fire(INVALID_POS);
            assertNull(result);
            assertEquals(initialInvalidShots + 1, game.getInvalidShots());
        }

        // Cobre o 2º ramo: else (valid shot!) -> if (repeatedShot(pos))
        @Test
        @DisplayName("3.2. Tiro Repetido: Aumenta countRepeatedShots")
        void testFire_RepeatedShot() {
            game.getShots().add(VALID_POS); // Setup
            int initialRepeatedShots = game.getRepeatedShots();

            IShip result = game.fire(VALID_POS);

            assertNull(result);
            assertEquals(initialRepeatedShots + 1, game.getRepeatedShots());
        }

        // Cobre o 3º ramo: else (novo tiro válido) -> shipAt(pos) == null
        @Test
        @DisplayName("3.3. Tiro na Água (Miss): Adiciona tiro, mas sem hits")
        void testFire_Miss() {
            when(mockFleet.shipAt(MISS_POS)).thenReturn(null);
            IShip result = game.fire(MISS_POS);
            assertNull(result);
            assertEquals(1, game.getShots().size());
            assertEquals(0, game.getHits());
            verify(mockFleet, times(1)).shipAt(MISS_POS);
        }

        // Cobre o 4º ramo: if (s != null) -> s.shoot(pos) -> !s.stillFloating() (false)
        @Test
        @DisplayName("3.4. Hit (Não Afunda): Aumenta countHits")
        void testFire_Hit() {
            IShip mockShip = mock(IShip.class);
            when(mockFleet.shipAt(VALID_POS)).thenReturn(mockShip);
            when(mockShip.stillFloating()).thenReturn(true); // Navio ainda flutua

            int initialHits = game.getHits();
            IShip result = game.fire(VALID_POS);

            assertNull(result);
            assertEquals(initialHits + 1, game.getHits());
            assertEquals(0, game.getSunkShips());
            verify(mockShip, times(1)).shoot(VALID_POS);
        }

        // Cobre o 5º ramo: if (s != null) -> s.shoot(pos) -> !s.stillFloating() (true)
        @Test
        @DisplayName("3.5. Tiro Final (Sink): Aumenta Hits e Sinks, retorna o navio")
        void testFire_Sink() {
            IShip mockShip = mock(IShip.class);
            when(mockFleet.shipAt(VALID_POS)).thenReturn(mockShip);
            when(mockShip.stillFloating()).thenReturn(false); // Navio afunda!

            int initialHits = game.getHits();
            IShip result = game.fire(VALID_POS);

            assertEquals(mockShip, result);
            assertEquals(initialHits + 1, game.getHits());
            assertEquals(1, game.getSunkShips());
            verify(mockShip, times(1)).shoot(VALID_POS);
        }
    }

    // --- COBERTURA DOS MÉTODOS DE IMPRESSÃO ---

    @Nested
    @DisplayName("4. Cobertura dos Métodos de Impressão (printBoard)")
    class PrintMethodsCoverage {

        // Testar a execução de printValidShots para cobrir as linhas de printBoard
        @Test
        @DisplayName("4.1. Testar printValidShots (execução do loop e print)")
        void testPrintValidShots() {
            game.getShots().add(VALID_POS);
            // Asserts that the method runs without throwing an exception
            assertDoesNotThrow(() -> game.printValidShots());
        }

        // Testar a execução de printFleet para cobrir o loop for no printBoard
        @Test
        @DisplayName("4.2. Testar printFleet (execução de loops e chamada a getShips)")
        void testPrintFleet() {
            // Setup para mockar navios e posições para que o loop corra no printFleet
            IShip mockShip = mock(IShip.class);
            when(mockShip.getPositions()).thenReturn(List.of(VALID_POS));
            when(mockFleet.getShips()).thenReturn(List.of(mockShip));

            // Asserts that the method runs without throwing an exception
            assertDoesNotThrow(() -> game.printFleet());
            verify(mockFleet, times(1)).getShips();
        }
    }
}