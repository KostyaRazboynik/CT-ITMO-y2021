package com.konstantinmuzhik.hw80

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class AndrewDownAnimation @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : View(context, attrs) {

    private val andrewDown = BitmapFactory.decodeResource(resources, R.drawable.down)
    private var f = true

    private var down: Float
    private val compressImageCoefficient: Float

    private val rectDown: RectF
    private val andrewWidth: Float
    private val andrewHeightDown: Float


    init {
        val a: TypedArray = context.obtainStyledAttributes(
            attrs, R.styleable.AndrewDownAnimation, defStyleAttr, defStyleRes
        )
        try {
            compressImageCoefficient =
                a.getFloat(R.styleable.AndrewDownAnimation_compressImageCoefficientDown, 2f / 3f)
            down = a.getFloat(R.styleable.AndrewDownAnimation_downStart, 5f)

            andrewWidth = andrewDown.width * compressImageCoefficient
            andrewHeightDown = andrewDown.height * compressImageCoefficient
            rectDown = RectF(0f, 0f, 0f, 0f)
        } finally {
            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        rectDown.left = (width - andrewWidth) / 2
        rectDown.top = down
        rectDown.right = (width - andrewWidth) / 2 + andrewWidth
        rectDown.bottom = andrewHeightDown + down

        canvas.save()
        canvas.drawBitmap(andrewDown, null, rectDown, null)
        canvas.restore()

        nextFrame()
    }

    fun nextFrame() {
        if (f) {
            down++
        } else {
            down--
            if (down == 0f)
                f = true
        }

        if (down == 50f) {
            f = false
        }

        postDelayed(this::invalidate, 16)
    }
}