package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da enum Compass")
class CompassTest {

    @Test
    @DisplayName("getDirection devolve o caracter correto tendo em conta a direcao")
    void getDirectionReturnsCorrectChar() {
        assertAll(
                () -> assertEquals('n', Compass.NORTH.getDirection()),
                () -> assertEquals('s', Compass.SOUTH.getDirection()),
                () -> assertEquals('e', Compass.EAST.getDirection()),
                () -> assertEquals('o', Compass.WEST.getDirection()),
                () -> assertEquals('u', Compass.UNKNOWN.getDirection())
        );
    }

    @Test
    @DisplayName("toString devolve o mesmo caracter da direcao")
    void toStringReturnsSameAsDirectionChar() {
        assertAll(
                () -> assertEquals("n", Compass.NORTH.toString()),
                () -> assertEquals("s", Compass.SOUTH.toString()),
                () -> assertEquals("e", Compass.EAST.toString()),
                () -> assertEquals("o", Compass.WEST.toString()),
                () -> assertEquals("u", Compass.UNKNOWN.toString())
        );
    }

    @Test
    @DisplayName("charToCompass converte caracteres válidos na direção correta")
    void charToCompassValidChars() {
        assertAll(
                () -> assertEquals(Compass.NORTH, Compass.charToCompass('n')),
                () -> assertEquals(Compass.SOUTH, Compass.charToCompass('s')),
                () -> assertEquals(Compass.EAST, Compass.charToCompass('e')),
                () -> assertEquals(Compass.WEST, Compass.charToCompass('o')),
                () -> assertEquals(Compass.UNKNOWN, Compass.charToCompass('u'))
        );
    }

    @Test
    @DisplayName("charToCompass devolve UNKNOWN para caracter inválido")
    void charToCompassInvalidCharReturnsUnknown() {
        assertEquals(Compass.UNKNOWN, Compass.charToCompass('x'));
    }
}