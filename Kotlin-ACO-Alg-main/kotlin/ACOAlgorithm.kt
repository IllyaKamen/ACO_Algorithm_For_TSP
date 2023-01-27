import kotlin.math.pow
import kotlin.random.Random
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer.ValueParametersHandler.DEFAULT

class ACOAlgorithm(val startNode: Node, val defaultEdges: Edges, val defaultVision: Array<DoubleArray>) {
    val grid = Grid()

    val ant = Ant(startNode)
    val pheromones = AntPheromones()
    val M = 30

    val visited = mutableListOf<Node>()
    var counter = 0

    fun move(){
        pheromones.setDefaultPheromones()
        var newNode: Node
        newNode = startNode
        visited.add(newNode)
        startNode.visited = true
        goToNextNode(startNode)
        while(counter!=grid.cols){
//            if (counter == 0){
//                newNode = startNode
//                visited.add(newNode)
//                startNode.visited = true
//            }
//            else{
                val fromNode = visited.last()
                newNode = goToNextNode(fromNode)
//            }
            counter++
        }


    }

    fun goToNextNode(fromNode: Node): Node{
        println("I start from node ${fromNode.nodeX}")
        for ((index) in ant.vision.withIndex()){
            ant.vision[index][fromNode.nodeX] = 0.0
        }
        val nextX = getWayNextX(fromNode)
        println(getWayNextX(fromNode))
        if (nextX!=-1){
            val nextNode = Node(nextX,  0, defaultEdges)
            nextNode.visited = true
            visited.add(nextNode)
            grid.printArray(ant.vision)
            println("Now im on node num ${nextNode.nodeX}")
            return nextNode
        }
        else{
            return visited.first()
        }

    }



    fun getWayNextX(node: Node): Int{
        var nextX = 0
        val random = Random.nextDouble(0.0, 1.0)
        val P = getP(node)
        val edges = mutableListOf<Double>()
        for ((index) in  P.withIndex()){
            edges.add(P[index])
        }
        var res = 0.0
        for ((index) in edges.withIndex()){
            if (random > res && random <= res+edges[index] && edges[index] > 0.0) {
                return index
            }
            else {
                if (index==edges.size-1){return -1}
                else{res+=edges[index]}
            }
        }
       return nextX
    }

    fun getP(node: Node): MutableList<Double> {
        val p = mutableListOf<Double>()
        val indexP = node.nodeX
        val sum = calcSumOfAll(node)
        for ((index) in node.getRow(indexP).withIndex()){
            var pi: Double
            pi = (pheromones.getRow(indexP)[index].pow(ant.alpha) *
                    ant.getVisionRow(node)[index].pow(ant.beta)) /
                    sum
            p.add(pi)
        }
        return p
    }

    fun calcSumOfAll (node: Node): Double{
        var result = 0.0
        val indexP = node.nodeX
        for ((index) in node.getRow(indexP).withIndex()){
            result += (pheromones.getRow(indexP)[index].pow(ant.alpha) *
                    ant.getVisionRow(node)[index].pow(ant.beta))
        }

        if (result == 0.0){
            ant.vision = defaultVision
            for ((index) in node.getRow(indexP).withIndex()){
                result += (pheromones.getRow(indexP)[index].pow(ant.alpha) *
                        ant.getVisionRow(node)[index].pow(ant.beta))
            }
        }

        return result
    }

}