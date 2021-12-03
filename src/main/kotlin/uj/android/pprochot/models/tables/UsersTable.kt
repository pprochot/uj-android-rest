package uj.android.pprochot.models.tables

import org.jetbrains.exposed.dao.IntIdTable

object UsersTable : IntIdTable("users") {
    val name = varchar("name", 255)
    val password = varchar("password", 255)
}