package com.strwatcher.noder.nodes.filter_nodes

import com.strwatcher.noder.base.FilterNode
import com.strwatcher.noder.base.GrayFilterNodeType
import com.strwatcher.noder.imageToMat
import com.strwatcher.noder.matToImage
import javafx.fxml.FXML
import javafx.scene.image.Image
import javafx.scene.input.DataFormat
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class GrayFilterNode(nodeState: DataFormat, linkState: DataFormat, id: UInt): FilterNode(nodeState, linkState, id) {

    @FXML
    override fun initialize() {
        super.initialize()
        inputs = mapOf()
        initInputs()
    }

    override fun filterFunction(img: Image): Image {
        val tmpMat = imageToMat(img)
        val resultMat = Mat()
        Imgproc.cvtColor(tmpMat, resultMat, Imgproc.COLOR_RGB2GRAY)
        return matToImage(resultMat)
    }

    override fun setTitle() {
        nodeTitle.text = "Gray Filter"
    }

    override fun initType(): String = GrayFilterNodeType
    override fun initInputs() {
        linkInputs.addAll(
            listOf(
                imageInput,
            )
        )
    }
}