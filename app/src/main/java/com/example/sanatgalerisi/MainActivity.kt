package com.example.sanatgalerisi

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    private val notificationPermissionRequestCode = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sendNotificationIfPermissionGranted()

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val btnOpenSidebar = findViewById<ImageView>(R.id.btn_open_sidebar)
        btnOpenSidebar.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

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

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> showToast("Ana Sayfa seçildi")
                R.id.nav_likes -> showToast("Beğeniler seçildi")
                R.id.nav_map -> showToast("Harita seçildi")
                R.id.nav_art -> {
                    startArtCreationActivity()
                    true
                }
            }
            true
        }
    }

    private fun sendNotificationIfPermissionGranted() {
        val channelId = "default_channel_id"
        val channelName = "Default Channel"
        val notificationId = 1001
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val hasNotificationPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_NOTIFICATION_POLICY) == PackageManager.PERMISSION_GRANTED

            if (!hasNotificationPermission) {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_NOTIFICATION_POLICY), notificationPermissionRequestCode)
            } else {
                showNotification(notificationManager, channelId, notificationId)
            }
        } else {
            showNotification(notificationManager, channelId, notificationId)
        }
    }

    private fun showNotification(notificationManager: NotificationManager, channelId: String, notificationId: Int) {
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification_icon)
            .setContentTitle("Yeni Sanat Eseri Keşfedin!")
            .setContentText("Bugün yeni bir sanat eseri keşfetmeyi unutmayın!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == notificationPermissionRequestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                showNotification(notificationManager, "default_channel_id", 1001)
            } else {
                showToast("Bildirim göndermek için izin vermelisiniz.")
            }
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
