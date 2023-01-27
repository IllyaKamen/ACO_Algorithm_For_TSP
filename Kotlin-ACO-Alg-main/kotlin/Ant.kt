import kotlin.random.Random

class Ant (val node: Node) {
    val alpha = 2
    val beta = 4
    val ro = 0.4
    val lMin = 5

    var vision = Array(node.defaultEdges.edges.size) { DoubleArray(node.defaultEdges.edges.size) }


    fun getVisionOfAnt (onNode: Node): Array<DoubleArray> {
        for (i in 0 until node.defaultEdges.edges.size) {
            for (j in 0 until node.defaultEdges.edges.size) {
                if(i == j){
                    vision[i][j] = 0.0
                }
                else {
                    vision[i][j] = 1 / onNode.defaultEdges.edges[i][j]
                }
            }
        }
        return vision
    }

    fun getVisionRow(node: Node): DoubleArray {

        return vision[node.nodeX]
    }
}