import kotlin.math.pow
import kotlin.random.Random

class ACOAlgorithm(val startNode: Node,
                   val defaultEdges: Edges,
                   val defaultVision: Array<DoubleArray>,
                   val defaultPheromones: Array<DoubleArray>,
                   val alpha: Int = 2,
                   val beta: Int = 4,
                   val ro: Double = 0.4,
                   val lMin: Double = 5.0,
                   val M: Int = 30) {
    val grid = Grid()
    val ant = Ant(startNode)
    var L = 0.0
    var x = startNode.nodeX
    var newPhers = defaultPheromones

    val visited = mutableListOf<Node>()
    var counter = 0

    fun move(): Node{
        var newNode: Node
        ant.vision = defaultVision
        newPhers = calculateNewPhers(M, defaultPheromones)
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
            calculateNewPhers(M, newPhers)
//            }
            counter++
        }
        visited.add(startNode)
    return newNode

    }

    fun calculateNewPhers(m: Int, pheromones: Array<DoubleArray>): Array<DoubleArray>{
        for ((indexX) in newPhers.withIndex()) {
            for ((indexY) in newPhers.withIndex()) {
                var mCounter = 0
                val value = (1-ro)*(pheromones[indexX][indexY])
                while (mCounter <= m) {
                    value.plus(getLmin(startNode)/L)
                    mCounter++
                }
                newPhers[indexX][indexY] = value

            }
        }
//        println("New Phers")
//        grid.printArray(newPhers)
        return newPhers
    }

    fun getLmin (node: Node): Double{
        var lmin = 0.0
        val res = mutableListOf<Double>()
        for ((index) in node.defaultEdges.edges.withIndex()){
            if (node.getValue(node.x, node.y) != 0.0) {
                res.add(node.getValue(node.x, node.y))
            }
        }
        lmin = res.min()
        return  lmin
    }

    fun goToNextNode(fromNode: Node): Node{
        println("I start from node ${fromNode.nodeX}")
        for ((index) in ant.vision.withIndex()){
            ant.vision[index][fromNode.nodeX] = 0.0
        }
        val nextX = getWayNextX(fromNode)

//        println("nextX value - ${nextX}")
//        println("L value - ${L}")
//        println(getWayNextX(fromNode))
        if (nextX!=-1){
            val nextNode = Node(nextX,  0, defaultEdges)
            nextNode.visited = true
            visited.add(nextNode)
            grid.printArray(ant.vision)
            println("Now im on node num ${nextNode.nodeX}")
            L+=startNode.getValue(x, nextX)
            x = nextX
            return nextNode
        }
        else{
            L+=startNode.getValue(x, startNode.x)
            return visited.first()
        }

    }



    fun getWayNextX(node: Node): Int{
        var nextX = 0
        val random = Random.nextDouble(0.0, 1.0)
        val P = getP(node)
        var res = 0.0
        for ((index) in P.withIndex()){
            if (random > res && random <= res+P[index] && P[index] > 0.0) {
                return index
            }
            else {
                if (index==P.size-1){return -1}
                else{res+=P[index]}
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
            pi = (AntPheromones().getRow(indexP, newPhers)[index].pow(alpha) *
                    ant.getVisionRow(node)[index].pow(beta)) /
                    sum
            p.add(pi)
        }
        return p
    }

    fun calcSumOfAll (node: Node): Double{
        var result = 0.0
        val indexP = node.nodeX
        for ((index) in node.getRow(indexP).withIndex()){
            result += (AntPheromones().getRow(indexP, newPhers)[index].pow(alpha) *
                    ant.getVisionRow(node)[index].pow(beta))
        }

        return result
    }

}