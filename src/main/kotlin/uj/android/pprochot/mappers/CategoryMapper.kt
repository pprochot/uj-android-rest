package uj.android.pprochot.mappers

import uj.android.pprochot.models.dto.CategoryRequest
import uj.android.pprochot.models.dto.CategoryResponse
import uj.android.pprochot.models.entity.Category

class CategoryMapper {

    fun toRequest(category: Category): CategoryRequest {
        return CategoryRequest(category.name, category.description)
    }

    fun toResponse(category: Category): CategoryResponse {
        return CategoryResponse(category.id.value, category.name, category.description)
    }
}