package com.deeromptech.domain.repository

import com.deeromptech.domain.model.CategoriesListModel
import com.deeromptech.domain.network.ResultWrapper

interface CategoryRepository {
    suspend fun getCategories(): ResultWrapper<CategoriesListModel>
}