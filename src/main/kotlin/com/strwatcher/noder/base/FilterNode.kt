package com.strwatcher.noder.base

import com.strwatcher.noder.utils.labelFont
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.input.DataFormat
import javafx.scene.layout.RowConstraints

abstract class FilterNode(nodeState: DataFormat, linkState: DataFormat): BaseImageNode(nodeState, linkState) {
    lateinit var imageInput: LinkInput<Image>
    lateinit var imageOutput: LinkOutput<Image>
    lateinit var inputs: Map<LinkInput<*>, String>

    @FXML
    override fun initialize() {
        super.initialize()
        setTitle()
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

    protected fun bindInputs() {
        for(input in inputs) {
            input.key.onDragDropped = linkDragDroppedHandler
            input.key.valueProperty.addListener {
                    _, _, _ ->
                val filteredImage = filterImage(imageInput.valueProperty.value)
                valueProperty.value = filteredImage
                link.valueProperty.value = filteredImage
                image.image = filteredImage
            }
        }
    }

    protected fun addInputs(startRow: Int) {
        var currentRow = startRow
        inputs.forEach { entry ->
            grid.rowConstraints.add(RowConstraints(60.0))
            grid.add(entry.key, 0, currentRow)
            grid.add(Label(entry.value).also { it.font = labelFont }, 1, currentRow)
            currentRow += 1
        }
    }
    open fun filterImage(img: Image?): Image? {
        for (input in inputs) {
            if (input.key.valueProperty.value == null) return null
        }
        if (img == null) return null
        return filterFunction(img)
    }

    abstract fun filterFunction(img: Image): Image
    abstract fun setTitle()
}