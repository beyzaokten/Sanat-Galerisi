package com.example.sanatgalerisi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class ArtCreationActivity : AppCompatActivity() {

    private lateinit var generatedImageView: ImageView
    private val apiKey = ""
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.harvardartmuseums.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    interface HarvardArtService {
        @GET("image")
        fun getRandomImage(
            @Query("apikey") apiKey: String,
            @Query("size") size: Int = 1
        ): Call<ImageResponse>
    }

    data class ImageResponse(
        val records: List<Image>
    )

    data class Image(
        val baseimageurl: String
    )

    private fun generateRandomImage() {
        val service = retrofit.create(HarvardArtService::class.java)
        service.getRandomImage(apiKey, size = 20).enqueue(object : Callback<ImageResponse> {
            override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>) {
                if (response.isSuccessful) {
                    val imageResponse = response.body()
                    if (imageResponse != null && imageResponse.records.isNotEmpty()) {
                        val randomImage = imageResponse.records.random()
                        val imageUrl = randomImage.baseimageurl
                        Picasso.get().load(imageUrl).into(generatedImageView)
                        generatedImageView.visibility = View.VISIBLE
                    } else {
                        Toast.makeText(applicationContext, "No images found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                Log.e("ArtCreationActivity", "Error fetching data", t)
                Toast.makeText(applicationContext, "Failed to fetch data", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_art_creation)

        generatedImageView = findViewById(R.id.generatedImageView)


        val toolbar = findViewById<Toolbar>(R.id.toolbarCreation)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Eser Keşfet"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    fun generateRandomImage(view: View) {
        generateRandomImage()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Geri düğmesine basıldığında ana aktiviteye geri dön
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // Aktivite yığınındaki tüm aktiviteleri temizler
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
