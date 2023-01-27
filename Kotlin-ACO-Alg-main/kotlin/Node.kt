import kotlin.random.Random

class Node(var x: Int, var y: Int, edges: Edges) {
    val grid = Grid()

    var nodeX = x
    var nodeY = y
    var visited = false

    val defaultEdges = edges

    fun getRow(x: Int): DoubleArray {
        return defaultEdges.edges[x]
    }

    fun getValue(x: Int, y: Int): Double {
//        println("x - ${x} and y - ${y}")
//        grid.printArray(defaultEdges.edges)
//        println("defaultEdges.edges[x][y] ${defaultEdges.edges[x][y]}")
        return defaultEdges.edges[x][y]
    }


    fun checkVisited(node: Node): Boolean{
        if (node.visited){
            return true
        }
        return false
    }
}