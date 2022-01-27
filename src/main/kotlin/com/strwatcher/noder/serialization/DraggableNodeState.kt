package com.strwatcher.noder.serialization

import com.strwatcher.noder.base.DraggableNode
import javafx.geometry.Bounds

class DraggableNodeState(node: DraggableNode<*>) {
    val position: Bounds = node.boundsInParent
    val id = node.id
    val type = node.type
    val value = node.value
}