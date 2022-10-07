package cz.incanus.javalinTest

import com.fasterxml.jackson.core.JacksonException
import cz.incanus.javalinTest.common.BadRequest
import cz.incanus.javalinTest.common.NotFound
import cz.incanus.javalinTest.viewsets.Button
import cz.incanus.javalinTest.viewsets.ViewsetRegistry
import cz.incanus.javalinTest.viewsets.ViewsetRouter
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.http.HttpCode

val HomeViewset = ViewsetRegistry.viewSet("home") {
    view("main") {
        Button("Press me")
    }
}

// NOTE: it's a pain that we have to do it like this, but I don't see any other way, because
// - we can't just call the ViewsetRegistry.viewset() function in top level, except for assignment RHS
// - the RHS isn't evaluated until the first variable access, so the viewset won't be registered, until we call this
// - maybe we could create some @Viewset meta-annotation that would register it in the DI?
//   - the DI would basically become the registry, but how would we get all registered classes then?
fun registerViewsets() {
    HomeViewset
}

fun main() {
    registerViewsets()

    Javalin
        .create { config ->
            config.showJavalinBanner = false
            config.defaultContentType = "application/json"

            config.enableDevLogging()

            config.enableCorsForAllOrigins()
        }
        .routes {
            get("/") { ctx -> ctx.json(listOf("viewsets")) }

            ViewsetRouter().routes()
        }
        .exception(NotFound::class.java) { error, ctx ->
            ctx.status(HttpCode.NOT_FOUND).json(mapOf("error" to error.message))
        }
        .exception(BadRequest::class.java) { error, ctx ->
            ctx.status(HttpCode.BAD_REQUEST).json(mapOf("error" to error.message))
        }
        .exception(JacksonException::class.java) { error, ctx ->
            ctx.status(HttpCode.BAD_REQUEST).json(mapOf("error" to error.message))
        }
        .start(7070)
}
