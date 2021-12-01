package com.p2ptestexercise.di

import org.koin.core.module.Module

interface ModuleFactory {
    fun create(): Module
}