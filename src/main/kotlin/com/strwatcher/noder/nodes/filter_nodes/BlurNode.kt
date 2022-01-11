package com.strwatcher.noder.nodes.filter_nodes

import com.strwatcher.noder.base.FilterNode
import com.strwatcher.noder.base.LinkInput
import com.strwatcher.noder.imageToMat
import com.strwatcher.noder.matToImage
import javafx.fxml.FXML
import javafx.scene.image.Image
import javafx.scene.input.DataFormat
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc

class BlurNode(nodeState: DataFormat, linkState: DataFormat): FilterNode(nodeState, linkState) {
    private lateinit var kernelSizeInput: LinkInput<Int?>

    @FXML
    override fun initialize() {
        super.initialize()
        kernelSizeInput = LinkInput(null)

        inputs = mapOf(
            Pair(kernelSizeInput, "Kernel size")
        )

        addInputs(3)
        bindInputs()
    }

    override fun filterFunction(img: Image): Image {
        val src = imageToMat(img)
        val res = Mat()
        var kernelSize = kernelSizeInput.valueProperty.value!!
        if (kernelSize > 300) kernelSize = 300
        if (kernelSize % 2 == 0) kernelSize += 1

        Imgproc.GaussianBlur(src, res, Size(kernelSize.toDouble(), kernelSize.toDouble()), 0.0)
        return matToImage(res)
    }

    override fun setTitle() {
        nodeTitle.text = "Blur"
    }

}