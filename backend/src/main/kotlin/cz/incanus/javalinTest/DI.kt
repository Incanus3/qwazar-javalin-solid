package cz.incanus.javalinTest

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.koinApplication
import org.koin.ksp.generated.module

@Module
@ComponentScan("cz.incanus.javalinTest")
class DI

val di = koinApplication {
    modules(DI().module)
}.koin

inline fun <reified T : Any> get() = di.get<T>()
inline fun <reified T : Any> inject() = di.inject<T>()
