package uj.android.pprochot.models.dto

import kotlinx.serialization.Serializable

@Serializable
data class CategoryRequest(val name: String, val description: String) {
}