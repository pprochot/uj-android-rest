package uj.android.pprochot.models.tables

import org.jetbrains.exposed.dao.IntIdTable

object CartsTable : IntIdTable("carts") {
    val ownerId = reference("ownerId", UsersTable)
    val discountPercentage = integer("discountPercentage").check { it.between(0, 100) }
}