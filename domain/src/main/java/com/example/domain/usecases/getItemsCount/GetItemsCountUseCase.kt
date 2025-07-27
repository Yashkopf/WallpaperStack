package com.example.domain.usecases.getItemsCount

import kotlinx.coroutines.flow.Flow

interface GetItemsCountUseCase {
    operator fun invoke(): Flow<Int?>
}