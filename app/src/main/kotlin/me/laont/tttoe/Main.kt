package me.laont.tttoe

fun main() {
    val field = List(3) { MutableList(3) { ' ' } }
    var current = 'X'
    draw(field)

    do {
        val (x, y) = move(field)
        field[x][y] = current
        draw(field)
        current = if (current == 'X') 'O' else 'X'
    } while (!isXWin(field) && !isOWin(field) && !isDraw(field))

    if (isXWin(field)) {
        println("X wins")
    } else if (isOWin(field)) {
        println("O wins")
    } else if (isDraw(field)) {
        println("Draw")
    } else {
        println("Impossible")
    }
}

fun move(field: List<MutableList<Char>>): List<Int> {
    var x = 0
    var y = 0

    do {
        print("Enter the coordinates: ")
        var ok = false
        val move = readln().split(" ")
        val xs = move[0][0]
        if (!xs.isDigit()) {
            println("You should enter numbers!")
            continue
        }
        val ys = move[1][0]
        if (!ys.isDigit()) {
            println("You should enter numbers!")
            continue
        }
        x = xs.digitToInt() - 1
        y = ys.digitToInt() - 1
        if (x !in 0..2 || y !in 0..2) {
            println("Coordinates should be from 1 to 3!")
            continue
        }
        if (field[x][y] == 'X' || field[x][y] == 'O') {
            println("This cell is occupied! Choose another one!")
            continue
        }

        ok = true
    } while (!ok)

    return listOf(x, y)
}

fun draw(field: List<MutableList<Char>>) {
    println("---------")
    println("| ${field[0][0]} ${field[0][1]} ${field[0][2]} |")
    println("| ${field[1][0]} ${field[1][1]} ${field[1][2]} |")
    println("| ${field[2][0]} ${field[2][1]} ${field[2][2]} |")
    println("---------")
}

fun isDraw(field: List<MutableList<Char>>): Boolean {
    for (row in field) {
        for (cell in row) {
            if (cell == ' ') return false
        }
    }

    return true
}

fun isXWin(field: List<MutableList<Char>>): Boolean {
    return isWin(field, 'X')
}

fun isOWin(field: List<MutableList<Char>>): Boolean {
    return isWin(field, 'O')
}

fun isWin(field: List<MutableList<Char>>, player: Char): Boolean {
    val want = List(3) { player }

    val combs = listOf(
        // in row
        listOf(field[0][0], field[0][1], field[0][2]),
        listOf(field[1][0], field[1][1], field[1][2]),
        listOf(field[2][0], field[2][1], field[2][2]),

        // in col
        listOf(field[0][0], field[1][0], field[2][0]),
        listOf(field[0][1], field[1][1], field[2][1]),
        listOf(field[0][2], field[1][2], field[2][2]),

        // in diagonal
        listOf(field[0][0], field[1][1], field[2][2]),
        listOf(field[0][2], field[1][1], field[2][0]),
    )

    for (comb in combs) if (comb == want) return true

    return false
}
