package io.eflamm.paspla.executor

@FunctionalInterface
interface ActionExecutor<T, U> {

    fun process(input: T): U?
}