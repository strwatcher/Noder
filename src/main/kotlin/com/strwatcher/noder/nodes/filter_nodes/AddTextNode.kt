package com.strwatcher.noder.nodes.filter_nodes

import com.strwatcher.noder.base.FilterNode
import com.strwatcher.noder.base.LinkInput
import com.strwatcher.noder.utils.labelFont
import javafx.embed.swing.SwingFXUtils
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.input.DataFormat
import javafx.scene.layout.RowConstraints
import java.awt.Color
import java.awt.Font

class AddTextNode(nodeState: DataFormat, linkState: DataFormat): FilterNode(nodeState, linkState) {
    lateinit var xInput: LinkInput<Int?>
    lateinit var yInput: LinkInput<Int?>
    lateinit var textInput: LinkInput<String?>

    @FXML
    override fun initialize() {
        super.initialize()
        xInput = LinkInput(null)
        yInput = LinkInput(null)
        textInput = LinkInput(null)

        val inputList = listOf(xInput, yInput, textInput)

        grid.rowConstraints.add(RowConstraints(60.0))
        grid.rowConstraints.add(RowConstraints(60.0))
        grid.rowConstraints.add(RowConstraints(60.0))

        grid.add(textInput, 0, 3)
        grid.add(Label("Text").also { it.font = labelFont }, 1, 3)

        grid.add(xInput, 0, 4)
        grid.add(Label("x").also { it.font = labelFont }, 1, 4)

        grid.add(yInput, 0, 5)
        grid.add(Label("y").also { it.font = labelFont }, 1, 5)

        bindInputs(inputList)
    }

    private fun bindInputs(inputs: List<LinkInput<*>>) {
        for(input in inputs) {
            input.onDragDropped = linkDragDroppedHandler
            input.valueProperty.addListener {
                _, _, _ ->
                val filteredImage = filterImage(imageInput.valueProperty.value)
                valueProperty.value = filteredImage
                link.valueProperty.value = filteredImage
                image.image = filteredImage
            }
        }

    }

    override fun filterImage(img: Image?): Image? {
        if (xInput.valueProperty.value == null ||
            yInput.valueProperty.value == null ||
            textInput.valueProperty.value == null ||
            img == null ) return null

        val bufferedImage = SwingFXUtils.fromFXImage(img, null)
        val font = Font("Arial", Font.BOLD, 100)
        val graphics = bufferedImage.graphics
        graphics.font = font
        graphics.color = Color.BLACK
        graphics.drawString(
            textInput.valueProperty.value!!,
            xInput.valueProperty.value!!,
            yInput.valueProperty.value!!
        )

        return SwingFXUtils.toFXImage(bufferedImage, null)
    }

}