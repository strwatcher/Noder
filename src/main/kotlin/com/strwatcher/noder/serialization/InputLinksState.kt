package com.strwatcher.noder.serialization

import com.strwatcher.noder.base.LinkKey
import java.io.Serializable

data class InputLinksState (
    val id: Int,
    val connections: MutableList<LinkKey<Int, Int>>
    ): Serializable