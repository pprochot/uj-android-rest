package uj.android.pprochot.models.entity

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import uj.android.pprochot.models.tables.OrdersProductTable
import uj.android.pprochot.models.tables.OrdersTable

class Order(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Order>(OrdersTable)

    var products by Product via OrdersProductTable
    var buyer by User referencedOn OrdersTable.buyerId
    var date by OrdersTable.date
}