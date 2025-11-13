/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface IFleet define o comportamento esperado de uma frota
 * (conjunto de navios) no jogo Battleship.

 * Uma frota contém múltiplos navios (objetos que implementam IShip)
 * e permite gerir, pesquisar e mostrar o seu estado no tabuleiro.
 */
public interface IFleet {
    Integer BOARD_SIZE = 10;
    Integer FLEET_SIZE = 10;

    /**
     * Obtém a lista de todos os navios que pertencem à frota.
     *
     * @return lista com todos os navios (IShip)
     */
    List<IShip> getShips();

    /**
     * Adiciona um novo navio à frota.
     *
     * @param s - navio a adicionar
     * @return true se o navio for adicionado com sucesso,
     *         false se não for possível (ex: frota cheia ou conflito de posições)
     */
    boolean addShip(IShip s);

    /**
     * Retorna todos os navios de um determinado tipo (categoria),
     * como por exemplo "Barge", "Galleon", etc.
     *
     * @param category - nome ou tipo do navio a procurar
     * @return lista de navios desse tipo
     */
    List<IShip> getShipsLike(String category);

    /**
     * Retorna a lista de todos os navios que ainda estão "a flutuar",
     * ou seja, que ainda não foram totalmente destruídos.
     *
     * @return lista de navios ainda não afundados
     */
    List<IShip> getFloatingShips();

    /**
     * Procura se existe um navio em determinada posição do tabuleiro.
     *
     * @param pos - posição a verificar
     * @return o navio que ocupa essa posição,
     *         ou null se não existir nenhum
     */
    IShip shipAt(IPosition pos);

    /**
     * Imprime o estado atual da frota no ecrã.
     * Pode mostrar informações como:
     * - quais navios estão afundados
     * - quais ainda estão a flutuar
     * - posições ou danos sofridos
     */
    void printStatus();
}
