package parking

fun main() {
    val spots = mutableListOf<Spot>()
    while (true) {
        val input = readLine()!!.split(" ").toMutableList()
        if (input[0] == "create") {
            createSpots(input[1].toInt(), spots)
        } else if (input[0] == "status") {
            status(spots)
        } else if (input[0] == "spot_by_color") {
            println(spotByColor(spots, input[1]))
        } else if (input[0] == "reg_by_color") {
            println(regByColor(spots, input[1]))
        } else if (input[0] == "spot_by_reg") {
            println(spotByRegNum(spots, input[1]))
        } else if (input[0] == "park") {
            if (spots.isEmpty()) {
                println("Sorry, a parking lot has not been created.")
            } else {
                isOccupied(spots, input[1], input[2])
            }
        } else if (input[0] == "leave") {
            if (spots.isEmpty()) {
                println("Sorry, a parking lot has not been created.")
            } else {
                byNum(spots, input[1].toInt())
            }
        } else if (input[0] == "exit") {
            break
        }
    }
}

data class Spot(var occupied: Boolean = false, val number: Int = 0, var color: String = "", var car: String = "")

fun isOccupied(spots: MutableList<Spot>, car: String, color: String) {
    for ((index, value) in spots.withIndex()) {
        if (!value.occupied) {
            value.occupied = true
            value.color = color.lowercase()
            value.car = car
            println("$color car parked in spot ${value.number}.")
            break
        } else if (index == spots.lastIndex) {
            println("Sorry, the parking lot is full.")
        }
    }
}

fun byNum(spots: MutableList<Spot>, num: Int) {
    for ((index, value) in spots.withIndex()) {
        if (value.number == num && value.occupied) {
            println("Spot ${value.number} is free.")
            spots.remove(value)
            break
        } else if (!value.occupied && value.number == num) {
            println("The spot is already free")
            break
        } else if (index == spots.lastIndex) {
            println("There is no spot with this num")
            break
        }
    }
}

fun createSpots(num: Int, spots: MutableList<Spot>): MutableList<Spot> {
    spots.clear()
    var i = 1
    repeat(num) {
        spots.add(Spot(number = i++))
    }
    println("Created a parking lot with $num spots.")
    return spots
}

fun status(spots: MutableList<Spot>) {
    if (spots.isEmpty()) {
        println("Sorry, a parking lot has not been created.")
    } else if (!spots.any { it.occupied }) {
        println("Parking lot is empty.")
    } else {
        for (i in spots) {
            if (i.occupied) {
                println("${i.number} ${i.car} ${i.color}")
            }
        }
    }
}

fun spotByColor(spots: MutableList<Spot>, color: String): String {
    val listOfSpots = mutableListOf<Int>()
    return if (spots.isEmpty()) {
        "Sorry, a parking lot has not been created."
    } else if (!spots.any { it.occupied }) {
        "No cars with color $color were found."
    } else {
        for (i in spots) {
            if (i.color == color.lowercase()) {
                listOfSpots.add(i.number)
            }
        }
        if (listOfSpots.isEmpty()) {
            "No cars with color $color were found."
        } else {
            listOfSpots.joinToString()
        }
    }
}

fun spotByRegNum(spots: MutableList<Spot>, reg: String): String {
    val listOfReg = mutableListOf<Int>()
    return if (spots.isEmpty()) {
        "Sorry, a parking lot has not been created."
    } else if (!spots.any { it.occupied }) {
        "No cars with registration number $reg were found."
    } else {
        for (i in spots) {
            if (i.car == reg) {
                listOfReg.add(i.number)
            }
        }
        if (listOfReg.isEmpty()) {
            "No cars with registration number $reg were found."
        } else {
            listOfReg.joinToString()
        }
    }
}

fun regByColor(spots: MutableList<Spot>, color: String): String {
    val listOfReg = mutableListOf<String>()
    return if (spots.isEmpty()) {
        "Sorry, a parking lot has not been created."
    } else if (!spots.any { it.occupied }) {
        "No cars with color $color were found."
    } else {
        for (i in spots) {
            if (i.color == color.lowercase()) {
                listOfReg.add(i.car)
            }
        }
        if (listOfReg.isEmpty()) {
            "No cars with color $color were found."
        } else {
            listOfReg.joinToString()
        }
    }
}
