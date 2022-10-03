package cz.incanus.javalinTest.viewsets

import cz.incanus.javalinTest.common.Router
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path

class ViewsetRouter : Router() {
    fun routes() {
        path("/viewsets") {
            get {
                ViewsetHandler(it).viewsets()
            }

            path(placeholder(ViewsetHandler.viewsetNameParam)) {
                get {
                    ViewsetHandler(it).viewset()
                }

                path("views") {
                    get {
                        ViewsetHandler(it).views()
                    }

                    path(placeholder(ViewsetHandler.viewNameParam)) {
                        get("") {
                            ViewsetHandler(it).view()
                        }

                        get("render") {
                            ViewsetHandler(it).renderView()
                        }
                    }
                }
            }
        }
    }
}
