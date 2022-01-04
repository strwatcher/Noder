package com.strwatcher.noder.nodes

import com.strwatcher.noder.base.LinkInput
import javafx.scene.input.DataFormat

class InputFloatNode(nodeState: DataFormat, linkState: DataFormat): FloatNode(nodeState, linkState) {
    private lateinit var inputFloatLink: LinkInput<Float>

    override fun initialize() {
        super.initialize()

        inputFloatLink = LinkInput()
        inputFloatLink.onDragDropped = linkDragDroppedHandler
        inputLayout.children.add(inputFloatLink)

        inputFloatLink.valueProperty.addListener {
                _, _, newValue ->
            value = newValue
            println(newValue)
            editField.text = newValue.toString()
        }

    }
}