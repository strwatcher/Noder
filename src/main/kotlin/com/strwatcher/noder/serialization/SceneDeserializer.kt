package com.strwatcher.noder.serialization

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.strwatcher.noder.base.*
import javafx.scene.input.DataFormat
import java.awt.image.BufferedImage
import java.lang.reflect.Type

class SceneDeserializer(val nodeState: DataFormat, val linkState: DataFormat): JsonDeserializer<Scene> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Scene? {
        val jsonObject = json?.asJsonObject
        if(context == null || json == null) return null

        return jsonObject?.let { jo ->
            val currentId = jo.get("currentId").asInt.toUInt()
            val scene = Scene(nodeState, linkState, currentId)
            val nodes = jo.get("nodes").asJsonArray
            for(node in nodes) {
                scene.add(context.deserialize(node, DraggableNode::class.java))
            }

            val jsonConnections = jo.get("connections")
            val connections =
                context.deserialize<HashMap<String, MutableList<LinkKey<String, String>>>>(
                jsonConnections,
                HashMap::class.java
            )

            println(connections.keys)

            for (nodeId in connections.keys) {
                val node = scene.findNodeById(nodeId.toUInt())
                node?.let {
                    val connection = connections[nodeId]!!
                    if (connection.size > 0) {
                        println(connection[0].nodeId)
                    }
//                    for(connectionKey in connections[nodeId]!!) {
//                        val connectedNode = scene.findNodeById(connectionKey.nodeId.toUInt())
//                        connectedNode.let {
//                            val connectedLink = connectedNode!!.link
//                            val currentInput = node.linkInputs[connectionKey.inputId.toInt()]
//                            when {
//                                connectedLink.valueProperty.value is Int && currentInput.valueProperty.value is Int ->
//                                    node.connectLink(connectedLink as NodeLink<Int>, currentInput as LinkInput<Int>)
//
//                                connectedLink.valueProperty.value is Float && currentInput.valueProperty.value is Float ->
//                                    node.connectLink(connectedLink as NodeLink<Float>, currentInput as LinkInput<Float>)
//
//                                connectedLink.valueProperty.value is String && currentInput.valueProperty.value is String ->
//                                    node.connectLink(connectedLink as NodeLink<String>, currentInput as LinkInput<String>)
//
//                                connectedLink.valueProperty.value is BufferedImage && currentInput.valueProperty.value is BufferedImage ->
//                                    node.connectLink(connectedLink as NodeLink<BufferedImage>, currentInput as LinkInput<BufferedImage>)
//                            }
//                        }
//                    }
                }
            }
            scene
        }

    }
}