package com.strwatcher.noder.serialization

import com.google.gson.*
import com.strwatcher.noder.base.*
import com.strwatcher.noder.nodes.EndNode
import com.strwatcher.noder.nodes.StartNode
import com.strwatcher.noder.nodes.edit_nodes.FloatNode
import com.strwatcher.noder.nodes.edit_nodes.ImageNode
import com.strwatcher.noder.nodes.edit_nodes.IntNode
import com.strwatcher.noder.nodes.edit_nodes.StringNode
import com.strwatcher.noder.nodes.filter_nodes.*
import javafx.embed.swing.SwingFXUtils
import javafx.scene.input.DataFormat
import java.lang.reflect.Type
import javax.imageio.ImageIO

class DraggableNodeDeserializer(
    val nodeState: DataFormat,
    val linkState: DataFormat,
): JsonDeserializer<DraggableNode<*>>
{
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): DraggableNode<*>? {
        val jsonObject = json?.asJsonObject

        return jsonObject?.let { jo ->
            val id = jo.get("id").asInt.toUInt()
            val x = jo.get("x").asDouble
            val y = jo.get("y").asDouble

            when(jo.get("type").asString) {
                IntNodeType -> {
                    val value = jo.get("value").asInt
                    IntNode(nodeState, linkState, id).also { it.load(x, y, value) }
                }
                FloatNodeType -> {
                    val value = jo.get("value").asFloat
                    FloatNode(nodeState, linkState, id).also { it.load(x, y, value) }
                }
                StringNodeType -> {
                    val value = jo.get("value").asString
                    StringNode(nodeState, linkState, id).also {it.load(x, y, value)}
                }
                ImageNodeType ->  {
                    val bufImage = ImageIO.read(jo.get("value").asString.byteInputStream())
                    ImageNode(nodeState, linkState, id).also { it.load(x, y, bufImage) }
                }
                EndNodeType -> {
                   EndNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                StartNodeType -> {
                    StartNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                AddImageNodeType -> {
                    AddImageNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                AddTextNodeType -> {
                    AddTextNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                BlurNodeType -> {
                    BlurNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                BrightnessNodeType -> {
                    BrightnessNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                GrayFilterNodeType -> {
                    GrayFilterNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                InvertNodeType -> {
                    InvertNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                MoveNodeType -> {
                    MoveNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                RotationNodeType -> {
                    RotationNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                ScaleNodeType -> {
                    ScaleNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                SepiaNodeType -> {
                    SepiaNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                else -> null
            }

        }

    }

}