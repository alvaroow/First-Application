package com.alvaro.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class FormActivity : AppCompatActivity() {

    lateinit var tilNama : TextInputLayout
    lateinit var tilAlamat : TextInputLayout
    lateinit var tilNomor : TextInputLayout
    lateinit var spinnerAgama : Spinner
    lateinit var rgGender : RadioGroup
    lateinit var rbpria : RadioButton
    lateinit var rbwanita : RadioButton
    lateinit var cbMembaca : CheckBox
    lateinit var cbMakan : CheckBox
    lateinit var cbTidur : CheckBox
    lateinit var cbOlahraga : CheckBox
    lateinit var btSimpan : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form)
        init()

        btSimpan.setOnClickListener {

            val nama = tilNama.editText?.text.toString()
            val alamat = tilAlamat.editText?.text.toString()
            val nohp = tilNomor.editText?.text.toString()
            val agama = spinnerAgama.selectedItem.toString()
            val selectedRadioId = rgGender.checkedRadioButtonId
            val gender = if (selectedRadioId != -1){
                val RadioId = findViewById<RadioButton>(selectedRadioId)
                RadioId.text.toString()
            }else{
                " "
            }

            val hobilist = mutableListOf<String>()
            if (cbMembaca.isChecked) hobilist.add("Membaca")
            if (cbMakan.isChecked) hobilist.add("Makan")
            if (cbTidur.isChecked) hobilist.add("Tidur")
            if (cbOlahraga.isChecked) hobilist.add("Olahraga")
            val hobi = hobilist.joinToString(",")

            val keHasil = Intent(this, hasilFormActivity::class.java)

            keHasil.putExtra("NAMA",nama)
            keHasil.putExtra("ALAMAT",alamat)
            keHasil.putExtra("NOHP",nohp)
            keHasil.putExtra("AGAMA",agama)
            keHasil.putExtra("GENDER",gender)
            keHasil.putExtra("HOBI",hobi)
            startActivity(keHasil)

        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun init(){

        tilNama = findViewById(R.id.etNama)
        tilAlamat = findViewById(R.id.etAlamat)
        tilNomor = findViewById(R.id.etNomor)
        spinnerAgama = findViewById(R.id.spinneragama)
        rgGender = findViewById(R.id.rgGender)
        rbpria = findViewById(R.id.rbpria)
        rbwanita = findViewById(R.id.rbwanita)
        cbMembaca = findViewById(R.id.cbMembaca)
        cbMakan = findViewById(R.id.cbMakan)
        cbTidur = findViewById(R.id.cbTidur)
        cbOlahraga = findViewById(R.id.cbOlahraga)
        btSimpan = findViewById(R.id.btSimpan)
    }
}