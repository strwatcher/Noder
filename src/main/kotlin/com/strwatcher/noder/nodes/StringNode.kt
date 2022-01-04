package com.strwatcher.noder.nodes

import javafx.fxml.FXML
import javafx.scene.input.DataFormat

class StringNode(nodeState: DataFormat, linkState: DataFormat): EditNode<String>(nodeState, linkState) {

    @FXML
    override fun initialize() {
        super.initialize()

        editField.text = ""
        nodeTitle.text = "String"

    }
}