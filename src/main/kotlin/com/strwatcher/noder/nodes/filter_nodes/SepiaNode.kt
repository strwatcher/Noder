package com.strwatcher.noder.nodes.filter_nodes

import com.strwatcher.noder.base.FilterNode
import com.strwatcher.noder.imageToMat
import com.strwatcher.noder.matToImage
import javafx.fxml.FXML
import javafx.scene.image.Image
import javafx.scene.input.DataFormat
import org.opencv.core.Core
import org.opencv.core.CvType
import org.opencv.core.Mat

class SepiaNode(nodeState: DataFormat, linkState: DataFormat): FilterNode(nodeState, linkState) {
    lateinit var mSepiaKernel: Mat

    @FXML
    override fun initialize() {
        super.initialize()
        nodeTitle.text = "Sepia"
        mSepiaKernel =  Mat(4, 4, CvType.CV_32F)
        mSepiaKernel.put(0, 0, /* R */0.272, 0.534, 0.131, 0.0)
        mSepiaKernel.put(1, 0, /* G */0.349, 0.686, 0.168, 0.0)
        mSepiaKernel.put(2, 0, /* B */0.393, 0.769, 0.189, 0.0)
        mSepiaKernel.put(3, 0, /* A */0.000, 0.000, 0.000, 1.0)
    }

    override fun filterImage(img: Image?): Image? {
        img?.let {
            val tmpMat: Mat = imageToMat(img)
            val resultMat = Mat()
            Core.transform(tmpMat, resultMat, mSepiaKernel)
            return matToImage(resultMat)
        }
        return null
    }
}