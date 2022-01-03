package com.strwatcher.noder.base

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.geometry.Point2D
import javafx.scene.chart.PieChart
import javafx.scene.input.*
import javafx.scene.layout.AnchorPane


open class DraggableNode(
    private val nodeState: DataFormat,
    private val linkState: DataFormat,
    path: String
): AnchorPane() {

    private val dragOverHandler = EventHandler<DragEvent> {
            event ->
        moveTo(Point2D(event.sceneX, event.sceneY))
        event.consume()
    }

    private val dragDroppedHandler = EventHandler<DragEvent> {
            event ->
        parent.onDragOver = null
        parent.onDragDropped = null
        event.isDropCompleted = true
        event.consume()
    }

    val dragDetectedHandler get() = EventHandler<MouseEvent> {

            event ->

        parent.onDragOver = dragOverHandler
        parent.onDragDropped = dragDroppedHandler

        offset = Point2D(event.x, event.y)
        moveTo(Point2D(event.sceneX, event.sceneY))

        val content = ClipboardContent()
        content[nodeState] = 1
        startDragAndDrop(*TransferMode.ANY).setContent(content)
        event.consume()
    }

    val linkDragDroppedHandler = EventHandler<DragEvent> {
        event ->

        parent.onDragOver = null
        parent.onDragDropped = null

        link.isVisible = false
        superParent!!.children.removeAt(0)

        val newLink = NodeLink()
        newLink.bindStartEnd(event.gestureSource as DraggableNode, this)
        superParent!!.children.add(0, newLink)

        val content = ClipboardContent()
        content[linkState] = "link"
        startDragAndDrop(*TransferMode.ANY).setContent(content)
        event.consume()
    }

    val linkDragDetectedHandler = EventHandler<MouseEvent> {
        event ->

        parent.onDragOver = contextLinkDragOverHandler
        parent.onDragDropped = contextLinkDragDroppedHandler

        superParent!!.children.add(0, link)
        link.isVisible = true

        val point = Point2D(layoutX + width/2, layoutY + height/2)
        link.setStart(point)

        val content = ClipboardContent()
        content[linkState] = "link"
        startDragAndDrop(*TransferMode.ANY).setContent(content)
        event.consume()

    }

    val contextLinkDragOverHandler = EventHandler<DragEvent> {
        event ->

        event.acceptTransferModes(*TransferMode.ANY)
        if(!link.isVisible) link.isVisible = true
        link.setEnd(Point2D(event.x, event.y))

        event.consume()
    }

    val contextLinkDragDroppedHandler = EventHandler<DragEvent> {
        event ->

        parent.onDragDropped = null
        parent.onDragOver = null
        link.isVisible = false

        superParent!!.children.removeAt(0)
        event.isDropCompleted = true
        event.consume()
    }

    private var superParent: AnchorPane? = null
    private var loader: FXMLLoader
    private var offset = Point2D(0.0, 0.0)
    private var link = NodeLink()

    @FXML
    open fun initialize() {


        link.isVisible = false

        parentProperty().addListener{_, _, _ -> superParent = parent as AnchorPane}
    }

    private fun moveTo(point: Point2D) {
        val local = parent.sceneToLocal(point)

        relocate(
            (local.x - offset.x),
            (local.y - offset.y)
        )
    }


    init {
        loader = FXMLLoader(javaClass.getResource(path))
        loader.setController(this)
        children.add(loader.load())
    }
}