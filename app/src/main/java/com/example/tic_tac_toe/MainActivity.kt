package tictactoe

import java.lang.Exception
import java.lang.IndexOutOfBoundsException
import java.lang.NumberFormatException
import kotlin.system.exitProcess

var list = mutableListOf(mutableListOf<Char>())

var countSpaces = 9
var winnerX = 0
var winnerO = 0
var previous = 'O'

fun main() {
    val listNew = MutableList(3) {
        MutableList(3) {'_'}
    }
    list = listNew

    println("---------")
    for (i in list) {
        print("| ")
        print(i.joinToString(" "))
        println(" |")
    }
    println("---------")
    coordinates()
}

fun coordinates() {
    print("Enter the coordinates: ")
    try {
        val (row, line) = readLine()!!.split(" ")
        if (list[row.toInt() - 1][line.toInt() - 1] == '_') {
            when (previous) {
                'O' -> { list[row.toInt() - 1][line.toInt() - 1] = 'X'
                    previous = 'X'
                    countSpaces--
                }
                'X' -> {
                    list[row.toInt() - 1][line.toInt() - 1] = 'O'
                    previous = 'O'
                    countSpaces--
                }
            }
            println("---------")
            for (i in list) {
                print("| ")
                print(i.joinToString(" "))
                println(" |")
            }
            println("---------")
        } else {
            println("This cell is occupied! Choose another one!")
            coordinates()
        }
    } catch (e: NumberFormatException) {
        println("You should enter numbers!")
        coordinates()
    } catch (e: IndexOutOfBoundsException) {
        println("Coordinates should be from 1 to 3!")
        coordinates()
    }
    printResult()
}

fun printResult() {
    if (list[0][0] == 'X' && list[0][1] == 'X' && list[0][2] == 'X' ||
        list[1][0] == 'X' && list[1][1] == 'X' && list[1][2] == 'X' ||
        list[2][0] == 'X' && list[2][1] == 'X' && list[2][2] == 'X' ||
        list[0][0] == 'X' && list[1][1] == 'X' && list[2][2] == 'X' ||
        list[0][2] == 'X' && list[1][1] == 'X' && list[2][0] == 'X' ||
        list[0][0] == 'X' && list[1][0] == 'X' && list[2][0] == 'X' ||
        list[0][1] == 'X' && list[1][1] == 'X' && list[2][1] == 'X' ||
        list[0][2] == 'X' && list[1][2] == 'X' && list[2][2] == 'X'
    ) {
        winnerX = 1
    }
    if (list[0][0] == 'O' && list[0][1] == 'O' && list[0][2] == 'O' ||
        list[1][0] == 'O' && list[1][1] == 'O' && list[1][2] == 'O' ||
        list[2][0] == 'O' && list[2][1] == 'O' && list[2][2] == 'O' ||
        list[0][0] == 'O' && list[1][1] == 'O' && list[2][2] == 'O' ||
        list[0][2] == 'O' && list[1][1] == 'O' && list[2][0] == 'O' ||
        list[0][0] == 'O' && list[1][0] == 'O' && list[2][0] == 'O' ||
        list[0][1] == 'O' && list[1][1] == 'O' && list[2][1] == 'O' ||
        list[0][2] == 'O' && list[1][2] == 'O' && list[2][2] == 'O'
    ) {
        winnerO = 1
    }

    if (winnerO == 0 && winnerX == 1) {
        println("X wins")
        exitProcess(0)
    } else if (winnerO == 1 && winnerX == 0) {
        println("O wins")
        exitProcess(0)
    } else if (winnerO == 0 && winnerX == 0 && countSpaces == 0) {
        println("Draw")
        exitProcess(0)
    } else {
        coordinates()
    }
}
