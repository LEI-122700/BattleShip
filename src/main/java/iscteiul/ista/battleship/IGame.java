/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface IGame define o comportamento principal de um jogo de Battleship.

 * Fornece métodos para efetuar disparos, registar resultados
 * e obter estatísticas sobre o progresso da partida.

 * Esta interface representa a lógica geral do jogo:
 *  - gerir tiros efetuados,
 *  - contabilizar acertos e erros,
 *  - e acompanhar o estado dos navios.
 */
public interface IGame {

    /**
     * Realiza um disparo (tiro) sobre uma posição do tabuleiro.
     *
     * @param pos - posição onde o jogador dispara
     * @return o navio atingido (IShip), se o disparo for um acerto,
     *         ou null caso o tiro não atinja nenhum navio.
     */
    IShip fire(IPosition pos);

    /**
     * Retorna a lista de todas as posições onde já foram efetuados disparos.
     *
     * @return lista de posições (IPosition) já tentadas
     */
    List<IPosition> getShots();

    /**
     * Devolve o número total de disparos repetidos,
     * ou seja, tiros efetuados em posições onde o jogador já tinha disparado antes.
     *
     * @return quantidade de disparos repetidos
     */
    int getRepeatedShots();

    /**
     * Devolve o número de disparos inválidos.
     *
     * Um disparo pode ser inválido se, por exemplo:
     *  - estiver fora dos limites do tabuleiro;
     *  - for feito numa posição inválida (nula);
     *  - violar alguma regra do jogo.
     *
     * @return quantidade de disparos inválidos
     */
    int getInvalidShots();

    /**
     * Retorna o número total de tiros que acertaram em algum navio.
     *
     * @return quantidade de acertos (hits)
     */
    int getHits();

    /**
     * Retorna quantos navios foram completamente afundados até ao momento.
     *
     * @return número de navios afundados
     */
    int getSunkShips();

    /**
     * Retorna o número de navios que ainda estão "a flutuar",
     * ou seja, que ainda não foram totalmente destruídos.
     *
     * @return número de navios restantes
     */
    int getRemainingShips();

    /**
     * Imprime as posições dos disparos válidos (acertos e falhas)
     * já efetuados no tabuleiro.
     *
     * Pode ser útil para depuração ou para mostrar o progresso do jogador.
     */
    void printValidShots();

    /**
     * Imprime o estado atual da frota no jogo,
     * mostrando os navios e a sua condição (afundado, atingido, intacto, etc.).
     */
    void printFleet();
}
