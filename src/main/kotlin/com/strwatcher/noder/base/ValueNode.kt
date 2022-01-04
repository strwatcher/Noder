package com.strwatcher.noder.base

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.input.DataFormat
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox

open class ValueNode<T>(nodeState: DataFormat, linkState: DataFormat): DraggableNode<T>(
    nodeState,
    linkState,
    FXMLLoader(ValueNode::class.java.getResource("ValueNode.fxml"))
) {
    @FXML
    lateinit var nodeTitle: Label

    @FXML
    lateinit var inputLayout: VBox

    @FXML
    lateinit var outputLayout: VBox

    @FXML
    lateinit var draggedArea: AnchorPane

    @FXML
    lateinit var valueContainer: AnchorPane

    @FXML
    lateinit var deleteButton: Button

    @FXML
    open fun initialize() {
        draggedArea.onDragDetected = dragDetectedHandler

        deleteButton.setOnAction {

            (parent as AnchorPane).children.remove(this)
            for(link: NodeLink<T> in connectedLinks) {
                superParent!!.children.remove(link)
                link.isConnected = false
                link.unbindEnd()
            }

            superParent!!.children.remove(link)
        }
    }

}