package com.strwatcher.noder.nodes

import com.strwatcher.noder.base.StartNodeType
import com.strwatcher.noder.nodes.edit_nodes.ImageNode
import javafx.fxml.FXML
import javafx.scene.input.DataFormat

class StartNode(nodeState: DataFormat, linkState: DataFormat, id: UInt): ImageNode(nodeState, linkState, id) {
    @FXML
    override fun initialize() {
        super.initialize()
        nodeTitle.text = "Start Node"
        grid.children.remove(deleteButton)
    }

    override fun initType(): String = StartNodeType
}