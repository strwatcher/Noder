package com.strwatcher.noder.nodes

import com.strwatcher.noder.Prerender
import com.strwatcher.noder.base.BaseImageNode
import com.strwatcher.noder.base.LinkInput
import javafx.embed.swing.SwingFXUtils
import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.input.DataFormat
import javafx.scene.layout.RowConstraints
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

class EndNode(nodeState: DataFormat, linkState: DataFormat): BaseImageNode(nodeState, linkState) {
    private var prerender: Prerender? = null

    @FXML
    override fun initialize() {
        super.initialize()

        nodeTitle.text = "End Node"

        val input = LinkInput(image.image)
        input.onDragDropped = linkDragDroppedHandler
        input.valueProperty.addListener {
                _, _, newValue ->
            valueProperty.value = newValue
            image.image = newValue
        }
        grid.add(input, 0, 2)

        grid.children.remove(deleteButton)

        val saveImageButton = Button("Save")
        grid.rowConstraints.add(RowConstraints(100.0))
        grid.add(saveImageButton, 1, 3)
        saveImageButton.setOnAction {
            saveAs()
        }

        image.setOnMouseClicked {
            openPrerender()
        }

    }

    private fun openPrerender() {

        prerender = Prerender(this)

        try {
            val stage = Stage()
            stage.title = "Prerender"
            stage.scene = Scene(prerender)
            stage.isMaximized = true
            stage.show()
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun saveAs() {
        val fileChooser = FileChooser()
        val extensionFilter = FileChooser.ExtensionFilter("PNG files (*.png)", "*.png")

        fileChooser.extensionFilters.add(extensionFilter)

        var file = fileChooser.showSaveDialog(scene.window as Stage)

        if (file.nameWithoutExtension == file.name) {
            file = File(file.parentFile, file.nameWithoutExtension + ".png")
        }

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(valueProperty.value!!, null), "png", file)
        } catch (exception: IOException) {
            exception.printStackTrace()
        }
    }
}