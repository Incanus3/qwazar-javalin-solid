package cz.incanus.javalinTest

import com.fasterxml.jackson.core.JacksonException
import cz.incanus.javalinTest.handlers.ViewsHandler
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.crud
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.http.HttpCode

fun main() {
    Javalin
        .create { config ->
            config.showJavalinBanner = false
            config.defaultContentType = "application/json"
            config.enableDevLogging()
        }
        .routes {
            get("/") { ctx ->
                ctx.json(
                    mapOf(
                        "routes" to mapOf(
                            "/views" to listOf("GET", "POST"),
                            "/views/{viewId}" to listOf("GET", "PATCH", "DELETE"),
                        ),
                    ),
                )
            }
            crud("views/{viewId}", ViewsHandler())
        }
        .exception(JacksonException::class.java) { error, ctx ->
            ctx.status(HttpCode.BAD_REQUEST).json(mapOf("error" to error.message))
        }
        .start(7070)
}
