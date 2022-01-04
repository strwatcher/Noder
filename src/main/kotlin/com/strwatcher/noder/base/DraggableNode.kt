package com.strwatcher.noder.base

import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.geometry.Point2D
import javafx.scene.input.*
import javafx.scene.layout.AnchorPane


open class DraggableNode<T>(
    private val nodeState: DataFormat,
    private val linkState: DataFormat,
    loader: FXMLLoader
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

    private val contextLinkDragOverHandler = EventHandler<DragEvent> {
            event ->

        event.acceptTransferModes(*TransferMode.ANY)
        if(!link.isVisible) link.isVisible = true
        link.setEnd(Point2D(event.x, event.y))

        event.consume()
    }

    private val contextLinkDragDroppedHandler = EventHandler<DragEvent> {
            event ->

        parent.onDragDropped = null
        parent.onDragOver = null
        link.isVisible = false

        superParent!!.children.removeAt(0)
        event.isDropCompleted = true
        event.consume()
    }

    val linkDragDetectedHandler = EventHandler<MouseEvent> {
            event ->
        if (!link.isConnected) {
            parent.onDragOver = contextLinkDragOverHandler
            parent.onDragDropped = contextLinkDragDroppedHandler

            link.isVisible = true
            link.bindStart(event.source as LinkOutput<*>)

            superParent!!.children.add(0, link)
            val content = ClipboardContent()
            content[linkState] = "link"
            startDragAndDrop(*TransferMode.ANY).setContent(content)
            event.consume()
        }

    }

    val linkDragDroppedHandler = EventHandler<DragEvent> {
            event ->

        parent.onDragOver = null
        parent.onDragDropped = null

        val connectedLink = (event.gestureSource as DraggableNode<T>).link

        connectedLink.bindEnd(event.source as LinkInput<T>)
        connectedLink.isConnected = true
        connectedLink.destination = event.source as LinkInput<T>

        val value = connectedLink.valueProperty.value
        connectedLink.valueProperty.set(null)
        connectedLink.valueProperty.set(value)

        connectedLinks.add(connectedLink)

        val content = ClipboardContent()

        content[linkState] = "link"
        startDragAndDrop(*TransferMode.ANY).setContent(content)
        event.consume()
    }


    private var offset = Point2D(0.0, 0.0)
    protected var superParent: AnchorPane? = null
    var link = NodeLink(this)
    var value: T? = null
    protected val connectedLinks = mutableListOf<NodeLink<T>>()

    private fun moveTo(point: Point2D) {
        val local = parent.sceneToLocal(point)

        relocate(
            (local.x - offset.x),
            (local.y - offset.y)
        )
    }


    init {
        loader.setController(this)
        children.add(loader.load())
        parentProperty().addListener { _, _, _ ->
            parent?.let {
                superParent = parent as AnchorPane
            }
        }

        link.setOnMouseClicked {
            superParent!!.children.remove(link)
            link.isConnected = false
            link.unbindEnd()
        }

    }

}