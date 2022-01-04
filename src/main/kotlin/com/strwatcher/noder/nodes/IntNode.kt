package com.strwatcher.noder.nodes

import javafx.fxml.FXML
import javafx.scene.input.DataFormat

class IntNode(nodeState: DataFormat, linkState: DataFormat): EditNode<Int>(nodeState, linkState) {
    @FXML
    override fun initialize() {
        super.initialize()

        editField.text = "0"
        nodeTitle.text = "Int"

    }
}