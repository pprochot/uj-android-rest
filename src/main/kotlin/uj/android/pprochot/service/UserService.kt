package uj.android.pprochot.service

import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt
import uj.android.pprochot.exceptions.ResourceNotFoundException
import uj.android.pprochot.exceptions.UserAlreadyExistsException
import uj.android.pprochot.mappers.UserMapper
import uj.android.pprochot.models.dto.ListResponse
import uj.android.pprochot.models.dto.user.UserRequest
import uj.android.pprochot.models.dto.user.UserResponse
import uj.android.pprochot.models.entity.User
import uj.android.pprochot.models.tables.UsersTable

class UserService(private val userMapper: UserMapper) : CrudService<UserRequest, UserResponse> {

    // Todo UserService needs special logic

    override fun create(request: UserRequest): UserResponse = transaction {
        val isEmpty = User.find { UsersTable.name eq request.name }.empty()
        if (!isEmpty)
            throw UserAlreadyExistsException(request.name)
        val user = User.new {
            name = request.name
            password = BCrypt.hashpw(request.password, BCrypt.gensalt())
        }
        return@transaction userMapper.toResponse(user)
    }

    override fun getById(id: Int) = transaction {
        val category = User.findById(id)
            ?: throw ResourceNotFoundException("User with id $id not found")
        return@transaction userMapper.toResponse(category)
    }

    override fun getAll(): ListResponse<UserResponse> = transaction {
        val users = User.all()
            .map(userMapper::toResponse)
            .toList()
        return@transaction ListResponse(users)
    }

    override fun update(id: Int, request: UserRequest): UserResponse = transaction {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }
}