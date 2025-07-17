package com.develop.basicandroid.jettip2.util

fun calculateTotalTip(
    billAmount: Double,
    tipPercentage: Int
): Double {
    return if (billAmount > 1 && billAmount.toString().isNotEmpty())
        (billAmount * tipPercentage) / 100 else 0.0

}

fun calculateTotalPerPerson(
    totalBill : Double,
    splitBy : Int,
    tipPercentage: Int
) : Double {
    val bill = calculateTotalTip(billAmount = totalBill, tipPercentage = tipPercentage,) + totalBill
    return (bill / splitBy)

}