package com.example.wallpaperstack.domain.usecases.getWallpaperList

import kotlinx.coroutines.flow.Flow

interface GetItemsCountUseCase {
    operator fun invoke(): Flow<Int?>
}