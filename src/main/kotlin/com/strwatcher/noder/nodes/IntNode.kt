package com.strwatcher.noder.nodes

import com.strwatcher.noder.base.LinkInput
import com.strwatcher.noder.base.LinkOutput
import com.strwatcher.noder.base.ValueNode
import javafx.fxml.FXML
import javafx.scene.control.TextField
import javafx.scene.input.DataFormat

class IntNode(nodeState: DataFormat, linkState: DataFormat): ValueNode(nodeState, linkState) {
    private lateinit var intOutput: LinkOutput
    private lateinit var intInput: LinkInput

    @FXML
    override fun initialize() {
        super.initialize()

        intOutput = LinkOutput()
        intOutput.onDragDetected = linkDragDetectedHandler
        val intInput2 = LinkInput()
        intInput2.onDragDropped = linkDragDroppedHandler

        intInput = LinkInput()
        intInput.onDragDropped = linkDragDroppedHandler

        nodeTitle.text = "Int"
        val editField = TextField("0")
        editField.prefWidth = 64.0
        editField.prefHeight = 36.0
        editField.layoutX = 34.0
        editField.layoutY = 33.0
        valueContainer.children.add(editField)
        outputLayout.children.add(intOutput)
        inputLayout.children.add(intInput)
        inputLayout.children.add(intInput2)
        draggedArea.onDragDetected = dragDetectedHandler

    }
}