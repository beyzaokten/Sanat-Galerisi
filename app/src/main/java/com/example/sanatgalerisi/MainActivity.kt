package com.example.sanatgalerisi



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)


        val btnOpenSidebar = findViewById<ImageView>(R.id.btn_open_sidebar)
        btnOpenSidebar.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START) // Navigation Drawer'ı aç
        }

        // Galeri seçenekleri CardView'ları
        val baroqueGalleryCard = findViewById<CardView>(R.id.baroqueGalleryCard)
        val gothicGalleryCard = findViewById<CardView>(R.id.gothicGalleryCard)
        val surrealismGalleryCard = findViewById<CardView>(R.id.surrealismGalleryCard)
        val renaissanceGalleryCard = findViewById<CardView>(R.id.renaissanceGalleryCard)
        val realismGalleryCard = findViewById<CardView>(R.id.realismGalleryCard)


        baroqueGalleryCard.setOnClickListener {
            startGalleryActivity(BaroqueGalleryActivity::class.java)
        }

        gothicGalleryCard.setOnClickListener {
            startGalleryActivity(GothicGalleryActivity::class.java)
        }

        surrealismGalleryCard.setOnClickListener {
            startGalleryActivity(SurrealismGalleryActivity::class.java)
        }

        renaissanceGalleryCard.setOnClickListener {
            startGalleryActivity(RenaissanceGalleryActivity::class.java)
        }

        realismGalleryCard.setOnClickListener {
            startGalleryActivity(RealismGalleryActivity::class.java)
        }

        val navArt = navView.menu.findItem(R.id.nav_art)
        navArt.setOnMenuItemClickListener {
            startArtCreationActivity()
            true
        }

        // NavigationView'da menü elemanları
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> showToast("Ana Sayfa seçildi")
                R.id.nav_likes -> showToast("Beğeniler seçildi")
                R.id.nav_map -> showToast("Harita seçildi")
                R.id.nav_art -> {
                    startArtCreationActivity() // Yeni eklendi
                    true
                }
            }
            true
        }
    }

    private fun startArtCreationActivity() {
        val intent = Intent(this, ArtCreationActivity::class.java)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun startGalleryActivity(activityClass: Class<*>) {
        Log.d("MainActivity", "startGalleryActivity called with class: ${activityClass.simpleName}")
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}
