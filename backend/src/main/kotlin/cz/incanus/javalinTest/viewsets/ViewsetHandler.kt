package cz.incanus.javalinTest.viewsets

import cz.incanus.javalinTest.common.Handler
import cz.incanus.javalinTest.inject
import io.javalin.http.Context

class ViewsetHandler(context: Context) : Handler(context) {
    companion object {
        const val viewNameParam = "viewName"
        const val viewsetNameParam = "viewsetName"
    }

    fun viewsets() {
        json(viewsets.all())
    }

    fun viewset() {
        json(viewset)
    }

    fun views() {
        json(viewset.views)
    }

    fun renderView() {
        json(view.render(ViewContext()))
    }

    private val viewsets by inject<ViewsetRegistry>()

    private val viewset by lazy {
        viewsets[viewsetName] ?: notFound("Viewset $viewsetName not found")
    }

    private val view by lazy {
        viewset[viewName] ?: notFound("View $viewName not found")
    }

    private val viewName by lazy { context.pathParam(viewNameParam) }
    private val viewsetName by lazy { context.pathParam(viewsetNameParam) }
}
