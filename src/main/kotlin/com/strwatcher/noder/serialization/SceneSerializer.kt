package com.strwatcher.noder.serialization

import com.google.gson.*
import com.strwatcher.noder.base.Scene
import com.strwatcher.noder.base.LinkKey
import java.lang.reflect.Type

class SceneSerializer: JsonSerializer<Scene> {
    override fun serialize(src: Scene?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val result = JsonObject()

        if (src == null || context == null) return result

        val nodes = JsonArray()
        val connections: HashMap<Int, MutableList<LinkKey<Int, Int>>> = HashMap(mutableMapOf())
        for (node in src.nodes) {
            val nodeState = DraggableNodeState(node)
            val serializedNode = context.serialize(nodeState)
            nodes.add(serializedNode)

            val nodeConnections = node.connectedLinks
            connections[node.id.toInt()] = mutableListOf()
            for (connection in nodeConnections) {
                connections[node.id.toInt()]?.add(
                    LinkKey(connection.source.id.toInt(),
                        node.linkInputs.indexOf(connection.destination)
                    )
                )
            }
        }

        result.addProperty("currentId", src.getId().toInt())
        result.add("connections", context.serialize(connections))
        result.add("nodes", nodes)

        return result
    }
}