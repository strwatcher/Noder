package com.strwatcher.noder.nodes

import javafx.fxml.FXML
import javafx.scene.input.DataFormat
import javafx.scene.layout.*
import javafx.scene.paint.Paint

open class FloatNode(nodeState: DataFormat, linkState: DataFormat): EditNode<Float>(nodeState, linkState) {
    @FXML
    override fun initialize() {
        super.initialize()
        editField.text = "0.0"
        nodeTitle.text = "Float"
        value = 0.0f

        editField.textProperty().addListener {
            _, old, new ->
            if (!new.matches("^[+-]?([0-9]*[.])?[0-9]+".toRegex())) {
                valueOutput.onDragDetected = null
                editField.border = Border(
                    BorderStroke(Paint.valueOf("FF0000"),
                    BorderStrokeStyle.SOLID,
                    CornerRadii(5.0),
                    BorderStroke.DEFAULT_WIDTHS))
            }
            else {
                valueOutput.onDragDetected = linkDragDetectedHandler
                value = editField.text.toFloat()
                link.valueProperty.set(value)
                editField.border = Border(
                    BorderStroke(Paint.valueOf("000000"),
                        BorderStrokeStyle.SOLID,
                        CornerRadii(5.0),
                        BorderStroke.DEFAULT_WIDTHS))
            }
        }

    }
}