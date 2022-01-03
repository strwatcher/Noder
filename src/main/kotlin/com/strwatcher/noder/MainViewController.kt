package com.strwatcher.noder

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.input.DataFormat
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane

class MainViewController {
    private val stateNode = DataFormat("stateNode")
    private val stateLink = DataFormat("stateLink")

    @FXML
    private lateinit var pane: AnchorPane

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
        val newNode = DarkDraggableTitle(stateNode, stateLink, "DarkDraggableTitle.fxml")
        pane.children.add(newNode)
    }
}