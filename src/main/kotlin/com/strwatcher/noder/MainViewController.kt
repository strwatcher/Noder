package com.strwatcher.noder

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.input.DataFormat
import javafx.scene.layout.Pane

class MainViewController {
    private val stateNode = DataFormat("stateNode")

    @FXML
    private lateinit var pane: Pane

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
        val newNode = DarkDraggableTitle(stateNode,
            FXMLLoader(javaClass.getResource("DraggableTitleButDark.fxml")),
            DraggableTitleButDarkController())
        pane.children.add(newNode)
    }
}