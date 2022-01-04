package com.strwatcher.noder.nodes

import com.strwatcher.noder.base.LinkOutput
import com.strwatcher.noder.base.ValueNode
import javafx.fxml.FXML
import javafx.scene.control.TextField
import javafx.scene.input.DataFormat

open class EditNode<T>(nodeState: DataFormat, linkState: DataFormat): ValueNode<T>(nodeState, linkState) {
    protected lateinit var valueOutput: LinkOutput<T>
    protected lateinit var editField: TextField
    @FXML
    override fun initialize() {
        super.initialize()
        valueOutput = LinkOutput()
        valueOutput.onDragDetected = linkDragDetectedHandler
        editField = TextField()
        editField.prefWidth = 64.0
        editField.prefHeight = 36.0
        editField.layoutX = 34.0
        editField.layoutY = 33.0
        valueContainer.children.add(editField)
        outputLayout.children.add(valueOutput)

        draggedArea.onDragDetected = dragDetectedHandler

    }
}