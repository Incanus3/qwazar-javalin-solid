package cz.incanus.javalinTest.common

import io.javalin.http.Context

open class Handler(private val context: Context) {
    fun json(body: Any) = context.json(body)

    fun notFound(message: String = "", cause: Throwable? = null): Nothing = throw NotFound(message, cause)
    fun badRequest(message: String = "", cause: Throwable? = null): Nothing = throw BadRequest(message, cause)
}
