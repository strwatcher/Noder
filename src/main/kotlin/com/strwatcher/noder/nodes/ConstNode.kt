package com.strwatcher.noder.nodes

import javafx.fxml.FXML
import javafx.scene.input.DataFormat

open class ConstNode(nodeState: DataFormat, linkState: DataFormat): ImageNode(nodeState, linkState) {
    @FXML
    override fun initialize() {
        super.initialize()
        grid.children.remove(deleteButton)
    }




}