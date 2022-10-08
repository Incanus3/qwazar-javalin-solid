package cz.incanus.javalinTest.widgets

import cz.incanus.javalinTest.viewsets.Viewset

open class Widget(val type: String)
class Text(val content: String) : Widget("text")
class Button(val label: String) : Widget("button")
class Link(val viewsetName: String, val viewName: String) : Widget("link")
