package com.strwatcher.noder.base

import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle

class LinkInput: AnchorPane() {

    init {
        setPrefSize(20.0, 20.0)
        val circle = Circle(10.0, Color.AQUA)
        children.add(circle)
    }
}