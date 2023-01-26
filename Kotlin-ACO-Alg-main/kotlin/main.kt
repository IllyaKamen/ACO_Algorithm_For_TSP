import kotlin.random.Random

fun main() {
    val grid = Grid()
    val defaultEdges = Edges(setDefaultEdges())

    grid.printArray(defaultEdges.edges)

    val node = Node(2,0, defaultEdges)
    val ant = Ant(node)

    grid.printArray(ant.getVisionOfAnt(node))

    for ((index) in ant.getVisionRow(node).withIndex()){
        println(ant.getVisionRow(node)[index])
    }

    val algo = ACOAlgorithm(1, 0,  defaultEdges)

    grid.printArray(algo.pheromones.setDefaultPheromones())

//    println(algo.calcSumOfAll(node))
//    println("Move")
//    algo.move()

    algo.goToNextNode(node)


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