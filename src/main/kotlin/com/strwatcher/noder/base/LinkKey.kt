package com.strwatcher.noder.base

import java.io.Serializable

data class LinkKey<T: Serializable, E: Serializable>(
    val nodeId: T,
    val inputId: E
): Serializable