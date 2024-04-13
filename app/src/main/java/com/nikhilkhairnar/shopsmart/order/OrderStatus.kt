package com.nikhilkhairnar.shopsmart.order

sealed class OrderStatus(val status: String) {
    object Ordered: OrderStatus("Ordered")
    object Canceled: OrderStatus("Canceled")
    object Confirmed :OrderStatus("Confirmed")
    object Delivered: OrderStatus("Delivered")
    object Returned: OrderStatus("Returned")
    object Shipped: OrderStatus("Shipped")


}

fun getOrderStatus(status: String): OrderStatus{
    return when (status){
        "Ordered" -> {

            OrderStatus.Ordered
        } "Canceled" -> {

            OrderStatus.Canceled
        } "Confirmed" -> {

            OrderStatus.Confirmed
        } "Delivered" -> {

            OrderStatus.Delivered
        }
        else ->  OrderStatus.Returned

    }
}