package com.alvaro.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class CoffeeshopActivity : AppCompatActivity() {

    private lateinit var etNama: TextInputEditText
    private lateinit var btnSimpan: Button

    private lateinit var etJmlAmericano: EditText
    private lateinit var rgAmericano: RadioGroup
    private lateinit var etJmlLatte: EditText
    private lateinit var rgLatte: RadioGroup

    private lateinit var etJmlToast: EditText
    private lateinit var spToast: Spinner
    private lateinit var etJmlDonut: EditText
    private lateinit var spDonut: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffeeshop)
        try {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        } catch (e: Exception) {

        }

        init()

        btnSimpan.setOnClickListener {
            val nama = etNama.text.toString().trim()

            if (nama.isEmpty()) {
                etNama.error = "Nama pemesan harus diisi!"
                return@setOnClickListener
            }

            val qtyAmericano = etJmlAmericano.text.toString().toIntOrNull() ?: 0
            val qtyLatte = etJmlLatte.text.toString().toIntOrNull() ?: 0
            val qtyToast = etJmlToast.text.toString().toIntOrNull() ?: 0
            val qtyDonut = etJmlDonut.text.toString().toIntOrNull() ?: 0


            if (qtyAmericano == 0 && qtyLatte == 0 && qtyToast == 0 && qtyDonut == 0) {
                Toast.makeText(this, "Minimal pesan 1 item!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val optAmericano = getRadioSelection(rgAmericano)
            val optLatte = getRadioSelection(rgLatte)
            val optToast = spToast.selectedItem?.toString() ?: "-"
            val optDonut = spDonut.selectedItem?.toString() ?: "-"

            val intent = Intent(this, NotaCoffeeshop::class.java)
            intent.putExtra("Nama", nama)
            intent.putExtra("QtyAmericano", qtyAmericano)
            intent.putExtra("OptAmericano", optAmericano)
            intent.putExtra("QtyLatte", qtyLatte)
            intent.putExtra("OptLatte", optLatte)
            intent.putExtra("QtyToast", qtyToast)
            intent.putExtra("OptToast", optToast)
            intent.putExtra("QtyDonut", qtyDonut)
            intent.putExtra("OptDonut", optDonut)

            startActivity(intent)
        }
    }

    private fun init() {

        etNama = findViewById(R.id.etNama)
        btnSimpan = findViewById(R.id.btSimpan)
        etJmlAmericano = findViewById(R.id.etJumlahAmric)
        rgAmericano = findViewById(R.id.rgPilihanAmric)
        etJmlLatte = findViewById(R.id.etJumlahLatte)
        rgLatte = findViewById(R.id.rgPilihanLatte)
        etJmlToast = findViewById(R.id.etJumlahToast)
        spToast = findViewById(R.id.spToast)
        etJmlDonut = findViewById(R.id.etJumlahDonut)
        spDonut = findViewById(R.id.spDonut)
    }

    private fun getRadioSelection(group: RadioGroup): String {
        val selectedId = group.checkedRadioButtonId
        if (selectedId == -1) return "Ice"


        val radioButton = group.findViewById<RadioButton>(selectedId)
        return radioButton?.text?.toString() ?: "Ice"
    }
}