package com.example.sanatgalerisi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class GothicGalleryActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gothic_gallery)

        val toolbar = findViewById<Toolbar>(R.id.toolbarGothic)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Gotik Galerisi"
            setDisplayHomeAsUpEnabled(true)
        }

        val imageUrls = listOf(
            R.drawable.gothic1,
            R.drawable.gothic2,
            R.drawable.gothic3,
            R.drawable.gothic4,
            R.drawable.gothic5,
            R.drawable.gothic6,
            R.drawable.gothic7,
            R.drawable.gothic8,
            R.drawable.gothic9
        )
        val imageContainer = findViewById<LinearLayout>(R.id.imageContainer1)
        val rowSize = 3
        val columnSize = 3
        for (i in 0 until rowSize) {
            val rowLayout = LinearLayout(this)
            rowLayout.orientation = LinearLayout.HORIZONTAL
            rowLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            for (j in 0 until columnSize) {
                val index = i * columnSize + j
                if (index < imageUrls.size) {
                    val imageView = ImageView(this)
                    imageView.setImageResource(imageUrls[index])
                    imageView.layoutParams = LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1f
                    )
                    imageView.adjustViewBounds = true
                    imageView.setPadding(0, 0, 0, 16)
                    rowLayout.addView(imageView)
                }
            }

            imageContainer.addView(rowLayout)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}