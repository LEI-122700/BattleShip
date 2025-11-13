/**
 *
 */
package iscteiul.ista.battleship;

/**
 * @author fba
 * /


/**
 * Interface IPosition representa uma posição (ou célula)
 * no tabuleiro do jogo Battleship.

 * Cada posição tem coordenadas (linha e coluna) e um estado:
 *  - pode estar ocupada por parte de um navio;
 *  - pode ter sido atingida por um disparo;
 *  - pode estar vazia e ainda não atacada.

 * Esta interface define o "contrato" que qualquer classe concreta (ex: Position)
 * deve implementar para representar corretamente uma célula do tabuleiro.
 */
public interface IPosition {

    /**
     * Retorna o número da linha (row) desta posição.
     *
     * @return índice da linha no tabuleiro (geralmente de 0 a 9)
     */
    int getRow();

    /**
     * Retorna o número da coluna (column) desta posição.
     *
     * @return índice da coluna no tabuleiro (geralmente de 0 a 9)
     */
    int getColumn();

    /**
     * Compara esta posição com outro objeto para verificar se representam
     * a mesma célula do tabuleiro (mesma linha e mesma coluna).
     *
     * @param other - outro objeto (geralmente outra IPosition)
     * @return true se forem iguais; false caso contrário.
     */
    boolean equals(Object other);

    /**
     * Verifica se esta posição é **adjacente** a outra.

     * Duas posições são adjacentes se estiverem lado a lado —
     * vertical, horizontal ou diagonalmente.
     *
     * @param other - outra posição a comparar
     * @return true se forem adjacentes; false caso contrário
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Marca esta posição como **ocupada** (por exemplo, quando um navio é colocado sobre ela).

     * Depois de ocupada, `isOccupied()` deve retornar true.
     */
    void occupy();

    /**
     * Marca esta posição como **atingida por um disparo**.

     * Normalmente chamada quando o jogador dispara para esta célula.
     * Se a posição estiver ocupada por um navio, então é um acerto;
     * caso contrário, é um tiro falhado.
     */
    void shoot();

    /**
     * Indica se esta posição está atualmente ocupada por um navio.
     *
     * @return true se houver parte de um navio nesta posição; false se estiver vazia
     */
    boolean isOccupied();

    /**
     * Indica se esta posição já foi atingida por um disparo.
     *
     * @return true se foi atingida; false caso contrário
     */
    boolean isHit();
}
