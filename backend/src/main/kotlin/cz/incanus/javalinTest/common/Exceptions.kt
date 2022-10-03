package cz.incanus.javalinTest.common

class NotFound(message: String = "", cause: Throwable? = null) : RuntimeException(message, cause)
class BadRequest(message: String = "", cause: Throwable? = null) : RuntimeException(message, cause)
