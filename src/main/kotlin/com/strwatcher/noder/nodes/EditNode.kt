package com.strwatcher.noder.nodes

import com.strwatcher.noder.base.LinkOutput
import com.strwatcher.noder.base.ValueNode
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.TextField
import javafx.scene.input.DataFormat
import javafx.scene.layout.Border
import javafx.scene.layout.BorderStroke
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Paint

abstract class EditNode<T>(
    nodeState: DataFormat,
    linkState: DataFormat,
    private val validatorRegex: Regex
    ): ValueNode<T>(nodeState, linkState, FXMLLoader(EditNode::class.java.getResource("EditNode.fxml"))
) {
    protected lateinit var valueOutput: LinkOutput<T>

    @FXML
    protected lateinit var editField: TextField

    @FXML
    override fun initialize() {
        super.initialize()
        valueOutput = LinkOutput()
        valueOutput.onDragDetected = linkDragDetectedHandler
        outputLayout.children.add(valueOutput)

        draggedArea.onDragDetected = dragDetectedHandler
        editField.textProperty().addListener {
                _, _, new ->
            validatorRegex ?: return@addListener
            if (!new.matches(validatorRegex)) {
                valueOutput.onDragDetected = null
                editField.border = Border(
                    BorderStroke(
                        Paint.valueOf("FF0000"),
                        BorderStrokeStyle.SOLID,
                        CornerRadii(5.0),
                        BorderStroke.DEFAULT_WIDTHS)
                )
            }
            else {
                valueOutput.onDragDetected = linkDragDetectedHandler
                value = toValue(editField.text)
                link.valueProperty.set(value)
                editField.border = Border(
                    BorderStroke(
                        Paint.valueOf("000000"),
                        BorderStrokeStyle.SOLID,
                        CornerRadii(5.0),
                        BorderStroke.DEFAULT_WIDTHS)
                )
            }
        }
    }

    abstract fun toValue(text: String): T
}