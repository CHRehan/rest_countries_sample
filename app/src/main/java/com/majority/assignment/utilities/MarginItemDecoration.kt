package com.majority.assignment.utilities

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import java.util.*

class MarginItemDecoration : ItemDecoration {
    @DimenRes
    private val marginLeft: Int

    @DimenRes
    private val marginTop: Int

    @DimenRes
    private val marginRight: Int

    @DimenRes
    private val marginBottom: Int

    @DimenRes
    private val marginBetween: Int
    private val orientationMode: OrientationMode

    constructor(
        @DimenRes marginBorder: Int,
        @DimenRes marginBetween: Int
    ) : this(
        marginBorder, marginBorder, marginBorder, marginBorder, marginBetween,
        OrientationMode.VERTICAL
    ) {
    }

    constructor(
        @DimenRes marginBorder: Int,
        @DimenRes marginBetween: Int,
        orientationMode: OrientationMode?
    ) : this(
        marginBorder, marginBorder, marginBorder, marginBorder, marginBetween,
        orientationMode
    ) {
    }

    constructor(
        @DimenRes marginHorizontal: Int,
        @DimenRes marginVertical: Int,
        @DimenRes marginBetween: Int
    ) : this(
        marginHorizontal, marginVertical, marginHorizontal, marginVertical, marginBetween,
        OrientationMode.VERTICAL
    ) {
    }

    constructor(
        @DimenRes marginHorizontal: Int,
        @DimenRes marginVertical: Int,
        @DimenRes marginBetween: Int,
        orientationMode: OrientationMode?
    ) : this(
        marginHorizontal, marginVertical, marginHorizontal, marginVertical, marginBetween,
        orientationMode
    ) {
    }

    @JvmOverloads
    constructor(
        @DimenRes marginLeft: Int,
        @DimenRes marginTop: Int,
        @DimenRes marginRight: Int,
        @DimenRes marginBottom: Int,
        @DimenRes marginBetween: Int,
        orientationMode: OrientationMode? =
            OrientationMode.VERTICAL
    ) {
        this.marginLeft = marginLeft
        this.marginTop = marginTop
        this.marginRight = marginRight
        this.marginBottom = marginBottom
        this.marginBetween = marginBetween
        this.orientationMode = orientationMode ?: OrientationMode.VERTICAL
    }

    private constructor(builder: Builder) {
        marginLeft = builder.marginLeft
        marginTop = builder.marginTop
        marginRight = builder.marginRight
        marginBottom = builder.marginBottom
        marginBetween = builder.marginBetween
        orientationMode = builder.orientationMode
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val resources = view.resources
        val pxLeft = getPixelSize(resources, marginLeft)
        val pxTop = getPixelSize(resources, marginTop)
        val pxRight = getPixelSize(resources, marginRight)
        val pxBottom = getPixelSize(resources, marginBottom)
        val pxBetween = getPixelSize(resources, marginBetween) / 2
        val count = Objects.requireNonNull(parent.adapter)?.itemCount?.minus(1)
        val position = parent.getChildAdapterPosition(view)
        val firstPosition = position == 0
        val lastPosition = position == count
        when (orientationMode) {
            OrientationMode.HORIZONTAL -> {
                if (firstPosition) {
                    outRect.left = pxLeft
                    outRect.right = pxBetween
                } else if (lastPosition) {
                    outRect.left = pxBetween
                    outRect.right = pxRight
                } else {
                    outRect.left = pxBetween
                    outRect.right = pxBetween
                }
                outRect.top = pxTop
                outRect.bottom = pxBottom
            }
            OrientationMode.VERTICAL -> {
                if (firstPosition) {
                    outRect.top = pxTop
                    outRect.bottom = pxBetween
                } else if (lastPosition) {
                    outRect.top = pxBetween
                    outRect.bottom = pxBottom
                } else {
                    outRect.top = pxBetween
                    outRect.bottom = pxBetween
                }
                outRect.left = pxLeft
                outRect.right = pxRight
            }
            else -> throw IllegalStateException(
                "There is no such orientation mode. Please, choose from " +
                        "OrientationMode.HORIZONTAL or OrientationMode.VERTICAL values. " +
                        "Or choose nothing to default OrientationMode.VERTICAL value."
            )
        }
    }

    private fun getPixelSize(resources: Resources, @DimenRes margin: Int): Int {
        return if (margin == 0) 0 else resources.getDimensionPixelSize(margin)
    }

    enum class OrientationMode {
        HORIZONTAL, VERTICAL
    }

    class Builder {
        var marginLeft = 0
        var marginTop = 0
        var marginRight = 0
        var marginBottom = 0
        var marginBetween = 0
        var orientationMode = OrientationMode.VERTICAL
        fun marginLeft(`val`: Int): Builder {
            marginLeft = `val`
            return this
        }

        fun marginTop(`val`: Int): Builder {
            marginTop = `val`
            return this
        }

        fun marginRight(`val`: Int): Builder {
            marginRight = `val`
            return this
        }

        fun marginBottom(`val`: Int): Builder {
            marginBottom = `val`
            return this
        }

        fun marginBetween(`val`: Int): Builder {
            marginBetween = `val`
            return this
        }

        fun orientationMode(`val`: OrientationMode): Builder {
            orientationMode = `val`
            return this
        }

        fun build(): MarginItemDecoration {
            return MarginItemDecoration(this)
        }
    }

    companion object {
        fun newBuilder(): Builder {
            return Builder()
        }

        fun newBuilder(copy: MarginItemDecoration): Builder {
            val builder = Builder()
            builder.marginLeft = copy.marginLeft
            builder.marginTop = copy.marginTop
            builder.marginRight = copy.marginRight
            builder.marginBottom = copy.marginBottom
            builder.marginBetween = copy.marginBetween
            builder.orientationMode = copy.orientationMode
            return builder
        }
    }
}