package com.strwatcher.noder

import com.strwatcher.noder.nodes.EndNode
import javafx.geometry.Pos
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.StackPane

class Prerender(parent: EndNode): AnchorPane() {
    private val imageView = ImageView()

    init {
//        imageView.fitWidthProperty().bind(this.widthProperty())
//        imageView.fitHeightProperty().bind(this.heightProperty())
        imageView.isPreserveRatio = true
        this.children.add(imageView)
        StackPane.setAlignment(imageView, Pos.CENTER)
        imageView.image = parent.valueProperty.value


        parent.valueProperty.addListener {
            _, _, newValue ->
            imageView.image = newValue
        }
    }
}