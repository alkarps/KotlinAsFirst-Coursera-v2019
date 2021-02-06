@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.ifEmpty { listOf(0.0) }.map { it * it }.reduce(Double::plus))

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = list.ifEmpty { listOf(0.0) }.reduce(Double::plus) / max(1, list.size)

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mean = mean(list)
    for (i in list.indices) {
        list[i] -= mean
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int =
    a.mapIndexed { inx, value -> value * b[inx] }.ifEmpty { listOf(0) }.reduce(Int::plus)

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int =
    p.mapIndexed { inx, value -> value * x.toDouble().pow(inx).toInt() }.ifEmpty { listOf(0) }.reduce(Int::plus)

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    var sum = 0
    for (i in list.indices) {
        sum += list[i]
        if (i != 0) {
            list[i] = sum
        }
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val result = mutableListOf<Int>()
    var remN = n
    var del = 2
    while (remN > 1) {
        if (remN % del > 0) {
            del++
        } else {
            result.add(del)
            remN /= del
        }
    }
    return result
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString("*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    if (n == 0) {
        return listOf(0)
    }
    val result = mutableListOf<Int>()
    var actualN = n
    while (actualN > 0) {
        result.add(0, actualN % base)
        actualN /= base
    }
    return result
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = convert(n, base).joinToString("") { v ->
    when (v) {
        in 0..9 -> v.toString()
        else -> (97 + v - 10).toChar().toString()
    }
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int =
    digits.reversed().reduceIndexed { index, acc, i -> acc + i * (base.toDouble().pow(index).toInt()) }

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = decimal(str.toCharArray().toList().map { c ->
    when (c) {
        in '0'..'9' -> c.toInt() - 48
        else -> c.toInt() - 87
    }
}, base)

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var result = ""
    var actualN = n
    val minusMultipleIfMoreOrEquals = { base: Int, number: String ->
        if (actualN >= base) {
            result += IntRange(1, actualN / base).joinToString("") { number }
            actualN %= base
        }
    }
    val minusIfMoreOrEquals = { base: Int, number: String ->
        if (actualN >= base) {
            result += number
            actualN -= base
        }
    }
    minusMultipleIfMoreOrEquals(1000, "M")
    minusIfMoreOrEquals(900, "CM")
    minusIfMoreOrEquals(500, "D")
    minusIfMoreOrEquals(400, "CD")
    minusMultipleIfMoreOrEquals(100, "C")
    minusIfMoreOrEquals(90, "XC")
    minusIfMoreOrEquals(50, "L")
    minusIfMoreOrEquals(40, "XL")
    minusMultipleIfMoreOrEquals(10, "X")
    minusIfMoreOrEquals(9, "IX")
    minusIfMoreOrEquals(5, "V")
    minusIfMoreOrEquals(4, "IV")
    result += IntRange(1, actualN).joinToString("") { "I" }
    return result
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var result = ""
    if (n >= 1000) {
        val upPart = n / 1000
        result += toRussianString(upPart, true).trim()
    }
    val downPart = toRussianString(n % 1000)
    if (downPart.isNotBlank()) result += " $downPart"
    return result.trim()
}

private fun toRussianString(n: Int, th: Boolean = false): String {
    val result = mutableListOf<String>()
    if (n >= 100) {
        result.add(toNumberThirdPow(n / 100))
    }
    if (n % 100 >= 10) {
        if (n % 100 >= 20) {
            result.add(toNumberSecondPow(n % 100 / 10))
            result.add(toNumberFirstPow(n % 10, th))
            if (th) result.add(th(n % 10))
        } else {
            result.add(toNumberSecondPow(n % 100))
            if (th) result.add("тысяч")
        }
    } else {
        result.add(toNumberFirstPow(n % 10, th))
        if (th) result.add(th(n % 10))
    }
    return result.filter { it.isNotBlank() }.joinToString(" ")
}

private fun th(n: Int): String = when (n) {
    1 -> "тысяча"
    in 2..4 -> "тысячи"
    else -> "тысяч"
}

private fun toNumberFirstPow(n: Int, th: Boolean = false): String = when (n) {
    1 -> if (th) "одна" else "один"
    2 -> if (th) "две" else "два"
    3 -> "три"
    4 -> "четыре"
    5 -> "пять"
    6 -> "шесть"
    7 -> "семь"
    8 -> "восемь"
    9 -> "девять"
    else -> ""
}

private fun toNumberSecondPow(n: Int) = when (n) {
    0 -> ""
    1, 10 -> "десять"
    in 2..3 -> toNumberFirstPow(n) + "дцать"
    4 -> "сорок"
    in 5..8 -> toNumberFirstPow(n) + "десят"
    9 -> "девяносто"
    12 -> "двенадцать"
    14 -> "четырнадцать"
    15 -> "пятнадцать"
    16 -> "шестнадцать"
    17 -> "семнадцать"
    18 -> "восемнадцать"
    19 -> "девятнадцать"
    else -> toNumberFirstPow(n % 10) + "надцать"
}

private fun toNumberThirdPow(n: Int) = when (n) {
    0 -> ""
    1 -> "сто"
    2 -> "двести"
    in 3..4 -> toNumberFirstPow(n) + "ста"
    else -> toNumberFirstPow(n) + "сот"
}