package com.codepath.articlesearch

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class CelebrityDetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var knownForTextView: TextView
    private lateinit var popularityTextView : TextView
    private lateinit var genderTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_celebrity)

        // TODO: Find the views for the screen
        mediaImageView = findViewById(R.id.mediaActorImage)
        nameTextView = findViewById(R.id.mediaName)
        knownForTextView = findViewById(R.id.mediaKnownFor)
        popularityTextView = findViewById(R.id.mediaActorPopularity)
        genderTextView = findViewById(R.id.mediaGender)

        // TODO: Get the extra from the Intent
       val celebrity = intent.getSerializableExtra(CELEBRITY_EXTRA) as Celebrity

        // TODO: Set the title, byline, and abstract information from the article
        nameTextView.text = celebrity.name
        knownForTextView.text = ("Known for: " + celebrity.knownFor)
        popularityTextView.text = ("Popularity: " + celebrity.popularity)

        val genderResult: String = if (celebrity.gender == "1") {
            "Female"
        } else if (celebrity.gender == "2") {
            "Male"
        } else {
            "Unknown Gender"
        }
        Log.e("GENDER", genderResult)
        genderTextView.text = "Gender: " + genderResult


        // TODO: Load the media image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500"+ celebrity.imageUrl)
            .into(mediaImageView)

    }
}