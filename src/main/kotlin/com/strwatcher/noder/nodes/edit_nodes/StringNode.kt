package com.strwatcher.noder.nodes.edit_nodes

import com.strwatcher.noder.base.EditNode
import com.strwatcher.noder.base.StringNodeType
import javafx.fxml.FXML
import javafx.scene.input.DataFormat

class StringNode(
    nodeState: DataFormat,
    linkState: DataFormat,
    id: UInt
): EditNode<String>(nodeState, linkState, id, Regex("^[\\s\\S]*")) {

    @FXML
    override fun initialize() {
        super.initialize()
        value = ""
        link.valueProperty.set(value)
        editField.text = ""
        nodeTitle.text = "String"
    }

    override fun toValue(text: String): String = text
    override fun initType(): String = StringNodeType
    override fun initInputs() {
    }
}