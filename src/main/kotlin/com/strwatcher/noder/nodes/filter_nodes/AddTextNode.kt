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
    private lateinit var xInput: LinkInput<Int?>
    private lateinit var yInput: LinkInput<Int?>
    private lateinit var textInput: LinkInput<String?>

    @FXML
    override fun initialize() {
        super.initialize()
        xInput = LinkInput(null)
        yInput = LinkInput(null)
        textInput = LinkInput(null)

        inputs = mapOf(
            Pair(xInput, "x"),
            Pair(yInput, "y"),
            Pair(textInput, "Text")
        )

        addInputs(3)
        bindInputs()
    }

    override fun filterFunction(img: Image): Image {
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

    override fun setTitle() {
        nodeTitle.text = "Add Text"
    }

}