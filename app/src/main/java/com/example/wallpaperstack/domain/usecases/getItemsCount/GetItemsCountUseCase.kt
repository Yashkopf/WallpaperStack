package com.example.wallpaperstack.domain.usecases.getItemsCount

import kotlinx.coroutines.flow.Flow

interface GetItemsCountUseCase {
    operator fun invoke(): Flow<Int?>
}