package uj.android.pprochot.models.tables

import org.jetbrains.exposed.sql.Table

object OrdersProductTable : Table("OrdersProductTable") {
    val orderId = reference("orderId", OrdersTable).primaryKey()
    val productId = reference("productId", ProductsTable).primaryKey()
}
