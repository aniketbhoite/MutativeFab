package com.example.aniket.floatingtextbutton

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.aniket.mutativefloatingactionbutton.MutativeFab
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val coordinator = findViewById<CoordinatorLayout>(R.id.coordinator)

        val showSnackbar = findViewById<Button>(R.id.showSnackbar)

        showSnackbar.setOnClickListener { Snackbar.make(coordinator, "SnackBar", Snackbar.LENGTH_SHORT).show() }

        val mFab = findViewById<MutativeFab>(R.id.m_fab)
        mFab.apply {
            setFabText("start chat")
            setFabTextColor(ContextCompat.getColor(context, R.color.textColor))
            setFabIcon(R.drawable.ic_chat)
//            setFabBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark))
        }

        button.setOnClickListener {


            mFab.fabTextVisibility = if (mFab.fabTextVisibility == View.VISIBLE) View.GONE else View.VISIBLE

        }

        mFab.setOnClickListener {
            Toast.makeText(this,"Toast",Toast.LENGTH_SHORT).show()
        }

    }


}
