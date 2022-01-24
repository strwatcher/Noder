package com.strwatcher.noder.base

import com.google.gson.GsonBuilder
import com.strwatcher.noder.serialization.DraggableNodeSerializer
import com.strwatcher.noder.serialization.DraggableNodeState
import com.strwatcher.noder.serialization.SceneSerializer

class Scene {
    val nodes: MutableList<DraggableNode<*>> = mutableListOf()
    private var currentId: UInt = 0u


    fun add(node: DraggableNode<*>) {
        nodes.add(node)
    }

    fun remove(node: DraggableNode<*>) {
        nodes.remove(node)
    }

    fun getId(): UInt {
        return currentId.also { currentId += 1u }
    }

    fun save() {
        val gson = GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Scene::class.java, SceneSerializer())
            .registerTypeAdapter(DraggableNodeState::class.java, DraggableNodeSerializer())
            .create()

        println(gson.toJson(this))
    }
}