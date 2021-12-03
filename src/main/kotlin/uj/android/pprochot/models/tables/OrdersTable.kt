package uj.android.pprochot.models.tables

import org.jetbrains.exposed.dao.IntIdTable

object OrdersTable : IntIdTable("orders") {
    val date = date("date")
    val buyerId = reference("buyerId", UsersTable)
    val priceWhenBought = decimal("priceWhenBought", 10, 2) //todo scale
}