package uj.android.pprochot.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import uj.android.pprochot.models.tables.*

class DatabaseConfig {

    private val hikariConfig = HikariConfig().apply {
        jdbcUrl = "jdbc:postgresql://localhost:8001/shop"
        driverClassName = "org.postgresql.Driver"
        username = "shop_app"
        password = "shopapp123"
    }

    val database = Database.connect(HikariDataSource(hikariConfig))

    fun createTables() {
        transaction {
            SchemaUtils.create(
                UsersTable, CategoriesTable, ProductsTable,
                CartsTable, CartsProductTable,
                OrdersTable, OrdersProductTable
            )
        }
    }
}