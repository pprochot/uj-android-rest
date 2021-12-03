package uj.android.pprochot.models.dto.product

import kotlinx.serialization.Serializable
import uj.android.pprochot.models.dto.catalog.CategoryResponse

@Serializable
data class ProductResponse(val id: Int, val name: String, val description: String, val category: CategoryResponse)