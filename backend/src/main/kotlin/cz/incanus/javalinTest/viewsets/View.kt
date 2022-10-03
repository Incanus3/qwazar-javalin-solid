package cz.incanus.javalinTest.viewsets

class ViewContext
abstract class View(val name: String) {
    abstract fun render(context: ViewContext): Widget
}
