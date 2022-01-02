package com.strwatcher.noder

import javafx.fxml.FXMLLoader
import javafx.scene.input.DataFormat

class DarkDraggableTitle(
    state: DataFormat,
    loader: FXMLLoader,
    controller: Controller
): DraggableNode(state, loader, controller)
