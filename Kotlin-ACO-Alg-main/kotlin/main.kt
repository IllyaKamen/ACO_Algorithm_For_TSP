import kotlin.random.Random

fun main() {
    val grid = Grid()
    val defaultEdges = Edges(setDefaultEdges())


    grid.printArray(defaultEdges.edges)

    val node = Node(2,0, defaultEdges)
    val ant = Ant(node)
    val defaultVision = ant.getVisionOfAnt(node)
    grid.printArray(ant.getVisionOfAnt(node))

    val algo = ACOAlgorithm(node,  defaultEdges, defaultVision)

    grid.printArray(algo.pheromones.setDefaultPheromones())

    algo.move()

//    algo.goToNextNode(node)

}

fun setDefaultEdges(): Array<DoubleArray> {
    val grid = Grid()
    for (i in 0 until grid.rows) {
        for (j in 0 until grid.cols) {
            if (i == j) {
                grid.grid[i][j] = Double.POSITIVE_INFINITY
            } else {
                grid.grid[i][j] = Random.nextInt(5, 50).toDouble()
            }
        }
    }
    return grid.grid
}