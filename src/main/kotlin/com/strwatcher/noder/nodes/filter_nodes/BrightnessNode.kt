package com.strwatcher.noder.nodes.filter_nodes

import com.strwatcher.noder.base.BrightnessNodeType
import com.strwatcher.noder.base.FilterNode
import com.strwatcher.noder.base.LinkInput
import com.strwatcher.noder.imageToMat
import com.strwatcher.noder.matToImage
import javafx.fxml.FXML
import javafx.scene.image.Image
import javafx.scene.input.DataFormat
import org.opencv.core.Mat

class BrightnessNode(nodeState: DataFormat, linkState: DataFormat, id: UInt) : FilterNode(nodeState, linkState, id) {
    private lateinit var levelInput: LinkInput<Int?>

    @FXML
    override fun initialize() {
        super.initialize()
        levelInput = LinkInput(null, this)

        inputs = mapOf(
            Pair(levelInput, "Level")
        )
        initInputs()
        addInputs(3)
        bindInputs()
    }

    override fun filterFunction(img: Image): Image {
        val resultMat = imageToMat(img)
        imageToMat(img).convertTo(resultMat, -1, 1.0, levelInput.valueProperty.value!!.toDouble())
        return matToImage(resultMat)
    }

    override fun setTitle() {
       nodeTitle.text = "Brightness"
    }

    override fun initType(): String = BrightnessNodeType
    override fun initInputs() {
       linkInputs.addAll(
           listOf(
               imageInput,
               levelInput,
           )
       )
    }
}