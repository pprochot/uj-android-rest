package uj.android.pprochot.mappers

import uj.android.pprochot.models.dto.order.OrderResponse
import uj.android.pprochot.models.entity.Order

class OrderMapper(private val productMapper: ProductMapper, private val userMapper: UserMapper) {

    fun toResponse(order: Order): OrderResponse {
        val productResponses = order.products.map(productMapper::toResponse).toList()
        return OrderResponse(
            order.id.value,
            userMapper.toResponse(order.customer),
            productResponses,
            order.cost,
            order.date
        )
    }
}