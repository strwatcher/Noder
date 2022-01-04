package com.strwatcher.noder.base

import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle

class LinkOutput<T>: AnchorPane() {
    init {
        setPrefSize(20.0, 20.0)
        val circle = Circle(10.0, Color.RED)
        setRightAnchor(circle, -10.0)
        children.add(circle)
    }



}