package uj.android.pprochot.models.dto.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductRequest(val id: Int, val name: String, val description: String, val categoryId: Int)
