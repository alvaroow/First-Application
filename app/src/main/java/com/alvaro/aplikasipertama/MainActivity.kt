package com.alvaro.aplikasipertama

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog // 1. Jangan lupa import ini
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    //langkah 1
    //Membuat variabel
    lateinit var cardForm : CardView
    lateinit var cardCalculator : CardView
    lateinit var cardCoffeeShop : CardView
    lateinit var cardTemperature : CardView
    lateinit var cardProfile : CardView
    lateinit var cardExit : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        //Langkah 4
        //Memanggil fun init()
        init()

        //Langkah 5
        //menambahkan kode onClick pada cardView

        cardForm.setOnClickListener {
            val intent = Intent(this@MainActivity, FormActivity::class.java)
            startActivity(intent)
        }

        cardCalculator.setOnClickListener {
            val intent = Intent(this@MainActivity, CalculatorActivity::class.java)
            startActivity(intent)
        }

        cardCoffeeShop.setOnClickListener {
            val intent = Intent(this@MainActivity, CoffeeshopActivity::class.java)
            startActivity(intent)
        }

        cardTemperature.setOnClickListener {
            val intent = Intent(this@MainActivity,TemperatureActivity::class.java )
            startActivity(intent)
        }

        cardProfile.setOnClickListener {
            val intent = Intent(this@MainActivity, ProfileActivity::class.java)
            startActivity(intent)
        }

        cardExit.setOnClickListener {
            tampilkanDialogKeluar()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    //Langkah 2
    // Membuat fun baru
    fun init() {
        //Langkah 3
        //mengisikan variable
        cardForm = findViewById(R.id.cardHome)
        cardCalculator = findViewById(R.id.cardHelp)
        cardCoffeeShop = findViewById(R.id.cardPegawai)
        cardTemperature = findViewById(R.id.cardGaleri)
        cardProfile = findViewById(R.id.cardSurvey)
        cardExit = findViewById(R.id.cardExit)
    }
    private fun tampilkanDialogKeluar() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Konfirmasi Keluar")
        builder.setMessage("Apakah Anda benar-benar ingin keluar?")
        builder.setCancelable(false)

        builder.setPositiveButton("Ya") { dialog, which ->
            finish()
        }

        builder.setNegativeButton("Tidak") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}