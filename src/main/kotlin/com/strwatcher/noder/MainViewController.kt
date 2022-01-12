package com.strwatcher.noder

import com.strwatcher.noder.base.DraggableNode
import com.strwatcher.noder.nodes.*
import com.strwatcher.noder.nodes.edit_nodes.FloatNode
import com.strwatcher.noder.nodes.edit_nodes.ImageNode
import com.strwatcher.noder.nodes.edit_nodes.IntNode
import com.strwatcher.noder.nodes.edit_nodes.StringNode
import com.strwatcher.noder.nodes.filter_nodes.*
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
import javafx.scene.input.DataFormat
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox
import javafx.scene.transform.Scale
import nu.pattern.OpenCV

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
    private lateinit var sepiaNodeButton: Button

    @FXML
    private lateinit var addTextNodeButton: Button

    @FXML
    private lateinit var grayNodeButton: Button

    @FXML
    private lateinit var imageNodeButton: Button

    @FXML
    private lateinit var addImageNodeButton: Button

   @FXML
   private lateinit var brightnessNodeButton: Button

   @FXML
   private lateinit var blurNodeButton: Button

   @FXML
   private lateinit var invertNodeButton: Button

   @FXML
   private lateinit var moveNodeButton: Button

   @FXML
   private lateinit var scaleNodeButton: Button

   @FXML
   private lateinit var rotateNodeButton: Button

   @FXML
    fun initialize() {
        OpenCV.loadLocally()

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

        sepiaNodeButton.setOnAction {
            println("Sepia Node created")
            addNode(SepiaNode(nodeState, linkState))
        }

        addTextNodeButton.setOnAction {
            println("Add Text Node created")
            addNode(AddTextNode(nodeState, linkState))
        }

        grayNodeButton.setOnAction {
            println("Gray Filter Node created")
            addNode(GrayFilterNode(nodeState, linkState))
        }

        imageNodeButton.setOnAction {
            println("Image Node created")
            addNode(ImageNode(nodeState, linkState))
        }

        addImageNodeButton.setOnAction {
            println("Add Image Node created")
            addNode(AddImageNode(nodeState, linkState))
        }

       brightnessNodeButton.setOnAction {
           println("Brightness Node created")
           addNode(BrightnessNode(nodeState, linkState))
       }

       blurNodeButton.setOnAction {
           println("Blur Node created")
           addNode(BlurNode(nodeState, linkState))
       }

       invertNodeButton.setOnAction {
           println("Invert Node created")
           addNode(InvertNode(nodeState, linkState))
       }

       rotateNodeButton.setOnAction {
           println("Rotate Node created")
           addNode(RotationNode(nodeState, linkState))
       }

       scaleNodeButton.setOnAction {
           println("Scale Node created")
           addNode(ScaleNode(nodeState, linkState))
       }

       moveNodeButton.setOnAction {
           println("Move Node created")
           addNode(MoveNode(nodeState, linkState))
       }

        addNode(StartNode(nodeState, linkState))
        addNode(EndNode(nodeState, linkState).also { it.layoutX = 700.0 })

    }

    private fun <T> addNode(node: DraggableNode<T>) {
        sceneContainer.children.add(node)
    }
}