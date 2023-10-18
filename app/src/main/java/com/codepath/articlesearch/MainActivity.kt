package com.codepath.articlesearch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException


fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"

private const val MOVIE_URL =
    "https://api.themoviedb.org/3/discover/movie?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&page=1&start_date=2023-01-01"

private const val CELEBRITY_URL =
    "https://api.themoviedb.org/3/trending/person/day?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"


class MainActivity : AppCompatActivity() {

    //Variables MOVIES
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var bindingMovie: ActivityMainBinding
    private val movies = mutableListOf<Movie>()
    private lateinit var binding: ActivityMainBinding
    //Variables Celebrities
    private lateinit var celebrityRecyclerView: RecyclerView
    private lateinit var bindingCelebrity: ActivityMainBinding
    private val celebrities = mutableListOf<Celebrity>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // Set up moviesRecyclerView
        moviesRecyclerView = binding.movies
        val layoutManagerMovies = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        moviesRecyclerView.layoutManager = layoutManagerMovies
        val movieAdapter = MovieAdapter(this, movies)
        moviesRecyclerView.adapter = movieAdapter

        // Set up celebrityRecyclerView
        celebrityRecyclerView = binding.celebrities
        val layoutManagerCelebrities = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        celebrityRecyclerView.layoutManager = layoutManagerCelebrities
        val celebrityAdapter = CelebrityAdapter(this, celebrities)
        celebrityRecyclerView.adapter = celebrityAdapter











        val clientMovie = AsyncHttpClient()
        clientMovie.get(MOVIE_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch articles: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched movies: $json")
                try {
                    // TODO: Create the parsedJSON

                    // TODO: Do something with the returned json (contains article information)
                    val parsedJson = createJson().decodeFromString(
                        SearchNewsResultsMovie.serializer(),
                        json.jsonObject.toString()
                    )
                    // TODO: Save the articles and reload the screen
                    parsedJson.results?.let { list ->
                        movies.addAll(list)
                        movieAdapter.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }

        })

        val clientCelebrity = AsyncHttpClient()
        clientCelebrity.get(CELEBRITY_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch articles: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched celebrities: $json")
                try {
                    // TODO: Create the parsedJSON

                    // TODO: Do something with the returned json (contains article information)
                    val parsedJson = createJson().decodeFromString(
                        SearchNewsResultsCelebrity.serializer(),
                        json.jsonObject.toString()
                    )
                    // TODO: Save the articles and reload the screen
                    parsedJson.results?.let { list ->
                        celebrities.addAll(list)
                        Log.e("cheese", celebrities.toString())
                        celebrityAdapter.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }

        })



    }
}