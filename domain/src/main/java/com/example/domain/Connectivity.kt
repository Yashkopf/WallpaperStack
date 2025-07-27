package com.example.domain

import kotlinx.coroutines.flow.Flow

interface Connectivity {
    val connectionAsStateFlow: Flow<Boolean>
}