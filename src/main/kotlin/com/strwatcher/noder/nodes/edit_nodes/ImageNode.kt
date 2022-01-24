package com.strwatcher.noder.nodes.edit_nodes

import com.strwatcher.noder.base.BaseImageNode
import com.strwatcher.noder.base.ImageNodeType
import com.strwatcher.noder.base.LinkOutput
import javafx.embed.swing.SwingFXUtils
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.input.DataFormat
import javafx.scene.layout.RowConstraints
import javafx.stage.FileChooser
import javafx.stage.Stage
import javax.imageio.ImageIO

open class ImageNode(
    nodeState: DataFormat,
    linkState: DataFormat,
    id: UInt
): BaseImageNode(nodeState, linkState, id) {
    @FXML
    override fun initialize() {
        super.initialize()

        nodeTitle.text = "Image"

        grid.rowConstraints.add(RowConstraints(100.0))
        val openButton = Button("Open image")
        grid.add(openButton, 1, 3)

        openButton.setOnAction {
            val img = importImage()
            valueProperty.set(SwingFXUtils.fromFXImage(img, null))
            image.image = img
        }

        val output = LinkOutput<Image>()
        output.onDragDetected = linkDragDetectedHandler
        grid.add(output, 2, 2)


    }

    override fun initType(): String = ImageNodeType
    override fun initInputs() {

    }

    private fun importImage(): Image {
        val fileChooser = FileChooser()

        val extensionFilters = listOf(
            FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"),
            FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg"),
            FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.jpeg")
        )
        fileChooser.extensionFilters.addAll(extensionFilters)

        val file = fileChooser.showOpenDialog(scene.window as Stage)
        return SwingFXUtils.toFXImage(ImageIO.read(file.inputStream()), null)
    }
}