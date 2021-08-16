package com.example.myapplication.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R

class VolumeBarView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val barPaint = Paint()
    private val thumbPaint = Paint()

    private val defaultBarWidth = resources.getDimensionPixelSize(R.dimen.volume_bar_default_width)
    private val defaultBarHeight =
        resources.getDimensionPixelSize(R.dimen.volume_bar_default_height)

    private var volumeLevelsCount: Int? = null
    private var currentVolumeLevel: Int? = null

    init {
        barPaint.color = Color.GRAY
        thumbPaint.color = Color.RED
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width = when (widthMode) {
            //MeasureSpec封装了从父节点传递到子节点的布局需求。每个MeasureSpec表示对宽度或高度的要求。MeasureSpec由size和mode组成。有三种可能的模式:
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> defaultBarWidth
            MeasureSpec.UNSPECIFIED -> defaultBarWidth
            else -> defaultBarWidth
        }

        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> defaultBarHeight
            MeasureSpec.UNSPECIFIED -> defaultBarHeight
            else -> defaultBarHeight
        }

        setMeasuredDimension(width, height) //保存宽高
    }

    override fun onDraw(canvas: Canvas) {
        drawBar(canvas)
        drawThumb(canvas)
    }

    private fun drawBar(canvas: Canvas) {
        canvas.drawRect(0.0F, 0.0F, width.toFloat(), height.toFloat(), barPaint)
    }

    private fun drawThumb(canvas: Canvas) {
        val thumbX = calculateThumbX()
        val thumbY = height.toFloat() / 2.0F
        val radius = height.toFloat() / 2.0F

        canvas.drawCircle(thumbX, thumbY, radius, thumbPaint)
    }

    private fun calculateThumbX(): Float {
        val volumeLevelsCount = this.volumeLevelsCount
        val currentVolumeLevel = this.currentVolumeLevel

        return if (volumeLevelsCount != null && currentVolumeLevel != null) {
            ((width - height) / volumeLevelsCount * currentVolumeLevel).toFloat() + height / 2.0F
        } else {
            0.0F
        }
    }


}