package com.strwatcher.noder.nodes.edit_nodes

import com.strwatcher.noder.base.EditNode
import com.strwatcher.noder.base.FloatNodeType
import javafx.fxml.FXML
import javafx.scene.input.DataFormat

open class FloatNode(
    nodeState: DataFormat,
    linkState: DataFormat,
    id: UInt
): EditNode<Float>(nodeState, linkState, id, Regex("[+-]?([0-9]*[.])?[0-9]+")) {
    @FXML
    override fun initialize() {
        super.initialize()
        value = 0.0f
        link.valueProperty.set(value)
        editField.text = "0.0"
        nodeTitle.text = "Float"
    }

    override fun toValue(text: String): Float = text.toFloat()
    override fun initType() = FloatNodeType
    override fun initInputs() {
    }
}