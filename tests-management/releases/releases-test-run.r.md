# releases-test-run

## operations
* [unknown] C1 CheckList
    * relatórios de cobertura do codigo na pasta reports
    * executar testes unitarios no IntelliJ
    * verificar e atualizar a cobertura de codico antes de cada release


## unit-test-cases
* [unknown] C2 BargeTest
    * teste getSize(1)
    * teste singlePositionCreated
    * teste Bearing

* [unknown] C3 CaravelTest
    * teste getSize(2)
    * teste ConstructorExceptions
    * teste coordenadas(N,S,E,W)

* [unknown] C4 CarrackTest
    * teste getSize(3)
    * teste ConstructorExceptions
    * teste coordenadas(N,S,E,W)

* [passed] C5 CompassTest - teste char direções, tostring, válidos e devolve UNKOWN    @LEI-123279

* [unknown] C6 FleetTest - adicionar navios válidos/fora dos limites, detetar colisões/proximidade, capacidade da frota, pesquisa (getShipsLike), shipAt, floatingShips, printStatus e casos nulos
    * 

* [passed] C7 FrigateTest - test tamanho 4 e preenchimento das 4 posições nas direções NORTH, SOUTH, EAST e WEST    @LEI-122700

* [passed] C8 GalleonTest - testa tamanho 5, exceção no construtor com bearing null e forma em T nas direções NORTH, SOUTH, EAST e WEST   @LEI-122700

* [passed] C9 GamesTest - testa o construtor e contadores, validShot/repeatedShot, getRemainingShips, os 5 ramos de fire(pos)  e os métodos print (printValidShots, printFleet)   @LEI-122700

* [passed] C10 PositionTest - testa construtor, equals/hashCode, isAdjacentTo, ocupar/disparar e toString    @LEI-123279

* [unknown] C11 ShipTest - testa a criação de um barco genérico, registo de hit/miss, stillFloating, limites, toString e buildShip
    tags: unit-tests, test-cases


