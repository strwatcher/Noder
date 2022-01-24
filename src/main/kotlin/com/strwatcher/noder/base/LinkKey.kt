package com.strwatcher.noder.base

import java.io.Serializable

data class LinkKey<T, E>(
    val nodeId: T,
    val inputId: E
): Serializable