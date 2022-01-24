package com.strwatcher.noder.serialization

import com.strwatcher.noder.base.DraggableNode
import javafx.geometry.Point2D

class DraggableNodeState(node: DraggableNode<*>) {
    val position: Point2D = node.localToScene(node.layoutX, node.layoutY)
    val id = node.id
    val type = node.type
    val value = node.value
}