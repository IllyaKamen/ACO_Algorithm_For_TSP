import kotlin.random.Random

fun main() {
    val grid = Grid()
    val defaultEdges = Edges(setDefaultEdges())

    val antPheromones = AntPheromones()

    antPheromones.setDefaultPheromones()
//    grid.printArray(antPheromones.pheromones)
//    for ((index) in antPheromones.pheromones.withIndex()){
//        for ((indexs) in antPheromones.pheromones.withIndex()) {
//            println(antPheromones.pheromones[index][indexs])
//        }
//    }

    grid.printArray(defaultEdges.edges)

    val node = Node(Random.nextInt(1, 150), Random.nextInt(1, 150), defaultEdges)
    val ant = Ant(node)
    val defaultVision = ant.getVisionOfAnt(node)
    grid.printArray(ant.getVisionOfAnt(node))

    antPheromones.setDefaultPheromones()

    val algo = ACOAlgorithm(node,  defaultEdges, defaultVision, antPheromones.pheromones)

    grid.printArray(antPheromones.pheromones)

    algo.move()
    for ((index) in algo.visited.withIndex()){
        println(algo.visited[index].x)
    }

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