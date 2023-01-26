class Node(var x: Int, var y: Int, edges: Edges) {
    var nodeX = x
    var nodeY = y
    var visited = false

    val defaultEdges = edges

    fun getRow(x: Int): DoubleArray {
        return defaultEdges.edges[x]
    }

    fun checkVisited(node: Node): Boolean{
        if (node.visited){
            return true
        }
        return false
    }
}