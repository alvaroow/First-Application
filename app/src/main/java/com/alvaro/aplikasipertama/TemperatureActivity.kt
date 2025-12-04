package com.alvaro.aplikasipertama

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TemperatureActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_temperature)

        val spSuhuAwal : Spinner = findViewById(R.id.spSuhuawal)
        val SuhuAwal : EditText = findViewById(R.id.etSuhuAwal)
        val spSuhuAkhir : Spinner = findViewById(R.id.spSuhuAkhir)
        val SuhuAkhir : TextView = findViewById(R.id.tvHasilSuhuakhir)
        val btHitung : Button = findViewById(R.id.btHitung)
        var hasil = 0.0

        btHitung.setOnClickListener {
            var suhu1 = spSuhuAwal.selectedItem.toString()
            var suhu2 = spSuhuAkhir.selectedItem.toString()
            var isi = SuhuAwal.text.toString().toDoubleOrNull()

            if(isi != null){

                if (suhu1 == "Celcius"){
                    if (suhu2 == "Celcius"){
                        hasil = isi
                    }
                    if (suhu2 == "Fahrenheit"){
                        hasil = (isi * 1.8) + 32
                    }
                    if (suhu2 == "Kelvin"){
                        hasil = isi + 273.15
                    }
                    SuhuAkhir.text = hasil.toString()

                }

                if (suhu1 == "Fahrenheit"){
                    if (suhu2 == "Fahrenheit"){
                        hasil = isi
                    }
                    if (suhu2 == "Celcius"){
                        hasil = (isi - 32) / 1.8
                    }
                    if (suhu2 == "Kelvin"){
                        hasil = isi - 32 / 1.8 + 273.15
                    }
                    SuhuAkhir.text = hasil.toString()

                }

                if (suhu1 == "Kelvin"){
                    if (suhu2 == "Kelvin"){
                        hasil = isi
                    }
                    if (suhu2 == "Celcius"){
                        hasil = isi - 273.25
                    }
                    if (suhu2 == "Fahrenheit"){
                        hasil = (isi - 273.15) * 1.8 + 32
                    }
                    SuhuAkhir.text = hasil.toString()

                }

            } else{
                SuhuAwal.error = "Tidak Boleh Kosong"
                Handler(Looper.getMainLooper()).postDelayed({
                    SuhuAwal.error = null
                },2000)
            }



        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}