package com.example.semicircleview

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.content.ContextCompat


/**
 * Created by Trần Đạt on 24/04/2022
 * ezCloud
 */
class SemiCircleView constructor(
    context: Context, attrs: AttributeSet
) : View(context, attrs) {
    private var backgroundPaint: Paint = Paint()
    private var mainPaint: Paint = Paint()
    private var progress: Float = 0f
    private var margin: Float = resources.getDimension(R.dimen.circle_margin) // Lề cho progress, 6dp

    fun setupUI(colorRes: Int) {
        setupPaint(backgroundPaint, R.color.circle_background)
        setupPaint(mainPaint, colorRes)
        invalidate()
    }

    private fun setupPaint(paint: Paint, colorRes: Int) {
        paint.isAntiAlias = true // Giúp các viền của view mịn màng hơn
        paint.color = ContextCompat.getColor(context, colorRes) // Màu của đường tròn
        paint.style = Paint.Style.STROKE // Chọn dạng stroke
        paint.strokeCap = Paint.Cap.ROUND // Hình dạng 2 đầu paint (ROUND là bo tròn)
        paint.strokeWidth =
            resources.getDimension(R.dimen.circle_width) // 12dp, Độ rộng của đường tròn
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val frame = RectF(margin, margin,
            width.toFloat() - margin,
            height.toFloat() - margin) // Set khoảng cách viền margin cho đường tròn không bị tràn lề
        canvas?.drawArc(
            frame, // Khung bao quanh đường tròn
            135f, // Góc bắt đầu vẽ, tính theo độ, full là 360
            255f, // Độ dài cung vẽ
            false, // Có sử dụng tâm để vẽ không
            backgroundPaint
        ) // Chọn paint vẽ
        canvas?.drawArc(frame, 135f, progress * 255f, false, mainPaint)
    }

    fun setProgress(v: Float) {
        val currentProgress = this.progress
        val animator = ValueAnimator().apply { // Thực hiện hiệu ứng chuyển động khi thay đổi progress
            setValues(PropertyValuesHolder.ofFloat(
                "percent",
                currentProgress,v
            ))
            duration = 300
            interpolator = AccelerateDecelerateInterpolator()

            addUpdateListener {
               val newValue = it.getAnimatedValue("percent") as Float
                this@SemiCircleView.progress = newValue
                invalidate()
            }
        }
        animator.start()
    }
}