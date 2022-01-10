package com.strwatcher.noder.nodes

import com.strwatcher.noder.base.ConstNode
import com.strwatcher.noder.base.LinkInput
import javafx.fxml.FXML
import javafx.scene.input.DataFormat

class EndNode(nodeState: DataFormat, linkState: DataFormat): ConstNode(nodeState, linkState) {
    @FXML
    override fun initialize() {
        super.initialize()

        nodeTitle.text = "End Node"

        val input = LinkInput(image.image)
        input.onDragDropped = linkDragDroppedHandler
        input.valueProperty.addListener {
                _, _, newValue ->
            valueProperty.value = newValue
            image.image = newValue
        }
        grid.add(input, 0, 2)
    }
}