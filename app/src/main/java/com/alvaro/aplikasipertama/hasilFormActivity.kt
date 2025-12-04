package com.alvaro.aplikasipertama

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class hasilFormActivity : AppCompatActivity() {
    lateinit var tvNama: TextView
    lateinit var tvAlamat: TextView
    lateinit var tvNoHp: TextView
    lateinit var tvAgama: TextView
    lateinit var tvGender: TextView
    lateinit var tvHobi: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hasil_form)

        init()

            val nama = intent.getStringExtra("NAMA")
            val alamat = intent.getStringExtra("ALAMAT")
            val nohp = intent.getStringExtra("NOHP")
            val agama = intent.getStringExtra("AGAMA")
            val gender = intent.getStringExtra("GENDER")
            val hobi = intent.getStringExtra("HOBI")

            tvNama.text = "Nama : " + nama
            tvAlamat.text = "Alamat : " + alamat
            tvNoHp.text = "Nomor Hp : " + nohp
            tvAgama.text = "Agama : " + agama
            tvGender.text = "Jenis Kelamin : " + gender
            tvHobi.text = "Hobi : " + hobi


            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun init() {
        tvNama = findViewById(R.id.tvhasilNama)
        tvAlamat = findViewById(R.id.tvhasilAlamat)
        tvNoHp = findViewById(R.id.tvhasilNoHp)
        tvAgama = findViewById(R.id.tvAgama)
        tvGender = findViewById(R.id.tvGender)
        tvHobi = findViewById(R.id.tvHobi)
    }
}