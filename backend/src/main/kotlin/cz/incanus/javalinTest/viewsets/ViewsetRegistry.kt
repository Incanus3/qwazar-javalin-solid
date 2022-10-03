package cz.incanus.javalinTest.viewsets

import cz.incanus.javalinTest.get
import org.koin.core.annotation.Single

@Single
class ViewsetRegistry {
    private val viewsets = mutableMapOf<String, Viewset>()

    fun viewSet(name: String, init: ViewsetBuilder.() -> Unit) {
        viewsets[name] = ViewsetBuilder(name).apply(init).build()
    }

    fun all() = viewsets.values.toList()

    fun exists(name: String) = name in viewsets

    operator fun get(name: String) = viewsets[name]

    // FIXME: this is overingeneered - we could use the class itself as registry,
    // but I wanted to try the DI
    companion object {
        fun viewSet(name: String, init: ViewsetBuilder.() -> Unit) {
            get<ViewsetRegistry>().viewSet(name, init)
        }
    }
}
