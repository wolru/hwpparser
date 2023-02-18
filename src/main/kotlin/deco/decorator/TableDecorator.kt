package deco.decorator

import deco.TagDecoratorInterface

class TableDecorator : TagDecoratorInterface {

    override fun decorate(text: String): String {
        return text.replace("<table", "<table border=\"1\" cellpadding=\"20px\"")
    }
}