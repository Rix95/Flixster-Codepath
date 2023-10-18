package com.codepath.articlesearch

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import org.w3c.dom.Text


class MovieDetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var releaseDateTextView : TextView
    private lateinit var overviewTextView: TextView
    private lateinit var popularityTextView : TextView
    private lateinit var  languageTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        // TODO: Find the views for the screen
        mediaImageView = findViewById(R.id.mediaImage)
        titleTextView = findViewById(R.id.mediaTitle)
        overviewTextView = findViewById(R.id.mediaOverView)
        releaseDateTextView = findViewById(R.id.mediaReleaseDate)
        languageTextView = findViewById(R.id.mediaLanguage)
        popularityTextView = findViewById(R.id.mediaPopularity)

        // TODO: Get the extra from the Intent
       val movie = intent.getSerializableExtra(MOVIE_EXTRA) as Movie
        // TODO: Set the title, byline, and abstract information from the article
        titleTextView.text = movie.title
        overviewTextView.text = movie.overview
        releaseDateTextView.text = ("Release Date: \n" + movie.releaseDate)
        languageTextView.text = ("Original Language: " + movie.originalLanguage)
        popularityTextView.text = ("Popularity: " + movie.popularity)
        // TODO: Load the media image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500"+ movie.imageUrl)
            .into(mediaImageView)

    }
}