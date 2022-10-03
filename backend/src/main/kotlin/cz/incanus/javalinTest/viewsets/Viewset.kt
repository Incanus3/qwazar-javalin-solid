package cz.incanus.javalinTest.viewsets

class ViewsetBuilder(private val name: String) {
    private val views = mutableMapOf<String, View>()

    fun view(name: String, render: (ViewContext) -> Widget) {
        views[name] = object : View(name) {
            override fun render(context: ViewContext) = render(context)
        }
    }

    fun build() = Viewset(name, views)
}

class Viewset(val name: String, val views: Map<String, View>) {
    operator fun get(name: String) = views[name]
}
