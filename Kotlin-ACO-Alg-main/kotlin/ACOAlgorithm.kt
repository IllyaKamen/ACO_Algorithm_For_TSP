import kotlin.math.pow
import kotlin.random.Random

class ACOAlgorithm(private val startX: Int, private val startY: Int = 0, val defaultEdges: Edges) {
    val grid = Grid()

    val startNode = Node(startX, startY, defaultEdges)
    val ant = Ant(startNode)
    val pheromones = AntPheromones()
    val M = 30

    val visited = mutableListOf<Node>()
    var counter = 0

    fun move(){
        pheromones.setDefaultPheromones()
        var newNode: Node
        while(counter!=9){
            if (counter == 0){
                newNode = startNode
                visited.add(newNode)
                startNode.visited = true
            }

            else{
                val fromNode = visited.last()
                newNode = goToNextNode(fromNode)
//                println(newNode.nodeX)
            }
//            println(getP(newNode).forEach { i -> getP(newNode)[i.toInt()] })
            counter++
        }


    }

    fun goToNextNode(fromNode: Node): Node{
        val nextX = getWayNextX(fromNode)
        val nextNode: Node
        grid.printArray(ant.vision)
        if (nextX!=-1){
            nextNode = Node(nextX, startY, defaultEdges)
            nextNode.visited = true
            visited.add(nextNode)
            for ((index) in ant.getVisionRow(nextNode).withIndex()){
                ant.vision[index][fromNode.nodeX] = 0.0
            }
            grid.printArray(ant.vision)
            return nextNode
        }
        return startNode
    }



    fun getWayNextX(node: Node): Int{
        var nextX = 0
        val random = Random.nextDouble(0.0, 1.0)
        val P = getP(node)
        println(random)
        val edges = mutableListOf<Double>()
        for ((index) in  P.withIndex()){
            edges.add(P[index])
            println(P[index])
        }
        var res = 0.0
        for ((index) in edges.withIndex()){
            if (random > res && random <= res+edges[index] && edges[index] > 0.0) {
                nextX = index
            }
            else {
                if (index==edges.size-1){return  -1}
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
        println(indexP)
        for ((index) in node.getRow(indexP).withIndex()){
            result += (pheromones.getRow(indexP)[index].pow(ant.alpha) *
                    ant.getVisionRow(node)[index].pow(ant.beta))
        }
        return result
    }

}