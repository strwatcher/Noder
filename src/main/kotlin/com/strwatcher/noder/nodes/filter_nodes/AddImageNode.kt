package com.strwatcher.noder.nodes.filter_nodes

import com.strwatcher.noder.base.FilterNode
import com.strwatcher.noder.base.LinkInput
import javafx.embed.swing.SwingFXUtils
import javafx.fxml.FXML
import javafx.scene.image.Image
import javafx.scene.input.DataFormat
import java.awt.Color
import java.awt.Font

class AddImageNode(nodeState: DataFormat, linkState: DataFormat): FilterNode(nodeState, linkState) {
    private lateinit var xInput: LinkInput<Int?>
    private lateinit var yInput: LinkInput<Int?>
    private lateinit var addingImageInput: LinkInput<Image?>

    @FXML
    override fun initialize() {
        super.initialize()

        xInput = LinkInput(null)
        yInput = LinkInput(null)
        addingImageInput = LinkInput(null)

        inputs = mapOf(
            Pair(addingImageInput, "Image"),
            Pair(xInput, "x"),
            Pair(yInput, "y")
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
        graphics.drawImage(
            SwingFXUtils.fromFXImage(addingImageInput.valueProperty.value!!, null),
            xInput.valueProperty.value!!,
            yInput.valueProperty.value!!,
            null
        )

        return SwingFXUtils.toFXImage(bufferedImage, null)
    }

    override fun setTitle() {
        nodeTitle.text = "Add Image"
    }
}