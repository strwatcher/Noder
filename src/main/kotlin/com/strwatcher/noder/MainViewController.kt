package com.strwatcher.noder

import com.strwatcher.noder.base.DraggableNode
import com.strwatcher.noder.base.LinkInput
import com.strwatcher.noder.base.NodeLink
import com.strwatcher.noder.base.Scene
import com.strwatcher.noder.nodes.EndNode
import com.strwatcher.noder.nodes.StartNode
import com.strwatcher.noder.nodes.edit_nodes.FloatNode
import com.strwatcher.noder.nodes.edit_nodes.ImageNode
import com.strwatcher.noder.nodes.edit_nodes.IntNode
import com.strwatcher.noder.nodes.edit_nodes.StringNode
import com.strwatcher.noder.nodes.filter_nodes.*
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.MenuItem
import javafx.scene.control.ScrollPane
import javafx.scene.input.DataFormat
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox
import javafx.stage.FileChooser
import javafx.stage.Stage
import nu.pattern.OpenCV
import java.awt.image.BufferedImage
import java.io.File

class MainViewController {
    private val nodeState = DataFormat("nodeState")
    private val linkState = DataFormat("linkState")
    private var scene = Scene(nodeState, linkState, 0u)

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
   private lateinit var saveMenuItem: MenuItem

   @FXML
   private lateinit var openMenuItem: MenuItem

   @FXML
    fun initialize() {
        OpenCV.loadLocally()

        intNodeButton.setOnAction {
            println("Int Node Created")
            addNode(IntNode(nodeState, linkState, scene.getId()))
        }

        floatNodeButton.setOnAction {
            println("Float Node created")
            addNode(FloatNode(nodeState, linkState, scene.getId()))
        }

        stringNodeButton.setOnAction {
            println("String Node created")
            addNode(StringNode(nodeState, linkState, scene.getId()))
        }

        sepiaNodeButton.setOnAction {
            println("Sepia Node created")
            addNode(SepiaNode(nodeState, linkState, scene.getId()))
        }

        addTextNodeButton.setOnAction {
            println("Add Text Node created")
            addNode(AddTextNode(nodeState, linkState, scene.getId()))
        }

        grayNodeButton.setOnAction {
            println("Gray Filter Node created")
            addNode(GrayFilterNode(nodeState, linkState, scene.getId()))
        }

        imageNodeButton.setOnAction {
            println("Image Node created")
            addNode(ImageNode(nodeState, linkState, scene.getId()))
        }

        addImageNodeButton.setOnAction {
            println("Add Image Node created")
            addNode(AddImageNode(nodeState, linkState, scene.getId()))
        }

       brightnessNodeButton.setOnAction {
           println("Brightness Node created")
           addNode(BrightnessNode(nodeState, linkState, scene.getId()))
       }

       blurNodeButton.setOnAction {
           println("Blur Node created")
           addNode(BlurNode(nodeState, linkState, scene.getId()))
       }

       invertNodeButton.setOnAction {
           println("Invert Node created")
           addNode(InvertNode(nodeState, linkState, scene.getId()))
       }

       rotateNodeButton.setOnAction {
           println("Rotate Node created")
           addNode(RotationNode(nodeState, linkState, scene.getId()))
       }

       scaleNodeButton.setOnAction {
           println("Scale Node created")
           addNode(ScaleNode(nodeState, linkState, scene.getId()))
       }

       moveNodeButton.setOnAction {
           println("Move Node created")
           addNode(MoveNode(nodeState, linkState, scene.getId()))
       }

        addNode(StartNode(nodeState, linkState, scene.getId()))
        addNode(EndNode(nodeState, linkState, scene.getId()).also { it.layoutX = 700.0 })

       saveMenuItem.setOnAction {
           val json = scene.save()
           val fileChooser = FileChooser()
           val extensionFilter = FileChooser.ExtensionFilter("JSON files (*.json)", "*.json")

           fileChooser.extensionFilters.add(extensionFilter)

           var file = fileChooser.showSaveDialog(sceneContainer.scene.window as Stage)

           if (file.nameWithoutExtension == file.name) {
               file = File(file.parentFile, file.nameWithoutExtension + ".json")
           }

           file.writeText(json)
       }

       openMenuItem.setOnAction {
           val fileChooser = FileChooser()
           val extensionFilter = FileChooser.ExtensionFilter("JSON files (*.json)", "*.json")

           fileChooser.extensionFilters.add(extensionFilter)

           val file = fileChooser.showOpenDialog(sceneContainer.scene.window as Stage)
           val json = file.readText()
           scene = scene.load(json)
           clearScene()

           val nodesIterator = scene.nodes.iterator()
           for(node in nodesIterator) {
               sceneContainer.children.add(node)
           }

           loadLinks()
       }

    }

    private fun <T> addNode(node: DraggableNode<T>) {
        node.onNodeRemovedCallback = {
            scene.remove(it)
        }
        sceneContainer.children.add(node)
        scene.add(node)
    }


    private fun clearScene() {
        sceneContainer.children.clear()
    }

    private fun loadLinks() {
        for (nodeConnections in scene.connections) {
            val node = scene.findNodeById(nodeConnections.id.toUInt())
            node?.let {
                for (connectionKey in nodeConnections.connections) {
                    val connectedNode = scene.findNodeById(connectionKey.nodeId.toUInt())
                    connectedNode.let {
                        val connectedLink = connectedNode!!.link
                        val currentInput = node.linkInputs[connectionKey.inputId]
                        when {
                            connectedLink.valueProperty.value is Int? && currentInput.valueProperty.value is Int? ->
                                node.loadLink(connectedLink as NodeLink<Int?>, currentInput as LinkInput<Int?>)

                            connectedLink.valueProperty.value is Float? && currentInput.valueProperty.value is Float? ->
                                node.loadLink(connectedLink as NodeLink<Float?>, currentInput as LinkInput<Float?>)

                            connectedLink.valueProperty.value is String? && currentInput.valueProperty.value is String? ->
                                node.loadLink(connectedLink as NodeLink<String?>, currentInput as LinkInput<String?>)

                            connectedLink.valueProperty.value is BufferedImage? && currentInput.valueProperty.value is BufferedImage? ->
                                node.loadLink(
                                    connectedLink as NodeLink<BufferedImage?>,
                                    currentInput as LinkInput<BufferedImage?>
                                )
                        }
                    }
                }
            }
        }
    }
}