package io.eflamm.paspla.executor

interface ActionExecutor<T, U> {

    fun process(input: T): U
}