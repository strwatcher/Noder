package com.strwatcher.noder

import com.strwatcher.noder.base.DraggableNode
import com.strwatcher.noder.nodes.*
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
import javafx.scene.input.DataFormat
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox

class MainViewController {
    private val nodeState = DataFormat("nodeState")
    private val linkState = DataFormat("linkState")

    @FXML
    private lateinit var sceneContainer: AnchorPane

    @FXML
    private lateinit var sceneScroll: ScrollPane


    @FXML
    private lateinit var addMenuScroll: ScrollPane


    @FXML
    private lateinit var addMenuContainer: VBox

    @FXML
    private lateinit var intNodeButton: Button

    @FXML
    private lateinit var floatNodeButton: Button

    @FXML
    private lateinit var stringNodeButton: Button

    @FXML
    private lateinit var floatOutputNodeButton: Button

    @FXML
    fun initialize() {
        intNodeButton.setOnAction {
            println("Int Node Created")
            addNode(IntNode(nodeState, linkState))
        }

        floatNodeButton.setOnAction {
            println("Float Node created")
            addNode(FloatNode(nodeState, linkState))
        }

        stringNodeButton.setOnAction {
            println("String Node created")
            addNode(StringNode(nodeState, linkState))
        }

        floatOutputNodeButton.setOnAction {
            println("Float Input Node created")
            addNode(ImageNode(nodeState, linkState))
        }

        addNode(StartNode(nodeState, linkState))
        addNode(EndNode(nodeState, linkState))

    }

    private fun <T> addNode(node: DraggableNode<T>) {
        sceneContainer.children.add(node)
    }
}