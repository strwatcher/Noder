package com.strwatcher.noder.nodes

import com.strwatcher.noder.nodes.edit_nodes.ImageNode
import javafx.fxml.FXML
import javafx.scene.input.DataFormat

class StartNode(nodeState: DataFormat, linkState: DataFormat): ImageNode(nodeState, linkState) {
    @FXML
    override fun initialize() {
        super.initialize()
        nodeTitle.text = "Start Node"
        grid.children.remove(deleteButton)
    }
}