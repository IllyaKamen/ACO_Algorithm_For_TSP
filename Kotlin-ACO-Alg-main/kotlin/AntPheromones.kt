import javax.swing.text.html.HTML.Tag.I
import kotlin.random.Random

class AntPheromones() {
    // Initialize rows x cols

    val grid = Grid()
    var pheromones = grid.grid


    fun setDefaultPheromones(): Array<DoubleArray> {
        for (i in 0 until grid.rows) {
            for (j in 0 until grid.cols) {
                if (i == j) {
                    pheromones[i][j] = 0.0
                } else {
                    pheromones[i][j] = Random.nextDouble(0.0, 1.0)
                }
            }
        }
        return pheromones
    }

//    fun setNewPheromones(value: Double): Array<DoubleArray> {
//        for (i in 0 until grid.rows) {
//            for (j in 0 until grid.cols) {
//                if(i == j){
//                    pheromones[i][j] = 0.0
//                }
//                else {
//                    pheromones[i][j] = value
//                }
//            }
//        }
//        return pheromones
//    }

    fun getRow(x: Int, pheromones: Array<DoubleArray>): DoubleArray {
        return pheromones[x]
    }

    fun getValue(x: Int, y: Int): Double {
        return pheromones[x][y]
    }
}
