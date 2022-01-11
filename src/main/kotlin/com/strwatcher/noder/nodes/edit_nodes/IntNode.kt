package com.strwatcher.noder.nodes.edit_nodes

import com.strwatcher.noder.base.EditNode
import javafx.fxml.FXML
import javafx.scene.input.DataFormat

class IntNode(
    nodeState: DataFormat,
    linkState: DataFormat,
): EditNode<Int>(nodeState, linkState, Regex("^[+-]?\\d+\$")) {
    @FXML
    override fun initialize() {
        super.initialize()
        value = 0
        link.valueProperty.set(value)
        editField.text = "0"
        nodeTitle.text = "Int"
    }

    override fun toValue(text: String): Int = text.toInt()
}