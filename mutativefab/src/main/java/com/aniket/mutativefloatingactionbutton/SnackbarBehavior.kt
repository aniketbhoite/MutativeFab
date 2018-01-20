package com.aniket.mutativefloatingactionbutton

import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.view.View

/**
 * Created by aniket on 14-01-2018.
 */


class SnackbarBehavior : CoordinatorLayout.Behavior<View>() {

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
        return (SNACKBAR_BEHAVIOR_ENABLED && dependency is Snackbar.SnackbarLayout)
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        val translationY = Math.min(0, (dependency.translationY - dependency.height).toInt())
        child.translationY = translationY.toFloat()
        return true
    }

    override fun onDependentViewRemoved(parent: CoordinatorLayout?, child: View?, dependency: View?) {
        val translationY = Math.min(0, (dependency!!.translationY + dependency.height).toInt())
        child!!.translationY = translationY.toFloat()
    }

    companion object {
        private var SNACKBAR_BEHAVIOR_ENABLED: Boolean = true

    }
}
