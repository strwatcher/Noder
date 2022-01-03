package com.strwatcher.noder

import com.strwatcher.noder.nodes.IntNode
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.input.DataFormat
import javafx.scene.layout.AnchorPane

class MainViewController {
    private val nodeState = DataFormat("nodeState")
    private val linkState = DataFormat("linkState")

    @FXML
    private lateinit var superParent: AnchorPane

    @FXML
    private lateinit var bAddNewNode: Button

    @FXML
    fun initialize() {
        bAddNewNode.setOnAction {
            println("hello")
            createNode()
        }
    }

    private fun createNode() {
        val newNode = IntNode(nodeState, linkState)
        superParent.children.add(newNode)
    }
}