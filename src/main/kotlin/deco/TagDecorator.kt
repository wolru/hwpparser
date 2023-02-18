package deco

import deco.decorator.TableDecorator

class TagDecorator {
    private val decorators = listOf(
        TableDecorator(),
    )

    fun decorate(text: String): String {
        var checkedText = text
        decorators.forEach {
            checkedText = it.decorate(checkedText)
        }
        return checkedText
    }
}