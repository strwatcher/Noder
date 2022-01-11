package com.strwatcher.noder.nodes.edit_nodes

import com.strwatcher.noder.base.EditNode
import javafx.fxml.FXML
import javafx.scene.input.DataFormat

class StringNode(
    nodeState: DataFormat,
    linkState: DataFormat,
): EditNode<String>(nodeState, linkState, Regex("^[\\s\\S]*")) {

    @FXML
    override fun initialize() {
        super.initialize()
        value = ""
        link.valueProperty.set(value)
        editField.text = ""
        nodeTitle.text = "String"
    }

    override fun toValue(text: String): String = text
}