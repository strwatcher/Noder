package com.strwatcher.noder.base

import javafx.fxml.FXML
import javafx.scene.image.Image
import javafx.scene.input.DataFormat

abstract class FilterNode(nodeState: DataFormat, linkState: DataFormat): ImageNode(nodeState, linkState) {
    lateinit var imageInput: LinkInput<Image>
    lateinit var imageOutput: LinkOutput<Image>

    @FXML
    override fun initialize() {
        super.initialize()
        imageInput = LinkInput(null)
        imageInput.valueProperty.addListener {
                _, _, newValue ->
            val filteredImage = filterImage(newValue)
            valueProperty.value = filteredImage
            link.valueProperty.value = filteredImage
            image.image = filteredImage

        }
        imageInput.onDragDropped = linkDragDroppedHandler
        grid.add(imageInput, 0, 2)

        imageOutput = LinkOutput()
        imageOutput.onDragDetected = linkDragDetectedHandler
        grid.add(imageOutput, 2, 2)
    }

    abstract fun filterImage(img: Image?): Image?
}