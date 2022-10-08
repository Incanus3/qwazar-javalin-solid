package cz.incanus.javalinTest.viewsets

import cz.incanus.javalinTest.widgets.Widget

class ViewContext
abstract class View(val name: String) {
    abstract fun render(context: ViewContext): Widget
}
