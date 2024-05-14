package com.example.sanatgalerisi
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class RealismGalleryActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realism_gallery)

        val toolbar = findViewById<Toolbar>(R.id.toolbarRealism)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Realizm Galerisi"
            setDisplayHomeAsUpEnabled(true)
        }
        val imageUrls = listOf(
            R.drawable.realism1,
            R.drawable.realism2,
            R.drawable.realism3,
            R.drawable.realism4,
            R.drawable.realism5,
            R.drawable.realism6,
            R.drawable.realism7,
            R.drawable.realism8,
            R.drawable.realism9
        )

        val imageContainer = findViewById<LinearLayout>(R.id.imageContainer2)
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