package com.natiqhaciyef.fooddeliveryapp_abbgraduation.util

import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.CartOrderModel

fun String.filterInt(): Int{
    val numberList = arrayListOf<Int>()
    var result = 0

    for (i in this){
        try {
            if (i.toString().toInt() in 0..9){
                numberList.add(i.toString().toInt())
                println(i.toString().toInt())
            }
        }catch (e:Exception){
            break
        }
    }
    var counter = 1
    for (number in numberList.reversed()){
        result += number*counter
        counter*=10
    }

    return result
}

fun List<CartOrderModel>.filterNameText(): String {
    var name = ""
    if (this.size > 2)
        name += "${this[0].name}, ${this[1].name}"
    else if (this.isNotEmpty()){
        name += this[0].name
    }
    return name
}


