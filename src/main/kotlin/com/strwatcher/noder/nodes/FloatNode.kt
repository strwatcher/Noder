package com.strwatcher.noder.nodes

import javafx.fxml.FXML
import javafx.scene.input.DataFormat
import javafx.scene.layout.*
import javafx.scene.paint.Paint

open class FloatNode(
    nodeState: DataFormat,
    linkState: DataFormat,
): EditNode<Float>(nodeState, linkState, Regex("[+-]?([0-9]*[.])?[0-9]+")) {
    @FXML
    override fun initialize() {
        super.initialize()
        value = 0.0f
        link.valueProperty.set(value)
        editField.text = "0.0"
        nodeTitle.text = "Float"
    }

    override fun toValue(text: String): Float = text.toFloat()
}