package com.example.data.di

import com.example.data.repository.WallpapersRepositoryImpl
import com.example.domain.repository.WallpapersRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<WallpapersRepository> {
        WallpapersRepositoryImpl(get())
    }
}
