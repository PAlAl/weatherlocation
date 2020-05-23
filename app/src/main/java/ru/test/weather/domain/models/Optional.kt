package ru.test.weather.domain.models

sealed class Optional<out T> {
    class Some<out T>(val data: T) : Optional<T>()
    object None : Optional<Nothing>()
}