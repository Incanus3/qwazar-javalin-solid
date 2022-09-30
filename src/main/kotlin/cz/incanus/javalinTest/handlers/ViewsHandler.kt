package cz.incanus.javalinTest.handlers

import cz.incanus.javalinTest.inject
import cz.incanus.javalinTest.models.View
import io.javalin.apibuilder.CrudHandler
import io.javalin.http.Context
import io.javalin.http.HttpCode
import org.koin.core.annotation.Single

@Single
class ViewsRegistry {
    private val views: MutableMap<String, View> = mutableMapOf()

    fun all() = views.values
    fun exists(name: String) = name in views

    operator fun get(name: String) = views[name]
    operator fun set(name: String, view: View) {
        views[name] = view
    }

    fun delete(resourceId: String) {
        views.remove(resourceId)
    }
}

class ViewsHandler : CrudHandler {
    private val views: ViewsRegistry by inject()

    override fun getAll(ctx: Context) {
        ctx.json(views.all())
    }

    override fun getOne(ctx: Context, resourceId: String) {
        if (views.exists(resourceId)) {
            ctx.json(views[resourceId]!!)
        } else {
            ctx.status(HttpCode.NOT_FOUND)
        }
    }

    override fun create(ctx: Context) {
        val view = ctx.bodyAsClass(View::class.java)
        views[view.name] = view
        ctx.status(HttpCode.CREATED)
    }

    override fun update(ctx: Context, resourceId: String) {
        TODO("Not yet implemented")
    }

    override fun delete(ctx: Context, resourceId: String) {
        if (views.exists(resourceId)) {
            views.delete(resourceId)
            ctx.status(HttpCode.NO_CONTENT)
        } else {
            ctx.status(HttpCode.NOT_FOUND)
        }
    }
}
