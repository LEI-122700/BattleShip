/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface IShip define o comportamento e as propriedades básicas
 * de um navio no jogo Battleship.

 * Cada navio tem:
 *  - um tipo (categoria),
 *  - uma orientação (bearing),
 *  - uma posição inicial,
 *  - e uma lista de posições (as células do tabuleiro que ocupa).

 * Esta interface garante que todos os tipos de navios
 * (ex: Barge, Galleon, etc.) partilham a mesma estrutura base.
 */
public interface IShip {

    /**
     * Retorna a categoria (ou nome) do navio.

     * Exemplo: "Barge", "Galleon", "Destroyer".
     *
     * @return nome ou tipo do navio
     */
    String getCategory();

    /**
     * Retorna o tamanho do navio, isto é,
     * o número de posições (células) que ocupa no tabuleiro.
     *
     * @return tamanho do navio (número de células)
     */
    Integer getSize();

    /**
     * Retorna a lista de posições (IPosition) que este navio ocupa no tabuleiro.

     * Cada posição representa uma parte do navio.
     *
     * @return lista de posições ocupadas pelo navio
     */
    List<IPosition> getPositions();

    /**
     * Retorna a posição de referência (geralmente a célula superior esquerda)
     * usada para calcular as restantes posições do navio.
     *
     * @return posição inicial do navio
     */
    IPosition getPosition();

    /**
     * Retorna a direção (orientação) do navio no tabuleiro.

     * As direções possíveis são as definidas na enum Compass:
     * NORTH, SOUTH, EAST, WEST.
     *
     * @return orientação do navio
     */
    Compass getBearing();

    /**
     * Verifica se o navio ainda está "a flutuar",
     * ou seja, se ainda existe pelo menos uma parte não atingida.
     *
     * @return true se ainda tiver partes intactas; false se estiver completamente afundado
     */
    boolean stillFloating();

    /**
     * Retorna a coordenada da linha mais alta (menor valor de row)
     * que o navio ocupa.

     * Útil para cálculos de posição ou verificações de sobreposição.
     *
     * @return índice da linha superior
     */
    int getTopMostPos();

    /**
     * Retorna a coordenada da linha mais baixa (maior valor de row)
     * que o navio ocupa.
     *
     * @return índice da linha inferior
     */
    int getBottomMostPos();

    /**
     * Retorna a coordenada da coluna mais à esquerda (menor valor de column)
     * que o navio ocupa.
     *
     * @return índice da coluna esquerda
     */
    int getLeftMostPos();

    /**
     * Retorna a coordenada da coluna mais à direita (maior valor de column)
     * que o navio ocupa.
     *
     * @return índice da coluna direita
     */
    int getRightMostPos();

    /**
     * Verifica se o navio ocupa uma determinada posição do tabuleiro.
     *
     * @param pos - posição a verificar
     * @return true se o navio ocupa essa posição; false caso contrário
     */
    boolean occupies(IPosition pos);

    /**
     * Verifica se este navio está demasiado próximo de outro navio.

     * Normalmente, dois navios não podem estar lado a lado (nem encostados na diagonal),
     * por isso esta função ajuda a validar a colocação no tabuleiro.
     *
     * @param other - outro navio
     * @return true se os navios estiverem demasiado próximos; false se houver distância válida
     */
    boolean tooCloseTo(IShip other);

    /**
     * Verifica se o navio está demasiado próximo de uma posição específica.

     * Usado para garantir que não há navios nem células ocupadas
     * nas posições adjacentes durante a colocação.
     *
     * @param pos - posição a verificar
     * @return true se estiver demasiado perto; false caso contrário
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Regista um disparo sobre uma posição específica do navio.

     * Marca essa posição como atingida (se for uma parte ocupada pelo navio).
     *
     * @param pos - posição onde o tiro foi efetuado
     */
    void shoot(IPosition pos);
}
