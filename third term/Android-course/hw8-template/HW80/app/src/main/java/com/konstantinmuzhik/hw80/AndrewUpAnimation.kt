package com.konstantinmuzhik.hw80

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class AndrewUpAnimation @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : View(context, attrs) {

    private val andrewUp = BitmapFactory.decodeResource(resources, R.drawable.up)
    private var f = true

    private var up: Float
    private val compressImageCoefficient: Float

    private var rectUp: RectF
    private val andrewWidth: Float
    private val andrewHeightUp: Float


    init {
        val a: TypedArray = context.obtainStyledAttributes(
            attrs, R.styleable.AndrewUpAnimation, defStyleAttr, defStyleRes
        )
        try {
            up = a.getFloat(R.styleable.AndrewUpAnimation_upStart, 5f)
            compressImageCoefficient =
                a.getFloat(R.styleable.AndrewUpAnimation_compressImageCoefficientUp, 2f / 3f)

            andrewWidth = andrewUp.width * compressImageCoefficient
            andrewHeightUp = andrewUp.height * compressImageCoefficient

            rectUp = RectF(0f, 0f, 0f, 0f)
        } finally {
            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        rectUp.left = (width - andrewWidth) / 2
        rectUp.top = height - andrewHeightUp - up
        rectUp.right = (width - andrewWidth) / 2 + andrewWidth
        rectUp.bottom = height - up

        canvas.save()
        canvas.drawBitmap(andrewUp, null, rectUp, null)
        canvas.restore()

        nextFrame()
    }

    fun nextFrame() {
        if (f)
            up++
        else {
            up--
            if (up == 0f)
                f = true
        }

        if (up == 50f) {
            f = false
        }

        postDelayed(this::invalidate, 16)
    }
}