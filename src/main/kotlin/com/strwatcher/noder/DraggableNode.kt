package com.strwatcher.noder

import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.geometry.Point2D
import javafx.scene.Node
import javafx.scene.input.*
import javafx.scene.layout.AnchorPane
import java.io.IOException


abstract class DraggableNode(
    private val state: DataFormat,
    private val loader: FXMLLoader,
    private val controller: Controller

    ): AnchorPane() {

    private val dragOverHandler = EventHandler<DragEvent> {
            event ->
        moveTo(Point2D(event.sceneX, event.sceneY))
        event.consume()
    }

    private val dragDroppedHandler = EventHandler<DragEvent> {
            event ->
        this.parent.onDragOver = null
        this.parent.onDragDropped = null
        event.isDropCompleted = true
        event.consume()
    }

    private val dragDetectedHandler = EventHandler<MouseEvent> {
            event ->

        this.parent.onDragOver = null
        this.parent.onDragDropped = null

        this.parent.onDragOver = dragOverHandler
        this.parent.onDragDropped = dragDroppedHandler

        offset = Point2D(event.x, event.y)
        moveTo(Point2D(event.sceneX, event.sceneY))

        val content = ClipboardContent()
        content[state] = 1
        this.startDragAndDrop(*TransferMode.ANY).setContent(content)
        event.consume()
    }

    private fun moveTo(point: Point2D) {
        val local = parent.sceneToLocal(point)

        relocate(
            (local.x - offset.x),
            (local.y - offset.y)
        )
    }
    private var offset = Point2D(0.0, 0.0)
    private lateinit var view: Node



    init {
        loader.setControllerFactory {
            return@setControllerFactory controller
        }

        try {
            view = loader.load() as Node
        } catch (e: IOException) {
            println(e.toString())
        }
        children.add(view)

        onDragDetected = dragDetectedHandler
    }

}