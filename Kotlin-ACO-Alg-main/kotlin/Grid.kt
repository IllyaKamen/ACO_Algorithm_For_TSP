class Grid(r: Int = 150, c: Int = 150) {
    // Initialize rows x cols
    val rows = r
    val cols = c
    // Initialize walls to print the entire maze
    val grid = Array(rows) { DoubleArray(cols) }

    // Prints grid
    fun printArray(array: Array<DoubleArray>) {
        for (i in 0 until array.size) {
            for (j in 0 until array.size) {
                if(i == j){
                    print("${array[i][j]} \t")
                }
                else {
                    print("${array[i][j]} \t")
                }
            }
            println()
        }
        println()
    }
}