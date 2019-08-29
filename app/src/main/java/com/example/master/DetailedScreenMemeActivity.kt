package com.example.master

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detailed_screen_meme.*
import android.text.method.ScrollingMovementMethod
import java.util.*


class DetailedScreenMemeActivity : AppCompatActivity() {

    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_screen_meme)

        val arguments = intent.extras
        textViewDescription.setMovementMethod(ScrollingMovementMethod())
        val currentDate = Date()
        val longTime = currentDate.time

        setSupportActionBar(toolbarDetailedProfile)

        if (arguments != null){
            val days = (longTime / 1000 - arguments.getInt("createdDate")) / 60 / 60 / 24
            toolbarDetailed.setTitle(arguments.getString("title"))
            val mImageAddress = arguments.getString("photoUtl")
            Glide
                .with(this)
                .load(mImageAddress)
                .into(imageViewDMeme)
            textViewDescription.setText(arguments.getString("description"))
            textViewDate.setText(days.toString() + " дней назад")
            if (arguments.getBoolean("isFavorite")) {
                isFavorite = true
                imageButtonDFavorite.setImageResource(R.drawable.baseline_favorite_24)
            }
        }

        imageButtonDFavorite.setOnClickListener {
            if (isFavorite) {
                imageButtonDFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
                isFavorite = false
            } else {
                imageButtonDFavorite.setImageResource(R.drawable.baseline_favorite_24)
                isFavorite = true
            }
        }
        imageButtonClose.setOnClickListener {
            val intent = Intent(this@DetailedScreenMemeActivity,
                MainScreenActivity::class.java)
            startActivity(intent)
        }
    }
}
