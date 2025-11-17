package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da classe Position")
class PositionTest {

    private Position center;
    private Position sameAsCenter;
    private Position neighbourOrthogonal;
    private Position neighbourDiagonal;
    private Position far;

    @BeforeEach
    void setUp() {
        center = new Position(5, 5);
        sameAsCenter = new Position(5, 5);
        neighbourOrthogonal = new Position(5, 6);
        neighbourDiagonal = new Position(4, 4);
        far = new Position(7, 7);
    }

    @Test
    @DisplayName("Construtor inicializa row, column, isHit e isOccupied a false")
    void constructorInitializesFields() {
        Position p = new Position(2, 3);

        assertAll(
                () -> assertEquals(2, p.getRow()),
                () -> assertEquals(3, p.getColumn()),
                () -> assertFalse(p.isOccupied()),
                () -> assertFalse(p.isHit())
        );
    }

    @Test
    @DisplayName("equals é verdadeiro para posicoes com mesma linha e coluna")
    void equalsSameRowAndColumn() {
        assertAll(
                () -> assertEquals(center, sameAsCenter),
                () -> assertEquals(center, (IPosition) sameAsCenter)
        );
    }

    @Test
    @DisplayName("equals é falso para posicoes com linha e coluna diferente")
    void equalsDifferentRowOrColumn() {
        Position differentRow = new Position(4, 5);
        Position differentColumn = new Position(5, 6);

        assertAll(
                () -> assertNotEquals(center, differentRow),
                () -> assertNotEquals(center, differentColumn)
        );
    }

    @Test
    @DisplayName("equals é falso para objetos de outro tipo")
    void equalsWithDifferentType() {
        assertFalse(center.equals("não sou uma posição"));
    }

    @Test
    @DisplayName("equals para a mesma variavel")
    void equalsIsReflexive() {
        assertEquals(center, center);
    }

    @Test
    @DisplayName("hashCode é igual para posicoess iguais (mesmos campos)")
    void hashCodeSameForEqualPositions() {
        Position p1 = new Position(3, 4);
        Position p2 = new Position(3, 4);

        assertAll(
                () -> assertEquals(p1, p2),
                () -> assertEquals(p1.hashCode(), p2.hashCode())
        );
    }

    @Test
    @DisplayName("isAdjacentTo é verdadeiro para a mesma posicao")
    void isAdjacentToSamePosition() {
        assertTrue(center.isAdjacentTo(sameAsCenter));
    }

    @Test
    @DisplayName("isAdjacentTo é verdadeiro para vizinho ortogonal")
    void isAdjacentToOrthogonalNeighbour() {
        assertTrue(center.isAdjacentTo(neighbourOrthogonal));
    }

    @Test
    @DisplayName("isAdjacentTo é verdadeiro para vizinho diagonal")
    void isAdjacentToDiagonalNeighbour() {
        assertTrue(center.isAdjacentTo(neighbourDiagonal));
    }

    @Test
    @DisplayName("isAdjacentTo é falso para posição distante")
    void isAdjacentToFarPosition() {
        assertFalse(center.isAdjacentTo(far));
    }

    @Test
    @DisplayName("occupy marca a posição como ocupada")
    void occupyMarksPositionAsOccupied() {
        Position p = new Position(1, 1);
        assertFalse(p.isOccupied());

        p.occupy();

        assertTrue(p.isOccupied());
    }

    @Test
    @DisplayName("shoot marca a posição como atingida")
    void shootMarksPositionAsHit() {
        Position p = new Position(1, 1);
        assertFalse(p.isHit());

        p.shoot();

        assertTrue(p.isHit());
    }

    @Test
    @DisplayName("toString devolve o formato esperado")
    void toStringReturnsExpectedFormat() {
        Position p = new Position(2, 3);
        assertEquals("Linha = 2 Coluna = 3", p.toString());
    }
}