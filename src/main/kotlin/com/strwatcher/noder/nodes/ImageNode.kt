package com.strwatcher.noder.nodes

import com.strwatcher.noder.base.LinkInput
import com.strwatcher.noder.base.ValueNode
import javafx.beans.binding.Bindings
import javafx.beans.property.SimpleObjectProperty
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.DataFormat
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.scene.layout.RowConstraints

open class ImageNode(nodeState: DataFormat, linkState: DataFormat):
    ValueNode<Image>(nodeState, linkState, FXMLLoader(ImageNode::class.java.getResource("ImageNode.fxml")))
{
    @FXML
    lateinit var image: ImageView

    @FXML
    lateinit var grid: GridPane

    lateinit var valueProperty: SimpleObjectProperty<Image?>

    @FXML
    override fun initialize() {
        super.initialize()

        val imageColumn = grid.columnConstraints[1]
        val imageRow = grid.rowConstraints[2]

        valueProperty = SimpleObjectProperty()

        image.fitWidthProperty().bind(Bindings.add(imageColumn.prefWidthProperty(), -10.0))
        image.fitHeightProperty().bind(Bindings.add(imageRow.prefHeightProperty(), - 10.0))
        grid.rowConstraints.add(RowConstraints(100.0))

        valueProperty.addListener { _, _, newValue ->
            link.valueProperty.set(newValue)
        }
    }
}