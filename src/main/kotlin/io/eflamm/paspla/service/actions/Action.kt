package io.eflamm.paspla.service.actions

interface Action<T, U> {

    fun process(data: T?): U
}