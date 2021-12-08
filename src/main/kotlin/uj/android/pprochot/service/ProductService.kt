package uj.android.pprochot.service

import uj.android.pprochot.exceptions.ResourceNotFoundException
import uj.android.pprochot.mappers.ProductMapper
import uj.android.pprochot.models.dto.ListResponse
import uj.android.pprochot.models.dto.product.ProductRequest
import uj.android.pprochot.models.dto.product.ProductResponse
import uj.android.pprochot.models.entity.Category
import uj.android.pprochot.models.entity.Product

class ProductService(private val productMapper: ProductMapper) :
    CrudService<ProductRequest, ProductResponse> {

    override fun create(request: ProductRequest): ProductResponse {
        val category = Category.findById(request.categoryId)
            ?: throw ResourceNotFoundException("Category with ${request.categoryId} not found!")
        val product = Product.new {
            name = request.name
            description = request.description
            cost = request.cost
            this.category = category
        }
        return productMapper.toResponse(product)
    }

    override fun getAll(): ListResponse<ProductResponse> {
        val list = Product.all()
            .map(productMapper::toResponse)
            .toList()
        return ListResponse(list)
    }

    override fun getById(id: Int): ProductResponse {
        val product = Product.findById(id)
            ?: throw ResourceNotFoundException("Product with id $id not found!")
        return productMapper.toResponse(product)
    }

    override fun update(id: Int, request: ProductRequest): ProductResponse {
        val product = Product.findById(id)
            ?: throw ResourceNotFoundException("Product with id $id not found!")
        val category = Category.findById(request.categoryId)
            ?: throw ResourceNotFoundException("Category with id $id not found!")
        //if (request.categoryId != product.category.id.value) //TODO lazy or not?
        product.apply {
            name = request.name
            description = request.description
            cost = request.cost
            this.category = category
        }
        return productMapper.toResponse(product)
    }

    override fun delete(id: Int) {
        val product = Product.findById(id)
            ?: throw ResourceNotFoundException("Product with id $id not found")
        product.delete()
    }
}