package com.example.sanatgalerisi
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class BaroqueGalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baroque_gallery)

        val toolbar = findViewById<Toolbar>(R.id.toolbarBaroque)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Barok Galerisi"
            setDisplayHomeAsUpEnabled(true)
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
